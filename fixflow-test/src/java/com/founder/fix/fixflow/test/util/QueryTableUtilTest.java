/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
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
 * @author ych
 */
package com.founder.fix.fixflow.test.util;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.util.QueryTableUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.runtime.QueryLocation;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

/**
 * 查询数据来源工具类测试
 * @author Administrator
 *
 */
public class QueryTableUtilTest extends AbstractFixFlowTestCase{
	
	public void testGetTableName(){
		boolean pigeOnHole = StringUtil.getBoolean(processEngineConfiguration.getPigeonholeConfig().getIsEnable());
		String tableName = QueryTableUtil.getTableSelect("fixflow_run_processinstance", QueryLocation.RUN);
		assertEquals("FIXFLOW_RUN_PROCESSINSTANCE", tableName);
		if(pigeOnHole){
			String hisTableName = QueryTableUtil.getTableSelect("fixflow_run_processinstance", QueryLocation.HIS);
			assertEquals("FIXFLOW_HIS_PROCESSINSTANCE", hisTableName);
			String runHisTableName = QueryTableUtil.getTableSelect("fixflow_run_processinstance", QueryLocation.RUN_HIS);
			assertNotNull(runHisTableName);
		}else{
			String hisTableName = QueryTableUtil.getTableSelect("fixflow_run_processinstance", QueryLocation.HIS);
			assertEquals("FIXFLOW_RUN_PROCESSINSTANCE", hisTableName);
			String runHisTableName = QueryTableUtil.getTableSelect("fixflow_run_processinstance", QueryLocation.RUN_HIS);
			assertEquals("FIXFLOW_RUN_PROCESSINSTANCE", runHisTableName);
		}
		try{
			tableName = QueryTableUtil.getTableSelect("fixflow_run_processinstance11", QueryLocation.RUN);
			fail();
		}catch(Exception ex){
			if(ex instanceof FixFlowException){
				return;
			}
		}
		fail();
	}
}
