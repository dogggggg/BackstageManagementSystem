(function ($) {
    var _progress_index=0;
    $.ajaxSetup({
        beforeSend : function(){
            _progress_index = kdm.progress.show();
        },
        complete : function(xhr) {
            kdm.progress.hide(_progress_index);
            if (xhr.status == 302) {
                window.location.reload(true);
            }
            if( xhr.responseJSON != undefined && xhr.responseJSON.errCode == "ERR_MSG_0001"){
                kdm.win.alert(xhr.responseJSON.msg);
            }

        },
        error : function(data){
        	kdm.progress.hide(_progress_index);
            if(data.status != 404)
                window.location.reload(true);
        }
    });

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }

})(jQuery);

$.fn.extend({       
     dict_select:function(key,defaultValue) {       
     	var This=this;
     	var type= This[0].type;
     	if(type){
     		if(type=='select-one'){
     			This.empty();
     			var dict_key= kdm.dict.getValues(key);
     			var options="<option value='' >请选择</option>";
     			This.append(options);
     			for(var p in dict_key){
     				var select="";
     				if(p==defaultValue){
     					select="selected";
     				}
     				var options="<option value='" + p+ "' "+select+">" +dict_key[p]+ "</option>";
     				This.append(options);
     			}
     		}
     	}
      },
      dict_select2:function(key,defaultValue) {       
       	var This=this;
       	var type= This[0].type;
       	if(type){
       		if(type=='select-one'){
       			var dict_key= kdm.dict.getValues(key);
       			This.append(options);
       			for(var p in dict_key){
       				var select="";
       				if(p==defaultValue){
       					select="selected";
       				}
       				var options="<option value='" + p+ "' "+select+">" +dict_key[p]+ "</option>";
       				This.append(options);
       			}
       		}
       	}
        },
      /**
       * domId :绑定的DOMID
       * checkbox :是否复选
       * display_type：弹窗，还是弹窗
       * value_display 显示值
       * value_id      显示ID
       */
      org_tree:function(domId,checkbox,display_type,value_display,value_id) {    
       	var This=this;
       	var type= This[0].type;
       	if(type){
       		if(type=='select-one'){
       			This.empty();
       			var dict_key= kdm.dict.getValues(key);
       			var options="<option value='' >请选择</option>";
       			This.prepend(options);
       			for(var p in dict_key){
       				var select="";
       				if(p==defaultValue){
       					select="selected";
       				}
       				var options="<option value='" + p+ "' "+select+">" +dict_key[p]+ "</option>";
       				This.prepend(options);
       			}
       		}
       		}
        },
        user_tree:function(){
        	
        }
}); 
var kdm = kdm || {};

kdm.iframeId = "iframeId";

