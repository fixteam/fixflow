package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

import java.util.ArrayList;
import java.util.List;

public abstract class SvgLineBaseTo extends SvgBaseTo {
	/**
	 *  线条上的文本
	 */
	protected String label;

	/**
	 * 线条的构成点
	 */
	protected List<SvgPoint> svgPointList;

	// get set

	public String getLabel() {
		if(label==null)
			label="";
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<SvgPoint> getSvgPointList() {
		if (this.svgPointList == null) {
			this.svgPointList = new ArrayList<SvgLineTo.SvgPoint>();
		}
		return svgPointList;
	}

	public void setSvgPointList(List<SvgPoint> svgPointList) {
		this.svgPointList = svgPointList;
	}

	public class SvgPoint {

		protected static final float X_EDEFAULT = 0.0F;

		protected float x = X_EDEFAULT;

		protected static final float Y_EDEFAULT = 0.0F;

		protected float y = Y_EDEFAULT;

		public float getX() {
			return this.x;
		}

		public void setX(float newX) {
			this.x = newX;
		}

		public float getY() {

			return this.y;
		}

		public void setY(float newY) {
			this.y = newY;
		}

	}
}
