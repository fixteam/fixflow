package com.founder.fix.fixflow.expand.flowconnector.SendMail;


import java.util.Date;

import com.founder.fix.bpmn2extensions.coreconfig.MailInfo;
import com.founder.fix.bpmn2extensions.coreconfig.SysMailConfig;
import com.founder.fix.fixflow.core.action.ConnectorHandler;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.runtime.ExecutionContext;
import com.founder.fix.fixflow.expand.mail.FixMailEngine;
import com.founder.fix.fixflow.expand.mail.FixMailTo;

public class SendMail implements ConnectorHandler {

	private java.lang.String mailContent;

	private java.lang.String to;

	private java.lang.String title;

	private java.lang.String cc;

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
		
		
		
		FixMailTo fixMailTo=new FixMailTo();
		
		fixMailTo.setMailName(title);
		fixMailTo.setMailSubject(title);
		fixMailTo.setMailTo(to);
		fixMailTo.setMailCc(cc);
		fixMailTo.setMailBody(mailContent);
		fixMailTo.setCreateTime(new Date());
		fixMailTo.setBizType("fixother");
		fixMailTo.setBizValue("proxessinsid:"+executionContext.getProcessInstance().getId()+"_FIX_nodeid:"+executionContext.getToken().getFlowNode().getId());
		fixMailTo.setCreateUser(Authentication.getAuthenticatedUserId());

		
		FixMailEngine.saveMail(fixMailTo);
		
		
		/*
		final MailUtil mailUtil=new MailUtil();
		mailUtil.setSmtpHost(mailInfoObj.getSmtpHost(), StringUtil.getInt(mailInfoObj.getSmtpPort()));
		mailUtil.setSmtpAuthentication(mailInfoObj.getUserName(), mailInfoObj.getPassWord());
		//支持发送多人邮件 #4185
		if(to==null||to.equals("")){
			return ;
		}
		String[] str = to.split(",");
		mailUtil.setTo(str);
		
		if(cc!=null&&!cc.equals("")){
			String[] strCC=cc.split(",");
			mailUtil.setCC(strCC);
		}
		
		
		mailUtil.setFrom(mailInfoObj.getMailAddress());
		mailUtil.setSubject(title);
		mailUtil.setBody(mailContent);
		mailUtil.setContentType(mailUtil.MODE_HTML);
		//异步发送
		
		ThreadPoolExecutor executor=Context.getProcessEngineConfiguration().getScheduleService().getThreadPoolExecutor();
		
		//异步发送
		executor.execute(new Runnable() {   
		    
            public void run() {   
                mailUtil.send();   
            }   
        });   
		*/
		

	}

	public void  setMailContent(java.lang.String mailContent){
		this.mailContent = mailContent;
	}

	public void  setTo(java.lang.String to){
		this.to = to;
	}

	public void  setTitle(java.lang.String title){
		this.title = title;
	}

	public void  setCc(java.lang.String cc){
		this.cc = cc;
	}

}