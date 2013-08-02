/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author kenshin
 */
package com.founder.fix.fixflow.core.impl.flowgraphics.svg.to;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.component.SvgAdHocSubProcessComponent;

public class SvgAdHocSubProcessTo extends SvgShapeBaseTo {
	
	
	/**
	 * 任务节点循环类型默认值
	 */
	protected static final LoopType LoopType_DEFAULT = LoopType.NoLoop;
	
	/**
	 * 任务节点循环类型
	 */
	protected LoopType loopType=LoopType_DEFAULT;
	

	public String getComponentClass() {
		return SvgAdHocSubProcessComponent.class.getCanonicalName();
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
