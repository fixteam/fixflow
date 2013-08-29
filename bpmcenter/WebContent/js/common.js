/** @namespace Fix 组件命名空间*/
Fix = window['Fix'] || {};

Fix = {
	Util:{
		GetQueryString:function(name){ 
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
			var r = window.location.search.substr(1).match(reg); 
			if (r!=null) 
				return unescape(r[2]); 
			return null; 
		},
		CheckBtnStatus:function($table){
			var selectedRow;
			if(!$table)
				selectedRow = $("table.fix-table tr.selected").length;
			else
				selectedRow = $("tr.selected",$table).length;
			if(selectedRow==0){
				$("div[data-scope]").addClass("disable");
				$("div[data-scope=all]").removeClass("disable");
			}else if(selectedRow==1){
				$("div[data-scope]").addClass("disable");
				$("div[data-scope=all]").removeClass("disable");
				$("div[data-scope=single]").removeClass("disable");
			}else{
				$("div[data-scope]").addClass("disable");
				$("div[data-scope=all]").removeClass("disable");
				$("div[data-scope=multi]").removeClass("disable");
			}
		},
		ClickTr:function($table,isMulti,hasBox,boxPosition){
			if(!$table)$table = $("table.fix-table");
			if(!isMulti)isMulti = false;
			if(!hasBox)hasBox = false;
			if(!boxPosition)boxPosition = 0;
			if(hasBox){
				$("tbody tr",$table).each(function(){
					$("td:eq("+boxPosition+") input",$(this)).bind("trClick",function(){
						var $tr = $(this).parents("tr");
						if($tr.hasClass("selected")){
							$(this).attr("checked",true);
						}else{
							$(this).attr("checked",false);
						}
					}).click(function(){
						var isChecked = $(this).attr("checked");
						if(isChecked){
							$(this).attr("checked",false);
						}else{
							$(this).attr("checked",true);
						}
						$(this).parents("tr").trigger("boxClick");
					});
				});
			}
			$("tbody tr",$table).click(function(){
				if(!isMulti){
					$("tbody tr",$table).removeClass("selected");
					$(this).addClass("selected");
					if(hasBox){
						$("td:eq("+boxPosition+") input",$(this)).click();
					}
				}else{
					if($(this).hasClass("selected")){
						$(this).removeClass("selected");
					}else{
						$(this).addClass("selected");
					}
					if(hasBox){
						$("td:eq("+boxPosition+") input",$(this)).trigger("trClick");
					}
				}
			}).bind("boxClick",function(){
				var isChecked = $("td:eq("+boxPosition+") input",$(this)).attr("checked");
				if(isChecked){
					$(this).addClass("selected");
				}else{
					$(this).removeClass("selected");
				}
			});
		}
	},
	OpenMethod:{
		openWindow:function(url, width, height){
			var _width = width || 800;
			var _height = height || 600;
			window.showModalDialog(url,{},"dialogWidth="+_width+"px;dialogHeight="+_height+"px");
		}
		
	}	
};

/*
 * 启动时的一些预设方法
 * */
$(function(){
	$("table.fix-table tbody tr:odd").addClass("odd");
	$("table.fix-table tbody tr").click(function(){
		
	});
});
