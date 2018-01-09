/**
 * Created by wangtg on 15/12/11.
 */
kdm.nameSpace.reg("sys");

(function(){
    sys.index = function() {
        var ctxData = kdm.utils.getServerPath("system");

        // 申明内部对象
        var obj = this;

        var globalData={};
        var theme="default";
        /**  初始化页面 */
        this.init = function () {
        	
            obj.initMenuFun();
            //加载用户信息
            obj.initUserInfoFun();
            obj.initGlobalData();
            obj.globalData.winstyle="forword";//dialog
        
            
            //绑定菜单点击事件
            $("#portal_menus").on("click","li>a",obj.menuClickFun);
            //登出
            $("#btn-logout").on("click",obj.logoutFun);
            //加载快捷键
            obj.loadQuickKeyFun();

            //本地时间
            obj.setLocalLock();
        };

        /** 本地时间 */
        this.setLocalLock = function(){
            $('#localClock').html(kdm.common.localClock());
            setTimeout("index.setLocalLock()", 200);
        };

        this.initThemeLayout=function(){
        	if(obj.globalData.setting.theme=='allInOne'){
        		obj.initBreadcrumbs();
        	}else{
        		obj.initNavBar();
//              //鼠标进入时，添加tab改变样式;鼠标退出时，删除tab改变样式
                $("#portal_tabs").delegate("li>a>span","mouseover",function(event) {
                    $(event.currentTarget).addClass("badge");
                    $(event.currentTarget).addClass("badge-warning");
                });
                $("#portal_tabs").delegate("li>a>span","mouseout",function(event) {
                    $(event.currentTarget).removeClass("badge");
                    $(event.currentTarget).removeClass("badge-warning");
                });
                //选中tab是修改选中菜单
                $("#portal_tabs").on("click","li>a",function(e){
                    if($(e.target).hasClass("fa-remove"))
                        return false;
                    obj.selectMenuFun($(this).attr("menuid"))
                });
                //点击移除事件
                obj.tabCloseFun();
                //tab工具栏初始化
                obj.tabToolInitFun();
                //tab工具栏绑定事件
                obj.tabToolEvent();
        	}
        }
        /** 初始化菜单 */
        this.initMenuFun = function(){
            $.ajax({
                "url": ctxData + "/sys/menu/querymenubyuser?date=" + new Date().getTime() ,
                "success": function(retData){
                    var menuHtml = "";
                    menuHtml =  obj.foreachFun(menuHtml,retData.data) + "</ul></li>";
                    $("#portal_menus").append(menuHtml);
                    try {
                    	ace.demo.init();
                      } catch(e) {
                    	  console.log(e);
                      }
                },
                "dataType": "jsonp",
                "cache": false
            });
        };

        this.initGlobalData=function(){
        	obj.globalData={};
        	 $.ajax({
                 url: ctxData + "/init/dict?date=" + new Date().getTime() ,
                 async: false,
                 success: function(retData){
                	 if(retData.status == "0"){
                		 obj.globalData.dict=retData.data;
                	 }
                 },
                 dataType: "jsonp",
                 cache: false
             });
        	 $.ajax({
                 url: ctxData + "/pot/baseLib/allList?date=" + new Date().getTime(),
                 async: false,
                 success: function(retData){
                	 if(retData.status == "0"){
                		 $.extend(obj.globalData.dict, retData.data);
                	 }
                 },
                 dataType: "jsonp",
                 cache: false
             });
        	 $.ajax({
                 url: ctxData + "/utils/allOrg?date=" + new Date().getTime(),
                 async: false,
                 success: function(retData){
                	 if(retData.status == "0"){
                		 $.extend(obj.globalData.dict, retData.data);
                	 }
                 },
                 dataType: "jsonp",
                 cache: false
             });
        	 $.ajax({
                 url: ctxData + "/utils/allUser?date=" + new Date().getTime(),
                 async: false,
                 success: function(retData){
                	 if(retData.status == "0"){
                		 $.extend(obj.globalData.dict, retData.data);
                	 }
                 },
                 dataType: "jsonp",
                 cache: false
             });
        	 $.ajax({
                 url: ctxData + "/init/setting?date=" + new Date().getTime() ,
                 success: function(retData){
                	 if(retData.status == "0"){
                		 obj.globalData.setting=retData.data;
                		 obj.initThemeLayout();
                	 }
                 },
                 dataType: "jsonp",
                 cache: false
             });
        };
        /** 菜单循环处理 */
        this.foreachFun = function(menuHtml,datas){
            $.each(datas,function(index,object){
                if(object.id == undefined)
                    return;
                if(object.children != undefined && object.children.length > 0){
                    menuHtml += '<li><a href="javascript:void(0);" class="dropdown-toggle" isleaf="1">';
                    menuHtml += '<i class="menu-icon fa ' + (object.icon == "" ? "fa-truck" : object.icon) + '"></i>';
                    menuHtml += '<span class="menu-text">' + object.menuName + '</span>';
                    menuHtml += '<b class="arrow fa fa-angle-down"></b></a>';
                    menuHtml += '<b class="arrow"></b>';
                    menuHtml += '<ul class="submenu">';

                    if(object.children.length > 0){
                        var chaildrenHtml = "";
                        menuHtml += obj.foreachFun(chaildrenHtml,object.children);
                    }
                    menuHtml += '</ul></li>';
                }else{
                    menuHtml += '<li><a data-url="' + object.url + '" href="#' + object.url + '"  menuid="' + object.id + '" isleaf="0" title="' + object.menuName + '">';
                    menuHtml += '<i class="menu-icon fa ' + (object.icon == "" ? "fa-truck" : object.icon)  + '"></i>';
                    menuHtml += '<span class="menu-text">' + object.menuName + '</span>';
                    menuHtml += '</a><b class="arrow"></b></li>';
                }
            });
            return menuHtml;
        };

        /** 选中指定的菜单 */
        this.selectMenuFun = function(id){
            var menuA = $("#portal_menus a[menuid=" + id + "]").parent();
            if(menuA == undefined)
                return;

            $("#portal_menus li").removeClass("active");
            menuA.addClass("active");

            $("#portal_menus li ul").css("display","none");
            $("#portal_menus li").removeClass("open");

            if(menuA.parent().hasClass("submenu")){
                menuA.parent().css("display","block");
                menuA.parent().parent().addClass("open");
            }
        }

        /** 关闭事件 */
        this.tabCloseFun = function(){
            $("#portal_tabs").delegate("li>a>span","click",function(event) {
                var currentClickTab = $(this).parent().parent();

                obj.closeCurrentTab(currentClickTab);

                //阻止事件冒泡
                event.stopPropagation();
                //阻止事件执行
                event.preventDefault();
            });
        }

        /** 关闭指定的tab */
        this.closeCurrentTab = function(currentSelectedTab){

            if($(currentSelectedTab).attr("id") == "tab_home"){
                kdm.win.alert("首页不能关闭");
                obj.tabToolsFun("hide");
                return false;
            }

            var nextSelectTab;
            if ($(currentSelectedTab).prev().length > 0) {
                nextSelectTab = $(currentSelectedTab).prev();
            } else if ($(currentSelectedTab).next().length > 0) {
                nextSelectTab = $(currentSelectedTab).next();
            } else {
                nextSelectTab = $("#portal_tabs li").eq(0);
            }

            //移除tab内容
            $("#portal_tab_content "+ $(currentSelectedTab).find("a").attr("href")).remove();
            //移除tab项
            $(currentSelectedTab).remove();

            //如果有选中的tab则返回
            if($("#portal_tabs .active>a").length > 0){
                return false;
            }
            //下一个tab选中
            $(nextSelectTab).addClass("active");
            $("#portal_tab_content > "+ $(nextSelectTab).find("a").attr("href") +"").addClass("active");
            obj.selectMenuFun($(nextSelectTab).find("a").attr("menuid"));
        }

        /** 菜单点击事件 */
        this.menuClickFun = function(){
        
        	
            var href = $(this).attr("data-url"),
                id = $(this).attr("menuid"),
                title = $(this).attr("title");

            if($(this).attr("isleaf") == 1)
                return;

            //改变样式
            $("#portal_menus li").removeClass("active");
            $(this).parent().addClass("active");
//        	console.log(href);
        	 
        	obj.addTabPageFun(id,title,href,false,$(this));
        }
        
        /** 添加tab function */
        this.OnePageFun = function(id,title,href,reload){
        	  $("#home").attr("src",href);
              kdm.common.setIframeHeight("home");
        }
        this.addTabPageFun = function(id,title,href,reload,link){
        	if(obj.globalData.setting.theme=='allInOne'){
        		link = (link == undefined) ? $("") : link;
        		obj.updateBreadcrumbs(link);
        		obj.OnePageFun(id,title,href);
        		return;
        	}
        
            reload = (reload == undefined) ? false : reload;
            //移除选中样式
            $("#portal_tabs li").removeClass("active");
            $("#portal_tab_content div").removeClass("active");

            //判断是否存在，不存在添加，存在选中
            if($("#portal_tabs #tab_" + id).length > 0){
                $("#tab_" + id).addClass("active");
                $("#tab_content_" + id).addClass("active");
                if(reload)
                    $("#iframe_" + id).attr("src",href);

                return;
            }
            var joinChar = (href.indexOf("?") > 0) ? "&" : "?";

            //添加
            obj.addTabFun(id,title);
            obj.addTabContentFun(id,href + joinChar + "iframeId=iframe_" +id);
        }

        /** tab reload function **/
        this.tabReloadFun = function(){
            var currentTabContentId = $("#portal_tabs .active>a").attr("href"),
                currentTabContentIframe = $(currentTabContentId + ">iframe")[0];
            $(currentTabContentIframe).attr("src",currentTabContentIframe.src);
            obj.tabToolsFun("hide");
        }
        this.tabReloadByIdFun = function(id){
            $("#iframe_" + id).attr("src",$("#iframe_" + id).attr("src"));
        }

        /** 添加tab */
        this.addTabFun = function(id,title){
            var tabHtml = '<li role="presentation" class="active" id="tab_' + id + '">';
            tabHtml += '<a href="#tab_content_' + id + '" aria-controls="' + id + '" menuid="' + id + '" role="tab" data-toggle="tab">';
            tabHtml += title + '<span class=""><i class="ace-icon fa fa-remove"></i></span></a></li>';

            $("#portal_tabs").append(tabHtml);
        }

        /** 添加tab内容 */
        this.addTabContentFun = function(id,href){
            var tabContent = '<div class="tab-pane active" id="tab_content_' + id + '">';
            tabContent += '<iframe src="' + href + '" scrolling="auto" class="no-border" frameborder="0px;" width="100%" ';
            tabContent += ' id="iframe_' + id + '" onload="kdm.common.setIframeHeight(this.id);"></iframe> </div>';

            $("#portal_tab_content").append(tabContent);
        }

        this.initNavBar=function(){
        	/**导航*/
        	var breadcrumbs = $('#breadcrumbs');
        	breadcrumbs.find("> ul").remove();
        	breadcrumbs.find("> div").remove();
        
        	var ul=$('<ul class="nav nav-tabs" role="tablist" id="portal_tabs"> </ul>').appendTo(breadcrumbs);
        	var li=$('<li role="presentation" class="active" id="tab_home"> </li>').appendTo(ul);
        	$('<a href="#tab_content_home" aria-controls="home" menuid="home" role="tab" data-toggle="tab">首页&nbsp;&nbsp;&nbsp;</a>').appendTo(li);
        
	    	/**内容*/
	    	var page_content = $('.page-content');
	     	page_content.find('> div:not(:first-child)').remove();
	        var tab_content=$('<div class="tab-content no-border padding-0" id="portal_tab_content"></div>').appendTo(page_content);
	        var tab_pane=$(' <div class="tab-pane active" id="tab_content_home"></div>').appendTo(tab_content);
	        $('<iframe src="main?id=home" scrolling="auto" class="no-border" frameborder="0px;"  width="100%" height="100%" id="home" onload="kdm.common.setIframeHeight(this.id);"></iframe>').appendTo(tab_pane);
	        var tabtool=$("#tabtool");
	        var tabtool_clone = tabtool.clone();
	       
	        tabtool_clone.insertAfter(tab_pane);
	        tabtool.remove();
        }
        this.initBreadcrumbs=function(){
        	var breadcrumbs = $('#breadcrumbs');
        	breadcrumbs.find("> ul").remove();
        	breadcrumbs.find("> div").remove();
        	var ul=$('<ul class="breadcrumb"  id="portal_tabs"></ul>').appendTo(breadcrumbs);
        	var li=$('<li></li>').appendTo(ul);
        	var ei=$('<i class="ace-icon fa fa-home home-icon"></i>').appendTo(li);
        	$('<a href="#">首页</a>').insertAfter(ei);
        	var div=$('<div class="nav-search" id="nav-search"></div>').insertAfter(ul);
//        	 当前时间：<span class="red" id="localClock"></span>
        	 var span=$('<span class="red" id="localClock"></span').appendTo(div);
//        	var form=$('<form class="form-search"></form>').appendTo(div);
//        	var span=$('<span class="input-icon"></span>').appendTo(form);
//        	var input=$('<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />').appendTo(span);
//        	$('<i class="ace-icon fa fa-search nav-search-icon"></i>').insertAfter(input);
        	/**内容*/
        	var page_content = $('.page-content');
	     	page_content.find('> div:not(:first-child)').remove();
	        var div_row=$('<div class="row"></div>').appendTo(page_content);
	       // var div_xs=$(' <div class="col-xs-12"></div>').appendTo(div_row);
	        $('	<iframe src="main?id=home" scrolling="auto" class="no-border" frameborder="0px;"  width="100%" height="100%" id="home" onload="kdm.common.setIframeHeight(this.id);"></iframe>').appendTo(div_row);
        
        	
        	
        }
        /**更新导航**/
        this.updateBreadcrumbs= function (link_element) {
			var link_text = '';
		 
			//update breadcrumbs
			var breadcrumbs = $('.breadcrumb');
			if(breadcrumbs.length > 0 && breadcrumbs.is(':visible')) {
				breadcrumbs.find('> li:not(:first-child)').remove();

				var i = 0;		
				link_element.parents('.nav li').each(function() {
					var link = $(this).find('> a');
					
					var link_clone = link.clone();
					link_clone.find('i,.fa,.glyphicon,.ace-icon,.menu-icon,.badge,.label').remove();
					var text = link_clone.text();
					link_clone.remove();
					
					var href = link.attr('href');

					if(i == 0) {
						var li = $('<li class="active"></li>').appendTo(breadcrumbs);
						li.text(text);
						link_text = text;
					}
					else {
						var li = $('<li><a /></li>').insertAfter(breadcrumbs.find('> li:first-child'));
						li.find('a').attr('href', href).text(text);
					}
					i++;
				})
			}
			
			return link_text;
		 }
		 
        /** logout function **/
        this.logoutFun = function(){
            $.ajax({
                url: ctxData + "/authc/logout?date=" + new Date().getTime() ,
                success: function(){
                    window.location.reload(true);
                },
                error : function(){
                    window.location.reload(true);
                },
                dataType: "jsonp",
                cache: false
            });
        };

        /** 展示用户信息  **/
        this.initUserInfoFun = function(){
            $.ajax({
                "url": ctxData + "/sys/login/queryuserinfo?date=" + new Date().getTime(),
                "success": function(retData){
                    if(retData.data == undefined || retData.data == null)
                        return;
                    $('#showOrgName').html(retData.data.orgName);
                    if(retData.data.roleList.length>0){
                    	$('#roleName').html(retData.data.roleList[0].roleName);
                    }
                    $("#userName").html(retData.data.userName);
                    if(retData.data.imgUrl != "" && retData.data.imgUrl != undefined) {
                        $('#userImg').attr('src', SysConfig.base+retData.data.imgUrl).fadeIn();
                    }else {
                        $('#userImg').attr('src', "../static/images/user.jpg").fadeIn();
                    }
                },
                "dataType": "jsonp",
                "cache": false
            });
        };

        /** 跳转页面 **/
        this.forWardFun = function(flag){
            var id,title,href;
            if(flag == "userInfo"){
                id = "111",title = "用户信息",href = "system/set/userInfo.html";
            }else if(flag == "set"){
                id = "222",title = "设置",href = "system/set/quicklyKey.html";
            }
            //obj.addTabPageFun(id,title,href);
            kdm.win.show(title,href,600,400,false);
         
        };

        /**  加载快捷键 */
        this.loadQuickKeyFun = function(){
            $.ajax({
                "url": ctxData + "/sys/quickkey/querybyid?date=" + new Date().getTime(),
                "success": function(retData){
                    if(retData.data == undefined || retData.data == null || retData.data.length == 0)
                        return;
                    $("#quickKey").html("");
                    var quickHtml='<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">';
                    $.each(retData.data,function(index,object){
                        var iconCss,buttonCss;
                        if(index == 0){
                            iconCss="fa-signal", buttonCss = "btn-success";
                        }else if(index == 1){
                            iconCss="fa-pencil",buttonCss = "btn-info";
                        }else if(index == 2){
                            iconCss="fa-users",buttonCss = "btn-warning";
                        }else if(index == 3){
                            iconCss="fa-cogs",buttonCss = "btn-danger";
                        }

                        quickHtml += '<button id="quick_btn' + index + '" class="btn '+ buttonCss + '" data-rel="tooltip" data-placement="' + (index > 1 ? "left" : "right") + '" title="';
                        quickHtml += object.keyTitle + '" rel="' + object.keyValue + '">';
                        quickHtml += ' <i class="ace-icon fa ' + (object.keyIcon == "" ? iconCss : object.keyIcon) + '"></i></button>&nbsp;';
                    });
                    quickHtml += '</div>';

                    quickHtml += '<div class="sidebar-shortcuts-mini"><span class="btn btn-success"></span><span class="btn btn-info"></span>';
                    quickHtml += '<span class="btn btn-warning"></span><span class="btn btn-danger"></span></div>';
                    $("#quickKey").html(quickHtml);
                    $('#quickKey [data-rel=tooltip]').tooltip();

                    $("#quickKey button").on("click",function(){
                        obj.addTabPageFun($(this).attr("id"),$(this).attr("data-original-title"),$(this).attr("rel"));
                    });
                },
                "dataType": "jsonp",
                "cache": false
            });
        };

        /**  绑定右键和双击菜单事件 */
        obj.tabToolInitFun = function(){
            //鼠标右键显示工具栏
            $("#portal_tabs").delegate("li","contextmenu",function(event){
                //移除选中样式
                $("#portal_tabs li").removeClass("active");
                $("#portal_tab_content div").removeClass("active");

                //选中当前tab
                $(this).addClass("active");
                $($(this).find("a").attr("href")).addClass("active");
                obj.selectMenuFun($(this).find("a").attr("menuid"));

                //设置工具栏位置
                var leftPosition = $(this).offset().left - $("#sidebar").width(),
                    topPostion = $(this).offset().top - $("#navbar").height() ;
                $("#tabtool").css({left:leftPosition + "px",top:topPostion + "px"}).slideDown("fast");

                obj.tabToolsFun("show");
                //阻止事件冒泡
                event.stopPropagation();
                //阻止事件执行
                event.preventDefault();
            });

            //鼠标双击显示工具栏
            $("#portal_tabs").delegate("li","dblclick",function(){
                var leftPosition = $(this).offset().left - $("#sidebar").width(),
                    topPostion = $(this).offset().top - $("#navbar").height() ;
                //设置工具栏位置
                $("#tabtool").css({left:leftPosition + "px",top:topPostion + "px"}).slideDown("fast");

                obj.tabToolsFun("show");
            });
            //点击其他地方关闭工具栏
            $(document).delegate("#tabToolsShade","click",function(){
                obj.tabToolsFun("hide");
            });
        };

        /**  工具栏菜单事件 */
        obj.tabToolEvent = function() {
            //批量关闭tab
            var batchCloseTab = function(tabs){
                $.each(tabs,function(index,object){
                    if($(object).attr("id") != "tab_home"){
                        $("#portal_tab_content "+ $(object).find("a").attr("href")).remove();
                        $(object).remove();
                    }
                });
            }

            // 刷新
            $('#tabtool_refresh').click(function() {
                obj.tabReloadFun();
                obj.tabToolsFun("hide");
            });

            // 关闭当前
            $('#tabtool_close').click(function() {
                //当前选中的tab
                var currentSelectedTab = $("#portal_tabs .active>a").parent();
                obj.closeCurrentTab(currentSelectedTab);
                obj.tabToolsFun("hide");
                //阻止事件冒泡
                event.stopPropagation();
                //阻止事件执行
                event.preventDefault();
            });

            // 全部关闭
            $('#tabtool_closeall').click(function() {
                batchCloseTab($('#portal_tabs li'));
                $("#tab_home").addClass("active");
                $("#tab_content_home").addClass("active");
                obj.tabToolsFun("hide");
            });
            // 关闭除当前之外的TAB
            $('#tabtool_closeother').click(function() {
                var currentSelectedTab = $("#portal_tabs .active>a").parent();
                batchCloseTab($(currentSelectedTab).prevAll());
                batchCloseTab($(currentSelectedTab).nextAll());
                obj.tabToolsFun("hide");
            });

            // 关闭当前右侧的TAB
            $('#tabtool_closeright').click(function() {
                var currentSelectedTab = $("#portal_tabs .active>a").parent();
                batchCloseTab($(currentSelectedTab).nextAll());
                obj.tabToolsFun("hide");
            });

            // 关闭当前左侧的TAB
            $('#tabtool_closeleft').click(function() {
                var currentSelectedTab = $("#portal_tabs .active>a").parent();
                batchCloseTab($(currentSelectedTab).prevAll());
                obj.tabToolsFun("hide");
            });

            // 退出
            $("#tabtool_exit").click(function() {
                obj.tabToolsFun("hide");
            });
        }

        /** 工具栏显示和隐藏 **/
        this.tabToolsFun = function(action){
            if(action == "show"){
               $('<div class="layui-layer-shade" id="tabToolsShade" style="z-index:2015; background-color:#000;opacity: 0.1; filter:alpha(opacity=20);"></div>').appendTo( $('body'));
                $("#tabtool").show();
            }else if(action == "hide"){
                $("#tabToolsShade").remove();
                $("#tabtool").hide("slow");
            }
        }
        this.getFrameHeight=function(){
//        	 var iframes = document.getElementsByTagName("iframe");
//        	 if(iframes.length==0){
//             	return 500;
//             }else{
//            	 return iframes[0].height;
//             }
        	return $(".page-content").height()-9;
        }
    }

    // 初始化数据
    $(document).ready(function() {
        index.init();
    });
})();
var index = new sys.index();

