/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.editor;

import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.WorkbenchPartAction;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditorContextMenuProvider;
import org.eclipse.graphiti.ui.internal.action.DeleteAction;
import org.eclipse.graphiti.ui.internal.action.PrintGraphicalViewerAction;
import org.eclipse.graphiti.ui.internal.action.RemoveAction;
import org.eclipse.graphiti.ui.internal.action.UpdateAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolder2Listener;
import org.eclipse.swt.custom.CTabFolderEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;
import org.eclipse.ui.part.MultiPageSelectionProvider;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * This class implements a multi-page version of the BPMN2 Modeler (BPMN2Editor class).
 * To revert back to the original, single-page version simply change the editor extension
 * point in plugin.xml (see comments there).
 * 
 * This is still in the experimental phase and currently only supports a single diagram
 * per .bpmn file. An optional second page, which displays the XML source, can be created
 * from the context menu. The source view is not yet synchronized to the design view and
 * can only show the XML as of the last "Save" i.e. the current state of the file on disk,
 * not the in-memory model. Design/Source view synchronization will be implemented in a
 * future version, but direct editing of the XML will not be supported - it will remain
 * "view only".
 * 
 * Future versions will support multiple diagrams per .bpmn file with the ability to add
 * and remove pages containing different diagram types. It should be possible for the user
 * to create a single file that contains a mix of Process, Collaboration and Choreography
 * diagrams. Whether or not these types of files are actually deployable and/or executable
 * is another story ;)
 */
public class BPMN2MultiPageEditor extends MultiPageEditorPart {

	BPMN2Editor designEditor;
	StructuredTextEditor sourceViewer;
	CTabFolder tabFolder;
	int defaultTabHeight;
	
	/**
	 * 
	 */
	public BPMN2MultiPageEditor() {
		super();
		//注册编辑器切换事件
//		registerEditorSelectChangeListener();
	}