kdm.common = {
    // 金额格式化，三位一瞥
    formatMoney : function(amt, pre) {
        if(amt == undefined)
            return "0.00";
        var isfu = false;
        if (parseFloat(amt) < 0) {
            amt = parseFloat(amt) * -1;
            isfu = true;
        }
        pre = pre > 0 && pre <= 20 ? pre : 2;
        amt = parseFloat((amt + "").replace(/[^\d\.-]/g, "")).toFixed(pre) + "";
        var left = amt.split(".")[0].split("").reverse();
        var right = amt.split(".")[1];

        var t = "";
        var fu = "";
        for (var i = 0; i < left.length; i++) {
            t += left[i] + ((i + 1) % 3 == 0 && (i + 1) != left.length ? "," : "");
        }

        fu = isfu ? "-" : "";

        return fu + t.split("").reverse().join("") + "." + right;

    },

    // 金额去格式化
    unFormatMoney : function(money) {
        money = new String(money);
        while (money.indexOf(',', 0) != -1) {
            money = money.replace(',', '');
        }
        return money;
    },

    //只输入数字
    NumberText :function() {
        if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39)&&!(event.keyCode==190))
            if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
                event.returnValue=false;
    },

    //设置iframe 高度  date：2015-12-20
    setIframeHeight : function(iframeId){
        var iframe = $("#" + iframeId);//
        if(iframe.length==0){
        	return;
        }
        var winHeight = $(window).height() -1* ($("#navbar").height() + $("#breadcrumbs").height())-35;//date 2017-2-5 portal_tabs-->breadcrumbs
        var iframeHeight = $(iframe.contents()).find("body").height();
        iframe.height(Math.max(winHeight,iframeHeight));
    },

    //设置所有Iframe的宽度 date：2015-12-20
    setAllIframeWidth : function(){
        var iframes = document.getElementsByTagName("iframe");
        for(var i=0;i < iframes.length;i++){
            var iframe = $("#" + iframes[i].id);
            var width = $(window).width() - $("#sidebar").width();
            $(iframe).width(width - 5);
            $(iframe.body).width(width);
            //设置高度
            var winHeight = $(window).height() - 2*($("#navbar").height() + $("#breadcrumbs").height());
            var iframeHeight = $(iframe.contents()).find("body").height();
            iframe.height(Math.max(winHeight,iframeHeight));
        }
    },
    getFrameHeight:function(){
    	//page-content
    	var path = document.location.pathname;
		if(path.match(/(\/page\/)(index)/)){
			return index.getFrameHeight();
		}else{
			return parent.index.getFrameHeight();
			//console.log(parent.index.globalData);
			//return parent.index.globalData.dict[key];
		}
    },
    //本地时钟
     localClock : function(){
        var now = new Date();
        var year = now.getFullYear(); // getFullYear getYear
        var month = now.getMonth();
        var date = now.getDate();
        var day = now.getDay();
        var hour = now.getHours();
        var minu = now.getMinutes();
        var sec = now.getSeconds();
        var week;
        month = month + 1;
        if (month < 10)
            month = "0" + month;
        if (date < 10)
            date = "0" + date;
        if (hour < 10)
            hour = "0" + hour;
        if (minu < 10)
            minu = "0" + minu;
        if (sec < 10)
            sec = "0" + sec;
        var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
        week = arr_week[day];

         return year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;
    }
};
kdm.globel={
		/*窗体的显示方式，活着跳转方式*/
		getwinstyle:function(){
			var winstyle="dialog";
			try {
				var path = document.location.pathname;
				if(path.match(/(\/page\/)(index)/)){
					winstyle= index.globalData.winstyle;
				}else{
					winstyle= parent.index.globalData.winstyle;
				}
			} catch (e) {
				 
			}
			return winstyle;
		}
}
/**
 * 日期格式化
 * @type {{formatYMDHms: Function, formatYMD: Function}}
 */
kdm.moment = {
    formatYMDHms :function(value){
        return window.top.moment(value).format("YYYY-MM-DD HH:mm:ss");
    },
    formatYMD :function(value){
        return window.top.moment(value).format("YYYY-MM-DD");
    }
}

kdm.utils = {
	/**
	 * 替换所有字符串
	 * @param str
	 * @param oldVal
	 * @param newVal
	 * @returns
	 */
	replaceAll : function(str,oldVal,newVal){
		return str.replace(new RegExp(oldVal,"gm"),newVal); 
	},
    /**
     * 创建from表单并提交，一般用于下载
     * @param action
     * @param method
     * @param target
     */
    createFromAndSubmit : function(action,method,target){
        var form = $('<form></form>');
        form.attr('action', action);
        form.attr('method', method == undefined ? "post" : method);
        // _self -> 当前页面 _blank -> 新页面
        form.attr('target', method == undefined ? "_blank" : target);
        form.submit();
    },

    /**
     * 清除查询的值
     * @param id
     */
    cleanValue :function(element){
        $(element +" input").val("");
        $(element +" select option:first").prop("selected", 'selected');
    },

    /**
     * 获取请求的服务
     * @param reqType
     * @returns {string}
     */
    getServerPath : function(reqType){
        var serverPath = "";
        switch (reqType){
            case "system" :  //系统管理
                serverPath = kdm.utils.getContextPath();
                break;
            case "anti" :
                serverPath = kdm.utils.getContextPath();
                break;
            case "dataTableLocal" :
                serverPath = kdm.utils.getContextPath() + "/static/ace/js/dataTables/language/zn_ch.json";
                break;
            case "wfDesigner" :
                serverPath ="http://172.31.247.197:8080/eip-wf-desiger";
                break;
            default:
                serverPath = kdm.utils.getContextPath();
        }
        return serverPath;
    },
    getContextPath : function() {
        var contextPath = document.location.pathname;
        var index =contextPath.substr(1).indexOf("/"); //这个地方可能有问题，要根据具体项目适当修改
        contextPath = contextPath.substr(0,index+1);
        return contextPath == "/page" ? "" : contextPath;
    }

};

