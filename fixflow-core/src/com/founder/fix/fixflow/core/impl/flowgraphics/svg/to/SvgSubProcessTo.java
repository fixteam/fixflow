package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.component.SvgSubProcessComponent;

public class SvgSubProcessTo extends SvgShapeBaseTo {
	
	
	/**
	 * 任务节点循环类型默认值
	 */
	protected static final LoopType LoopType_DEFAULT = LoopType.NoLoop;
	
	/**
	 * 任务节点循环类型
	 */
	protected LoopType loopType=LoopType_DEFAULT;
	

	public String getComponentClass() {
		return SvgSubProcessComponent.class.getCanonicalName();
	}

	/**
	 * 获取循环类型
	 * @return
	 */
	public LoopType getLoopType() {
		return loopType;
	}


	/**
	 * 设置循环类型
	 * @param loopType
	 */
	public void setLoopType(LoopType loopType) {
		this.loopType = loopType;
	}
	
	

}
