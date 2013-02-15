package com.founder.fix.fixflow.core.impl.flowgraphics.svg.component;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgBaseTo;

public interface ISvgComponent {
	
	public final static String local_x = "{x}";
	
	public final static String local_y = "{y}";
	
	public final static String id= "{svg_id}";
	
	public final static String text= "{text}";
	
	public final static String width ="{width}";
	
	public final static String hight ="{hight}";
	
	public String createComponent(SvgBaseTo svgTo);
}
