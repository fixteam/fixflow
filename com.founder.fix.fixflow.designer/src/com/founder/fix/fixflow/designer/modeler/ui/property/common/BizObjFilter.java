package com.founder.fix.fixflow.designer.modeler.ui.property.common;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.founder.fix.fixflow.designer.expand.dataobj.DataObjImportImpl;

public class BizObjFilter extends ViewerFilter {

	private String searchString;

	public void setSearchText(String s) {
		// Search must be a substring of the existing value
		this.searchString = s + ".*";
	}
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO Auto-generated method stub
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		DataObjImportImpl p = (DataObjImportImpl) element;
		if (p.getId().matches(searchString)
				|| p.getId().matches(searchString.toUpperCase())) {
			return true;
		}
		if (p.getName().matches(searchString)
				|| p.getName().matches(searchString.toUpperCase())) {
			return true;
		}
		return false;
	}

}
