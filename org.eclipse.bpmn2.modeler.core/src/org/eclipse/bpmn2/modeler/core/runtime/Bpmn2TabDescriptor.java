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
package org.eclipse.bpmn2.modeler.core.runtime;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabContents;

public class Bpmn2TabDescriptor extends AbstractTabDescriptor {

	protected String id;
	protected String category;
	protected String label;
	protected String afterTab = null;
	protected String replaceTab = null;
	protected boolean indented = false;
	protected Image image = null;
	
	public Bpmn2TabDescriptor(IConfigurationElement e) {
		id = e.getAttribute("id");
		category = e.getAttribute("category");
		if (category==null || category.isEmpty())
			category = "BPMN2";
		label = e.getAttribute("label");
		afterTab = e.getAttribute("afterTab");
		replaceTab = e.getAttribute("replaceTab");
		String s = e.getAttribute("indented");
		indented = s!=null && s.trim().equalsIgnoreCase("true");
	}
	
	public Bpmn2TabDescriptor(String id, String category, String label) {
		this.id = id;
		if (category==null || category.isEmpty() )
			category = "BPMN2";
		this.category = category;
		this.label = label;
	}
	
	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String getAfterTab() {
		if (afterTab==null || afterTab.trim().length()==0)
			return super.getAfterTab();
		return afterTab;
	}

	@Override
	public Image getImage() {
		if (image==null)
			return super.getImage();
		return image;
	}

	@Override
	public TabContents createTab() {
		// TODO Auto-generated method stub
		return super.createTab();
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return super.isSelected();
	}

	@Override
	public void setSectionDescriptors(List sectionDescriptors) {
		// TODO Auto-generated method stub
		super.setSectionDescriptors(sectionDescriptors);
	}

	@Override
	public boolean isIndented() {
		return indented;
	}

	@Override
	public Object clone() {
		Bpmn2TabDescriptor clone = new Bpmn2TabDescriptor(id, category, label);
		clone.afterTab = this.afterTab;
		clone.replaceTab = this.replaceTab;
		if (image!=null)
			clone.image = new Image(Display.getDefault(), this.image, SWT.IMAGE_COPY);
		clone.indented = this.indented;
//		for (Bpmn2SectionDescriptor sd : (List<Bpmn2SectionDescriptor>)getSectionDescriptors()) {
//			clone.getSectionDescriptors().add( new Bpmn2SectionDescriptor(sd) );
//		}
		return clone;
	}
	
	public String getReplaceTab() {
		if (replaceTab==null || replaceTab.trim().length()==0)
			return null;
		return replaceTab;
	}
	
	public boolean isReplacementForTab(String id) {
		String replacements = getReplaceTab();
		if (replacements!=null) {
			String[] rep = replacements.split(" ");
			for (String r : rep) {
				if (r.equals(id))
					return true;
			}
		}
		return false;
	}
}