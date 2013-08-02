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
package com.founder.fix.fixflow.core.variable;

import java.util.List;
import java.util.Map;

public interface BizData {
	
	/**
	  * getMasterValue(获取流程主表的指定字段的值)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getMasterValue
	  * @Description: TODO
	  * @param @param defkey 
	  * @param @param bizkey 
	  * @param @param field 字段名
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	  */
	public Object getMasterValue(String defkey, String bizkey,String field);
	
	/**
	  * getMasterMap(获取主表的整条记录)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getMasterMap
	  * @Description: TODO
	  * @param @param defkey
	  * @param @param bizkey
	  * @param @return    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	public Map<String,Object> getMasterMap(String defkey, String bizkey);

	//,"明细字段名称"
	//
	/**
	  * getDetailAll(获取明细关联的所有值)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getDetailAll
	  * @Description: TODO
	  * @param @param defkey
	  * @param @param bizkey
	  * @param @param detailName 明细表名
	  * @param @return    设定文件
	  * @return List<Map<String,Object>>    返回类型
	  * @throws
	  */
	public List<Map<String,Object>> getDetailAll(String defkey, String bizkey, String detailName);
	
	//
	/**
	  * getDetailRows(获取明细表关联的指定行数据)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: getDetailRows
	  * @Description: TODO
	  * @param @param defkey
	  * @param @param bizkey
	  * @param @param detailName
	  * @param @param rowNum
	  * @param @return    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	
	
	public Map<String,Object> getDetailRows(String defkey, String bizkey, String detailName, int rowNum);
	//获取明细表指定的行、列的数据
	public Object getDetailValue(String defkey, String bizkey, String detailName, int rowNum, String field);
	//获取一列所有行的数据
	public List<Object> getDetailColumnValue(String defkey, String bizkey, String detailName, String field);
}
