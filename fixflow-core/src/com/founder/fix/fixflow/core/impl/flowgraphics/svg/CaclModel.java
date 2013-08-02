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
package com.founder.fix.fixflow.core.impl.flowgraphics.svg;

import java.util.ArrayList;
import java.util.List;

import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLineBaseTo.SvgPoint;
import com.founder.fix.fixflow.core.impl.flowgraphics.svg.to.SvgLineTo;
public class CaclModel {
	/** 勾股定理, 取线段长度
	 * @param a
	 * @param b
	 * @return
	 */
	public static float segmentLength(SvgPoint a, SvgPoint b){
		double length = 0;
		float width = a.getX()-b.getX();
		float height = a.getY()-b.getY();
		double dWidth = (double)width;
		double dHeight = (double)height;
		
		length = Math.sqrt(Math.pow(dWidth,2)+Math.pow(dHeight,2));
		return (float)length;
	}	
	
	
	/** 统计所有线段的长度
	 * @param points
	 * @return
	 */
	private static List<Float> getSegmentsLength(List<SvgPoint> points){
		List<Float> segList = new ArrayList<Float>();
		
		float segLen = 0f;
		for(int i=0; i< points.size() -1; i++){
			SvgPoint aa = points.get(i);
			SvgPoint bb = points.get(i+1);
			
			segLen = segmentLength(aa, bb);
			segList.add(segLen);
		}
		
		
		return segList;
	}
	
	
	/** 计算中心点
	 * @param svgLine
	 * @return
	 */
	public static SvgPoint caclCenterPoint(SvgLineTo svgLine){
		
		
		List<SvgPoint>  pointList = svgLine.getSvgPointList();
		
		SvgPoint point =  svgLine.new SvgPoint();
		
		List<Float> segList = CaclModel.getSegmentsLength(pointList);
		
		float totalLength = 0f;
		
		for(float seg : segList){
			totalLength+=seg;
		}
		
		float centerLen = totalLength/2;
		
		
		int keySegIndex = 0;
		
		float keySeg = 0f;
		float tempLen = 0f;
		for(float seg : segList){
			keySeg = seg;
			keySegIndex++;
			if((tempLen+keySeg) > centerLen){
				break;
			}
			else{
				tempLen+=keySeg;
			}
			
		}

		float lastLen = centerLen - tempLen;
		
		SvgPoint startPoint = pointList.get(keySegIndex-1);
		SvgPoint endPoint = pointList.get(keySegIndex);
		
		float scale = lastLen/keySeg;
		
		
		float x = startPoint.getX() + (endPoint.getX() - startPoint.getX())*scale;
		float y = startPoint.getY() + (endPoint.getY() - startPoint.getY())*scale;
		
		point.setX(x);
		point.setY(y);
		
		
		return point;
	}
}
