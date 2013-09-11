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
package com.founder.fix.fixflow.expand.cmd;

import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.cmd.AbstractExpandTaskCmd;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.expand.command.DeleteProcessInstanceCommand;

public class DeleteProcessInstanceCmd extends AbstractExpandTaskCmd<DeleteProcessInstanceCommand, Void> {

	public DeleteProcessInstanceCmd(DeleteProcessInstanceCommand deleteProcessInstanceCommand) {
		super(deleteProcessInstanceCommand);
	}

	public Void execute(CommandContext commandContext) {

		// 初始化任务命令执行所需要的常用对象
		loadProcessParameter(commandContext);

		// 将外部变量注册到流程实例运行环境中
		addVariable();

		// 执行处理命令中的开发人员设置的表达式
		runCommandExpression();
		
		

		commandContext.getProcessInstanceManager().deleteProcessInstance(getProcessInstance().getId(), true);

		/*
		 * String
		 * Fix_BizName=StringUtil.getString(ExpressionMgmt.execute("Fix_BizName"
		 * ,executionContext)); String
		 * Fix_BizKeyFile=StringUtil.getString(ExpressionMgmt
		 * .execute("Fix_BizKeyFile",executionContext)); String
		 * bizKey=processInstanceEntity.getBizKey(); SqlCommand sqlCommand=new
		 * SqlCommand(Context.getDbConnection());
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Object[] objectParamWhere = { bizKey}; sqlCommand.delete(Fix_BizName,
		 * Fix_BizKeyFile+"=?", objectParamWhere);
		 * 
		 * //throw new FixFlowException(
		 * "未在删除流程实例(DeleteProcessInstanceCmd)方法内设置业务数据的清空方式!请检查该类,如不需要删除业务数据,请注释掉该句话。"
		 * );
		 */

		throw new FixFlowException("未在删除流程实例(DeleteProcessInstanceCmd)方法内设置业务数据的清空方式!请检查该类,如不需要删除业务数据,请注释掉该句话。");

		// return null;

	}

}
