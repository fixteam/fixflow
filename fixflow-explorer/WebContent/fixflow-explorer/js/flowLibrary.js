var breadcrumbList = new Array();
var currentTreeNode;
var currentTreeChildrenNodes;
var currentOperationType;
var zTreeSetting = {
	data: { simpleData: { enable: true}},
	callback: {
		beforeClick: function(){
			$("a.curSelectedNode").removeClass("curSelectedNode");
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
				return;
			}
			var $selectTarget = $("div.thumb-wrap[select=true]");
			switch($(this).attr("btn-type")){
				case "createFile":
					currentOperationType = "createFile";
					$("div.thumb-wrap[dirType=empty]").remove();
					var guid = FixFlow.Utils.createGuid();
					var $newFile = $('<div class="thumb-wrap" dirType="file"><div class="thumb model"></div></div>');
					$newFile.appendTo($("div.view_plugin"));
					var $newFileName = $('<span class="editable"><input type="text" class="editName" style="width:90px;" oldValue="未命名"/></span>').appendTo($newFile);
					var $input = $("input", $newFileName);
					$input.focus();
					$input.keydown(function(event){
						if(event.keyCode == 13){
							var name = $(this).val();
							$.ajax({
								url: "/bpmcenter/FlowWebManagerServlet",
								type: "POST",
								dataType: "text",
								data: {
									method: "createBPMNFile",
									path: getBreadcrumbNameList(breadcrumbList),
									name: "abc",
									id:name+".bpmn"
								},
								success: function(data){
									/*eval("var d = " + data);
									if(d.state == "error"){
										alert("文件夹重名！");
										$input.focus();
										$input.select();
										return;
									}*/
									$input.parent("span").replaceWith("<span>"+name+".bpmn</span>");
									//tree.addNodes(currentTreeNode,{name:name, isParent:true, id:guid});
								}
							});
						};
					});
					break;
				case "createFolder":
					currentOperationType = "create";
					$("div.thumb-wrap[dirType=empty]").remove();
					var guid = FixFlow.Utils.createGuid();
					var $newFolder = $('<div class="thumb-wrap" dirType="dir" treeNodeId="'+guid+'"><div class="thumb"><img src="/bpmcenter/fixflow-explorer/images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes1" class="x-thumb-icon"></div></div>');
					$newFolder.appendTo($("div.view_plugin"));
					var $newFolderName = $('<span class="editable"><input type="text" class="editName" style="width:90px;" oldValue="未命名"/></span>').appendTo($newFolder);
					var $input = $("input", $newFolderName);
					$input.focus();
					$input.keydown(function(event){
						if(event.keyCode == 13){
							var name = $(this).val();
							//breadcrumbList.push(name);
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
										alert("文件夹重名！");
										$input.focus();
										$input.select();
										return;
									}
									$input.parent("span").replaceWith("<span>"+name+"</span>");
									tree.addNodes(currentTreeNode,{name:name, isParent:true, id:guid});
								}
							});
						};
					});
					break;
					
				case "rename":
					currentOperationType = "rename";
					var $selectThumbWrap = $("div.thumb-wrap[select=true]");
					var $span = $("span", $selectThumbWrap);
					var $editInput = $("<input class='editName' type='text' style='width:90px;' oldValue='"+$span.html()+"' value='"+$span.html()+"'/>");
					$span.empty().append($editInput);
					$editInput.focus();
					$editInput.click(function(){
						return false;
					})
					$editInput.keydown(function(event){
						if(event.keyCode == 13){
							var name = $(this).val();
							$.ajax({
								url: "/bpmcenter/FileAndDirectoryServlet",
								type: "POST",
								dataType: "text",
								data: {
									method: "reName",
									path: getBreadcrumbNameList(breadcrumbList),
									oldFileName: $(this).attr("oldValue"),
									newFileName: name
								},
								success: function(data){
									$editInput.parent("span").replaceWith("<span>"+name+"</span>");
									$.ajax({
										url: "/bpmcenter/FileAndDirectoryServlet",
										type: "POST",
										dataType: "text",
										data: {
											method: "loadTree"
										},
										success: function(data){
											var treeNodeId = $("div.thumb-wrap[select=true]").attr("treenodeid");
											var node = tree.getNodeByParam("id", treeNodeId);
											node.name = name;
											$("#"+node.tId+" >a>span:last").html(name);
										}
									});
								}
							});
						};
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
			var fileType = name.substring(name.lastIndexOf(".")+1);
			if(fileType!="bpmn"){
				alert("该文件不是流程定义文件");
				return false;
			}
			var passObj = {
				path: getBreadcrumbNameList(breadcrumbList),
				fileName: name
			}
			window.showModalDialog("/bpmcenter/fixflow-editor/editor/editor.html", passObj, "dialogTop=10px;dialogWidth="+(screen.width-80)+";dialogHeight="+(screen.height-150));
		}else{
			
		}
	});
	
	$("#updateCache").click(function(){
		$.get("FlowManager?action=updateCache",function(msg){
			alert(msg);
		})
	});
	
	$(document).click(function(){
		var newName = "";
		if($("input.editName").length>0){
			if($("input.editName").val() == ""){
				newName = $("input.editName").attr("oldValue");
			}else{
				newName = $("input.editName").val();
			}
			var guid = $("input.editName").attr("id");
			if(currentOperationType == "create"){
				$.ajax({
					url: "/bpmcenter/FileAndDirectoryServlet",
					type: "POST",
					dataType: "text",
					data: {
						method: "create",
						path: getBreadcrumbNameList(breadcrumbList),
						newFileName: newName
					},
					success: function(data){
						eval("var d = " + data);
						if(d.state == "error"){
							alert("文件夹重名！");
							$("input.editName").focus();
							$("input.editName").select();
							return;
						}
						$("input.editName").parent("span").replaceWith("<span title="+newName+">"+newName+"</span>");
						tree.addNodes(currentTreeNode,{name:newName, isParent:true, id:guid});
					}
				});
			}else if(currentOperationType == "createFile"){
				$.ajax({
					url: "/bpmcenter/FlowWebManagerServlet",
					type: "POST",
					dataType: "text",
					data: {
						method: "createBPMNFile",
						path: getBreadcrumbNameList(breadcrumbList),
						name: "abc",
						id:newName+".bpmn"
					},
					success: function(data){
						/*eval("var d = " + data);
						if(d.state == "error"){
							alert("文件夹重名！");
							$input.focus();
							$input.select();
							return;
						}*/
						$input.parent("span").replaceWith("<span>"+name+".bpmn</span>");
						//tree.addNodes(currentTreeNode,{name:name, isParent:true, id:guid});
					}
				});
			}else if(currentOperationType == "rename"){
				var name = $("input.editName").val();
				$.ajax({
					url: "/bpmcenter/FileAndDirectoryServlet",
					type: "POST",
					dataType: "text",
					data: {
						method: "reName",
						path: getBreadcrumbNameList(breadcrumbList),
						oldFileName: $("input.editName").attr("oldValue"),
						newFileName: name
					},
					success: function(data){
						$("input.editName").parent("span").replaceWith("<span title="+name+">"+name+"</span>");
						$.ajax({
							url: "/bpmcenter/FileAndDirectoryServlet",
							type: "POST",
							dataType: "text",
							data: {
								method: "loadTree"
							},
							success: function(data){
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
	$("div.toolbar > div.listBtn").each(function(){
		if($(this).attr("btn-scope") == "single"){
			$(this).removeClass("btn-normal").addClass("btn-disable");
		}else{
			$(this).addClass("btn-normal").removeClass("btn-disable");
		}
	})
}

function checkIsResolvent(){
	return (breadcrumbList[1] && breadcrumbList[1].name == "resolvent");
}
