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
package org.eclipse.bpmn2.modeler.core;

import java.util.Comparator;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.SubChoreography;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.di.DiagramElement;

final class DIZorderComparator implements Comparator<DiagramElement> {
	@Override
	public int compare(DiagramElement a, DiagramElement b) {
		boolean aShape = a instanceof BPMNShape;
		boolean bShape = b instanceof BPMNShape;

		boolean aEdge = a instanceof BPMNEdge;
		boolean bEdge = b instanceof BPMNEdge;

		if (aShape && bEdge) {
			return -1;

		} else if (aEdge && bShape) {
			return 1;
		}
		if (aShape && bShape) {
			return compareShape((BPMNShape) a, (BPMNShape) b);
		}
		return 0;
	}

	private int compareShape(BPMNShape a, BPMNShape b) {
		boolean aIsPool = isPool(a);
		boolean bIsPool = isPool(b);

		// Pools must be the first DI elements as pools can only be placed on diagrams.
		if (aIsPool && bIsPool) {
			return 0;
		} else if (aIsPool && !bIsPool) {
			return -1;
		} else if (!aIsPool && bIsPool) {
			return 1;
		}

		BaseElement aElem = a.getBpmnElement();
		BaseElement bElem = b.getBpmnElement();
		boolean aIsSecondTier = aElem instanceof Lane || aElem instanceof SubProcess
				|| aElem instanceof SubChoreography;
		boolean bIsSecondTier = bElem instanceof Lane || bElem instanceof SubProcess
				|| bElem instanceof SubChoreography;

		if (aIsSecondTier && bIsSecondTier) {
			if (isParent(aElem, bElem)) {
				return -1;
			} else if (isParent(bElem, aElem)) {
				return 1;
			}
			return 0;
		} else if (aIsSecondTier && !bIsSecondTier) {
			return -1;
		} else if (!aIsSecondTier && bIsSecondTier) {
			return 1;
		}

		return 0;
	}

	private boolean isPool(BPMNShape a) {
		return a.getBpmnElement() instanceof Participant && a.getChoreographyActivityShape() == null;
	}

	private boolean isParent(BaseElement parent, BaseElement child) {
		if (child instanceof FlowNode) {
			if (((FlowNode) child).getLanes().contains(parent)) {
				return true;
			} else if (parent instanceof Lane) {
				return isChildParent(parent, child);
			}
		} else if (parent instanceof Lane) {
			if (child instanceof Lane) {
				LaneSet childLaneSet = ((Lane) parent).getChildLaneSet();
				if (childLaneSet == null) {
					return false;
				}
				if (((Lane) parent).getChildLaneSet().getLanes().contains(child)) {
					return true;
				}
				return isChildParent(parent, child);
			}
		}

		return false;
	}

	private boolean isChildParent(BaseElement parent, BaseElement child) {
		LaneSet childLaneSet = ((Lane) parent).getChildLaneSet();
		if (childLaneSet == null) {
			return false;
		}

		List<Lane> lanes = childLaneSet.getLanes();
		for (Lane lane : lanes) {
			if (isParent(lane, child)) {
				return true;
			}
		}
		return false;
	}

}