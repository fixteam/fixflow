/** @namespace Fix 组件命名空间*/
Fix = window['Fix'] || {};

Fix = {
	Util:{
		
	},
	OpenMethod:{
		openWindow:function(url, width, height){
			var _width = width || 800;
			var _height = height || 600;
			window.showModalDialog(url,{},"dialogWidth="+_width+"px;dialogHeight="+_height+"px");
		}
		
	}
		
		
};