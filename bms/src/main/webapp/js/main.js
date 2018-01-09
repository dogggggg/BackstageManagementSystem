/**
 * Created by user on 2015/6/10.
 */

kdm.nameSpace.reg("sys");

(function(){
    sys.main = function(){

        var ctxData = kdm.utils.getServerPath("system");

        // 申明内部对象
        var obj = this;
        /**
         * 列表对象
         *
         * @type {{}}
         */
        this.onLineTable = {};
        
        // 初始化页面
        this.init = function() {
        	 obj.loadOnLineFun();
        };
        // 登录
        this.loadOnLineFun = function() {
        	var record_table = $("#onLine-table").DataTable({
                "oLanguage" : { // 汉化
                    sUrl : kdm.utils.getServerPath("dataTableLocal")
                },
                "bAutoWidth" : false,
                "bFilter" : false,// 搜索栏
                "bLengthChange" : false,// 每行显示记录数
                "iDisplayLength" : 5,// 每页显示行数
                "bSort" : false,
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
                "sPaginationType" : "full", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bServerSide" : false,
                "sAjaxSource": ctxData + '/sessions/list',
                "fnServerData": function (sUrl, aoData, fnCallback) {
                    $.ajax({
                        "url": sUrl,
                        "data": aoData,
                        "success": function(data){
                            fnCallback(data);
                            //渲染结束重新设置高度
                            parent.kdm.common.setIframeHeight($.getUrlParam(kdm.iframeId));
                        },
                        "dataType": "jsonp",
                        "cache": false
                    });
                },
                "aoColumnDefs": [
                    {
                        sDefaultContent: '',
                        aTargets: [ '_all' ]
                    }
                ],
                "aoColumns": [
                {"data": "loginId",sClass : "text-left",sSort : false},
                {"data": "updateTime",sClass : "text-left",
                	render : function(value){
                    	return kdm.moment.formatYMDHms(value);
                	}
                },
                {"data": "duration", sClass : "text-left",
                	render : function(value,a,b){
                    	var start=b.updateTime;
                    	var end=new Date();
                    	var seconds=Math.abs(end-start)/1000;
                    	var hours=parseInt(seconds/60/60);
                    	var miniters=parseInt(seconds/60-hours*60);
                    	seconds=parseInt(seconds-hours*60*60-miniters*60);
                    	if(hours>0){
                    		return hours+"小时"+miniters+"分钟"+seconds+"秒";
                    	}else if(miniters>0){
                    		return miniters+"分钟"+seconds+"秒";
                    	}else{
                    		return seconds+"秒";
                    	}
                	}
                }
               ]
            });
            obj.onLineTable = record_table;
        };
        /**
        *
        * 部署弹出框回调函数
        *
        */
       this.editCallBackFun = function(params){
           //加载数据
           obj.onLineTable.ajax.reload();
       }
    };

    // 初始化数据
    $(document).ready(function() {
    	main.init();
    	window.setInterval("main.editCallBackFun()",30000)//30秒刷新一次
    });
})();

var main = new sys.main();