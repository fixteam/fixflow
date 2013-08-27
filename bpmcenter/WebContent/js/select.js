/**
 * 所有HTML选人界面的控制层
 * @param type 选择界面类型
 * @param isMulti 是否多选
 * @returns 选择的List<Map>
 */
function FixSelect(type,isMulti){
	var that = arguments[arguments.length-1];
	//alert($(that).val());
	var rv = null;
	switch(type){
		case "user":
			rv = window.showModalDialog("FlowCenter?action=selectUserList&isMulti="+isMulti);
			break;
		case "node":
			rv = window.showModalDialog("FlowCenter?action=selectNodeList");
			break;
		case "step":
			rv = window.showModalDialog("FlowCenter?action=selectStepList");
			break;
		default:
			break;
			
	}
	return rv;
};