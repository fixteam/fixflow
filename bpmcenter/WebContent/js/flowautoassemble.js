$(document).ready(function(){
	assemble();
});

function assemble(){
	var formId = document.forms[0].id;
	var processDefinitionId = request('processInstanceId');
	var processDefinitionKey = request('processDefinitionKey');
	var taskId = request('taskId');
	regFlowCommand(formId,processDefinitionId,processDefinitionKey,taskId);
}