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