package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;



import com.founder.fix.fixflow.core.impl.flowgraphics.svg.component.SvgLineComponent;


public class SvgLineTo extends SvgLineBaseTo {

	
	protected LineType lineType;
	

	public String getComponentClass() {
		return SvgLineComponent.class.getCanonicalName();
	}
	
	
	
	public LineType getLineType() {
		return lineType;
	}

	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	
	
	
	
	
	
	/**
	 * 线条类型枚举
	 * @author kenshin
	 *
	 */
	public enum LineType
	{
		/**
		 * 顺序线条
		 */
		SequenceFlow,
		/**
		 * 默认线条
		 */
		DefaultFlow,
		/**
		 * 条件线条
		 */
		ConditionalFlow
	}
	
}
