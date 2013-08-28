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