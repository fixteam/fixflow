package com.founder.fix.fixflow.test.engine.entity;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.sqlmappingconfig.Result;
import com.founder.fix.bpmn2extensions.sqlmappingconfig.ResultMap;
import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.ReflectUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.DelegationState;
import com.founder.fix.fixflow.test.AbstractFixFlowTestCase;

public class TaskInstanceEntityTest extends AbstractFixFlowTestCase {

	@SuppressWarnings("unchecked")
	public void testTaskInstanceEntityMapping() throws Exception {
		
		
		Map<String, Object> dataMap=getDataMap();

		ResultMap resultMap = processEngineConfiguration.getResultMap("taskResultMap");
		
		
		
		
		AbstractPersistentObject<TaskInstanceEntity> persistentObject = (AbstractPersistentObject<TaskInstanceEntity>) ReflectUtil.instantiate(resultMap.getType());
		persistentObject.persistentInit(resultMap,dataMap);
		
		TaskInstanceEntity task=(TaskInstanceEntity)persistentObject;

		

	}

	private Map<String, Object> getDataMap() {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			dataMap.put("TASKINSTANCE_ID", "任务编号");
			dataMap.put("NAME", "任务名称");
			dataMap.put("DESCRIPTION", "任务主题");
			dataMap.put("PROCESSINSTANCE_ID", "流程实例编号");
			dataMap.put("PROCESSDEFINITION_ID", "流程定义唯一编号");
			dataMap.put("PROCESSDEFINITION_KEY", "流程定义编号");
			dataMap.put("PROCESSDEFINITION_NAME", "流程定义名称");
			dataMap.put("VERSION", 1);
			dataMap.put("TOKEN_ID", "令牌编号");
			dataMap.put("NODE_ID", "节点编号");
			dataMap.put("NODE_NAME", "节点名称");
			dataMap.put("PARENTTASK_ID", "父任务编号");
			dataMap.put("ASSIGNEE", "处理者");

			dataMap.put("CLAIM_TIME", new SimpleDateFormat("yyyy-MM-dd").parse("2012-12-25"));

			dataMap.put("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd").parse("1985-09-18"));
			dataMap.put("START_TIME", new SimpleDateFormat("yyyy-MM-dd").parse("1985-09-19"));
			dataMap.put("END_TIME", new SimpleDateFormat("yyyy-MM-dd").parse("2100-09-19"));
			dataMap.put("DUEDATE", new SimpleDateFormat("yyyy-MM-dd").parse("1956-05-02"));
			dataMap.put("PRIORITY", 50);
			dataMap.put("CATEGORY", "分类");
			dataMap.put("OWNER", "拥有人");
			dataMap.put("DELEGATIONSTATESTRING", StringUtil.getString(DelegationState.PENDING));
			dataMap.put("BIZKEY", "业务关联值");
			dataMap.put("TASK_COMMENT", "任务意见");
			dataMap.put("FORMURI", "表单");
			dataMap.put("FORMURIVIEW", "浏览编号");
			dataMap.put("TASKGROUP", "会签任务组");
			dataMap.put("TASKTYPE", "任务类型");
			dataMap.put("ISBLOCKING", String.valueOf(false));
			dataMap.put("ISCANCELLED", String.valueOf(false));
			dataMap.put("ISSUSPENDED", String.valueOf(false));
			dataMap.put("ISOPEN", String.valueOf(false));
			dataMap.put("ISDRAFT", String.valueOf(false));
			dataMap.put("EXPECTED_EXECUTIONTIME", 112233);
			dataMap.put("AGENT", "代理人");
			dataMap.put("ADMIN", "管理操作");
			dataMap.put("CALLACTIVITY_INSTANCE_ID", "调用子流程实例编号");
			dataMap.put("PENDINGTASKID", "转办任务");
			dataMap.put("ARCHIVE_TIME", new SimpleDateFormat("yyyy-MM-dd").parse("1911-05-02"));
			dataMap.put("COMMAND_ID", "任务命令编号");
			dataMap.put("COMMAND_TYPE", "任务命令类型");
			dataMap.put("COMMAND_MESSAGE", "任务命令显示名称");

			// 扩展字段
			dataMap.put("EXTENSION_FIELD", "扩展字段");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataMap;

	}

}
