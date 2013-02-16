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

package org.eclipse.bpmn2.modeler.ui.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.swt.widgets.Composite;

public abstract class ExtensionValueTableComposite extends AbstractBpmn2TableComposite {

	EStructuralFeature extensionValueFeature;

	/**
	 * @param parent
	 * @param style
	 */
	public ExtensionValueTableComposite(Composite parent, int style) {
		super(parent, style);
	}
	
	public void bindList(EObject object, EStructuralFeature feature) {
		extensionValueFeature = feature;
		listItemClass = (EClass)feature.getEType();
		EStructuralFeature evf = object.eClass().getEStructuralFeature("extensionValues");
		super.bindList(object,evf);
	}
	
	/* (non-Javadoc)
	 * Subclasses must provide this because each extensionValue has its own set of attributes
	 *
	 * @see org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2TableComposite#addListItem(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	protected abstract EObject addListItem(EObject object, EStructuralFeature feature);
	
	@SuppressWarnings("unchecked")
	protected void addExtensionValue(EObject value) {
		EStructuralFeature evf = object.eClass().getEStructuralFeature("extensionValues");
		EList<EObject> list = (EList<EObject>)object.eGet(evf);
		
		if (list.size()==0) {
			ExtensionAttributeValue newItem = FACTORY.createExtensionAttributeValue();
			FeatureMap map = newItem.getValue();
			map.add(extensionValueFeature, value);
			list.add(newItem);
		}
		else {
			ExtensionAttributeValue oldItem = (ExtensionAttributeValue) list.get(0);
			FeatureMap map = oldItem.getValue();
			map.add(extensionValueFeature, value);
		}
	}
	
	protected Object removeListItem(EObject object, EStructuralFeature feature, int index) {
		EList<EObject> list = (EList<EObject>)object.eGet(feature);
		int i = 0;
		int iRemove = -1;
		FeatureMap fmRemove = null;
		Entry result = null;
		for (EObject o : list) {
			ExtensionAttributeValue eav = (ExtensionAttributeValue)o;
			FeatureMap fm = eav.getValue();
			for (Entry e : fm) {
				EStructuralFeature sf = e.getEStructuralFeature();
				if (sf == extensionValueFeature) {
					if (i==index) {
						iRemove = fm.indexOf(e);
						fmRemove = fm;
					}
					else if (i==index-1 || i==index+1)
						result = e;
					++i;
				}
			}
		}
		if (fmRemove!=null) {
			fmRemove.remove(iRemove);
		}
		return result==null ? null : result.getValue();
	}

	protected Object moveListItemUp(EObject object, EStructuralFeature feature, int index) {
		EList<EObject> list = (EList<EObject>)object.eGet(feature);
		Entry result = null;
		int i = 0;
		int iFrom = -1;
		int iTo = -1;
		FeatureMap fmFrom = null;
		FeatureMap fmTo = null;
		for (int iList=0; iList<list.size(); ++iList) {
			EObject o = list.get(iList);
			ExtensionAttributeValue eav = (ExtensionAttributeValue)o;
			FeatureMap fm = eav.getValue();
			for (int iMap=0; iMap<fm.size(); ++iMap) {
				Entry e = fm.get(iMap);
				EStructuralFeature sf = e.getEStructuralFeature();
				if (sf == extensionValueFeature) {
					if (i==index-1) {
						fmTo = fm;
						iTo = fm.indexOf(e);
					}
					else if (i==index) {
						fmFrom = fm;
						iFrom = fm.indexOf(e);
						break;
					}
					++i;
				}
			}
			if (iFrom>=0 && iTo>=0) {
				if (fmFrom == fmTo) {
					result = fmTo.get(iFrom);
					fmTo.move(iTo, iFrom);
				}
				else {
					result = fmFrom.remove(iFrom);
					fmTo.add(iTo,result);
				}
				break;
			}
		}
		return result==null ? null : result.getValue();
	}

	protected Object moveListItemDown(EObject object, EStructuralFeature feature, int index) {
		EList<EObject> list = (EList<EObject>)object.eGet(feature);
		Entry result = null;
		
		int i = 0;
		for (EObject o : list) {
			ExtensionAttributeValue eav = (ExtensionAttributeValue)o;
			FeatureMap fm = eav.getValue();
			for (Entry e : fm) {
				EStructuralFeature sf = e.getEStructuralFeature();
				if (sf == extensionValueFeature) {
					++i;
				}
			}
		}
		
		int iFrom = -1;
		int iTo = -1;
		FeatureMap fmFrom = null;
		FeatureMap fmTo = null;
		for (int iList=list.size()-1; iList>=0; --iList) {
			EObject o = list.get(iList);
			ExtensionAttributeValue eav = (ExtensionAttributeValue)o;
			FeatureMap fm = eav.getValue();
			for (int iMap=fm.size()-1; iMap>=0; --iMap) {
				Entry e = fm.get(iMap);
				EStructuralFeature sf = e.getEStructuralFeature();
				if (sf == extensionValueFeature) {
					--i;
					if (i==index+1) {
						fmTo = fm;
						iTo = fm.indexOf(e);
					}
					else if (i==index) {
						fmFrom = fm;
						iFrom = fm.indexOf(e);
						break;
					}
				}
			}
			if (iFrom>=0 && iTo>=0) {
				if (fmFrom == fmTo) {
					result = fmTo.get(iFrom);
					fmTo.move(iTo, iFrom);
				}
				else {
					result = fmFrom.remove(iFrom);
					fmTo.add(iTo,result);
				}
				break;
			}
		}
		return result==null ? null : result.getValue();
	}
	
	@Override
	public TableContentProvider getContentProvider(EObject object, EStructuralFeature feature, EList<EObject>list) {
		if (contentProvider==null) {
			contentProvider = new TableContentProvider(object, feature, list) {

				@Override
				public Object[] getElements(Object inputElement) {
					List<EObject> elements = new ArrayList<EObject>();
					for (EObject o : list) {
						ExtensionAttributeValue eav = (ExtensionAttributeValue)o;
						FeatureMap fm = eav.getValue();
						for (Entry e : fm) {
							EStructuralFeature sf = e.getEStructuralFeature();
							if (sf == extensionValueFeature)
								elements.add((EObject) e.getValue());
						}
					}
					return elements.toArray(new EObject[elements.size()]);
				}

			};
		}
		return contentProvider;
	}
}