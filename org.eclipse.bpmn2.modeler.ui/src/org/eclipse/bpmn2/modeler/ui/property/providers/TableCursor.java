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
package org.eclipse.bpmn2.modeler.ui.property.providers;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TypedListener;


/** 
 * A modification of the SWT TableCursor class to better handle empty tables and dynamic tables 
 */
public class TableCursor extends Canvas {

	Table table;
	int row = -1, column = 0; // having row negative will end up hiding the cursor
	Listener tableListener, resizeListener;
	boolean progVisible = false;
	boolean userVisible = true;
	boolean hasFocus = false;
	AccessibleAdapter accessAdapter = null;
	Listener selectionAccessListener = null;
	Listener focusAccessListener = null;
	AccessibleControlAdapter accessControlAdapter = null;
	boolean hasAccessibility = false;

	public TableCursor(Table parent, int style) {
		super(parent, style);
		table = parent;
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
					case SWT.Dispose :
						dispose(event);
						break;
					case SWT.KeyDown :
						keyDown(event);
						break;
					case SWT.Paint :
						paint(event);
						break;
					case SWT.Traverse :
						traverse(event);
						break;
				}
			}
		};
		addListener(SWT.Dispose, listener);
		addListener(SWT.KeyDown, listener);
		addListener(SWT.Paint, listener);
		addListener(SWT.Traverse, listener);

		tableListener = new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
					case SWT.MouseDown :
						tableMouseDown(event);
						break;
					case SWT.FocusIn :
						tableFocusIn(event);
						break;
				}
			}
		};
		table.addListener(SWT.FocusIn, tableListener);
		table.addListener(SWT.MouseDown, tableListener);

		resizeListener = new Listener() {
			public void handleEvent(Event event) {
				resize();
			}
		};
		
		ScrollBar hBar = table.getHorizontalBar();
		if (hBar != null)
			hBar.addListener(SWT.Selection, resizeListener);
		
		ScrollBar vBar = table.getVerticalBar();
		if (vBar != null) 
			vBar.addListener(SWT.Selection, resizeListener);
		
		this.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				hasFocus = true;
				redraw();
			}

			public void focusLost(FocusEvent e) {
				hasFocus = false;
				redraw();
				
			}});

		refresh();
		
		initAccessible();
	}
	
	/**
	 * Creates a table cursor that can be used to navigate tables for keyboard accessibility
	 * @param table
	 * @param tableViewer
	 * @return
	 */
	public static TableCursor create(final Table table, final TableViewer tableViewer) {
		// create a TableCursor to navigate around the table
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		cursor.addSelectionListener(new SelectionAdapter() {
			// when the TableEditor is over a cell, select the corresponding row in the table
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (cursor.getRow() != null)
					table.setSelection(new TableItem[] {cursor.getRow()});
			}
			// when the user hits "ENTER" in the TableCursor, pop up an editor
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem row = cursor.getRow();
				if (row != null) {
					int nRow = table.indexOf(row);
					int column = cursor.getColumn();
					Object obj = tableViewer.getElementAt(nRow);
					tableViewer.editElement(obj, column);
				}
			}
		});

		// Hide the TableCursor when the user hits the "CTRL" or "SHIFT" key.
		// This alows the user to select multiple items in the table.
		cursor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.keyCode == SWT.CTRL) || (e.keyCode == SWT.SHIFT)	|| 
					(e.stateMask & SWT.CONTROL) != 0	|| (e.stateMask & SWT.SHIFT) != 0) {
					cursor.setVisible(false);
				}
			}
		});
		
		cursor.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) { }
			public void mouseDown(MouseEvent e) {
				TableItem row = cursor.getRow();
				if (row != null) {
					int nRow = table.indexOf(row);
					int column = cursor.getColumn();
					Object obj = tableViewer.getElementAt(nRow);
					tableViewer.editElement(obj, column);
				}
			}
			public void mouseUp(MouseEvent e) {
			}
		});
				
		// Show the TableCursor when the user releases the "SHIFT" or "CTRL" key.
		// This signals the end of the multiple selection modelObject.
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CONTROL && (e.stateMask & SWT.SHIFT) != 0)
					return;
				if (e.keyCode == SWT.SHIFT && (e.stateMask & SWT.CONTROL) != 0)
					return;
				if (e.keyCode != SWT.CONTROL && (e.stateMask & SWT.CONTROL) != 0)
					return;
				if (e.keyCode != SWT.SHIFT && (e.stateMask & SWT.SHIFT) != 0)
					return;

				if (table.getItemCount() == 0)
					return;
				TableItem[] selection = table.getSelection();
				TableItem row = (selection.length == 0) ? table.getItem(table.getTopIndex()) : selection[0];
				table.showItem(row);
				cursor.setSelection(row, 0);
				cursor.setVisible(true);				
				cursor.setFocus();
			}
		});
		return cursor;
	}
	
	/** 
	 * this is called whenever the content of the table has changed, it 
	 * will reconcile the cursor and any listeners that we need
	 */ 
	
	public void refresh() {
		// the number of rows and columns could have changed, ideally we should track
		// which columns have listeners but for the now, we'll just remove and readd
		int columns = table.getColumnCount();
		for (int i = 0; i < columns; i++) {
			TableColumn column = table.getColumn(i);
			column.removeListener(SWT.Resize, resizeListener);
		}
		for (int i = 0; i < columns; i++) {
			TableColumn column = table.getColumn(i);
			column.addListener(SWT.Resize, resizeListener);
		}
		
		// reset the row and column to be a valid one
		//boolean repaint = false;
		if (row >= table.getItemCount()) {
			row = table.getItemCount()-1;
		}
		if (column >= columns) {
			column = columns - 1;
		}
		
		// check to see what the selection is, and reset the tablecursor to 
		// a valid row/column in that selection
		
		TableItem[] selection = table.getSelection();
		if (selection.length == 0) {
			row = -1;
		}
		else {
//			// there is a selection, so make sure our table cursor is in that selection
//			// range
//			int min = 999999;
//			int max = -1;
//			for (int i = 0; i < selection.length; i++) {
//				int temp = table.indexOf(selection[i]);
//				min = Math.min(temp, min);
//				max = Math.max(temp, max);
//			}
//			if (row > max || row < min)
//				row = min;
		}
		setSelection(row, column);
	}

	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Selection, typedListener);
		addListener(SWT.DefaultSelection, typedListener);
	}

	void dispose(Event event) {
		Display display = getDisplay();
		display.asyncExec(new Runnable() {
			public void run() {
				if (table.isDisposed())
					return;
				table.removeListener(SWT.FocusIn, tableListener);
				table.removeListener(SWT.MouseDown, tableListener);
				int columns = table.getColumnCount();
				for (int i = 0; i < columns; i++) {
					TableColumn column = table.getColumn(i);
					column.removeListener(SWT.Resize, resizeListener);
				}
				ScrollBar hBar = table.getHorizontalBar();
				if (hBar != null) {
					hBar.removeListener(SWT.Selection, resizeListener);
				}
				ScrollBar vBar = table.getVerticalBar();
				if (vBar != null) {
					vBar.removeListener(SWT.Selection, resizeListener);
				}
			}
		});
	}

	void keyDown(Event event) {
		switch (event.character) {
			case SWT.CR :
				notifyListeners(SWT.DefaultSelection, new Event());
				return;
		}
		switch (event.keyCode) {
			case SWT.ARROW_UP :
				if (column < 0)
					column = 0;
				setRowColumn(row - 1, column, true);
				break;
			case SWT.ARROW_DOWN :
			if (column < 0)
				column = 0;
				setRowColumn(row + 1, column, true);
				break;
			case SWT.ARROW_LEFT :
			case SWT.ARROW_RIGHT :
				{
					if (column < 0)
						column = 0;
					int leadKey = (getStyle() & SWT.RIGHT_TO_LEFT) != 0 ? SWT.ARROW_RIGHT : SWT.ARROW_LEFT;
					if (event.keyCode == leadKey) {
						setRowColumn(row, column - 1, true);
					} else {
						setRowColumn(row, column + 1, true);
					}
					break;
				}
			case SWT.HOME :
				if (column < 0)
					column = 0;
				setRowColumn(0, column, true);
				break;
			case SWT.END :
				{
					if (column < 0)
						column = 0;
					int row = table.getItemCount() - 1;
					setRowColumn(row, column, true);
					break;
				}
		}
	}

	void paint(Event event) {
		GC gc = event.gc;
		Display display = getDisplay();
		gc.setBackground(display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
		gc.setForeground(display.getSystemColor(SWT.COLOR_LIST_SELECTION));
		gc.fillRectangle(event.x, event.y, event.width, event.height);
		TableItem item = null;
		if (row >= 0)
			item = table.getItem(row);
		int x = 0, y = 0;
		Point size = getSize();
		if (item != null) {
			Image image = item.getImage(column);
			if (image != null) {
				Rectangle imageSize = image.getBounds();
				int imageY = y + (int) (((float)size.y - (float)imageSize.height) / 2.0);
				gc.drawImage(image, x, imageY);
				x += imageSize.width;
			}
			x += (column == 0) ? 2 : 6;
			int textY = y + (int) (((float)size.y - (float)gc.getFontMetrics().getHeight()) / 2.0);
			gc.drawString(item.getText(column), x, textY);
		}
		
		if (isFocusControl()) {
			gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
			gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
			gc.drawFocus(0, 0, size.x, size.y);
		}

	}

	void tableFocusIn(Event event) {
		if (isDisposed())
			return;
		if (isVisible()) {
			setFocus();
			redraw();
		}
	}

	void tableMouseDown(Event event) {
		event.doit = true;
		if (isDisposed() || !isVisible())
			return;
		Point pt = new Point(event.x, event.y);
		Rectangle clientRect = table.getClientArea();
		int columns = table.getColumnCount();
		int start = table.getTopIndex();
		int end = table.getItemCount();
		for (int row = start; row < end; row++) {
			TableItem item = table.getItem(row);
			for (int column = 0; column < columns; column++) {
				Rectangle rect = item.getBounds(column);
				if (rect.y > clientRect.y + clientRect.height)
					return;
				if (rect.contains(pt)) {
					setRowColumn(row, column, true);
					//setFocus();
					return;
				}
			}
		}
	}

	void traverse(Event event) {
		switch (event.detail) {
			case SWT.TRAVERSE_ARROW_NEXT :
			case SWT.TRAVERSE_ARROW_PREVIOUS :
			case SWT.TRAVERSE_RETURN :
				event.doit = false;
				return;
		}
		event.doit = true;
	}

	void setRowColumn(int row, int column, boolean notify) {
		if (0 <= row && row < table.getItemCount()) {
			if (0 <= column && column < table.getColumnCount()) {
				this.row = row;
				this.column = column;
				TableItem item = table.getItem(row);
				table.showItem(item);
				setBounds(item.getBounds(column));
//				redraw();
				if (notify) {
					notifyListeners(SWT.Selection, new Event());
				}
			}
		}
		redraw();
		updateVisible();
	}

	@Override
	public void setVisible(boolean visible) {
		checkWidget();
		userVisible = visible;
		resize();
	}

	void resize() {
		if (row >= 0 && row < table.getItemCount()) {
			TableItem item = table.getItem(row);
			setBounds(item.getBounds(column));
		}
		updateVisible();
	}
	
	void updateVisible() {
		progVisible = false;
		if (0 <= row && row < table.getItemCount()) {
			if (0 <= column && column < table.getColumnCount()) {
				progVisible = true;
			}
		}
		super.setVisible(progVisible && userVisible);
	}

	public int getColumn() {
		checkWidget();
		return column;
	}

	public TableItem getRow() {
		checkWidget();
		if (table.getItemCount() == 0)
			return null;
		return table.getItem(row);
	}

	public void setSelection(int row, int column) {
		checkWidget();
		setRowColumn(row, column, false);
	}
	
	public void setSelection(TableItem row, int column) {
		checkWidget();
		setRowColumn(table.indexOf(row), column, false);
	}
	
	private void initAccessible() {
		final Accessible accessible = getAccessible();
		if (accessAdapter == null) {
			accessAdapter = new AccessibleAdapter() {
				@Override
				public void getName(AccessibleEvent e) {
					String name = null;
					TableItem item = null;
					TableColumn[] tableColumns = table.getColumns();
					TableColumn thisCol = null;

					if (row >= 0 && row < table.getItemCount() && column >= 0 && column < table.getColumnCount()) {
						item = table.getItem(row);
						if (column >= 0 && column < tableColumns.length) {
							thisCol = tableColumns[column];
						}
					}
					if (item != null) {
						if (thisCol != null)
							name = thisCol.getText();
						if (name != null && name.length() > 0)
							name = name + "=" + item.getText(column); //$NON-NLS-1$
						else
							name = item.getText(column);
					}
					e.result = name;
				}

				@Override
				public void getHelp(AccessibleEvent e) {
					String help = null;
					e.result = help;
				}
				@Override
				public void getKeyboardShortcut(AccessibleEvent e) {
				}
			};

			accessControlAdapter = new AccessibleControlAdapter() {
				@Override
				public void getChildAtPoint(AccessibleControlEvent e) {
					Point testPoint = toControl(new Point(e.x, e.y));
					int childID = ACC.CHILDID_NONE;
					if (childID == ACC.CHILDID_NONE) {
						Rectangle location = getBounds();
						location.height = location.height - getClientArea().height;
						if (location.contains(testPoint)) {
							childID = ACC.CHILDID_SELF;
						}
					}
					e.childID = childID;
				}

				@Override
				public void getLocation(AccessibleControlEvent e) {
					Rectangle location = null;
					int childID = e.childID;
					if (childID == ACC.CHILDID_SELF) {
						location = getBounds();
					}
					if (location != null) {
						Point pt = toDisplay(new Point(location.x, location.y));
						e.x = pt.x;
						e.y = pt.y;
						e.width = location.width;
						e.height = location.height;
					}
				}

				@Override
				public void getChildCount(AccessibleControlEvent e) {
					e.detail = 0;
				}

				@Override
				public void getDefaultAction(AccessibleControlEvent e) {
					String action = "Edit field"; 
					e.result = action;
				}

				@Override
				public void getFocus(AccessibleControlEvent e) {
					int childID = ACC.CHILDID_NONE;
					if (isFocusControl()) {
						childID = ACC.CHILDID_SELF;
					}
					e.childID = childID;
				}

				@Override
				public void getRole(AccessibleControlEvent e) {
					int role = 0;
					int childID = e.childID;
					if (childID == ACC.CHILDID_SELF)
						role = ACC.ROLE_LISTITEM;
					e.detail = role;
				}

				@Override
				public void getSelection(AccessibleControlEvent e) {
					e.childID = ACC.CHILDID_NONE;
				}

				@Override
				public void getState(AccessibleControlEvent e) {
					int state = 0;
					int childID = e.childID;
					if (childID == ACC.CHILDID_SELF) {
						state = ACC.STATE_SELECTABLE;
						if (isFocusControl()) {
							state |= ACC.STATE_FOCUSABLE;
							if (TableCursor.this.hasFocus) {
								state += ACC.STATE_FOCUSED | ACC.STATE_SELECTED;
							}
						}
					}
					e.detail = state;
				}

				@Override
				public void getChildren(AccessibleControlEvent e) {
					e.children = null;
				}
			};

			selectionAccessListener = new Listener() {
				public void handleEvent(Event event) {
					accessible.setFocus(ACC.CHILDID_SELF);
				}
			};

			focusAccessListener = new Listener() {
				public void handleEvent(Event event) {
					accessible.setFocus(ACC.CHILDID_SELF);
				}
			};
		}

		if (hasAccessibility) {
			accessible.removeAccessibleListener(accessAdapter);
			accessible.removeAccessibleControlListener(accessControlAdapter);
			removeListener(SWT.Selection, selectionAccessListener);
			removeListener(SWT.FocusIn, focusAccessListener);
			hasAccessibility = false;
		}
		if (hasAccessibility == false) {
			accessible.addAccessibleListener(accessAdapter);
			accessible.addAccessibleControlListener(accessControlAdapter);
			addListener(SWT.Selection, selectionAccessListener);
			addListener(SWT.FocusIn, focusAccessListener);
			hasAccessibility = true;
		}
	}	
}

