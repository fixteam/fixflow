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
package com.founder.fix.fixflow.expand.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.fixflow.core.db.pagination.Pagination;
import com.founder.fix.fixflow.core.exception.FixFlowBizException;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.impl.util.MailUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class FixMailEngine {

	public static void saveMail(FixMailTo fixMailTo) {

		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection());

		sqlCommand.insert("FIXFLOW_MAIL", fixMailTo.getPersistentDbMap());

	}

	public synchronized static void sendMail() {

		SysMailConfig sysMailConfig = Context.getProcessEngineConfiguration().getSysMailConfig();
		MailInfo mailInfoObj = null;
		for (MailInfo mailInfo : sysMailConfig.getMailInfo()) {
			if (mailInfo.getMailName().equals(sysMailConfig.getSelected())) {
				mailInfoObj = mailInfo;
			}
		}
		if (mailInfoObj == null) {
			throw new FixFlowException("系统邮件配置错误请检查流程邮件配置！");
		}

		List<Object> pObjects = new ArrayList<Object>();
		//pObjects.add(MailStatus.COMPLETE.toString());
		//pObjects.add(MailStatus.FAILURE.toString());
		pObjects.add(MailStatus.NOSEND.toString());
		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection());
		
		Pagination pagination = Context.getProcessEngineConfiguration().getDbConfig().getPagination();
		
		
		
		
		String sqlText=pagination.getPaginationSql("SELECT * FROM FIXFLOW_MAIL WHERE MAIL_STATUS=?", 0, 10, "*", null);
		
		List<Map<String, Object>> dataList = sqlCommand.queryForList(sqlText, pObjects);
		
		for (Map<String, Object> mapData : dataList) {

			try {
				FixMailTo fixMailTo = new FixMailTo();
				fixMailTo.persistentInit(mapData);

				MailUtil mailUtil = new MailUtil();
				mailUtil.setSmtpHost(mailInfoObj.getSmtpHost(), StringUtil.getInt(mailInfoObj.getSmtpPort()));
				mailUtil.setSmtpAuthentication(mailInfoObj.getUserName(), mailInfoObj.getPassWord());
				// 支持发送多人邮件 #4185
				String to = fixMailTo.getMailTo();
				if (to == null || to.equals("")) {
					throw new FixFlowBizException("mailTo is null");
				}
				String[] str = to.split(",");
				List<String> userMailToList=new ArrayList<String>();
				for (String userMail : str) {
					
					if(userMail==null||userMail.equals("")||userMail.trim().equals("")){
						
					}
					else{
						userMailToList.add(userMail);
					}
					
					
					
					
				}
				
				if(userMailToList.size()==0){
					throw new FixFlowBizException("Mail toaddress is null");
				}
				String[] userMailToFinStrings=(String[])userMailToList.toArray(new String[userMailToList.size()]);
				
				
				mailUtil.setTo(userMailToFinStrings);
				
				
				
				
				
				String cc = fixMailTo.getMailCc();
				if (cc != null && !cc.equals("")) {
					String[] strCC = cc.split(",");
					
					List<String> userMailCCList=new ArrayList<String>();
					for (String userMail : strCC) {
						
						if(userMail==null||userMail.equals("")||userMail.trim().equals("")){
							
						}
						else{
							userMailCCList.add(userMail);
						}
						
					}
					
					if(userMailCCList.size()==0){
						throw new FixFlowBizException("Mail ccaddress is null");
					}
					
					String[] userMailCCFinStrings=(String[])userMailCCList.toArray(new String[userMailCCList.size()]);
			
					
					
					
					mailUtil.setCC(userMailCCFinStrings);
				}
				String title = fixMailTo.getMailSubject();
				String mailContent = fixMailTo.getMailBody();

				mailUtil.setFrom(mailInfoObj.getMailAddress());
				mailUtil.setSubject(title);
				mailUtil.setBody(mailContent);
				mailUtil.setContentType(mailUtil.MODE_HTML);
				

				Map<String, Object> objectParam = new HashMap<String, Object>();
				objectParam.put("MAIL_STATUS", MailStatus.COMPLETE.toString());

				// 构建Where查询参数
				Object[] objectParamWhere = { StringUtil.getString(mapData.get("MAIL_ID")) };

				sqlCommand.update("FIXFLOW_MAIL", objectParam, " MAIL_ID=?", objectParamWhere);
				
		
				// 异步发送
				mailUtil.send();

			} catch (Exception e) {

				try {
					Map<String, Object> objectParam = new HashMap<String, Object>();
					objectParam.put("MAIL_STATUS", MailStatus.FAILURE.toString());
					objectParam.put("FAILURE_REASON", e.getMessage());

					// 构建Where查询参数
					Object[] objectParamWhere = { StringUtil.getString(mapData.get("MAIL_ID")) };

					sqlCommand.update("FIXFLOW_MAIL", objectParam, " MAIL_ID=?", objectParamWhere);
				} catch (Exception e2) {
					e.printStackTrace();
					e2.printStackTrace();
					//throw new FixFlowException("邮件发送失败",e2);
				}

			} finally {
				try {
					Map<String, Object> objectParam = new HashMap<String, Object>();
					objectParam.put("SEND_TIME", new Date());

					// 构建Where查询参数
					Object[] objectParamWhere = { StringUtil.getString(mapData.get("MAIL_ID")) };

					sqlCommand.update("FIXFLOW_MAIL", objectParam, " MAIL_ID=?", objectParamWhere);
				} catch (Exception e) {
					e.printStackTrace();
					//throw new FixFlowException("邮件发送失败",e);
				}

			}

		}

	}

}
