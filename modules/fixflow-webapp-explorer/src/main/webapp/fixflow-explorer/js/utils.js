var FixFlow = FixFlow || {};
FixFlow.Utils = {
	createGuid: function(){
		var guid = "";
		for ( var i = 1; i <= 32; i++) {
			var n = Math.floor(Math.random() * 16.0).toString(16);
			guid += n;
		}
		return guid;
	}
};