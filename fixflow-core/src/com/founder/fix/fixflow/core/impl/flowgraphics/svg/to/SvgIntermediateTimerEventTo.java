package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.component.SvgIntermediateTimerEventComponent;

public class SvgIntermediateTimerEventTo extends SvgShapeBaseTo {

	private String inherit = "inherit";
	
	public String getInherit() {
		return inherit;
	}



	public void setInherit(String inherit) {
		this.inherit = inherit;
	}



	public String getComponentClass() {
		return SvgIntermediateTimerEventComponent.class.getCanonicalName();
	}
	
}