	@Override
	protected IEditorSite createSite(IEditorPart editor) {
		return new MultiPageEditorSite(this, editor) {
			@Override
			protected void handleSelectionChanged(SelectionChangedEvent event) {
				ISelectionProvider parentProvider = getMultiPageEditor().getSite()
						.getSelectionProvider();
				if (parentProvider instanceof MultiPageSelectionProvider) {
					
					// by kenshin 修改了点击保存按钮 属性页报错的问题
					if(event.getSelection().getClass().getSimpleName().equals("StructuredTextSelection")){
						return ;
					}
					try {
						SelectionChangedEvent newEvent = getNewEvent(parentProvider, event);
						MultiPageSelectionProvider prov = (MultiPageSelectionProvider) parentProvider;
						prov.fireSelectionChanged(newEvent);
					} catch (Exception e) {
						return;
					}
				}
			}
			
			@Override
			protected void handlePostSelectionChanged(SelectionChangedEvent event) {
				ISelectionProvider parentProvider = getMultiPageEditor().getSite()
						.getSelectionProvider();
				
				// by kenshin 修改了点击保存按钮 属性页报错的问题
				if(event.getSelection().getClass().getSimpleName().equals("StructuredTextSelection")){
					return ;
				}
				
				try {
					if (parentProvider instanceof MultiPageSelectionProvider) {
						SelectionChangedEvent newEvent = getNewEvent(parentProvider, event);
						MultiPageSelectionProvider prov = (MultiPageSelectionProvider) parentProvider;
						prov.firePostSelectionChanged(newEvent);
					}
				} catch (Exception e) {
					return;
				}
				
				
			}
			
			protected SelectionChangedEvent getNewEvent(ISelectionProvider parentProvider, SelectionChangedEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection ss = (IStructuredSelection)selection;
					Object o = ss.getFirstElement();
					if (o instanceof Node) {
						selection = getNewSelection((Node)o);
					}
				}
				if (selection!=null)
					return new SelectionChangedEvent(parentProvider, selection);
				return event;
			}

			protected StructuredSelection getNewSelection(Node node) {
				int type =  node.getNodeType();
				if (type==1) {
					// node type = element
					PictogramElement pe = null;
					Element elem = (Element)node;
					String value = elem.getAttribute("bpmnElement");
					if (value!=null) {
						pe = findPictogramElement(value);
					}
					
					if (pe==null) {
						value = elem.getAttribute("id");
						if (value!=null)
							pe = findPictogramElement(value);
					}
					
					if (pe!=null) {
						return new StructuredSelection(pe);
					}
					return getNewSelection(node.getParentNode());
				}
				else if (type==2) {
					// node type = attribute
					// search the attribute's owner
					Attr attr = (Attr)node;
					return getNewSelection(attr.getOwnerElement());
				}
				else if (type==3) {
					// node type = text
					return getNewSelection(node.getParentNode());
				}
				return null;
			}
			
			protected PictogramElement findPictogramElement(String id) {
				PictogramElement pictogramElement = null;
				if (id!=null) {
					BaseElement be = designEditor.getModelHandler().findElement(id);
					List<PictogramElement> pes = Graphiti.getLinkService().getPictogramElements(designEditor.getDiagramTypeProvider().getDiagram(), be);
					for (PictogramElement pe : pes) {
						if (pe instanceof ContainerShape) {
							pictogramElement = pe;
						}
						else if (pe instanceof FreeFormConnection) {
							pictogramElement = pe;
						}
					}
				}
				
				return pictogramElement;
			}
		};
	}

	@Override
	public String getTitle() {
		if (designEditor!=null)
			return designEditor.getTitle();
		return super.getTitle();
	}

	@Override
	public String getPartName() {
		if (designEditor!=null)
			return designEditor.getPartName();
		return super.getPartName();
	}

	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex>0 && newPageIndex==tabFolder.getItemCount()-1) {
			// TODO: sync source viewer's DOM with model
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
	 */
	@Override
	protected void createPages() {
		tabFolder = (CTabFolder)getContainer();
		tabFolder.addCTabFolder2Listener( new CTabFolder2Listener() {

			@Override
			public void close(CTabFolderEvent event) {
				if (event.item.getData() == sourceViewer)
					removeSourceViewer();
			}

			@Override
			public void minimize(CTabFolderEvent event) {
			}

			@Override
			public void maximize(CTabFolderEvent event) {
			}

			@Override
			public void restore(CTabFolderEvent event) {
			}

			@Override
			public void showList(CTabFolderEvent event) {
			}
			
		});
		createDesignEditor();
//		createSourceViewer();
	}

	protected void createDesignEditor() {
		if (designEditor==null) {
			designEditor = new DesignEditor();
			
			try {
				int pageIndex = tabFolder.getItemCount();
				if (sourceViewer!=null)
					--pageIndex;
				addPage(pageIndex, designEditor, BPMN2MultiPageEditor.this.getEditorInput());
				defaultTabHeight = tabFolder.getTabHeight();
				setPageText(pageIndex,"设计");

				// TODO: it should be possible to create additional instances of the BPMN2Editor
				// that use the same IEditorInput as the original, but work within different
				// BPMNPlane objects within the same model.
				// Likewise, it should be possible to remove a page, which causes the associated
				// BPMNPlane to be removed from the model. The last page may not be removed because
				// this would invalidate the bpmn file.
//				++pageIndex;
//				DesignEditor designEditor2 = new DesignEditor();
//				addPage(pageIndex, designEditor2, BPMN2MultiPageEditor.this.getEditorInput());
//				setPageText(pageIndex,"Design 2");
				
				defaultTabHeight = tabFolder.getTabHeight();
				
				updateTabs();
				//默认显示源代码
				createSourceViewer();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void createSourceViewer() {
		if (sourceViewer==null) {
			sourceViewer = new SourceViewer();

			try {
				int pageIndex = tabFolder.getItemCount();
				FileEditorInput input = new FileEditorInput(designEditor.getModelFile());
				addPage(pageIndex, sourceViewer, input);
				tabFolder.getItem(pageIndex).setShowClose(true);
				
				setPageText(pageIndex,"源文件");
				updateTabs();
			}
			catch (Exception e) {
				e.printStackTrace();
				if (sourceViewer!=null)
					sourceViewer.dispose();
			}
		}
	}

	@Override
	public void removePage(int pageIndex) {
		Object page = tabFolder.getItem(pageIndex).getData();
		if (page instanceof EditorPart) {
			// make sure the editor gets disposed - neither CTabFolder nor super does this for us!
			((EditorPart)page).dispose();
		}
		super.removePage(pageIndex);
		updateTabs();
	}

	public void removeSourceViewer() {
		// there will only be one source page and it will always be the last page in the tab folder
		if (sourceViewer!=null) {
			int pageIndex = tabFolder.getItemCount() - 1;
			if (pageIndex>0)
				removePage(pageIndex);
		}
	}

	private void updateTabs() {
		if (tabFolder.getItemCount()==1) {
			tabFolder.setTabHeight(0);
		}
		else
			tabFolder.setTabHeight(defaultTabHeight);
		tabFolder.layout();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		designEditor.doSave(monitor);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	public class DesignEditor extends BPMN2Editor {
		
		@Override
		protected void createActions() {
			super.createActions();
			ActionRegistry registry = getActionRegistry();
			IAction action = new WorkbenchPartAction(designEditor) {

				@Override
				protected void init() {
					super.init();
					setId("show.or.hide.source.view");
				}

				@Override
				public String getText() {
					return sourceViewer==null ? "显示源文件" : "隐藏源文件";
				}

				@Override
				protected boolean calculateEnabled() {
					return true;
				}
				
				public void run() {
					if (sourceViewer==null) {
						createSourceViewer();
						setActivePage(tabFolder.getItemCount()-1);
					}
					else {
						removeSourceViewer();
					}
				}
			};
			registry.registerAction(action);
		}

		@Override
		protected ContextMenuProvider createContextMenuProvider() {
			return new DiagramEditorContextMenuProvider(getGraphicalViewer(), getActionRegistry(), getConfigurationProvider()) {
				@Override
				public void buildContextMenu(IMenuManager manager) {
					
					//汉化 2012-6-4 wy
					IAction exportaction = getActionRegistry().getAction("export_diagram_action");
					if(exportaction!=null) {
						getActionRegistry().removeAction(exportaction);
						
						exportaction.setToolTipText("导出图形...");
						exportaction.setText("导出图形...");
						
						getActionRegistry().registerAction(exportaction);
					}
					
					IAction copyaction = getActionRegistry().getAction("copy");
					if(copyaction!=null) {
						getActionRegistry().removeAction(copyaction);
						
						copyaction.setText("复制");
						
						getActionRegistry().registerAction(copyaction);
					}
					
					IAction pasteaction = getActionRegistry().getAction("paste");
					if(pasteaction!=null) {
						getActionRegistry().removeAction(pasteaction);
						
						pasteaction.setText("粘贴");
						
						getActionRegistry().registerAction(pasteaction);
					}
					
					
					IAction updateaction = getActionRegistry().getAction("predefined update action");
					if(updateaction!=null) {
						getActionRegistry().removeAction(updateaction);
						
						updateaction.setText("更新");
						
						getActionRegistry().registerAction(updateaction);
					}
					
					IAction undoaction = getActionRegistry().getAction("undo");
					if(undoaction!=null) {
						getActionRegistry().removeAction(undoaction);
						
						undoaction.setToolTipText("撤销更新");
						undoaction.setText("撤销更新");
						
						getActionRegistry().registerAction(undoaction);
					}
					
					IAction redoaction = getActionRegistry().getAction("redo");
					if(redoaction!=null) {
						getActionRegistry().removeAction(redoaction);
						
						redoaction.setToolTipText("重做");
						redoaction.setText("重做");
						
						getActionRegistry().registerAction(redoaction);
					}
					
					
					super.buildContextMenu(manager);

					
					/*
					GEFActionConstants.addStandardActionGroups(manager);
					addDefaultMenuGroupUndo(manager);
					addDefaultMenuGroupSave(manager);
					addDefaultMenuGroupEdit(manager);
					addDefaultMenuGroupPrint(manager);
					addDefaultMenuGroupRest(manager);
					
					
					IAction actionDelete = getActionRegistry().getAction(ActionFactory.DELETE.getId());
					actionDelete.setText("删除");
					
					IAction actionUpdate = getActionRegistry().getAction("predefined update action");
					actionUpdate.setText("更新");
					
					//IAction actionRedo = getActionRegistry().getAction("redo");
					//actionRedo.setText("撤销");
					IAction actionPrint = getActionRegistry().getAction(ActionFactory.PRINT.getId());
					actionPrint.setText("打印");
					
					
					
					IAction actionUNDO = getActionRegistry().getAction(ActionFactory.UNDO.getId());
					actionUNDO.setText("导出");
					
					IAction actionRemove = getActionRegistry().getAction("predefined remove action");
					if(actionRemove!=null){
						getActionRegistry().removeAction(actionRemove);
					}
					//actionRemove.setText("移除");
					
					
					addActionToMenuIfAvailable(manager, UpdateAction.ACTION_ID, GEFActionConstants.GROUP_REST);
		addActionToMenuIfAvailable(manager, RemoveAction.ACTION_ID, GEFActionConstants.GROUP_REST);
		addActionToMenuIfAvailable(manager, DeleteAction.ACTION_ID, GEFActionConstants.GROUP_REST);
				
				IAction action = getActionRegistry().getAction("show.or.hide.source.view");
				action.setText( action.getText() );
				manager.add(action);*/
					IAction actionDelete = getActionRegistry().getAction(DeleteAction.ACTION_ID);
					if(actionDelete!=null){
						actionDelete.setText("删除");
					}
					
					IAction actionUpdate = getActionRegistry().getAction(UpdateAction.ACTION_ID);
					if(actionUpdate!=null){
					actionUpdate.setText("更新");
					}
					
					IAction actionRemove = getActionRegistry().getAction(RemoveAction.ACTION_ID);
					if(actionRemove!=null){
						actionRemove.setText("移除");
						getActionRegistry().removeAction(actionRemove);
					}
				
					IAction actionPrint = getActionRegistry().getAction(ActionFactory.PRINT.getId());
					if(actionPrint!=null){
						actionPrint.setText("打印");
					}
					IAction action = getActionRegistry().getAction("show.or.hide.source.view");
					action.setText( action.getText() );
					manager.add(action);
				}
			};
		}
	}

	public class SourceViewer extends StructuredTextEditor {
		
		ActionRegistry actionRegistry = null;
		
		@Override
		@SuppressWarnings("rawtypes")
		public Object getAdapter(Class required) {
			if (required==ActionRegistry.class)
				return getActionRegistry();
			if (required==BPMN2Editor.class)
				return designEditor;
			return super.getAdapter(required);
		}

		@Override
		public boolean isEditable() {
			return false;
		}

		@Override
		public void dispose() {
			super.dispose();
			BPMN2MultiPageEditor.this.sourceViewer = null;
		}

		protected ActionRegistry getActionRegistry() {
			if (actionRegistry == null)
				actionRegistry = new ActionRegistry();
			return actionRegistry;
		}
	}
	
	/**
	  * registerEditorSelectChangeListener(注册编辑器切换事件)
	  * @Title: registerEditorSelectChangeListener
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void registerEditorSelectChangeListener() {
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			workbenchPage.addSelectionListener(new ISelectionListener() {
				
				@Override
				public void selectionChanged(IWorkbenchPart part, ISelection selection) {
					// 根据当前的编辑器定位树节点
//					StudioInterface.linkTreeElement();
					
					if(part instanceof BPMN2MultiPageEditor) {
						//打开流程透视图
						try {
							PlatformUI.getWorkbench().showPerspective("com.founder.fix.fixflow.designer.FixFlowPerspective", 
								       PlatformUI.getWorkbench().getActiveWorkbenchWindow());
						} catch (WorkbenchException e) {
							e.printStackTrace();
						}
					} 
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
