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
package com.founder.fix.fixflow.core.impl.processversion;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
/**
 * 流程版本类
 * 
 * 主版本号.子版本号.修正版本号.编译版本号
 * Major_Version_Number.Minor_Version_Number[.Revision_Number[.Build_Number]]
 * 示例 : 4.7.1.20130531
 * @author kenshin
 *
 */
public class FixFlowVersion implements Comparable<FixFlowVersion> {
	
	private String versionString;
	private int majorVersionNumber;
	private int minorVersionNumber;
	private int revisionNumber;
	private int buildNumber;
	
	private int versionNumber;


	public FixFlowVersion(String versionString){
		
		if(versionString==null||versionString.equals("")){
			throw new FixFlowException("version不能为空!");
		}
		
		this.versionString=versionString;
		
		String[] versionStringTemp=versionString.split("\\.");
		if(versionStringTemp.length!=4){
			throw new FixFlowException("version:"+versionString+"格式不正确!");
		}
		
		try {
			this.majorVersionNumber=StringUtil.getInt(versionStringTemp[0]);
			this.minorVersionNumber=StringUtil.getInt(versionStringTemp[1]);
			this.revisionNumber=StringUtil.getInt(versionStringTemp[2]);
			this.buildNumber=StringUtil.getInt(versionStringTemp[3]);
			this.versionNumber=StringUtil.getInt(this.majorVersionNumber+this.minorVersionNumber+this.revisionNumber+this.buildNumber);
		} catch (Exception e) {
			throw new FixFlowException("version:"+versionString+"格式不正确!");
		}
		
		
		
	}
	
	/**
	 * 比较大小
	 */
	public int compareTo(FixFlowVersion fixFlowVersion) {

		if(this.versionNumber==fixFlowVersion.getVersionNumber()){
			return 0;
		}
		if(this.versionNumber>fixFlowVersion.getVersionNumber()){
			return 1;
		}
		if(this.versionNumber<fixFlowVersion.getVersionNumber()){
			return -1;
		}
		return 0;
		
	}


	
	
	
	/**
	 * 主版本号  [主版本号.子版本号.修正版本号.编译版本号]
	 * @return 
	 */
	public int getMajorVersionNumber(){
		return this.majorVersionNumber;
	}
	
	/**
	 * 子版本号  [主版本号.子版本号.修正版本号.编译版本号]
	 * @return
	 */
	public int getMinorVersionNumber(){
		return this.minorVersionNumber;
	}
	
	/**
	 * 修正版本号  [主版本号.子版本号.修正版本号.编译版本号]
	 * @return
	 */
	public int getRevisionNumber(){
		return this.revisionNumber;
	}
	
	/**
	 * 编译版本号  [主版本号.子版本号.修正版本号.编译版本号]
	 * @return
	 */
	public int getBuildNumber(){
		return this.buildNumber;
	}
	

	public String getVersionString() {
		return versionString;
	}

	public void setVersionString(String versionString) {
		this.versionString = versionString;
	}

	
	public int getVersionNumber() {
		return versionNumber;
	}


	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	
	
	

}
