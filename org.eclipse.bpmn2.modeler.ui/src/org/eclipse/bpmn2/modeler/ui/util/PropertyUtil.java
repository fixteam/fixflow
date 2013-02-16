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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.util;

import java.util.Collection;
import java.util.Hashtable;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.adapters.AdapterUtil;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class PropertyUtil {

	public static String deCamelCase(String string) {
		return string.replaceAll("([A-Z][a-z])", " $0").substring(1);
	}

	public static void disposeChildWidgets(Composite parent) {
		Control[] kids = parent.getChildren();
		for (Control k : kids) {
			if (k instanceof Composite) {
				disposeChildWidgets((Composite)k);
			}
			k.dispose();
		}
	}

	public static void layoutAllParents(Composite child) {
		Composite parent = child;
		while (parent!=null && parent.getParent() instanceof Composite) {
			parent = parent.getParent(); 
			parent.layout();
		}
	}
	
	// Debugging utilities for widget trees.
	public static void check(Control control) {
		String name = control.getClass().getSimpleName();
		if (control.isDisposed()) {
			System.err.println(name+" disposed!");
			return;
		}
		if (control instanceof Composite) {
			((Composite)control).layout(true);
		}
		control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		Point sz = control.getSize();
		if (sz.x==0 || sz.y==0)
			System.err.println(name+" zero size!");
	}

	public static void dump(Composite parent, String comment) {
		System.out.println(comment);
		int i = 1;
		System.out.println("parent="+parent.hashCode());
		check(parent);

		Composite p = parent.getParent();
		while (p!=null) {
			check(p);
			p = p.getParent();
			++i;
		}
		dump(parent,0);
	}
	
	public static void dump(Composite parent, int indent) {
		Control[] kids = parent.getChildren();
		for (Control k : kids) {
			for (int i=0; i<indent; ++i)
				System.out.print("|");
			System.out.print(" "+k);
			check(k);
			
			if (k instanceof Label) {
				System.out.print(((Label)k).getText());
			}
//			System.out.println("");
			if (k instanceof Composite) {
				dump((Composite)k, indent+1);
			}
		}
	}

	/**
	 * Ugly hack to force layout of the entire widget tree of the property sheet page.
	 * @param parent
	 */
	public static void recursivelayout(Composite parent) {
		Control[] kids = parent.getChildren();
		for (Control k : kids) {
			if (k.isDisposed())
				Activator.logError(new SWTException("Widget is disposed."));
			if (k instanceof Composite) {
				recursivelayout((Composite)k);
				((Composite)k).layout(true);
			}
		}
		parent.layout(true);
	}

	/*
	 * Various model object and feature UI property methods
	 */
	public static String getLabel(Object object) {
		String label = "";
		if (object instanceof EObject) {
			EObject eObject = (EObject)object;
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(eObject, ExtendedPropertiesAdapter.class);
			if (adapter!=null)
				label = adapter.getObjectDescriptor().getLabel(eObject);
			else
				label = ModelUtil.toDisplayName( eObject.eClass().getName() );
		}
		else
			label = object.toString();
		label = label.replaceAll(" Ref$", "");
		return label;
	}
	
	public static String getLabel(EObject object, EStructuralFeature feature) {
		String label = "";
		ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		if (adapter!=null)
			label = adapter.getFeatureDescriptor(feature).getLabel(object);
		else
			label = ModelUtil.toDisplayName( feature.getName() );
		label = label.replaceAll(" Ref$", "");
		return label;
	}
	
	public static String getText(Object object) {
		if (object instanceof EObject) {
			EObject eObject = (EObject)object;
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(eObject, ExtendedPropertiesAdapter.class);
			if (adapter!=null)
				return adapter.getObjectDescriptor().getText(eObject);
			return getDisplayName(eObject);
		}
		return object.toString();
	}
	
	public static String getText(EObject object, EStructuralFeature feature) {
		if (feature==null)
			return getText(object);
		
		ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		if (adapter!=null)
			return adapter.getFeatureDescriptor(feature).getText(object);
		return getDisplayName(object, feature);
	}

	public static boolean getIsMultiLine(EObject object, EStructuralFeature feature) {
		ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		if (adapter!=null)
			return adapter.getFeatureDescriptor(feature).isMultiLine(object);
		return false;
	}

	public static Hashtable<String, Object> getChoiceOfValues(EObject object, EStructuralFeature feature) {
		ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		if (adapter!=null)
			return adapter.getFeatureDescriptor(feature).getChoiceOfValues(object);
		return null;
	}

	public static EObject createObject(EObject object, EStructuralFeature feature) {
		ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		if (adapter!=null)
			return adapter.getFeatureDescriptor(feature).createObject(object);
		return null;
	}

	public static EObject createObject(EObject object, EStructuralFeature feature, EClass eclass) {
		ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		if (adapter!=null)
			return adapter.getFeatureDescriptor(feature).createObject(object, eclass);
		return null;
	}

	public static boolean canEdit(EObject object, EStructuralFeature feature) {
		if (feature.getEType() instanceof EClass) {
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
			if (adapter!=null) {
				Object result = adapter.getProperty(feature, ExtendedPropertiesAdapter.UI_CAN_EDIT);
				if (result instanceof Boolean)
					return ((Boolean)result);
			}
			return true;
		}
		return false;
	}

	public static boolean canEditInline(EObject object, EStructuralFeature feature) {
		if (feature.getEType() instanceof EClass) {
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
			if (adapter!=null) {
				Object result = adapter.getProperty(feature, ExtendedPropertiesAdapter.UI_CAN_EDIT_INLINE);
				if (result instanceof Boolean)
					return ((Boolean)result);
			}
		}
		return false;
	}
	
	public static boolean canCreateNew(EObject object, EStructuralFeature feature) {
		if (feature.getEType() instanceof EClass) {
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
			if (adapter!=null) {
				Object result = adapter.getProperty(feature, ExtendedPropertiesAdapter.UI_CAN_CREATE_NEW);
				if (result instanceof Boolean)
					return ((Boolean)result);
			}
			return true;
		}
		return false;
	}
	
	public static boolean canSetNull(EObject object, EStructuralFeature feature) {
		if (feature.getEType() instanceof EClass) {
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
			if (adapter!=null) {
				Object result = adapter.getProperty(feature, ExtendedPropertiesAdapter.UI_CAN_SET_NULL);
				if (result instanceof Boolean)
					return ((Boolean)result);
			}
			return true;
		}
		return false;
	}
	
	public static boolean isMultiChoice(EObject object, EStructuralFeature feature) {
		if (feature.getEType() instanceof EClass) {
			ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
			if (adapter!=null) {
				Object result = adapter.getProperty(feature, ExtendedPropertiesAdapter.UI_IS_MULTI_CHOICE);
				if (result instanceof Boolean)
					return ((Boolean)result);
			}
		}
		return getChoiceOfValues(object,feature) != null;
	}

	/*
	 * Fallbacks in case a property provider does not exist
	 */
	private static String getDisplayName(EObject object) {
		String objName = null;
		if (object instanceof BPMNDiagram) {
			Bpmn2DiagramType type = ModelUtil.getDiagramType((BPMNDiagram)object); 
			if (type == Bpmn2DiagramType.CHOREOGRAPHY) {
				objName = "Choreography Diagram";
			}
			else if (type == Bpmn2DiagramType.COLLABORATION) {
				objName = "Collaboration Diagram";
			}
			else if (type == Bpmn2DiagramType.PROCESS) {
				objName = "Process Diagram";
			}
		}
		if (objName==null){
			objName = ModelUtil.toDisplayName( object.eClass().getName() );
		}
		EStructuralFeature feature = object.eClass().getEStructuralFeature("name");
		if (feature!=null) {
			String name = (String)object.eGet(feature);
			if (name==null || name.isEmpty())
				name = "Unnamed " + objName;
			else
				name = objName + " \"" + name + "\"";
			return name;
		}
		feature = object.eClass().getEStructuralFeature("id");
		if (feature!=null) {
			if (object.eGet(feature)!=null)
				objName = (String)object.eGet(feature);
		}
		return objName;
	}
	
	private static String getDisplayName(EObject object, EStructuralFeature feature) {
		Object value = object.eGet(feature);
		if (value==null)
			return "";
		return value.toString();
	}
}
