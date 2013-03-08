package com.founder.fix.fixflow.designer.modeler.ui.property.callactivity;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class FlowFilter extends ViewerFilter {
	private String searchString;

	public void setSearchText(String s) {
		// Search must be a substring of the existing value
		this.searchString = ".*" + s + ".*";
	}
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// TODO Auto-generated method stub
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		ProcessEntity p = (ProcessEntity) element;
		if (p.getId().matches(searchString)) {
			return true;
		}
		if (p.getName().matches(searchString)) {
			return true;
		}
		return false;
	}

}
