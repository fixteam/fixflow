$(document).ready(function(){
	assemble();
});

function assemble(){
	var formId = document.forms[0].id;
	var processInstanceId = requestUrlParam('processInstanceId');
	var processDefinitionKey = requestUrlParam('processDefinitionKey');
	var taskId = requestUrlParam('taskId');
	regFlowCommand(formId,processInstanceId,processDefinitionKey,taskId);
}
