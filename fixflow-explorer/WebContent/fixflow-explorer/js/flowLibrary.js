var breadcrumbList = new Array(),
	currentTreeNode,
	currentTreeChildrenNodes,
	currentOperationType,
	openEditorTag = false;
	zTreeSetting = {
		data: { simpleData: { enable: true}},
		callback: {
			beforeClick: function(){
				$("a.curSelectedNode").removeClass("curSelectedNode");
				currentOperationType = "done";
			},
			onClick: function(event, treeId, treeNode){
				currentTreeNode = treeNode;
				breadcrumbList = new Array();
				breadcrumbList.push({name:treeNode.name, treeNodeId:treeNode.id});
				getBreadcrumb(treeNode, breadcrumbList);
				breadcrumbList.reverse();
				createBreadcrumbList(breadcrumbList);
				readSubFileAndDirectory(breadcrumbList);
				currentTreeChildrenNodes = tree.getNodesByFilter(function(node){
					return true;
				}, false, currentTreeNode);
				if(checkIsResolvent()){
					$(".toolbar > div").removeClass("btn-normal").addClass("btn-disable");
				}else{
					resetToolbarState();
				}
			}
		}
	};
var tree;
$(document).ready(function(){
	/************新建流程对话框**************/
	$("#okBtn").click(function(){
		var id = $("#flowFileId").val();
		var name = $("#flowFileName").val();
		if(id=="" || name==""){
			alert("请输入名称和编号");
			return;
		}
		var reg = /[^x00-xff]/ ;
		var b = reg.test(id);
		if(b){
			alert("流程ID不能有中文");
			return;
		}
		
		$.ajax({
			url: "/bpmcenter/FileAndDirectoryServlet",
			type: "POST",
			dataType: "text",
			data: {
				method: "createBPMNFile",
				path: getBreadcrumbNameList(breadcrumbList),
				name: name,
				id: id+".bpmn"
			},
			success: function(data){
				eval("var d = " + data);
				if(d.state == "error"){
					alert(d.result);
				}else{
					alert("创建成功！");
					var $newFile = $('<div class="thumb-wrap" dirType="file"><div class="thumb model"></div><span class="x-editable" title="'+id+'.bpmn">'+id+'.bpmn</span></div>');
					$newFile.appendTo($("div.view_plugin"));
					$("#createFileDialog").hide();
					$(".bg").hide();
				}
				$("#flowFileId").val("");
				$("#flowFileName").val("");
			}
		});
		
	});
	
	$("#closeBtn").click(function(){
		$("#createFileDialog").hide();
		$(".bg").hide();
	});
	/***********************************/
	
	/************发布流程对话框**************/
	$("#delopyUpdateBtn").click(function(){
		if($("tr.select").length<1){
			alert("请选择一行记录");
			return;
		}
		var fileName = $("#delopyFlowDialog table").data("fileName");
		var deploymentId = $("tr.select").data("deploymentId") || "";
		if(window.confirm('确定更新？')){
			$.ajax({
				url: "/bpmcenter/FileAndDirectoryServlet",
				type: "POST",
				dataType: "text",
				data: {
					method: "delopy",
					path: getBreadcrumbNameList(breadcrumbList),
					fileName: fileName,
					deploymentId: deploymentId
				},
				success: function(data){
					eval("var d = " + data);
					if(d.state == "error"){
						alert(d.result);
					}else{
						alert("更新成功！");
						$("#delopyFlowDialog").hide();
						$(".bg").hide();
					}
				}
			});
		}
	});
	
	$("#delopyOkBtn").click(function(){
		var fileName = $("#delopyFlowDialog table").data("fileName");
		if(window.confirm('确定发布？')){
			$.ajax({
				url: "/bpmcenter/FileAndDirectoryServlet",
				type: "POST",
				dataType: "text",
				data: {
					method: "delopy",
					path: getBreadcrumbNameList(breadcrumbList),
					fileName: fileName,
					deploymentId: ""
				},
				success: function(data){
					eval("var d = " + data);
					if(d.state == "error"){
						alert(d.result);
					}else{
						alert("发布成功！");
						$("#delopyFlowDialog").hide();
						$(".bg").hide();
					}
				}
			});
		}
	});
	
	$("#delopyCloseBtn").click(function(){
		$("#delopyFlowDialog").hide();
		$(".bg").hide();
	});
	/***********************************/
	
	$("#upload").change(function(){
		var fileName = $(this).val();
		var fName = fileName.substring(fileName.lastIndexOf("\\")+1);
		$("#fileName").val(fName);
		$("#path").val(getBreadcrumbNameList(breadcrumbList));
		try{
			$("#uploadFile").submit();
			alert("上传成功！");
			var guid = FixFlow.Utils.createGuid();
			$thumb_wrap = $('<div class="thumb-wrap" dirType="file"><div class="thumb model"></div><span class="x-editable" title="'+fName+'">'+fName+'</span></div>');
			$thumb_wrap.appendTo($("div.view_plugin"));
		}catch(e){
			alert("上传失败！");
		}
	});
	
	$.ajax({
		url: "/bpmcenter/FileAndDirectoryServlet",
		type: "POST",
		dataType: "text",
		data: {
			method: "loadTree"
		},
		success: function(data){
			eval("var d = " + data);
			tree = $.fn.zTree.init($("div.left ul.ztree"), zTreeSetting, d.result);
			$("#folderTree_1_a").click();
			var node = tree.getNodeByTId("folderTree_1");
			tree.expandNode(node, true);
		}
	});
	
	resetToolbarState();
	$("div.toolbar > div.listBtn").each(function(){
		
		$(this).click(function(event){
			if($(this).hasClass("btn-disable")){
				return false;
			}
			var $selectTarget = $("div.thumb-wrap[select=true]");
			switch($(this).attr("btn-type")){
				case "delopy":
					var $selectThumbWrap = $("div.thumb-wrap[select=true]");
					var $span = $("span", $selectThumbWrap);
					var fileName = $span.html();
					var fileType = fileName.substring(fileName.lastIndexOf(".")+1);
					if(fileType!="bpmn"){
						alert("该文件不是流程定义文件");
						return false;
					}
					$("#delopyFlowDialog table tbody").empty();
					$.ajax({
						url: "/bpmcenter/FileAndDirectoryServlet",
						type: "POST",
						dataType: "text",
						data: {
							method: "getProcessVersionInfo",
							fileName: fileName
						},
						success: function(data){
							eval("var d = " + data);
							$("#delopyFlowDialog table").data("fileName", fileName);
							$.each(d.result, function(index, obj){
								var $tbody = $("#delopyFlowDialog table tbody");
								var $tr = $("<tr><td>"+ obj.processDefinitionKey +"</td><td>"+ obj.processDefinitionName +"</td><td>"+ obj.version  +"</td></tr>");
								$tr.click(function(){
									$("tr", $tbody).removeClass("select");
									$(this).addClass("select");
								});
								
								$tr.data("deploymentId", obj.deploymentId);
								$tbody.append($tr);
							});
							$("#delopyFlowDialog").show();
							$(".bg").show();
						}
					});
					break;
				case "createFile":
					currentOperationType = "createFile";
					$("#createFileDialog").show();
					$(".bg").show();
					break;
				case "createFolder":
					currentOperationType = "create";
					$("div[btn-type]").removeClass("btn-normal").addClass("btn-disable");
					$("div.thumb-wrap[dirType=empty]").remove();
					Operation.createGuid = FixFlow.Utils.createGuid();
					var $newFolder = $('<div class="thumb-wrap" dirType="dir" treeNodeId="'+ Operation.createGuid +'"><div class="thumb"><img src="/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey.png" class="x-thumb-icon"></div><span class="editable"></span></div>');
					$newFolder.appendTo($("div.view_plugin"));
					var $input = $('<input type="text" class="editName" style="width:90px;"  />');
					$input.val("新建文件夹").attr("oldValue", "新建文件夹");
					$("span.editable", $newFolder).append($input);
					$input.select();
					$input.keydown(function(event){
						if(event.keyCode == 13){
							var reg = /[^x00-xff]/ ;
							var b = reg.test($(this).val());
							if(b){
								alert("文件名不能有中文");
								return;
							}
							Operation.create($(this));
						}else if(event.keyCode == 27){	//ESC
							currentOperationType = "done";
							$newFolder.remove();
							resetToolbarState();
						};
					}).click(function(){
						return false;
					});
					break;
					
				case "rename":
					$("div[btn-type]").removeClass("btn-normal").addClass("btn-disable");
					currentOperationType = "rename";
					var $selectThumbWrap = $("div.thumb-wrap[select=true]");
					var $span = $("span", $selectThumbWrap);
					var $editInput = $("<input class='editName' type='text' style='width:90px;' oldValue='"+$span.html()+"' value='"+$span.html()+"'/>");
					$span.empty().append($editInput);
					$editInput.focus();
					$editInput.click(function(){
						return false;
					}).keydown(function(event){
						if(event.keyCode == 13){	//Enter
							var reg = /[^x00-xff]/ ;
							var b = reg.test($(this).val());
							if(b){
								alert("文件名不能有中文");
								return;
							}
							Operation.rename($(this));
						}else if(event.keyCode == 27){	//ESC
							currentOperationType = "done";
							$editInput.parent("span").replaceWith("<span>"+$(this).attr("oldValue")+"</span>");
							resetToolbarState();
						}
					});
					break;
					
				case "delete":
					if(window.confirm('确定删除？')){
						var $selectThumbWrap = $("div.thumb-wrap[select=true]");
						var $span = $("span", $selectThumbWrap);
						var fileName = $span.html();
						$.ajax({
							url: "/bpmcenter/FileAndDirectoryServlet",
							type: "POST",
							dataType: "text",
							data: {
								method: "moveFileOrDirectory",
								path: getBreadcrumbNameList(breadcrumbList),
								fileName: fileName,
							},
							success: function(data){
								eval("var d = " + data);
								if(d.state == "error"){
									alert(d.result);
									return;
								}
								var treeNodeId = $("div.thumb-wrap[select=true]").attr("treenodeid");
								var node = tree.getNodeByParam("id", treeNodeId);
								tree.removeNode(node);
								$selectThumbWrap.remove();
								if($("div.thumb-wrap").length == 0){
									var $thumb_wrap = $('<div class="thumb-wrap" dirType="empty"><div class="thumb"><img src="/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey_white.png" class="x-thumb-icon"></div><span class="editable">空文件夹</span></div>');
									$("div.view_plugin").append($thumb_wrap);
								}
								alert("删除成功！");
							}
						});
					}
					break;
				case "upload":
					$("#upload").click();
					break;
				default:
					break;
			}
			
			event.stopPropagation();
		});
		
	});
	
	//Click Event
	$("div.thumb-wrap[dirType!=empty]").live("click",function(){
		if(Operation.checkCurrentOperationType()){
			return false;
		}
		if($("span.editable",$(this)).html() == "resolvent"){
			return false;
		}
		$("div.thumb-wrap").attr("select", "false");
		$("div.thumb").css("background-color","#FFFFFF");
		$("div.thumb > img").attr("src", "/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey.png");
		$("div.model").css("background-image", "url(/bpmcenter/fixflow-explorer/images/signavio/icon-model-background.png)");
		
		$(this).attr("select", "true");
		$("div.thumb", $(this)).css("background-color","#10a7d9");
		$("img", $(this)).attr("src", "/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey_selected.png");
		$("div.model", $(this)).css("background-image", "url(/bpmcenter/fixflow-explorer/images/signavio/icon-model-background_selected.png)");
		
		if(!(checkIsResolvent())){
			$("div.toolbar > div.btn-disable").addClass("btn-normal").removeClass("btn-disable");
		}
		
	});
	
	//DblClick Event
	$("div.thumb-wrap[dirType!=empty]").live("dblclick",function(){
		var dirType = $(this).attr("dirType"),
			treeNodeId = $(this).attr("treeNodeId");
		currentTreeNode  = tree.getNodeByParam("id",treeNodeId);
		currentTreeChildrenNodes = tree.getNodesByFilter(function(node){
			return true;
		}, false, currentTreeNode);
		//dir | file | other
		var name = $("span", $(this)).html();
		if(dirType == "dir"){
			breadcrumbList.push({name:name, treeNodeId:treeNodeId});
			createBreadcrumbList(breadcrumbList);
			readSubFileAndDirectory(breadcrumbList);
			$("a.curSelectedNode").removeClass("curSelectedNode");
			if(checkIsResolvent()){
				$(".toolbar > div").removeClass("btn-normal").addClass("btn-disable");
			}else{
				$("#"+currentTreeNode.tId+" >a").addClass("curSelectedNode");
				tree.expandNode(currentTreeNode, true);
				resetToolbarState();
			}
			
		}else if(dirType == "file"){
			if(!openEditorTag){
				var fileType = name.substring(name.lastIndexOf(".")+1);
				if(fileType!="bpmn"){
					alert("该文件不是流程定义文件");
					return false;
				}
				var passObj = {
					path: getBreadcrumbNameList(breadcrumbList),
					fileName: name
				}
				openEditorTag = true;
				window.showModalDialog("/bpmcenter/fixflow-editor/editor/editor.html", passObj, "dialogTop=10px;dialogWidth="+(screen.width-80)+";dialogHeight="+(screen.height-150));
			}else{
				alert("请关闭已打开的Web流程设计器");
			}
		}else{
			
		}
	});
	
	$("#updateCache").click(function(){
		if(Operation.checkCurrentOperationType()){
			return false;
		}
		$.get("FlowManager?action=updateCache",function(msg){
			alert(msg);
		})
	});
	
	$(document).click(function(){
		if(Operation.checkCurrentOperationType()){
			var that = $("input.editName");
			var reg = /[^x00-xff]/ ;
			var b = reg.test(that.val());
			if((currentOperationType=="create" || currentOperationType=="rename") && b){
				alert("文件名不能有中文");
				return;
			}
			Operation[currentOperationType](that);
		}
		
	});
	
});

