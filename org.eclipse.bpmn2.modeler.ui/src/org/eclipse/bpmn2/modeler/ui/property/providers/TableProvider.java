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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Item;

/**
 * A TableProvider allows clients to register a label provider and an optional
 * ICellModifier for each property they will display in the table.  The TableProvider
 * will delegate ITableLabelProvider and ICellModifier behaviour to the registered
 * providers.
 * 
 * Grouping related behaviours by column type (instead of grouping them by the
 * behaviour type) will hopefully make maintenance of table code easier.
 * 
 * IMPORTANT: The dispose() method of each registered label provider will be called
 * when the TableProvider is disposed.  If this is undesirable, clients can avoid it by
 * unregistering the property before the TableProvider is disposed.   
 */
public class TableProvider implements ITableLabelProvider, ICellModifier  {
	
	protected HashMap propertyToLabelProvider;
	protected HashMap propertyToCellModifier;
	
	protected ArrayList labelProviderListeners; 
	
	// NOTE: do not use columnProperties directly!  Subclasses may change its
	// semantics.  Use getColumnProperty() or getColumnProperties().
	protected String[] columnProperties;

	public TableProvider() {
		this.propertyToLabelProvider = new HashMap();
		this.propertyToCellModifier = new HashMap();
		labelProviderListeners = new ArrayList();
	}

	/**
	 * This method should be called with the properties being used for each column of
	 * the Table.  If a TableViewer is used, these are the same values passed to the
	 * TableViewer's setColumnProperties() method.  However, you must still call both
	 * methods because TableProvider doesn't know about the table viewer.
	 * 
	 * TableProvider needs the columnProperties in order to delegate label provider
	 * behaviour to the correct registered label provider.
	 */
	public void setColumnProperties(String[] columnProperties) {
		this.columnProperties = columnProperties;
	}

	/**
	 * Returns the columnProperties of this TableProvider.
	 */
	public String[] getColumnProperties() {
		return columnProperties;
	}
	
	/**
	 * Returns a particular column's property.  For some subclasses, this may be more
	 * efficient than using getColumnProperties().
	 */
	public String getColumnProperty(int index) {
		return columnProperties[index];
	}

	/**
	 * Unregister the old label provider and cell modifier for the given property.
	 * 
	 * @param property property to unregister.
	 */
	public void unregister(String property) {
		IBaseLabelProvider oldLp = (IBaseLabelProvider)propertyToLabelProvider.remove(property);
		propertyToCellModifier.remove(property);
		if (oldLp != null) {
			// remove all active listeners from the label provider.
			for (Iterator it = labelProviderListeners.iterator(); it.hasNext(); ) {
				oldLp.removeListener((ILabelProviderListener)it.next());
			}
		}
	}

	/**
	 * Unregister the old label provider and cell modifier for the given property.
	 * 
	 * @param property property to register.
	 * @param lp label provider to use for the property.  Must be an ILabelProvider or IBaseLabelProvider.
	 * @param cm ICellModifier to use for the property.  May be null.
	 */
	public void register(String property, IBaseLabelProvider lp, ICellModifier cm) {
		unregister(property);
		// add all active listeners to the label provider.
		for (Iterator it = labelProviderListeners.iterator(); it.hasNext(); ) {
			lp.addListener((ILabelProviderListener)it.next());
		}
		propertyToLabelProvider.put(property, lp);
		propertyToCellModifier.put(property, cm);
	}

	/* ITableLabelProvider */

	public Image getColumnImage(Object element, int columnIndex) {
		// delegate to the appropriate label provider.
		Object lp = propertyToLabelProvider.get(columnProperties[columnIndex]);
		if (lp instanceof ITableLabelProvider) {
			return ((ITableLabelProvider)lp).getColumnImage(element, columnIndex);
		}
		if (lp instanceof ILabelProvider) {
			return ((ILabelProvider)lp).getImage(element);
		}
		// no label provider found.
		return null;
	}
	
	public String getColumnText(Object element, int columnIndex) {
		// delegate to the appropriate label provider.
		Object lp = propertyToLabelProvider.get(columnProperties[columnIndex]);
		if (lp instanceof ITableLabelProvider) {
			return ((ITableLabelProvider)lp).getColumnText(element, columnIndex);
		}
		if (lp instanceof ILabelProvider) {
			return ((ILabelProvider)lp).getText(element);
		}
		// no label provider found.
		return null;
	}

	/* IBaseLabelProvider */

	public void addListener(ILabelProviderListener listener) {
		labelProviderListeners.add(listener);
		// add the listener to all label providers.
		for (Iterator it = propertyToLabelProvider.values().iterator(); it.hasNext(); ) {
			((IBaseLabelProvider)it.next()).addListener(listener);
		}
	}
	public void removeListener(ILabelProviderListener listener) {
		labelProviderListeners.add(listener);
		// remove the listener from all label providers.
		for (Iterator it = propertyToLabelProvider.values().iterator(); it.hasNext(); ) {
			((IBaseLabelProvider)it.next()).removeListener(listener);
		}
	}
	public void dispose() {
		// dispose all registered label providers.
		for (Iterator it = propertyToLabelProvider.values().iterator(); it.hasNext(); ) {
			((IBaseLabelProvider)it.next()).dispose();
		}
	}
	public boolean isLabelProperty(Object element, String property) {
		// TODO: simplify this by just always returning true?

		// return true if any registered label provider returns true.
		for (Iterator it = propertyToLabelProvider.values().iterator(); it.hasNext(); ) {
			if (((IBaseLabelProvider)it.next()).isLabelProperty(element, property))  return true;
		}
		return false;
	}

	/* ICellModifier */

	public boolean canModify(Object element, String property) {
		// delegate to the appropriate cell modifier.
		ICellModifier cm = (ICellModifier)propertyToCellModifier.get(property);
		if (cm != null)  return cm.canModify(element, property);
		// no cell modifier found.
		return false;
	}
	public Object getValue(Object element, String property) {
		// delegate to the appropriate cell modifier.
		ICellModifier cm = (ICellModifier)propertyToCellModifier.get(property);
		if (cm != null)  return cm.getValue(element, property);
		// no cell modifier found.
		return null;
	}
	public void modify(Object element, String property, Object value) {
		if (element instanceof Item)  element = ((Item)element).getData();
		// delegate to the appropriate cell modifier.
		ICellModifier cm = (ICellModifier)propertyToCellModifier.get(property);
		if (cm != null)  { cm.modify(element, property, value); return; }
		// no cell modifier found.
		// do nothing
	}	
}
