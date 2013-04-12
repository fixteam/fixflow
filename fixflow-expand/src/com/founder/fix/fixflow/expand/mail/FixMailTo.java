package com.founder.fix.fixflow.expand.mail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.founder.fix.fixflow.core.impl.db.AbstractPersistentObject;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;

public class FixMailTo extends AbstractPersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -124017347878039268L;
	
	
	protected String mailId;
	protected String mailName;
	protected String mailTo;
	protected String mailCc;
	protected String mailSubject;
	protected String mailBody;
	protected String bizType;
	protected String bizValue;
	protected MailStatus mailStatus;
	
	protected Date createTime;
	protected Date sendTime;
	protected String createUser;
	
	protected String failureReason;
	
	



	public FixMailTo(){
		this.mailId=GuidUtil.CreateGuid();
		this.mailStatus=MailStatus.NOSEND;
	}
	
	
	public String getFailureReason() {
		return failureReason;
	}



	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMailName() {
		return mailName;
	}
	public void setMailName(String mailName) {
		this.mailName = mailName;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailCc() {
		return mailCc;
	}
	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBizValue() {
		return bizValue;
	}
	public void setBizValue(String bizValue) {
		this.bizValue = bizValue;
	}
	public MailStatus getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(MailStatus mailStatus) {
		this.mailStatus = mailStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getId() {
		// TODO 自动生成的方法存根
		return this.mailId;
	}


	public Map<String, Object> getPersistentState() {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public void persistentInit(Map<String, Object> entityMap) {
		for (String dataKey : entityMap.keySet()) {


			if (dataKey.equals("MAIL_ID")) {
				this.mailId = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("MAIL_NAME")) {
				this.mailName = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("MAIL_TO")) {
				this.mailTo = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("MAIL_SUBJECT")) {
				this.mailSubject = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("MAIL_BODY")) {
				
				
				if(entityMap.get(dataKey)!=null){
					String body = new String((byte[])entityMap.get(dataKey));
					this.mailBody = body;
				}
				
				continue;
			}

			if (dataKey.equals("BIZ_TYPE")) {
				this.bizType = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("BIZ_VALUE")) {
				this.bizValue = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("MAIL_STATUS")) {
				this.mailStatus = MailStatus.valueOf(StringUtil.getString(entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals("CREATE_TIME")) {
				this.createTime = StringUtil.getDate((entityMap.get(dataKey)));
				continue;
			}

			if (dataKey.equals("SEND_TIME")) {
				this.sendTime = StringUtil.getDate(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("MAIL_CC")) {

				this.mailCc = StringUtil.getString(entityMap.get(dataKey));
				continue;

			}
			if (dataKey.equals("CREATE_USER")) {
				this.createUser = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

			if (dataKey.equals("FAILURE_REASON")) {
				this.failureReason = StringUtil.getString(entityMap.get(dataKey));
				continue;
			}

		
			//this.addExtensionField(dataKey, entityMap.get(dataKey));

		}
		
	}


	@Override
	public Map<String, Object> getPersistentDbMap() {
		Map<String, Object> objectParam = new HashMap<String, Object>();

		objectParam.put("MAIL_ID", this.getId());

		objectParam.put("MAIL_NAME", this.getMailName());

		objectParam.put("MAIL_TO", this.getMailTo());

		objectParam.put("MAIL_SUBJECT", this.getMailSubject());

		objectParam.put("MAIL_BODY", this.getMailBody().getBytes());

		objectParam.put("BIZ_TYPE", this.getBizType());

		objectParam.put("BIZ_VALUE", this.getBizValue());

		objectParam.put("MAIL_STATUS", this.getMailStatus().toString());

		objectParam.put("CREATE_TIME", this.getCreateTime());

		objectParam.put("SEND_TIME", this.getSendTime());

		objectParam.put("MAIL_CC", this.getMailCc());

		objectParam.put("CREATE_USER", this.getCreateUser());

		objectParam.put("FAILURE_REASON",this.getFailureReason());
	
		return objectParam;
	}
	
	
	
	
	
	
	
	
}
