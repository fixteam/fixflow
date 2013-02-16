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

import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;

public class Bpmn2SectionDescriptor extends AbstractSectionDescriptor {

		protected String id;
		protected String tab;
		protected AbstractPropertySection sectionClass;
		protected Class appliesToClass;
		protected String enablesFor;
		protected String filter;
		
		public Bpmn2SectionDescriptor(Bpmn2TabDescriptor td, IConfigurationElement e) {
			tab = td.getId();
			id = tab + ".section";

			try {
				sectionClass = (AbstractPropertySection) e.createExecutableExtension("class");
				filter = e.getAttribute("filter");
				if (filter==null || filter.isEmpty())
					filter = "org.eclipse.bpmn2.modeler.ui.property.Bpmn2PropertyFilter";
				enablesFor = e.getAttribute("enablesFor");
				String type = e.getAttribute("type");
				if (type!=null && !type.isEmpty())
					appliesToClass = Class.forName(type);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			td.getSectionDescriptors().add(this);
		}
		
		@Override
		public String getId() {
			return id;
		}

		@Override
		public ISection getSectionClass() {
			return sectionClass;
		}

		@Override
		public String getTargetTab() {
			return tab;
		}

		@Override
		public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
			
			// should we delegate to the section to determine whether it should be included in this tab?
			if (sectionClass instanceof IBpmn2PropertySection) {
				return ((IBpmn2PropertySection)sectionClass).appliesTo(part, selection);
			}
			
			// if an input description was specified, check if the selected business object is of this description. 
			if (appliesToClass!=null) {
				PictogramElement pe = BusinessObjectUtil.getPictogramElementForSelection(selection);
				// this is a special hack to allow selection of connection decorator labels:
				// the connection decorator does not have a business object linked to it,
				// but its parent (the connection) does.
				if (pe.getLink()==null && pe.eContainer() instanceof PictogramElement)
					pe = (PictogramElement)pe.eContainer();

				// check all linked BusinessObjects for a match
				if (pe.getLink()!=null) {
					for (EObject eObj : pe.getLink().getBusinessObjects()){
						if (appliesToClass.isInstance(eObj)) {
							return true;
						}
					}
				}
				return false;
			}
			return true;
		}

		@Override
		public int getEnablesFor() {
			try {
				return Integer.parseInt(enablesFor);
			}
			catch (Exception ex) {
				
			}
			return super.getEnablesFor();
		}

		@Override
		public IFilter getFilter() {
			return new IFilter() {

				@Override
				public boolean select(Object toTest) {
					return false;
				}
				
			};
		}

		@Override
		public List getInputTypes() {
			return super.getInputTypes();
		}

		/**
		 * @param replacedId
		 * @param part
		 * @param selection
		 * @return
		 */
		public boolean doReplaceTab(String replacedId, IWorkbenchPart part, ISelection selection) {
			if (sectionClass instanceof IBpmn2PropertySection) {
				return ((IBpmn2PropertySection)sectionClass).doReplaceTab(replacedId, part, selection);
			}
			return appliesTo(part,selection);
		}
		
	}