function regFlowCommand(formId,processInstanceId,processDefinitionKey,taskId){
		var ctaskParams = '<input type="hidden" id="taskParams" name="taskParams"/>';
		var ccommandId = '<input type="hidden" id="commandId" name="commandId"/>';
		var ccommandType = '<input type="hidden" id="commandType" name="commandType"/>';
		var cprocessInstanceId = '<input type="hidden" id="processInstanceId" name="processInstanceId" value="'+processInstanceId+'"/>';
		var cprocessDefinitionKey = '<input type="hidden" id="processDefinitionKey" name="processDefinitionKey" value="'+processDefinitionKey+'"/>';
		var ctaskId = '<input type="hidden" id="taskId" name="taskId" value="'+taskId+'"/>';
		
		$("#"+formId).append(ctaskParams);
		$("#"+formId).append(ccommandId);
		$("#"+formId).append(ccommandType);
		$("#"+formId).append(cprocessInstanceId);
		$("#"+formId).append(cprocessDefinitionKey);
		$("#"+formId).append(ctaskId);
		
		$("button[commandType]").click(function() {
			var id = $(this).attr("commandId");
			var type = $(this).attr("commandType");
			$("#commandId").val(id);
			$("#commandType").val(type);
			var params={};
			if(type=="processStatus"){
				var pii = processInstanceId;
				var pdk = processDefinitionKey;
				var obj = {};
				window.showModalDialog(
						"FlowCenter?action=getTaskDetailInfo&processInstanceId="
								+ pii+"&processDefinitionKey="
								+ pdk+"", obj,
						"dialogWidth=800px;dialogHeight=600px");
				return false;
			}else if(type=="transfer"){
				params={
						//被转发的UserId，这里设定了就是管理员
						transferUserId:""
				};

			}else if(type=="Pending"){//转办
				var obj = {type:"user"};
			  	var d = FixSelect(obj);
			  	if(d&&d.length>0){
					params={
							//转办的任务编号
						pendingTaskId:d[0].USERID
					};
			  	}else{
			  		return;
			  	}
			}else if(type=="recover"){
				params={
						//追回的任务编号
					recoverNodeId:""
				};
			}else if(type=="reminders"){
				params={
						//提醒某一个用户
					usersInfo:"",
						//提醒内容
					content:"",
						//提醒标题
					title:""
						
				};
			}else if(type=="rollBack"){
				params={
						//退回到某个节点
					rollBackNodeId:"",
						
				};
			}
			
			var ss = JSON.stringify(params);
			$("#taskParams").val(ss);
			$("#"+formId).submit();
		});
	}

function requestUrlParam(paras)
{ 
    var url = location.href; 
    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
    var paraObj = {} 
    for (i=0; j=paraString[i]; i++){ 
    paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
    } 
    var returnValue = paraObj[paras.toLowerCase()]; 
    if(typeof(returnValue)=="undefined"){ 
    return ""; 
    }else{ 
    return returnValue; 
    } 
}