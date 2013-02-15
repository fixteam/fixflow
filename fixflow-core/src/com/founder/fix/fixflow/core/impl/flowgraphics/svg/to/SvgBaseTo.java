package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

public abstract class SvgBaseTo {
	public abstract String getComponentClass();
	
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
  
}