function updateMyself(){
	var obj = {};
	window.open("FlowCenter?action=getUserInfo&isUpdate=true");
}

function getBreadcrumb(treeNode, breadcrumbList){
	var parentNode = treeNode.getParentNode();
	if(parentNode){
		breadcrumbList.push({name:parentNode.name, treeNodeId:parentNode.id});
		getBreadcrumb(parentNode, breadcrumbList);
	}
	return breadcrumbList;
}

function readSubFileAndDirectory(bcList){
	$.ajax({
		url: "/bpmcenter/FileAndDirectoryServlet",
		type: "POST",
		dataType: "text",
		data: {
			method: "readSubFileAndDirectory",
			path: getBreadcrumbNameList(bcList)
		},
		success: function(data){
			$("div.view_plugin").empty();
			eval("var d = " + data);
			if(d.result.length==0){
				var $thumb_wrap = $('<div class="thumb-wrap" dirType="empty"><div class="thumb"><img src="/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey_white.png" class="x-thumb-icon"></div><span class="editable">空文件夹</span></div>');
				$("div.view_plugin").append($thumb_wrap);
			}else{
				$.each(d.result, function(index, value){
					var $thumb_wrap;
					var treeNodeId;
					if(value.type == "dir"){
						for(var i=0; i<currentTreeChildrenNodes.length; i++){
							if(value.name == currentTreeChildrenNodes[i].name){
								treeNodeId = currentTreeChildrenNodes[i].id;
								break;
							}
						}
						$thumb_wrap = $('<div class="thumb-wrap" dirType="dir" treeNodeId="'+treeNodeId+'"><div class="thumb"><img src="/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes1" class="x-thumb-icon"></div><span class="editable" title="'+value.name+'">'+value.name+'</span></div>');
					}else{
						$thumb_wrap = $('<div class="thumb-wrap" dirType="file"><div class="thumb model"></div><span class="x-editable" title="'+value.name+'">'+value.name+'</span></div>');
					}
					$("div.view_plugin").append($thumb_wrap);
				});
			}
		}
	});
}

