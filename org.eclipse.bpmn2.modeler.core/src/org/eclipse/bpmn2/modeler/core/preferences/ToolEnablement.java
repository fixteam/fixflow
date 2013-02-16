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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.preferences;

import java.util.ArrayList;

import org.eclipse.bpmn2.modeler.core.AbstractPropertyChangeListenerProvider;
import org.eclipse.emf.ecore.ENamedElement;

public class ToolEnablement extends AbstractPropertyChangeListenerProvider {

	private ENamedElement tool;
	private Boolean enabled;

	private ArrayList<ToolEnablement> children = new ArrayList<ToolEnablement>();
	private ToolEnablement parent;

	public ToolEnablement() {
	}

	public ToolEnablement(ENamedElement tool, ToolEnablement parent) {
		this.tool = tool;
		this.parent = parent;
	}

	public String getName() {
		return tool.getName();
	}

	public String getPreferenceName() {
		if (parent == null) {
			return tool.getName();
		} else {
			return parent.getPreferenceName() + "." + getName();
		}
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setTool(ENamedElement tool) {
		this.tool = tool;
	}

	public ENamedElement getTool() {
		return tool;
	}

	public void setChildren(ArrayList<ToolEnablement> children) {
		this.children = children;
	}

	public ArrayList<ToolEnablement> getChildren() {
		return children;
	}

	public void setParent(ToolEnablement parent) {
		this.parent = parent;
	}

	public ToolEnablement getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return "ToolEnablement [tool=" + getName() + ", enabled=" + enabled + ", children=" + children + ", parent="
				+ (parent == null ? "null" : parent.getName()) + "]";
	}

	public boolean isAnyChildren() {
		return children != null && children.size() > 0;

	}
}