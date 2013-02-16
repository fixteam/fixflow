package com.founder.fix.fixflow.designer.modeler.ui.property.definitions;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Error;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.founder.fix.fixflow.designer.modeler.ui.property.AbstractFixFlowBpmn2PropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;

public class ErrorEventDefinitionConfigPropertiesComposite extends AbstractFixFlowBpmn2PropertiesComposite {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	protected List<ErrorTo> errors = new ArrayList<ErrorTo>();
	private Table table;
	private TableViewer tableViewer;
	private Composite composite;
	private Button button;
	private Button btnNewButton;
	private Button btnNewButton_1;
	private TableColumn tableColumn;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;

	public ErrorEventDefinitionConfigPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}
	public ErrorEventDefinitionConfigPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createUI() {
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginBottom = 10;
		gridLayout.marginHeight = 10;
		gridLayout.verticalSpacing = 10;
		gridLayout.marginLeft = 10;
		setLayout(gridLayout);

		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_table.heightHint = 150;
		gd_table.widthHint = 400;
		table.setLayoutData(gd_table);
	
		toolkit.adapt(table, true, true);

		tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("错误编号");

		tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("错误名称");

		tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(150);
		tableColumn_2.setText("错误代码");

		composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 1;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	
		toolkit.adapt(composite, true, true);

		button = new Button(composite, SWT.NONE);
		button.setText("添加");

		btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setText("删除");

		btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setText("保存");

		 createCellModifier();
	}

	@Override
	public void createUIBindings(EObject eObject) {
		m_bindingContext = initDataBindings();
		Definitions definitions = SectionBpmnElement.definitions;
		errors.clear();
		for (RootElement rootElement : definitions.getRootElements()) {
			if (rootElement instanceof Error) {
				Error error=(Error) rootElement;
				ErrorTo errorTo=new ErrorTo();
				errorTo.setErrorId(error.getId());
				errorTo.setErrorName(error.getName());
				errorTo.setErrorCode(error.getErrorCode());

				
				errors.add(errorTo);

			}
		}
		
		
		
		
		tableViewer.refresh();
		/*
		 * EList<EAttribute> eAllAttributes = be.eClass().getEAllAttributes();
		 * Bpmn2Preferences preferences =
		 * Bpmn2Preferences.getPreferences(project);
		 * 
		 * for (EAttribute attrib : eAllAttributes) {
		 * 
		 * if (preferences.isEnabled(be.eClass(), attrib)) {
		 * 
		 * 
		 * if (attrib.getName().equals("id")) { bind(attrib, idtext);
		 * 
		 * } if (attrib.getName().equals("name")) { bind(attrib, nametext);
		 * 
		 * } } }
		 * 
		 * for (EReference e : be.eClass().getEAllReferences()) {
		 * 
		 * if (e.getName().equals("documentation")) {
		 * 
		 * bindDocumentation(e, desctext); }
		 * 
		 * }
		 */



		button.addListener(SWT.Selection, addlisListener);

		btnNewButton.addListener(SWT.Selection, deletelisListener);
		
		btnNewButton_1.addListener(SWT.Selection, saveListener);

	}

	/**
	 * 添加的点击事件
	 */
	Listener addlisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {

		
			ErrorTo errorTo=new ErrorTo();
			errorTo.setErrorId("error_"+(String.valueOf(errors.size())));
			errorTo.setErrorName("errorName");
			errorTo.setErrorCode("errorCode");

			
			errors.add(errorTo);
		

			/*
			final Definitions definitions = SectionBpmnElement.definitions;
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					definitions.getRootElements().add(error);
				}
			});
			*/

			//errors.add(error);
			tableViewer.refresh();

		}
	};

	List<Error> errorDeleteList=new ArrayList<Error>();
	
	/**
	 * 保存事件
	 */
	Listener saveListener = new Listener() {

		@Override
		public void handleEvent(Event event) {


			final Definitions definitions = SectionBpmnElement.definitions;

			final List<RootElement> rootElements = definitions.getRootElements();
			
			for (RootElement rootElement : rootElements) {
				if(rootElement instanceof Error){
					Error error=(Error)rootElement;
					errorDeleteList.add(error);
				}
			}
			
			@SuppressWarnings("restriction")
			TransactionalEditingDomain editingDomain = getDiagramEditor().getEditingDomain();
			editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					definitions.getRootElements().removeAll(errorDeleteList);
					for (ErrorTo errorToObj : errors) {
						Error errorObj=Bpmn2Factory.eINSTANCE.createError();
						errorObj.setId(errorToObj.getErrorId());
						errorObj.setName(errorToObj.getErrorName());
						errorObj.setErrorCode(errorToObj.getErrorCode());
						definitions.getRootElements().add(errorObj);
						
					}
				}
			});
			
			getDiagramEditor().doSave(null);
			
			

		}
	};

	
	/**
	 * 删除事件
	 */
	Listener deletelisListener = new Listener() {

		@Override
		public void handleEvent(Event event) {

			IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
			Object sObject = selection.getFirstElement();

			if (sObject != null) {
				ErrorTo errorTo = (ErrorTo) sObject;
				
				
				errors.remove(errorTo);
				
				/*
				final String errorId = error.getId();

				final Definitions definitions = SectionBpmnElement.definitions;

				final List<RootElement> rootElements = definitions.getRootElements();
				
				
				
				
				@SuppressWarnings("restriction")
				TransactionalEditingDomain editingDomain = bpmn2Editor.getEditingDomain();
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						int countTemp=0;
						for (int x = 0; x < definitions.getRootElements().size(); x++) {

							RootElement rootElement = rootElements.get(x);
							if (rootElement instanceof Error) {
								Error errorTemp = (Error) rootElement;
								if (errorTemp.getId().equals(errorId)) {
									countTemp=x;
								}

							}
						}
						errors.remove(countTemp);
						tableViewer.refresh();
						rootElements.remove(countTemp);
					}
				});
				
				*/
			}
			tableViewer.refresh();

		}
	};


	private void createCellModifier() {

		final CellEditor[] cellEditor = new CellEditor[table.getColumnCount()];
		cellEditor[0] = new TextCellEditor(table);
		cellEditor[1] = new TextCellEditor(table);
		cellEditor[2] = new TextCellEditor(table);

		tableViewer.setColumnProperties(new String[] { "ERRORID", "ERRORNAME", "ERRORCODE" });
		tableViewer.setCellEditors(cellEditor);
		tableViewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				// TODO Auto-generated method stub
				TableItem tableitem = (TableItem) element;
				ErrorTo error = (ErrorTo) tableitem.getData();
				if (property.equals("ERRORID")) {
					error.setErrorId((String) value);
				}
				if (property.equals("ERRORNAME")) {
					error.setErrorName((String) value);
				}
				if (property.equals("ERRORCODE")) {
					error.setErrorCode((String) value);
				}

				tableViewer.refresh();
			}

			public Object getValue(Object element, String property) {
				// TODO Auto-generated method stub
				ErrorTo error = (ErrorTo) element;

				if (property.equals("ERRORID")) {
					return error.getErrorId();
				}
				if (property.equals("ERRORNAME")) {
					return error.getErrorName();
				}
				if (property.equals("ERRORCODE")) {
					return error.getErrorCode();
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				// TODO Auto-generated method stub
				return element instanceof ErrorTo;
			}
		});
	}

	

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), ErrorTo.class, new String[] { "errorId", "errorName", "errorCode" });
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableList writableList = new WritableList(errors, Error.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}
	public class ErrorTo
	{
		protected String errorId;
		protected String errorName;
		protected String errorCode;
	
		public String getErrorId() {
			return errorId;
		}
		public void setErrorId(String errorId) {
			this.errorId = errorId;
		}
		public String getErrorName() {
			return errorName;
		}
		public void setErrorName(String errorName) {
			this.errorName = errorName;
		}
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

	}
	
	
}
