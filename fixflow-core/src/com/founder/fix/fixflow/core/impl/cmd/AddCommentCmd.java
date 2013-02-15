package com.founder.fix.fixflow.core.impl.cmd;

import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.impl.task.CommentImpl;
import com.founder.fix.fixflow.core.impl.util.ClockUtil;
import com.founder.fix.fixflow.core.impl.util.GuidUtil;

/**
 * @author kenshin
 */
public class AddCommentCmd implements Command<Object> {

	protected String taskId;
	protected String processInstanceId;
	protected String message;
	protected String fullMessage;

	public AddCommentCmd(String taskId, String processInstanceId, String message, String fullMessage) {
		this.taskId = taskId;
		this.processInstanceId = processInstanceId;
		this.message = message;
		this.fullMessage = fullMessage;

	}

	public Object execute(CommandContext commandContext) {
		String userId = Authentication.getAuthenticatedUserId();
		CommentImpl comment = new CommentImpl();
		comment.setId(GuidUtil.CreateGuid());
		comment.setUserId(userId);
		comment.setType(CommentImpl.TYPE_COMMENT);
		comment.setTime(ClockUtil.getCurrentTime());
		comment.setTaskId(taskId);
		comment.setProcessInstanceId(processInstanceId);
		// comment.setAction(Event.ACTION_ADD_COMMENT);

		comment.setMessage(message);

		comment.setFullMessage(fullMessage);

		commandContext.getCommentManager().insert(comment);

		return null;
	}
}
