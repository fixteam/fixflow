package com.founder.fix.fixflow.designer.modeler.ui.property.callactivity;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.founder.fix.fixflow.designer.modeler.ui.property.process.ProcessCommonPropertiesComposite;
import com.founder.fix.fixflow.designer.modeler.ui.property.process.ProcessTo;



public class FlowSelectDialog extends TitleAreaDialog {
	private ProcessTo value;
	private FlowFilter filter;
	private TableViewer viewer;


	public FlowSelectDialog(Shell parentShell) {
		super(parentShell);
		setHelpAvailable(false);
		setShellStyle(SWT.PRIMARY_MODAL);
	}

	/**
	 * 设置对话框大小
	 */
	protected Point getInitialSize() {
		return new Point(550, 520);
	}

	public ProcessTo getValue() {
		return value;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite container = (Composite) super.createDialogArea(parent);

		GridLayout layout = new GridLayout();
		layout.marginLeft = 20;
		layout.marginTop = 10;
		layout.marginRight = 20;
		container.setLayout(layout);

		Composite client = new Composite(container, SWT.NULL);
		GridLayout layoutClient = new GridLayout();
		layoutClient.marginTop = 4;
		layoutClient.horizontalSpacing = 20;
		layoutClient.verticalSpacing = 20;
		layoutClient.numColumns = 2;
		client.setLayout(layoutClient);
		client.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label searchLabel = new Label(client, SWT.NONE);
		searchLabel.setText("搜索: ");
		
		final Text searchText = new Text(client, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				viewer.refresh();
			}
		});

		new Label(client, SWT.NULL);
		viewer = new TableViewer(client, /* SWT.CHECK | */SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		filter = new FlowFilter();
		viewer.addFilter(filter);

		createAttrColumns(client, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.addListener(SWT.MeasureItem, new Listener() { // TODO 修改行高度
					public void handleEvent(Event event) {
						event.width = table.getGridLineWidth();
						// 设置宽度
						event.height = (int) Math.floor(event.gc
								.getFontMetrics().getHeight() * 1.5);

					}
				});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				getFirstSelected();
				if (value != null)
					okPressed();

			}

			@Override
			public void mouseDown(MouseEvent e) {
				getFirstSelected();
				getButton(IDialogConstants.OK_ID).setEnabled(value != null);
			}
		});
		viewer.setContentProvider(new ArrayContentProvider());
		// viewer.setInput(StudioInterface.getBpmnBeansFromFileRealPath(StudioInterface.getBizobjRealPathFromBizobjId(bizObj.getId())));

		viewer.setInput(ProcessCommonPropertiesComposite.getProcessList());

		setDialogLocation();

		setTitle("流程选择");

		return container;
	}

	// 获取表中选中的对象
	public void getFirstSelected() {
		ISelection sel = viewer.getSelection();
		if (sel == null)
			return;
		Object[] objs = ((IStructuredSelection) sel).toArray();
		if (objs == null || objs.length == 0)
			return;
		value = (ProcessTo) objs[0];
	}

	private void createAttrColumns(final Composite parent,
			final TableViewer viewer) {

		TableViewerColumn col = createTableViewerColumn(viewer, "名称", 200, 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ProcessTo p = (ProcessTo) element;
				return p.getProcessName();
			}
		});

		col = createTableViewerColumn(viewer, "ID", 200, 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				ProcessTo p = (ProcessTo) element;
				return p.getProcessKey();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	/**
	 * 设置对话框在中心位置
	 * 
	 */
	private void setDialogLocation() {
		Rectangle monitorArea = getShell().getDisplay().getPrimaryMonitor()
				.getBounds();
		Rectangle shellArea = getShell().getBounds();
		int x = monitorArea.x + (monitorArea.width - shellArea.width) / 2;
		int y = monitorArea.y + (monitorArea.height - shellArea.height) / 2;
		getShell().setLocation(x, y);
	}

	/**
	 * 修改button的名称
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		createButton(parent, IDialogConstants.OK_ID, "确定", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "关闭", false);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
	}

}
