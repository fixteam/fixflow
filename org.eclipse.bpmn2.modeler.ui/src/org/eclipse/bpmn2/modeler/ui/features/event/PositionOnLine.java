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
package org.eclipse.bpmn2.modeler.ui.features.event;

public class PositionOnLine {

	public enum LineType {
		X, Y, XY, UNKNOWN
	}

	public enum LocationType {
		TOP, TOP_LEFT, TOP_RIGHT, BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT, LEFT, RIGHT, UNKNOWN
	}

	private LineType lineType;
	private LocationType locationType;

	public PositionOnLine(LineType lineType, LocationType locationType) {
		this.lineType = lineType;
		this.locationType = locationType;
	}

	public PositionOnLine(boolean alongLeftX, boolean alongRightX, boolean alongTopY, boolean alongBottomY) {
		boolean alongX = alongLeftX || alongRightX;
		boolean alongY = alongTopY || alongBottomY;

		if (alongX && alongY) {
			lineType = LineType.XY;
		} else if (alongX) {
			lineType = LineType.X;
		} else if (alongY) {
			lineType = LineType.Y;
		} else {
			lineType = LineType.UNKNOWN;
		}

		if (lineType != null) {
			switch (lineType) {
			case XY:
				if (alongLeftX) {
					if (alongTopY) {
						locationType = LocationType.TOP_LEFT;
					} else {
						locationType = LocationType.BOTTOM_LEFT;
					}
				} else if (alongRightX) {
					if (alongTopY) {
						locationType = LocationType.TOP_RIGHT;
					} else {
						locationType = LocationType.BOTTOM_RIGHT;
					}
				}
				break;
			case X:
				if (alongLeftX) {
					locationType = LocationType.LEFT;
				} else {
					locationType = LocationType.RIGHT;
				}
				break;
			case Y:
				if (alongTopY) {
					locationType = LocationType.TOP;
				} else {
					locationType = LocationType.BOTTOM;
				}
				break;
			default:
				locationType = LocationType.UNKNOWN;
				break;
			}
		}

		if (locationType == null) {
			locationType = LocationType.UNKNOWN;
		}
	}

	public LineType getLineType() {
		return lineType;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public boolean isLegalPosition() {
		return lineType != LineType.UNKNOWN && locationType != LocationType.UNKNOWN;
	}

	public static PositionOnLine fromString(String s) {
		if (s == null) {
			return null;
		}
		String[] arr = s.split(":");
		if (!arr[0].equals(PositionOnLine.class.getSimpleName().toLowerCase())) {
			return null;
		}

		return new PositionOnLine(LineType.valueOf(arr[1]), LocationType.valueOf(arr[2]));
	}

	@Override
	public String toString() {
		String prefix = PositionOnLine.class.getSimpleName().toLowerCase();
		String lineType = this.lineType.toString();
		String locationType = this.locationType.toString();
		String colon = ":";
		return new StringBuilder().append(prefix).append(colon).append(lineType).append(colon).append(locationType)
		        .toString();
	}
}