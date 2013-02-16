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
package org.eclipse.bpmn2.modeler.core.utils;

import java.util.Comparator;

import org.eclipse.bpmn2.Lane;
import org.eclipse.graphiti.mm.pictograms.Shape;

final class SiblingLaneComparator implements Comparator<Shape> {
	@Override
	public int compare(Shape o1, Shape o2) {
		Lane l1 = BusinessObjectUtil.getFirstElementOfType(o1, Lane.class);
		Lane l2 = BusinessObjectUtil.getFirstElementOfType(o2, Lane.class);

		if (l1 != null && l2 != null && l1.eContainer().equals(l2.eContainer())) {
			int y1 = o1.getGraphicsAlgorithm().getY();
			int y2 = o2.getGraphicsAlgorithm().getY();
			return new Integer(y1).compareTo(y2);
		}
		return 0;
	}
}