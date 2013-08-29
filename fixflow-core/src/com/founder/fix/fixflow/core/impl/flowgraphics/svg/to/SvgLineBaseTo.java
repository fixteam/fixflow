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
