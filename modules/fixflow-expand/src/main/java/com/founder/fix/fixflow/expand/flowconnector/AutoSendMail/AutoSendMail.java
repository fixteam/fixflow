package com.founder.fix.fixflow.expand.flowconnector.AutoSendMail;


import java.util.Date;
import java.util.List;

import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.task.IdentityLink;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.identity.GroupDefinition;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;
import com.founder.fix.fixflow.core.impl.identity.UserDefinition;
import com.founder.fix.fixflow.core.impl.identity.UserTo;
import com.founder.fix.fixflow.core.impl.task.TaskInstanceEntity;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.expand.mail.FixMailEngine;
import com.founder.fix.fixflow.expand.mail.FixMailTo;


public class AutoSendMail implements ConnectorHandler {

	private java.lang.String mailTitle;

	private java.lang.String mailContent;

	MailInfo mailInfoObj = null;

	public void execute(ExecutionContext executionContext) throws Exception {

		try {

			SysMailConfig sysMailConfig = Context.getProcessEngineConfiguration().getSysMailConfig();
			for (MailInfo mailInfo : sysMailConfig.getMailInfo()) {
				if (mailInfo.getMailName().equals(sysMailConfig.getSelected())) {
					mailInfoObj = mailInfo;
				}
			}
			if (mailInfoObj == null) {
				throw new FixFlowException("系统邮件配置错误请检查流程邮件配置！");
			}

			// 获取到当前任务
			TaskInstanceEntity taskInstance = (TaskInstanceEntity) executionContext.getTaskInstance();
			String flowNameString = taskInstance.getProcessDefinition().getName();
			String taskUrl = "http://localhost:8080/fixweb/";
			if (mailTitle == null || mailTitle.equals("")) {
				mailTitle = "[" + flowNameString + "] " + taskInstance.getDescription() + " is pending for your approval or handle";
			}

			String Fix_BizName = StringUtil.getString(Context.getAbstractScriptLanguageMgmt().execute("${Fix_BizName}", executionContext));
			String Fix_BizKeyFile = StringUtil.getString(Context.getAbstractScriptLanguageMgmt().execute("${Fix_BizKeyFile}", executionContext));
			if (Fix_BizName == null || Fix_BizName.equals("")) {
				throw new FixFlowException("数据变量${Fix_BizName}未空");
			}
			if (Fix_BizKeyFile == null || Fix_BizKeyFile.equals("")) {
				throw new FixFlowException("数据变量${Fix_BizKeyFile}未空");
			}
			String taskType="view";
			if(taskInstance.getAssignee()!=null){
				taskType="modify";
			}

			taskUrl = "http://localhost:8080/fixweb/framework.html?" + "_openMethod=tab&_title=view&_url='%2e%2e/%2e%2e/"
					+ taskInstance.getFormUri() + "?_objName=" + Fix_BizName + "%26_defKey=" + taskInstance.getProcessDefinitionKey() + "%26_defId="
					+ taskInstance.getProcessDefinitionId().replace(":", "%3A") + "%26_instId=" + taskInstance.getProcessInstanceId() + "%26_taskId="
					+ taskInstance.getId() + "%26_nodeId=" + taskInstance.getNodeId() + "%26_agent=%26_pk=" + Fix_BizKeyFile + "%26_pkValue="
					+ taskInstance.getBizKey() + "%26_useType="+taskType+"'";

			//taskUrl=java.net.URLEncoder.encode(taskUrl,"UTF-8");
			if (mailContent == null || mailContent.equals("")) {
				mailContent = "<br>Hello,<br>你好,<br><br> " + mailTitle + "+<br><br>"
						+ "Please click url to deal with job: <br>请访问此链接地址进入任务:<br> <a href=" + taskUrl + ">"
						+ "http://localhost:8080/fixweb/</a><br><br>"
						+ "Best Regards!<br>诚挚问候!<br>Note: Please do not reply to this email , This mailbox does not allow incoming messages."
						+ "<br>注意: 本邮件为工作流系统发送，请勿回复。 ";

			}

			UserDefinition userDefinition = Context.getProcessEngineConfiguration().getUserDefinition();

			List<GroupDefinition> groupDefinitions = Context.getProcessEngineConfiguration().getGroupDefinitions();

			if (taskInstance.getAssignee() != null) {
				String to = taskInstance.getAssignee();
				if (!to.equals("")) {
					UserTo userTo = userDefinition.findUserByUserId(to);
					if (userTo != null) {
						String eamil = StringUtil.getString(userTo.getPropertyValue("EMAIL"));
						if (eamil != null && !eamil.equals("")) {
							sendMail(eamil, mailTitle, mailContent,taskInstance);
						}
					}
				}

			} else {

				String to = "";

				for (IdentityLink identityLink : taskInstance.getTaskIdentityLinks()) {

					if (identityLink.getUserId() != null) {
						UserTo userTo = Authentication.findUserInfoByUserId(identityLink.getUserId());
						if (userTo != null) {
							String eamil = StringUtil.getString(userTo.getPropertyValue("EMAIL"));
							if (eamil != null && !eamil.equals("")) {
								// sendMail(eamil,title,mailContent);
								to = to + eamil + ",";
							}
						}
					} else {
						String groupIdString = identityLink.getGroupId();
						String groupTypeString = identityLink.getGroupType();
						GroupTo groupTo = Authentication.findGroupByGroupIdAndType(groupIdString, groupTypeString);
						if (groupTo != null) {

							for (GroupDefinition groupDefinition : groupDefinitions) {
								if (groupDefinition.getId().equals(groupTypeString)) {
									List<UserTo> userTos = groupDefinition.findUserByGroupId(groupIdString);
									for (UserTo userTo : userTos) {
										if (userTo != null) {
											String eamil = StringUtil.getString(userTo.getPropertyValue("EMAIL"));
											if (eamil != null && !eamil.equals("")) {
												// sendMail(eamil,title,mailContent);
												to = to + eamil + ",";
											}
										}
									}
								}
							}

						}

					}

				}
				if (!to.equals("")) {
					if (to.substring(to.length() - 1, to.length()).equals(",")) {
						to = to.substring(0, to.length() - 1);
					}

					sendMail(to, mailTitle, mailContent,taskInstance);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new FixFlowException("邮件发送失败!", e);
		}

	}

	public String Utf8URLencode(String text) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				result.append(c);
			} else {
				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (Exception ex) {
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return result.toString();
	}

	private void sendMail(String to, String title, String mailContent,TaskInstance taskInstance) {
		
		
		
		FixMailTo fixMailTo=new FixMailTo();
		
		fixMailTo.setMailName(title);
		fixMailTo.setMailSubject(title);
		fixMailTo.setMailTo(to);
		fixMailTo.setMailBody(mailContent);
		fixMailTo.setCreateTime(new Date());
		fixMailTo.setBizType("taskremind");
		fixMailTo.setBizValue(taskInstance.getId());
		fixMailTo.setCreateUser(Authentication.getAuthenticatedUserId());

		
		FixMailEngine.saveMail(fixMailTo);
		
		/*

		final MailUtil mailUtil = new MailUtil();
		mailUtil.setSmtpHost(mailInfoObj.getSmtpHost(), StringUtil.getInt(mailInfoObj.getSmtpPort()));
		mailUtil.setSmtpAuthentication(mailInfoObj.getUserName(), mailInfoObj.getPassWord());
		// 支持发送多人邮件 #4185
		String[] str = to.split(",");
		mailUtil.setTo(str);

		mailUtil.setFrom(mailInfoObj.getMailAddress());
		mailUtil.setSubject(title);
		mailUtil.setBody(mailContent);
		mailUtil.setContentType(mailUtil.MODE_HTML);
		// 异步发送

		ThreadPoolExecutor executor = Context.getProcessEngineConfiguration().getScheduleService().getThreadPoolExecutor();

		// 异步发送
		executor.execute(new Runnable() {

			public void run() {
				mailUtil.send();
			}
		});
		
		*/

	}

	public void setMailTitle(java.lang.String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public void setMailContent(java.lang.String mailContent) {
		this.mailContent = mailContent;
	}

}