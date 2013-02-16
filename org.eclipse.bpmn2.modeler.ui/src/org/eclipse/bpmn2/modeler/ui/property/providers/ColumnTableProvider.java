/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * A TableProvider which uses an additional Column abstraction to collect
 * all the interesting characteristics of a table column in one place.
 */
public class ColumnTableProvider extends TableProvider {

	protected TableViewer tableViewer;
	protected ArrayList<Column> columns = new ArrayList<Column>();

	/**
	 * Clients should implement this class for each type of column they want to show
	 * in the Table.
	 */
	public static abstract class Column implements IBaseLabelProvider {
		
		protected TableViewer tableViewer;
		
		public abstract String getHeaderText();
		public int getAlignment() { return SWT.LEFT; }
		public int getInitialWeight() { return 10; }
		public abstract String getProperty();
		public IBaseLabelProvider getLabelProvider() {
			if (this instanceof ITableLabelProvider)  return this;
			return (this instanceof ILabelProvider)? (ILabelProvider)this : null;
		}
		public ICellModifier getCellModifier() {
			return (this instanceof ICellModifier)? (ICellModifier)this : null;
		}
		public CellEditor createCellEditor(Composite parent) { return null; }
		/* IBaseLabelProvider - for the convenience of subclasses */
		public void addListener(ILabelProviderListener listener) { }
		public void removeListener(ILabelProviderListener listener) { }
		public void dispose() { }
		public boolean isLabelProperty(Object element, String property) {
			return getProperty().equals(property);
		}
		/* ILabelProvider */
		public Image getImage(Object element) { return null; }
		
		public void setTableViewer(TableViewer tv) {
			tableViewer = tv;
		}
	}

	public void add(int index, Column column) {
		register(column.getProperty(), column.getLabelProvider(), column.getCellModifier());
		columns.add(index, column);
		columnProperties = null;  // clear cache
	}
	public void add(Column column) {
		add(columns.size(), column);
	}
	public void remove(int index) {
		Column column = columns.get(index);
		if (column != null) remove(column);
	}
	public void remove(Column column) {
		unregister(column.getProperty());
		columns.remove(column);
		columnProperties = null;  // clear cache
	}
	public List getColumns() {
		return Collections.unmodifiableList(columns);
	}

	/**
	 * Creates a TableLayout on the given Table and populates it using the current
	 * set of Columns in the ColumnTableProvider.
	 */
	public void createTableLayout(Table table) {
		TableLayout tableLayout = new TableLayout();
		for (Column column : columns) {
			TableColumn tc = new TableColumn(table, column.getAlignment());
			tableLayout.addColumnData(new ColumnWeightData(column.getInitialWeight()));
			tc.setText(column.getHeaderText());
		}
		table.setLayout(tableLayout);
	}

	/**
	 * Overridden to do nothing.  The column properties for a ColumnTableProvider
	 * are determined dynamically from the current set of Columns.
	 */
	@Override
	public void setColumnProperties(String[] unused) {
		// do nothing
	}

	/**
	 * Returns the columnProperties of this TableProvider.
	 */
	@Override
	public String[] getColumnProperties() {
		if (columnProperties == null) {
			columnProperties = new String[columns.size()];
			for (int i = 0; i<columns.size(); i++)  {
				columnProperties[i] = getColumnProperty(i);
			}
		}
		return columnProperties;
	}
	
	/**
	 * Returns a particular column's property.  For some subclasses, this may be more
	 * efficient than using getColumnProperties().
	 */
	@Override
	public String getColumnProperty(int index) {
		return columns.get(index).getProperty();
	}
	
	/**
	 * Creates an array of CellEditor objects by calling the createCellEditor()
	 * method on each Column.
	 * 
	 * The returned array is suitable for e.g. passing to TableViewer.setCellEditors().
	 */
	public CellEditor[] createCellEditors(Composite parent) {
		CellEditor[] cellEditors = new CellEditor[columns.size()];
		for (int i = 0; i<columns.size(); i++)  {
			cellEditors[i] = columns.get(i).createCellEditor(parent);
		}
		return cellEditors;
	}
	
	public void setTableViewer(TableViewer tv) {
		tableViewer = tv;
		for (Column column : columns)  {
			column.setTableViewer(tv);
		}
	}
}
