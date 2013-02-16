package com.founder.fix.fixflow.expand.connector.SendMail;


import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.util.MailUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class SendMail implements ConnectorHandler {

	private java.lang.String to;

	private java.lang.String title;

	private java.lang.String mailContent;

	public void execute(ExecutionContext executionContext) throws Exception {

		SysMailConfig sysMailConfig=Context.getProcessEngineConfiguration().getSysMailConfig();
		MailInfo mailInfoObj=null;
		for (MailInfo mailInfo : sysMailConfig.getMailInfo()) {
			if(mailInfo.getMailName().equals(sysMailConfig.getSelected())){
				mailInfoObj=mailInfo;
			}
		}
		if(mailInfoObj==null){
			throw new FixFlowException("系统邮件配置错误请检查流程邮件配置！");
		}
		
		MailUtil mailUtil=new MailUtil();
		mailUtil.setSmtpHost(mailInfoObj.getSmtpHost(), StringUtil.getInt(mailInfoObj.getSmtpPort()));
		mailUtil.setSmtpAuthentication(mailInfoObj.getUserName(), mailInfoObj.getPassWord());
		//支持发送多人邮件 #4185
		String[] str = to.split(",");
		mailUtil.setTo(str);
		mailUtil.setFrom(mailInfoObj.getMailAddress());
		mailUtil.setSubject(title);
		mailUtil.setBody(mailContent);
		mailUtil.setContentType(mailUtil.MODE_HTML);
		//异步发送
		mailUtil.asynSend();


	}

	public void  setTo(java.lang.String to){
		this.to = to;
	}

	public void  setTitle(java.lang.String title){
		this.title = title;
	}

	public void  setMailContent(java.lang.String mailContent){
		this.mailContent = mailContent;
	}

}