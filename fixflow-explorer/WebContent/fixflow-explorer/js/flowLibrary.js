var breadcrumbList = new Array();
var currentTreeNode;
var zTreeSetting = {
	data: { simpleData: { enable: true}},
	callback: {
		onClick: function(event, treeId, treeNode){
			currentTreeNode = treeNode;
			breadcrumbList = new Array();
			breadcrumbList.push(treeNode.name);
			getBreadcrumb(treeNode, breadcrumbList);
			breadcrumbList.reverse();
			$("span.details").empty();
			$.each(breadcrumbList, function(index, value){
				$("span.details").append($('<span>»</span>'));
				$("span.details").append($('<a rel="/directory/09a3c14509a44992a287ec193ebd06b9"> '+value+'</a>'));
			});
			$.ajax({
				url: "/bpmcenter/FileAndDirectoryServlet",
				type: "POST",
				dataType: "text",
				data: {
					method: "readSubFileAndDirectory",
					userId: "1",
					path: breadcrumbList.join(",")
				},
				success: function(data){
					$("div.view_plugin").empty();
					eval("var d = " + data);
					if(d.result.length==0){
						var $thumb_wrap = $('<div class="thumb-wrap" dirType="empty"><div class="thumb"><img src="images/nuvola/64x64/filesystems/folder_grey_white.png" title="End-to-End processes1" class="x-thumb-icon"></div><span class="editable">空文件夹</span></div>');
						$("div.view_plugin").append($thumb_wrap);
					}else{
						$.each(d.result, function(index, value){
							var $thumb_wrap;
							if(value.type == "dir"){
								$thumb_wrap = $('<div class="thumb-wrap" dirType="dir"><div class="thumb"><img src="images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes1" class="x-thumb-icon"></div><span class="editable">'+value.name+'</span></div>');
							}else{
								$thumb_wrap = $('<div class="thumb-wrap" dirType="file"><div class="thumb model"></div><span class="x-editable" title="">'+value.name+'</span></div>');
							}
							$("div.view_plugin").append($thumb_wrap);
						});
					}
				}
			});
		}
	}
};
var tree;
$(document).ready(function(){
	$.ajax({
		url: "/bpmcenter/FileAndDirectoryServlet",
		type: "POST",
		dataType: "text",
		data: {
			method: "loadTree",
			userId: "1"
		},
		success: function(data){
			eval("var d = " + data);
			tree = $.fn.zTree.init($("div.left>ul.ztree"), zTreeSetting, d.result);
			$("#folderTree_1_a").click();
		}
	});
	
	$("div.toolbar > div.listBtn").each(function(){
		if($(this).attr("btn-scope") == "single"){
			$(this).removeClass("btn-normal").addClass("btn-disable");
		}
		$(this).click(function(event){
			if($(this).hasClass("btn-disable")){
				return;
			}
			var $selectTarget = $("div.thumb-wrap[select=true]");
			switch($(this).attr("btn-type")){
				case "createFolder":
					$("div.thumb-wrap[dirType=empty]").remove();
					var $newFolder = $('<div class="thumb-wrap" dirType="dir"><div class="thumb"><img src="images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes1" class="x-thumb-icon"></div></div>');
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
									userId: "1",
									path: breadcrumbList.join(","),
									newFileName: name
								},
								success: function(data){
									$input.parent("span").replaceWith("<span>"+name+"</span>");
									tree.addNodes(currentTreeNode,{name:name, isParent:true});
								}
							});
						};
					});
					break;
					
				case "rename":
					var $selectThumbWrap = $("div.thumb-wrap[select=true]");
					var $span = $("span", $selectThumbWrap);
					var $editInput = $("<input class='editName' type='text' style='width:90px;' oldValue='"+$span.html()+"' value='"+$span.html()+"'/>");
					$span.empty().append($editInput);
					$editInput.focus();
					$editInput.keydown(function(event){
						if(event.keyCode == 13){
							var name = $(this).val();
							$.ajax({
								url: "/bpmcenter/FileAndDirectoryServlet",
								type: "POST",
								dataType: "text",
								data: {
									method: "reName",
									userId: "1",
									path: breadcrumbList.join(","),
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
											method: "loadTree",
											userId: "1"
										},
										success: function(data){
											eval("var d = " + data);
											tree = $.fn.zTree.init($("div.left>ul.ztree"), zTreeSetting, d.result);
											var node = tree.getNodeByTId(currentTreeNode.tId);
											tree.expandNode(node);
											
										}
									});
								}
							});
						};
					});
					break;
					
				case "delete":
					
					break;
				default:
					break;
			}
			
			event.stopPropagation();
		});
		
	});
	
	//Click Event
	$("div.thumb-wrap[dirType!=empty]").live("click",function(){
		
		$("div.thumb-wrap").attr("select", "false");
		$("div.thumb").css("background-color","#FFFFFF");
		$("div.thumb > img").attr("src", "images/nuvola/64x64/filesystems/folder_grey.png");
		$("div.model").css("background-image", "url(images/signavio/icon-model-background.png)");
		
		$(this).attr("select", "true");
		$("div.thumb", $(this)).css("background-color","#10a7d9");
		$("img", $(this)).attr("src", "images/nuvola/64x64/filesystems/folder_grey_selected.png");
		$("div.model", $(this)).css("background-image", "url(images/signavio/icon-model-background_selected.png)");
		
		$("div.toolbar > div.btn-disable").addClass("btn-normal").removeClass("btn-disable");
	});
	
	//DblClick Event
	$("div.thumb-wrap[dirType!=empty]").live("dblclick",function(){
		var dirType = $(this).attr("dirType"),
			treeNodeId = $(this).attr("treeNodeId");
		//dir | file | other
		if(dirType == "dir"){
			var name = $("span", $(this)).html();
			breadcrumbList.push(name);
			$("span.details").empty();
			$.each(breadcrumbList, function(index, value){
				$("span.details").append($('<span>»</span>'));
				$("span.details").append($('<a rel="/directory/09a3c14509a44992a287ec193ebd06b9"> '+value+'</a>'));
			});
			$.ajax({
				url: "/bpmcenter/FileAndDirectoryServlet",
				type: "POST",
				dataType: "text",
				data: {
					method: "readSubFileAndDirectory",
					userId: "1",
					path: breadcrumbList.join(",")
				},
				success: function(data){
					$("div.view_plugin").empty();
					eval("var d = " + data);
					if(d.result.length==0){
						var $thumb_wrap = $('<div class="thumb-wrap" dirType="empty"><div class="thumb"><img src="images/nuvola/64x64/filesystems/folder_grey_white.png" title="End-to-End processes1" class="x-thumb-icon"></div><span class="editable">空文件夹</span></div>');
						$("div.view_plugin").append($thumb_wrap);
					}else{
						$.each(d.result, function(index, value){
							var $thumb_wrap;
							if(value.type == "dir"){
								$thumb_wrap = $('<div class="thumb-wrap" dirType="dir"><div class="thumb"><img src="images/nuvola/64x64/filesystems/folder_grey.png" title="End-to-End processes1" class="x-thumb-icon"></div><span class="editable">'+value.name+'</span></div>');
							}else{
								$thumb_wrap = $('<div class="thumb-wrap" dirType="file"><div class="thumb model"></div><span class="x-editable" title="">'+value.name+'</span></div>');
							}
							$("div.view_plugin").append($thumb_wrap);
						});
					}
				}
			});
		}else if(dirType == "file"){
			window.open("http://127.0.0.1:8080/bpmcenter/fixflow-editor/editor/editor.html");
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
			//var oldValue = $("input.editName").attr("oldValue");
			$("input.editName").parent("span").replaceWith($('<span class="editable">'+newName+'</span>'));
			tree.addNodes(currentTreeNode,{name:newName, isParent:true});
			$.ajax({
				url: "/bpmcenter/FileAndDirectoryServlet",
				type: "POST",
				dataType: "text",
				data: {
					method: "create",
					userId: "1",
					path: breadcrumbList.join(","),
					newFileName: newName
				},
				success: function(data){
					
				}
			});
		}
	});
	
});

function updateMyself(){
	var obj = {};
	window.open("FlowCenter?action=getUserInfo&isUpdate=true");
}

function openDialog(title){
	var $dialogDiv = $("<div class='dialog'><div class='up'>"+title+"</div><div class='down'>文件名：<input type='text'/></div></div>")
	$dialogDiv.appendTo("div.right");
}

function getBreadcrumb(treeNode, breadcrumbList){
	var parentNode = treeNode.getParentNode();
	if(parentNode){
		breadcrumbList.push(parentNode.name);
		getBreadcrumb(parentNode, breadcrumbList);
	}
	return breadcrumbList;
}