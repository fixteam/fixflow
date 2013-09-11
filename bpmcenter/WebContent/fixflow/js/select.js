/**
 * 所有HTML选人界面的控制层
 * @param type 选择界面类型
 * @param isMulti 是否多选
 * @returns 选择的List<Map>
 */
function FixSelect(obj,fn,params){
	//var that = arguments[arguments.length-1];
	window.rv = null;
	var rv = null;
	var w = obj.width||"800px";
	var h = obj.height||"600px";
	var isMulti = obj.isMulti||"false";
	var passObj = {
		fn:fn,
		params:params,
		opener:window
	};
	switch(obj.type){
		case "user":
			rv = window.showModalDialog("FlowCenter?action=selectUserList&isMulti="+isMulti,passObj,"dialogWidth="+w+";dialogHeight="+h);
			break;
		case "node":
			rv = window.showModalDialog("FlowCenter?action=selectNodeList&taskId="+obj.taskId,passObj,"dialogWidth="+w+";dialogHeight="+h);
			break;
		case "step":
			rv = window.showModalDialog("FlowCenter?action=selectStepList&taskId="+obj.taskId,passObj,"dialogWidth="+w+";dialogHeight="+h);
			break;
		default:
			break;
			
	}
	if(rv){
		return rv;
	}else{
		return window.rv;
	}
};