function createBreadcrumbList(bcList){
	$("span.details").empty();
	$.each(bcList, function(index, value){
		$("span.details").append($('<span>»</span>'));
		var $a = $('<a href="#" treeNodeId="'+value.treeNodeId+'"> '+value.name+'</a>');
		$a.click(function(){
			var treeNodeId = $(this).attr("treeNodeId");
			currentTreeNode = tree.getNodeByParam("id",treeNodeId);
			$("a.curSelectedNode").removeClass("curSelectedNode");
			$("#"+currentTreeNode.tId+" >a").addClass("curSelectedNode");
			currentTreeChildrenNodes = tree.getNodesByFilter(function(node){
				return true;
			}, false, currentTreeNode);
			breadcrumbList = [];
			breadcrumbList.unshift({name:$.trim($(this).html()), treeNodeId:treeNodeId});
			var breadcrumb_a = $(this).prevAll("a");
			breadcrumb_a.each(function(){
				breadcrumbList.unshift({name:$.trim($(this).html()), treeNodeId:$(this).attr("treeNodeId")});
			});
			createBreadcrumbList(breadcrumbList);
			readSubFileAndDirectory(breadcrumbList);
			if(checkIsResolvent()){
				$(".toolbar > div").removeClass("btn-normal").addClass("btn-disable");
			}else{
				resetToolbarState();
			}
		})
		$("span.details").append($a);
	});
}

