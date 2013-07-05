package com.founder.fix.fixflow.designer.jobconfig;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TimeTaskFilter extends ViewerFilter {
	private String searchString;

	public void setSearchText(String s) {
		// Search must be a substring of the existing value
		this.searchString = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchString == null || searchString.length() == 0) {
			return true;
		}
		JobTo jobTo = (JobTo) element;
		if (jobTo.getProcessName().matches(searchString)) {
			return true;
		}
		return false;
	}

}
