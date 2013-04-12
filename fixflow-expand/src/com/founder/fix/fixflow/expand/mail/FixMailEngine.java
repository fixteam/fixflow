package com.founder.fix.fixflow.expand.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
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

	public static void sendMail() {

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
		pObjects.add(MailStatus.COMPLETE.toString());
		SqlCommand sqlCommand = new SqlCommand(Context.getDbConnection());
		List<Map<String, Object>> dataList = sqlCommand.queryForList("SELECT * FROM FIXFLOW_MAIL WHERE MAIL_STATUS!=?", pObjects);
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
				mailUtil.setTo(str);
				String cc = fixMailTo.getMailCc();
				if (cc != null && !cc.equals("")) {
					String[] strCC = cc.split(",");
					mailUtil.setCC(strCC);
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