function getBreadcrumbNameList(bcList){
	var a = new Array();
	for(var i=0; i<bcList.length; i++){
		a.push(bcList[i].name);
	}
	return a.join(",");
}

function resetToolbarState(){
	var select = false;
	if($(".thumb-wrap[select=true]").length > 0){
		select = true;
	}
	$("div.toolbar > div.listBtn").each(function(){
		if(!select){
			if($(this).attr("btn-scope") == "single"){
				$(this).removeClass("btn-normal").addClass("btn-disable");
			}else{
				$(this).addClass("btn-normal").removeClass("btn-disable");
			}
		}else{
			$(this).addClass("btn-normal").removeClass("btn-disable");
		}
	});
}

function checkIsResolvent(){
	return (breadcrumbList[1] && breadcrumbList[1].name == "resolvent");
}

var Operation = {
	createGuid: "",
	checkCurrentOperationType: function(){
		if(currentOperationType == "create" || currentOperationType == "rename"){
			return true;
		}
		return false;
	},
	create: function(that){
		var name = that.val();
		$.ajax({
			url: "/bpmcenter/FileAndDirectoryServlet",
			type: "POST",
			dataType: "text",
			data: {
				method: "create",
				path: getBreadcrumbNameList(breadcrumbList),
				newFileName: name
			},
			success: function(data){
				eval("var d = " + data);
				if(d.state == "error"){
					alert(d.result);
					that.focus();
					that.select();
					return;
				}
				currentOperationType = "done";
				that.parent("span").replaceWith("<span>"+name+"</span>");
				tree.addNodes(currentTreeNode,{name:name, isParent:true, id:Operation.createGuid});
				resetToolbarState();
			}
		});
	},
	rename: function(that){
		var name = that.val();
		$.ajax({
			url: "/bpmcenter/FileAndDirectoryServlet",
			type: "POST",
			dataType: "text",
			data: {
				method: "reName",
				path: getBreadcrumbNameList(breadcrumbList),
				oldFileName: that.attr("oldValue"),
				newFileName: name
			},
			success: function(data){
				eval("var d = " + data);
				if(d.state == "error"){
					alert(d.result);
					that.focus();
					that.select();
					return;
				}
				that.parent("span").replaceWith("<span>"+name+"</span>");
				$("div[btn-type]").addClass("btn-normal").removeClass("btn-disable");
				$.ajax({
					url: "/bpmcenter/FileAndDirectoryServlet",
					type: "POST",
					dataType: "text",
					data: {
						method: "loadTree"
					},
					success: function(data){
						currentOperationType = "done";
						var treeNodeId = $("div.thumb-wrap[select=true]").attr("treenodeid");
						var node = tree.getNodeByParam("id", treeNodeId);
						node.name = name;
						$("#"+node.tId+" >a>span:last").html(name);
					}
				});
			}
		});
	}
}
