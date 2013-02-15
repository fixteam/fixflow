package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.component.SvgLaneComponent;

public class SvgLaneTo extends SvgShapeBaseTo {

	protected boolean isHorizontal;
	
	
	public boolean isHorizontal() {
		return isHorizontal;
	}


	public void setHorizontal(boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}


	public String getComponentClass() {
		return SvgLaneComponent.class.getCanonicalName();
	}
	
}
