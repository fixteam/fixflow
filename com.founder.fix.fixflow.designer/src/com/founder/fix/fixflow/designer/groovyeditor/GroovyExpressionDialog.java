package com.founder.fix.fixflow.designer.groovyeditor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.impl.ProcessImpl;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;

import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableDef;
import com.founder.fix.bpmn2extensions.variableconfig.Type;
import com.founder.fix.fixflow.designer.modeler.ui.common.DataVarTo;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree.ITreeElement;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;
import com.founder.fix.fixflow.designer.util.DataVarUtil;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.ImageUtil;

public class GroovyExpressionDialog extends Dialog {
	private Text searchtext;
	private Text doctext;
//	private Text text;
	private TreeViewer datavartreeViewer;
	private java.util.List<DataVarTo> dataVarTos;
	private Text datavarname;
	protected ExpressionTo expressionTo;
	private TreeViewer datatreetreeViewer;
	private TreeViewer functreeViewer;
	private FixGroovyEditor editor;
	private IEditorSite site;
	private ISelectionProvider diagramEditorSelectionProvider;
	private IWorkbench workbench;
	private IHandlerActivation fHandlerActivation;
	private ProjectionViewer viewer;
	private IDocument document;
	private IEditorInput input;
	private Button btnCheckButton;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public GroovyExpressionDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		initVar();
	}

	/**
	 * 修改时构造函数
	 * 
	 * @param parentShell
	 * @param dataVarTo
	 */
	public GroovyExpressionDialog(Shell parentShell, ExpressionTo expressionTo) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.PRIMARY_MODAL);
		this.expressionTo = expressionTo;
		initVar();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(container, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginRight = 10;
		gl_composite.marginLeft = 10;
		gl_composite.marginHeight = 10;
		composite.setLayout(gl_composite);
		composite.setBounds(0, 0, 64, 64);

		Composite topcomposite = new Composite(composite, SWT.NONE);
		topcomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1));
		topcomposite.setBounds(0, 0, 519, 20);
		GridLayout gl_topcomposite = new GridLayout(3, false);
		gl_topcomposite.verticalSpacing = 0;
		gl_topcomposite.marginWidth = 0;
		gl_topcomposite.horizontalSpacing = 0;
		gl_topcomposite.marginHeight = 0;
		topcomposite.setLayout(gl_topcomposite);

		Label fixflowvarLabel = new Label(topcomposite, SWT.NONE);
		fixflowvarLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		fixflowvarLabel.setText("显示名称：");

		datavarname = new Text(topcomposite, SWT.BORDER);
		GridData gd_datavarname = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_datavarname.widthHint = 197;
		datavarname.setLayoutData(gd_datavarname);

		Composite composite_1 = new Composite(topcomposite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 1, 1));
		composite_1.setLayout(new GridLayout(1, false));

		btnCheckButton = new Button(composite_1, SWT.CHECK);
		btnCheckButton.setSize(105, 17);
		btnCheckButton.setSelection(true);
		btnCheckButton.setText("同步表达式名称");

		Composite operatorcomposite = new Composite(composite, SWT.NONE);
		operatorcomposite.setLayout(new GridLayout(4, false));
		GridData gd_operatorcomposite = new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1);
		gd_operatorcomposite.widthHint = 452;
		operatorcomposite.setLayoutData(gd_operatorcomposite);
		if (expressionTo != null && expressionTo.getName() != null) {
			datavarname.setText(expressionTo.getName());
		}
		new Label(composite, SWT.NONE);

		Composite editorcomposite = new Composite(composite, SWT.NONE);
		FillLayout fl_editorcomposite = new FillLayout(SWT.HORIZONTAL);
		editorcomposite.setLayout(fl_editorcomposite);
		editorcomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true, 1, 1));
		
		// GroovyEditor备用

				workbench = PlatformUI.getWorkbench();
				
				IPreferenceStore store = new ChainedPreferenceStore(
						new IPreferenceStore[] {
								PreferenceConstants.getPreferenceStore(),
								EditorsUI.getPreferenceStore() });
				
				site = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
						.getActivePage().getActiveEditor().getEditorSite();
				
				diagramEditorSelectionProvider = site.getSelectionProvider();
				
				
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("fixflow-expand");
				File file = new File("/src/com/founder/fix/fixflow/expand/config/GroovyFile.groovy");
				IFile ifile = project.getFile(file.toString());
				
				
				
				editor = new FixGroovyEditor(store);
				try {
					
					input = new FileEditorInput(ifile);
					editor.getDocumentProvider().connect(input);
					document = editor.getDocumentProvider().getDocument(input);
//					input = new StringInput("", "groovy");
					editor.init(site, input);
					
					editor.createPartControl(editorcomposite);
					editor.createJavaSourceViewerConfiguration();
					
					if(editor.getGroovyCompilationUnit() != null){
						FixGroovyUtil.addToVariableScope(editor.getGroovyCompilationUnit(), null);
					}
					
				} catch (PartInitException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				viewer = (ProjectionViewer) editor.getViewer() ;

				// Set up content assist in the viewer
				IHandler handler = new AbstractHandler() {
					
					@Override
					public Object execute(ExecutionEvent event) throws ExecutionException {
						// TODO Auto-generated method stub
						if (viewer.canDoOperation(ISourceViewer.CONTENTASSIST_PROPOSALS)){
							viewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
						}
						return null;
					}
				};
				
				IHandlerService handlerService = (IHandlerService) workbench.getAdapter(IHandlerService.class);
				fHandlerActivation = handlerService.activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, handler);

		/*text = new Text(editorcomposite, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.CANCEL);*/

		if (expressionTo != null && expressionTo.getExpressionText() != null)
			document.set(expressionTo.getExpressionText());


		Composite varcomposite = new Composite(composite, SWT.NONE);
		GridLayout gl_varcomposite = new GridLayout(1, false);
		gl_varcomposite.marginLeft = 15;
		gl_varcomposite.horizontalSpacing = 0;
		gl_varcomposite.marginWidth = 0;
		gl_varcomposite.verticalSpacing = 0;
		gl_varcomposite.marginHeight = 0;
		varcomposite.setLayout(gl_varcomposite);
		varcomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true,
				1, 1));

		datavartreeViewer = new TreeViewer(varcomposite, SWT.BORDER);
		Tree datavartree = datavartreeViewer.getTree();
		GridData gd_datavartree = new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1);
		gd_datavartree.widthHint = 180;
		datavartree.setLayoutData(gd_datavartree);
		datavartreeViewer.setContentProvider(new TreeContentProvider());
		datavartreeViewer.setLabelProvider(new ViewerLabelProvider());
		datavartreeViewer.setInput(dataVarTos);
		// 展开所有节点
		// datavartreeViewer.expandAll();
		datavartreeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) datavartreeViewer
						.getSelection();
				ITreeElement selectElement = (ITreeElement) selection
						.getFirstElement();

				// 这个地方如果数据变量就叫 "数据变量" 的话 会出现选不中的问题!
				// 如果表达式就是为null的话 他会出问题 这个地方要改

				if (((DataVarTo) selectElement).getCantbechoose() == null) {
			/*		if (text.getText() == null)
						text.setText("");
					StringBuffer sb = new StringBuffer();
					int offset = text.getCaretPosition();
					String before = text.getText().substring(0, offset);
					if (offset == text.getText().length()) {
						sb.append(text.getText());
						sb.append(((DataVarTo) selectElement).getValue());
					} else {
						String after = text.getText().substring(offset,
								text.getText().length());
						sb.append(before);
						sb.append(((DataVarTo) selectElement).getValue());
						sb.append(after);
					}

					text.setText(sb.toString());

					DataVarTo dataVarTo = (DataVarTo) selectElement;
					if (dataVarTo.getValue() != null) {
						text.setSelection(offset
								+ ((DataVarTo) selectElement).getValue()
										.length(), offset
								+ ((DataVarTo) selectElement).getValue()
										.length());
					}*/
					StyledText control = viewer.getTextWidget();
					try {
						if (document.get().length() == 0)
							document.set("");
						int offset = control.getCaretOffset();
						String before = document.get(0, offset);
						
						if (offset == document.get().length()) {
							document.set(before + ((DataVarTo) selectElement).getValue());
						} else {
							String after = document.get().substring(offset,document.get().length());
							document.set(before + ((DataVarTo) selectElement).getValue() + after);
						}
	
//						text.setText(sb.toString());
						control.setCaretOffset(offset+((DataVarTo) selectElement).getValue().length());
						control.setFocus();
	
						/*DataVarTo dataVarTo = (DataVarTo) selectElement;
						if (dataVarTo.getValue() != null) {
							text.setSelection(offset + ((DataVarTo) selectElement).getValue().length(), offset + ((DataVarTo) selectElement).getValue().length());
						}*/
					
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Composite othercomposite = new Composite(composite, SWT.NONE);
		othercomposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 2, 1));
		GridLayout gl_othercomposite = new GridLayout(3, false);
		gl_othercomposite.horizontalSpacing = 15;
		gl_othercomposite.marginTop = 5;
		gl_othercomposite.marginWidth = 0;
		gl_othercomposite.marginHeight = 0;
		othercomposite.setLayout(gl_othercomposite);

		Label catgroyLabel = new Label(othercomposite, SWT.NONE);
		catgroyLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		catgroyLabel.setText("\u5206\u7C7B\uFF1A");

		Label varLabel = new Label(othercomposite, SWT.NONE);
		varLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		varLabel.setText("\u51FD\u6570\uFF1A");

		Label docLabel = new Label(othercomposite, SWT.NONE);
		docLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		docLabel.setText("\u6587\u6863\uFF1A");

		datatreetreeViewer = new TreeViewer(othercomposite, SWT.BORDER);
		Tree datatree = datatreetreeViewer.getTree();
		GridData gd_datatree = new GridData(SWT.FILL, SWT.FILL, false, false,
				1, 2);
		gd_datatree.widthHint = 120;
		datatree.setLayoutData(gd_datatree);
		datatreetreeViewer.setContentProvider(new TreeContentProvider1());
		datatreetreeViewer.setLabelProvider(new ViewerLabelProvider1());
		datatreetreeViewer.setInput(DataVarUtil.getFixFlowDataVariableConfig()
				.getDataVariableType().getType());
		datatreetreeViewer.getTree().addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				IStructuredSelection iStructuredSelection = (IStructuredSelection) datatreetreeViewer
						.getSelection();
				if (iStructuredSelection.getFirstElement() != null) {
					Type type = (Type) iStructuredSelection.getFirstElement();
					if (DataVarUtil.getfixFlowDataVariableByDataType(type
							.getId()) != null) {
						functreeViewer.setInput(DataVarUtil
								.getfixFlowDataVariableByDataType(type.getId())
								.getDataVariableDef());
					} else {
						functreeViewer.setInput(null);
					}

					functreeViewer.refresh();
				}

				doctext.setText("");
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		searchtext = new Text(othercomposite, SWT.BORDER | SWT.SEARCH
				| SWT.ICON_SEARCH | SWT.ICON_CANCEL);
		searchtext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		doctext = new Text(othercomposite, SWT.BORDER | SWT.READ_ONLY
				| SWT.WRAP | SWT.MULTI);
		GridData gd_doctext = new GridData(SWT.FILL, SWT.FILL, false, false, 1,
				2);
		gd_doctext.widthHint = 192;
		doctext.setLayoutData(gd_doctext);

		functreeViewer = new TreeViewer(othercomposite, SWT.BORDER);
		Tree functree = functreeViewer.getTree();
		functree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		functreeViewer.setContentProvider(new TreeContentProvider1());
		functreeViewer.setLabelProvider(new ViewerLabelProvider2());
		functreeViewer.getTree().addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				IStructuredSelection iStructuredSelection = (IStructuredSelection) functreeViewer
						.getSelection();
				if (iStructuredSelection.getFirstElement() != null) {
					DataVariableDef dataVariableDef = (DataVariableDef) iStructuredSelection
							.getFirstElement();
					doctext.setText(dataVariableDef.getDoc() == null ? ""
							: dataVariableDef.getDoc());
				}
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				IStructuredSelection iStructuredSelection = (IStructuredSelection) functreeViewer
						.getSelection();
				if (iStructuredSelection != null) {
					StyledText control = viewer.getTextWidget();
					
					DataVariableDef dataVariableDef = (DataVariableDef) iStructuredSelection
							.getFirstElement();
					if (document.get().length() == 0)
						document.set("");
					int offset = control.getCaretOffset();
					String before = document.get().substring(0, offset);
					if (offset == document.get().length()) {
						document.set(before + dataVariableDef.getValue());
					} else {
						String after = document.get().substring(offset,
								document.get().length());
						document.set(before + dataVariableDef.getValue() + after);
					}

					control.setCaretOffset(offset+dataVariableDef.getValue().length());
					control.setFocus();
				}
			}
		});

		
		viewer.getTextWidget().addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
				if((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'z'){
					TextOperationAction action = new TextOperationAction(ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Undo.", editor, ITextOperationTarget.UNDO); //$NON-NLS-1$ //$NON-NLS-2$
					action.run();
				}else if((e.stateMask == SWT.CTRL|| e.stateMask == SWT.COMMAND) && e.keyCode == 'y'){
					TextOperationAction action = new TextOperationAction(ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Redo.", editor, ITextOperationTarget.REDO); //$NON-NLS-1$ //$NON-NLS-2$
					action.run();
				}
			}

			public void keyPressed(KeyEvent e) {

				if(e.keyCode == SWT.DEL){
					TextOperationAction action = new TextOperationAction(ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Delete.", editor, ITextOperationTarget.DELETE); //$NON-NLS-1$ //$NON-NLS-2$
					action.run();
				}/*else if((e.stateMask == SWT.CTRL|| e.stateMask == SWT.COMMAND) && e.keyCode == 'i'){
					BonitaFormatGroovyAction action = new BonitaFormatGroovyAction(site,FormatKind.FORMAT,getEditor(),getEditor().getGroovyCompilationUnit()) ;
					action.run() ;
				}*/
			}
		});
		
		createToolBar(operatorcomposite);
		new Label(operatorcomposite, SWT.NONE);
		new Label(operatorcomposite, SWT.NONE);
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(900, 700);
	}

	@Override
	protected void okPressed() {

		if (btnCheckButton.getSelection()) {
			datavarname.setText(document.get().replace(" ", ""));// 把内容中空格去掉，不然存进id会报错
		}
		if (datavarname.getText().equals("")) {

			MessageDialog
					.openError(null, "警告", "表达式的名称为空\n不需要输入表达式的时候请点击取消按钮。");
			return;
			// datavarname.setText(text.getText());
		}
		if (expressionTo != null) {
			expressionTo.setName(datavarname.getText());
			expressionTo.setExpressionText(document.get());
		}
		if (expressionTo.getName().equals("")
				&& expressionTo.getExpressionText().equals("")) {
			MessageDialog.openError(null, "警告",
					"表达式的名称和值不能都为空\n不需要输入表达式的时候请点击取消按钮。");
			return;
		}
		
		close();
		
		super.okPressed();
	}

	private static class ViewerLabelProvider1 extends LabelProvider {
		public Image getImage(Object element) {
			@SuppressWarnings("deprecation")
			String image1 = ISharedImages.IMG_OBJS_TASK_TSK;
			if (element instanceof DataVarTo) {
				return PlatformUI.getWorkbench().getSharedImages()
						.getImage(image1);
			}
			return null;
		}

		public String getText(Object element) {
			Type type = (Type) element;
			if (DataVarUtil.getfixFlowDataVariableByDataType(type.getId()) != null) {
				return type.getName()
						+ "("
						+ DataVarUtil
								.getfixFlowDataVariableByDataType(type.getId())
								.getDataVariableDef().size() + ")";
			} else {
				return type.getName() + "(" + 0 + ")";
			}

		}

	}

	private static class ViewerLabelProvider2 extends StyledCellLabelProvider
			implements ILabelProvider {
		public Image getImage(Object element) {
			@SuppressWarnings("deprecation")
			String image1 = ISharedImages.IMG_OBJS_TASK_TSK;
			if (element instanceof DataVarTo) {
				return PlatformUI.getWorkbench().getSharedImages()
						.getImage(image1);
			}
			return null;
		}

		public String getText(Object element) {
			DataVariableDef dataVariableDef = (DataVariableDef) element;
			return dataVariableDef.getName()
					+ " "
					+ DataVarUtil.getDataTypeDefByDataVariableDataType(
							dataVariableDef.getDataType()).getId() + " "
					+ dataVariableDef.getValue();
		}

		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof DataVariableDef) {
				DataVariableDef d = (DataVariableDef) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = " " + d.getValue();
				styledString.append(d.getName()
						+ " "
						+ DataVarUtil.getDataTypeDefByDataVariableDataType(
								d.getDataType()).getId());
				styledString
						.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d));
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}

	private static class ViewerLabelProvider extends StyledCellLabelProvider
			implements ILabelProvider {
		public Image getImage(Object element) {
			// String image1 = ISharedImages.IMG_OBJS_TASK_TSK;
			Image image = ImageUtil.getImageFromURL("/user_16_hot.png");
			Image image2 = ImageUtil.getImageFromURL("/bookprev_16.png");
			Image image3 = ImageUtil.getImageFromURL("/group_16.png");

			if (element instanceof DataVarTo) {
				DataVarTo dataVarTo = (DataVarTo) element;
				if (dataVarTo.getType().equals("分类")) {
					return image2;
				}
				if (dataVarTo.getType().equals("用户")) {
					return image;
				}
				return image3;
			}
			return null;
		}

		public String getText(Object element) {
			DataVarTo treeElement = (DataVarTo) element;
			return treeElement.getId() + " -- " + treeElement.getType();
		}

		@Override
		public void update(ViewerCell cell) {
			// TODO Auto-generated method stub
			if (cell.getElement() instanceof DataVarTo) {
				DataVarTo d = (DataVarTo) cell.getElement();
				StyledString styledString = new StyledString();
				String decoration = " -- " + d.getType();
				styledString.append(d.getId() == null ? "" : d.getId());
				styledString
						.append(decoration, StyledString.DECORATIONS_STYLER);
				cell.setText(styledString.getString());
				cell.setImage(getImage(d));
				cell.setStyleRanges(styledString.getStyleRanges());
			}
		}
	}

	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof java.util.List) {
				@SuppressWarnings("rawtypes")
				java.util.List list = (java.util.List) inputElement;
				return list.toArray();
			} else {
				return new Object[0];
			}
		}

		public Object[] getChildren(Object parentElement) {
			ITreeElement treeElement = (ITreeElement) parentElement;
			java.util.List<ITreeElement> list = treeElement.getChildren();
			if (list == null || list.isEmpty()) {
				return new Object[0];
			} else {
				return list.toArray();
			}
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			DataVarTo dataVarTo = (DataVarTo) element;
			return getChildren(dataVarTo).length > 0;
		}
	}

	private static class TreeContentProvider1 implements ITreeContentProvider {
		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

		@Override
		public Object[] getChildren(Object parentElement) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getParent(Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			// TODO Auto-generated method stub
			return false;
		}

		public Object[] getElements(Object inputElement) {
			// TODO Auto-generated method stub
			if (inputElement instanceof List) {
				@SuppressWarnings("rawtypes")
				List list = (List) inputElement;
				return list.toArray();
			} else {
				return new Object[0];
			}
		}
	}

	public void createToolBar(Composite operatorcomposite) {
		StyledText control = viewer.getTextWidget();
		
		ToolBar toolBar = new ToolBar(operatorcomposite, SWT.FLAT | SWT.RIGHT);
		GridData gd_toolBar = new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1);
		gd_toolBar.widthHint = 393;
		toolBar.setLayoutData(gd_toolBar);

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("+");
		tltmNewItem
				.addSelectionListener(new GroovyOperatorSelectionAdapter("+", document , control));

		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setText("-");
		tltmNewItem_1.addSelectionListener(new GroovyOperatorSelectionAdapter("-",
				 document , control));

		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_2.setText("*");
		tltmNewItem_2.addSelectionListener(new GroovyOperatorSelectionAdapter("*",
				 document , control));

		ToolItem tltmNewItem_3 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_3.setText("/");
		tltmNewItem_3.addSelectionListener(new GroovyOperatorSelectionAdapter("/",
				 document , control));

		ToolItem tltmNewItem_4 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_4.setText("<");
		tltmNewItem_4.addSelectionListener(new GroovyOperatorSelectionAdapter("<",
				 document , control));

		ToolItem tltmNewItem_5 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_5.setText("<=");
		tltmNewItem_5.addSelectionListener(new GroovyOperatorSelectionAdapter("<=",
				 document , control));

		ToolItem tltmNewItem_6 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_6.setText("==");
		tltmNewItem_6.addSelectionListener(new GroovyOperatorSelectionAdapter("==",
				 document , control));

		ToolItem tltmNewItem_7 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_7.setText("!=");
		tltmNewItem_7.addSelectionListener(new GroovyOperatorSelectionAdapter("!=",
				 document , control));

		ToolItem tltmNewItem_8 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_8.setText(">=");
		tltmNewItem_8.addSelectionListener(new GroovyOperatorSelectionAdapter(">=",
				 document , control));

		ToolItem tltmNewItem_9 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_9.setText(">");
		tltmNewItem_9.addSelectionListener(new GroovyOperatorSelectionAdapter(">",
				 document , control));

		ToolItem tltmNewItem_10 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_10.setText("And");
		tltmNewItem_10.addSelectionListener(new GroovyOperatorSelectionAdapter("&&",
				 document , control));

		ToolItem tltmNewItem_11 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_11.setText("Or");
		tltmNewItem_11.addSelectionListener(new GroovyOperatorSelectionAdapter("||",
				 document , control));

		ToolItem tltmNewItem_12 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_12.setText("Not");
		tltmNewItem_12.addSelectionListener(new GroovyOperatorSelectionAdapter("!",
				 document , control));

		ToolItem tltmNewItem_13 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_13.setText("true");
		tltmNewItem_13.addSelectionListener(new GroovyOperatorSelectionAdapter(
				"true",  document , control));

		ToolItem tltmNewItem_14 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_14.setText("false");
		tltmNewItem_14.addSelectionListener(new GroovyOperatorSelectionAdapter(
				"false",  document , control));

		ToolItem tltmNewItem_15 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_15.setText("(");
		tltmNewItem_15.addSelectionListener(new GroovyOperatorSelectionAdapter("(",
				 document , control));

		ToolItem tltmNewItem_16 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_16.setText(")");
		tltmNewItem_16.addSelectionListener(new GroovyOperatorSelectionAdapter(")",
				 document , control));

		Button clearButton = new Button(operatorcomposite, SWT.NONE);
		clearButton.setText("清空");
		clearButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				if (MessageDialog.openConfirm(null, "提示", "确定要清空吗？"))
					;
				document.set("");
			}
		});
	}

	// 读取EMF中全局与私有的数据变量及平台组织结构角色等数据，放入自己创建的ToList中
	public void initVar() {
		// 全局
		dataVarTos = new ArrayList<DataVarTo>();
		DataVarTo dataVarTo1 = new DataVarTo();
		dataVarTo1.setId("全局数据变量");
		dataVarTo1.setCantbechoose("quanju");
		dataVarTo1.setType("分类");
		DataVarTo dataVarTo2 = new DataVarTo();
		dataVarTo2.setId("私有数据变量");
		dataVarTo2.setType("分类");
		dataVarTo2.setCantbechoose("siyou");
		DataVarTo dataVarTo3 = new DataVarTo();
		dataVarTo3.setId("常用数据变量");
		dataVarTo3.setType("分类");
		dataVarTo3.setCantbechoose("changyong");

		for (ExtensionAttributeValue eav : SectionBpmnElement.process
				.getExtensionValues()) {
			FeatureMap extensionElements = eav.getValue();
			Object objectElement = extensionElements.get(
					FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
			if (objectElement != null) {

				@SuppressWarnings("unchecked")
				java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
				for (DataVariable dataVariable : dataVariableList) {
					DataVarTo dataVarTo = new DataVarTo();
					dataVarTo.setId(dataVariable.getId());
					dataVarTo.setValue("${" + dataVariable.getId() + "}");
					dataVarTo.setType(DataVarUtil
							.getDataTypeDefByDataVariableDataType(
									dataVariable.getDataType()).getName());
					if (dataVariable.getDocumentation() != null
							&& dataVariable.getDocumentation().size() > 0) {
						dataVarTo.setDoc(dataVariable.getDocumentation().get(0)
								.getValue());
					}
					dataVarTo1.addChild(dataVarTo);
				}

			}
		}

		// 私有
		if (!(SectionBpmnElement.sectionElement instanceof Process)
				|| !(SectionBpmnElement.sectionElement instanceof ProcessImpl)) {
			BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;
			if (baseElement.getExtensionValues().size() > 0) {
				for (ExtensionAttributeValue extensionAttributeValue : baseElement
						.getExtensionValues()) {
					FeatureMap extensionElements = extensionAttributeValue
							.getValue();
					Object objectElement = extensionElements
							.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE,
									true);
					if (objectElement != null) {

						@SuppressWarnings("unchecked")
						java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
						for (DataVariable dataVariable : dataVariableList) {
							DataVarTo dataVarTo = new DataVarTo();
							dataVarTo.setId(dataVariable.getId());
							if (dataVariable.getExpression() != null) {
								dataVarTo.setValue(dataVariable.getExpression()
										.getValue());
							}

							dataVarTo.setType(DataVarUtil
									.getDataTypeDefByDataVariableDataType(
											dataVariable.getDataType())
									.getName());
							dataVarTo.setDoc(dataVariable.getDocumentation()
									.get(0).getValue());
							dataVarTo2.addChild(dataVarTo);
						}

					}
				}
			}
		}

		// 添加全局变量
		dataVarTos.add(dataVarTo1);
		// 添加选中元素变量
		// dataVarTos.add(dataVarTo2);

		// 如果设置启用则添加所有用户
		if (FixFlowConfigUtil.getFixFlowConfig().getDesignerOrgConfig()
				.getAllUserInfo().getIsEnabled().equals("true")) {
			dataVarTos.add(DataVarUtil.getAllUserDataVarTo());
		}

		// 添加组织结构
		// dataVarTos.add(DataVarUtil.getOrgStructureDataVarTo());
		for (DataVarTo dataVarTo : DataVarUtil.getGroupDataVarTo()) {
			dataVarTos.add(dataVarTo);
		}
		// 添加角色
		// dataVarTos.add(DataVarUtil.getRoleInfoDataVarTo());

		// 添加常用变量
		dataVarTos.add(dataVarTo3);
		DataVarTo dataVarTo = new DataVarTo();
		dataVarTo.setId("所有用户");
		dataVarTo.setValue("\"fixflow_allusers\"");
		dataVarTo.setType("常用变量");
		dataVarTo3.addChild(dataVarTo);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		DataVarTo dataVarTo11 = new DataVarTo();
		dataVarTo11.setId("当前时间");
		dataVarTo11.setValue(simpleDateFormat.format(new Date()));
		dataVarTo11.setType("常用变量");
		dataVarTo3.addChild(dataVarTo11);

		DataVarTo dataVarTo12 = new DataVarTo();
		dataVarTo12.setId("某部门某角色");
		dataVarTo12.setValue("processInfo.findUserDeptAndRole(deptId,roleId);");
		dataVarTo12.setType("常用变量");
		dataVarTo3.addChild(dataVarTo12);
	}

	public ExpressionTo getExpressionTo() {
		return expressionTo;
	}

	public void setExpressionTo(ExpressionTo expressionTo) {
		this.expressionTo = expressionTo;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		super.close() ;

		site.setSelectionProvider(diagramEditorSelectionProvider);
		
		IHandlerService handlerService = (IHandlerService) workbench.getAdapter(IHandlerService.class);
		handlerService.deactivateHandler(fHandlerActivation);
//		editor.setAction("find", null) ;
		document.set("");
		editor.dispose() ;
		
		return true;
	}

	@Override
	public int open() {
		// TODO Auto-generated method stub
		if(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() != null){
			return super.open();
		}else{
			MessageDialog.openError(Display.getDefault().getActiveShell(), "提示", "打开错误") ;
			return 0 ;
		}
	}
}