kdm.tab = {
    getIframeContent : function(){
    	try{
    		//console.log(window.top.$("#portal_tab_content .active > iframe")[0]);
    		 return window.top.$("#portal_tab_content .active > iframe")[0].contentWindow;
    	}catch(e){
    		 return window.top.$("iframe")[0].contentWindow;
    	}
    	
    	
      
    }
};


kdm.win = {
    /** 显示对话框 **/
    show : function(title,url,width,height,fit){
    	//-------
    	
    			 //获取宽度和高度
    	        var leftWidth = window.top.$("#sidebar").width(),
    	            topHeight = (window.top.$("#navbar").height() + window.top.$("#portal_tabs").height()),
    	            windowWidth = $(window).width(),
    	            windowHeight = window.screen.availHeight;//$(window).height();

    	        fit = fit == undefined ? true : fit;
    	        var offset = fit ? [topHeight + "px",leftWidth + "px"] : "150px";

    	        width = fit ? windowWidth - 10 : (width == undefined ? 600 : width);
    	        height = fit ? (windowHeight - 3*topHeight) : (height == undefined ? 400 : height);

    	        window.top.layer.open({
    	            type: 2,
    	            title: '<i class="ace-icon fa fa-edit"></i>  ' + title,
    	            shadeClose: false,
    	            shade: 0.2,
    	            shift: 1,
    	            resize:true,
    	            scrollbar: false,
    	            skin : "layui-layer-lan",
    	            moveOut : true,
    	            offset: offset,
    	            maxmin: true, //开启最大化最小化按钮
    	            area: [width + 'px', height + 'px'],
    	            content: url
    	        });

    },
    popup : function(title,url,width,height,callbackFun){//居中显示弹出层
        window.top.layer.open({
            type: 2,
            title: '<i class="ace-icon fa fa-edit"></i>  ' + title,
            shade: 0.2,
            shift: 1,
            skin : "layui-layer-lan",
            moveOut : true,
            maxmin: true, //开启最大化最小化按钮
            area: [width + 'px', height + 'px'],
            content: url,
            cancel: function(index){
            	if(callbackFun!=null){
            		callbackFun();
            	}
            	window.top.layer.close(index)
            }
        });
    },
   
    close : function(){
        var index = window.top.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        window.top.layer.close(index); //再执行关闭
    },

    /** 提示框 **/
    alert : function(msg,type){
       if(type == 0){
           window.top.layer.msg(msg, {icon: 1,offset: 100, shift: 6});
       }else if(type == -1){
           window.top.layer.msg(msg, {icon: 5,offset: 100, shift: 6});
       }else {
           window.top.layer.alert(msg, { title:'温馨提示',skin : "layui-layer-lan",icon: 0});
       }
    },

    /** 确认对话框 **/
    confirm : function(msg,callbackFun){
        window.top.layer.confirm(msg, {icon: 3, title:'温馨提示', skin : "layui-layer-lan",
            btn: ['确认','取消'] //按钮
        }, function(index){
            window.top.layer.close(index)
            callbackFun("yes");
        }, function(index){
            window.top.layer.close(index)
            callbackFun("cancel");
        });
    },

    // 提示层
    tipShow : function(text,maxWidth,maxHeight){
        return "<div class='tipshow'>" +
            "<div data-rel='tooltip' style='overflow: hidden;max-width:" +maxWidth + "px;max-height:" + maxHeight + "px;' " +
            "title='" + text + "'>" + text +"</div></div>"
    },

    // 图片展示层
    imgShow : function (url) {
        window.top.layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: true,
            area: ['600px', '400px'],
            content: "<img width='600px' height='400px' src='" + url + "'/>"
        });
    },
    forword : function(title,url,width,height,fit){
    	if(kdm.globel.getwinstyle()){
    		if(kdm.globel.getwinstyle()!='forword'){
    			kdm.win.show(title,url,width,height,fit)
    			return ;
    		}
    	}
    	window.location.href=SysConfig.base+"page/"+url;
    },
    
    //配合win 使用。
    showclose:function(msg,callback){
    	if(msg){
    		kdm.win.alert(msg);
    	}
    	if(kdm.globel.getwinstyle()){
    		if(kdm.globel.getwinstyle()!='forword'){
    			 if(callback!=null){
    		    		callback();
    		    	}
    			 kdm.win.close();
    			 return ;
    		}
    	} 
    	window.history.go(-1);
    	
    	
	   
    }

};

