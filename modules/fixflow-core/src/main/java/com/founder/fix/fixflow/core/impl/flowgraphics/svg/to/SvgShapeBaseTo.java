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

public abstract class SvgShapeBaseTo extends SvgBaseTo {
	
	

	protected static final float HEIGHT_EDEFAULT = 0.0F;
	
	/**
	 * 高度
	 */
	protected float height = HEIGHT_EDEFAULT;


	
	
	protected static final float WIDTH_EDEFAULT = 0.0F;
	
	/**
	 * 宽度
	 */
	protected float width = WIDTH_EDEFAULT;


	
	
	
	protected static final float X_EDEFAULT = 0.0F;

	/**
	 * 左的距离
	 */
	protected float x = X_EDEFAULT;


	
	
	
	protected static final float Y_EDEFAULT = 0.0F;

	/**
	 * 距上的距离
	 */
	protected float y = Y_EDEFAULT;


	
	
	/**
	 *  节点的文本
	 */
	protected String label;
	
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float newHeight) {
		this.height = newHeight;
	}

	public float getWidth() {
		return this.width;
	}

	public void setWidth(float newWidth) {
		this.width = newWidth;
	}

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
