package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class DataVariableImportDialog extends TitleAreaDialog {
	private DataBindingContext m_bindingContext;
	private Text text;
	private Table table;
	private List<DataObjImport> dataObjImports;
	private TableViewer tableViewer;
	private Button okbutton;
	private Button canclebutton;
	private DataObjImport dataObjImport;
	private BizObjFilter filter;
	private String importType;
	Button btnRadioButtonForm;
	Button btnRadioButtonDB;
	

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DataVariableImportDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.RESIZE | SWT.TITLE|SWT.PRIMARY_MODAL);
		dataObjImports = DataObjImportFactory.createDataObjImports();
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("数据变量导入");
		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout gl_area = new GridLayout(1, false);
		gl_area.verticalSpacing = 0;
		gl_area.marginWidth = 0;
		gl_area.horizontalSpacing = 0;
		gl_area.marginHeight = 0;
		area.setLayout(gl_area);
		Composite container = new Composite(area, SWT.NONE);
		GridLayout gl_container = new GridLayout(2, false);
		gl_container.marginBottom = 15;
		gl_container.marginHeight = 15;
		gl_container.marginRight = 25;
		gl_container.marginLeft = 25;
		container.setLayout(gl_container);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		new Label(container, SWT.NONE);
		
		Composite composite = new Composite(container, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 19;
		composite.setLayoutData(gd_composite);
		
		btnRadioButtonForm = new Button(composite, SWT.RADIO);
		btnRadioButtonForm.setBounds(0, 0, 69, 16);
		btnRadioButtonForm.setText("表单变量");
		
		btnRadioButtonDB = new Button(composite, SWT.RADIO);
		btnRadioButtonDB.setSelection(true);
		btnRadioButtonDB.setBounds(75, 0, 87, 16);
		btnRadioButtonDB.setText("数据库变量");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("搜索");
		
		text = new Text(container, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH | SWT.ICON_CANCEL);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(text.getText());
				tableViewer.refresh();
			}
		});
		new Label(container, SWT.NONE);
		
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		
		filter = new BizObjFilter();
		tableViewer.addFilter(filter);
		
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addListener(SWT.MeasureItem, new Listener() { // TODO 修改行高度
			public void handleEvent(Event event) {
				event.width = table.getGridLineWidth();
				// 设置宽度
				event.height = (int) Math.floor(event.gc.getFontMetrics().getHeight() * 1.5);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();
				if (iStructuredSelection.getFirstElement() != null){
					dataObjImport = (DataObjImport) iStructuredSelection.getFirstElement();
					okPressed();
				}
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();
				getButton(IDialogConstants.OK_ID).setEnabled(iStructuredSelection.getFirstElement() != null);
				dataObjImport = (DataObjImport) iStructuredSelection.getFirstElement();
			}
		});
		
		TableColumn fieldcolumn = new TableColumn(table, SWT.NONE);
		fieldcolumn.setWidth(150);
		fieldcolumn.setText("编号");
		fieldcolumn.setMoveable(true);
		
		TableColumn displaycolumn = new TableColumn(table, SWT.NONE);
		displaycolumn.setWidth(230);
		displaycolumn.setText("名称");
		displaycolumn.setMoveable(true);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okbutton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		okbutton.setEnabled(false);
		okbutton.setText("导入变量");

		
		
		canclebutton = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		canclebutton.setText("取消");
		m_bindingContext = initDataBindings();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(490, 496);
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), DataObjImport.class, new String[]{"id", "name"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		//
		WritableList writableList = new WritableList(dataObjImports, DataObjImport.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		setDataObjImport(dataObjImport);
		if(btnRadioButtonDB.getSelection()){
			importType= "db";
		}
		else{
			importType= "form";
		}
		super.okPressed();
	}
	
	public String getImportType() {
		return importType;
		
	
	}

	public DataObjImport getDataObjImport() {
		return dataObjImport;
	}

	public void setDataObjImport(DataObjImport dataObjImport) {
		this.dataObjImport = dataObjImport;
	}
}