kdm.progress = {
    loading : function(){
       return  window.top.layer.load(0,{
            shade: [0.1,'#000'],
            time: 5000
        });
    },
    removeLoading : function(index){
        window.top.layer.close(index);
    },
    show : function(){
        return  window.top.layer.load(0,{
            shade: [0.1,'#000'],
            time: 100
        });
    },
    hide : function(index){
        window.top.layer.close(index);
    }

}

//命名空间
kdm.nameSpace = {
    reg : function(s){
        var arr = s.split('.');
        var namespace = window;

        for(var i=0,k=arr.length;i<k;i++){
            if(typeof namespace[arr[i]] == 'undefined'){
                namespace[arr[i]] = {};
            }
            namespace = namespace[arr[i]];
        }
    },

    del : function(s){
        var arr = s.split('.');
        var namespace = window;

        for(var i=0,k=arr.length;i<k;i++){
            if(typeof namespace[arr[i]] == 'undefined'){
                return;
            }else if( k == i+1 ){
                delete  namespace[arr[i]];
                return;
            }else{
                namespace = namespace[arr[i]];
            }
        }
    }
};

kdm.dict={
	getValue:function(key,str){
		var path = document.location.pathname;
		if(path.match(/(\/page\/)(index)/)){
			return index.globalData.dict[key][str];
		}else{
			return parent.index.globalData.dict[key][str];
		}
	},
	getValues:function(key){
		var path = document.location.pathname;
		if(path.match(/(\/page\/)(index)/)){
			return index.globalData.dict[key];
		}else{
			//console.log(parent.index.globalData);
			return parent.index.globalData.dict[key];
		}
	//	console.log(path.match(/(\/page\/)(index)?/));
	}
}
kdm.validata = {
    //前后去空格
    strTrim : function(str){
        return str.replace(/(^\s*)|(\s*$)/g,'');
    },

    //去所有空格
    strTrimAll : function(str){
        return str.replace(/\s/ig,'');
    },

    //是不是手机号码
    isPhone : function(str){
        var reg = /^1\d{10}$/;
        return reg.test(str);
    },

    isEmail : function(str){
        var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        return reg.test(str);
    },
    //15位和18位身份证号码的基本校验
    isUserIdentity : function(str){
        var reg = /^\d{15}|(\d{17}(\d|x|X))$/;
        return reg.test(str);
    }
};




(function($){
	function getValues(target){
		var values = [];
		var combo = $.data(target, 'combo').combo;
		combo.find('input.combo-value').each(function(){
			values.push($(this).val());
		});
		return values;
	}
	function getText(target){
		var combo = $.data(target, 'combo').combo;
		return combo.find('input.combo-text').val();
	}
	$.fn.comselect = function(options, param){
		//检查是方法调用还是直接引用
		if (typeof options == 'string'){
			return $.fn.comselect.methods[options](this, param);
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'combo');
			if (state){
				$.extend(state.options, options);
			} else {
				var r = init(this);
				state = $.data(this, 'combo', {
					options: $.extend({}, $.fn.combo.defaults, parseOptions(this), options),
					combo: r.combo,
					panel: r.panel
				});
				$(this).removeAttr('disabled');
			}
			$('input.combo-text', state.combo).attr('readonly', !state.options.editable);
			setDisabled(this, state.options.disabled);
			setSize(this);
			bindEvents(this);
			validate(this);
		});
	};
	$.fn.comselect.methods = {
			getValues: function(jq){
				return getValues(jq[0]);
			},
			getText: function(jq){
				return getText(jq[0]);
			},
			reload: function(jq){
				return reload(this);
			}
	};
	$.fn.comselect.defaults = {
			width: 'auto',
			panelWidth: null,
			panelHeight: 200,
			multiple: false,
			separator: ',',
			editable: true,
			disabled: false,
			required: false,
			missingMessage: 'This field is required.',
			
			selectPrev: function(){},
			selectNext: function(){},
			selectCurr: function(){},
			filter: function(query){},
			
			onChange: function(newValue, oldValue){}
		};
});