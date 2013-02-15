
package com.founder.fix.fixflow.core.impl.cmd;

import java.util.List;

import com.founder.fix.fixflow.core.impl.interceptor.Command;
import com.founder.fix.fixflow.core.impl.interceptor.CommandContext;
import com.founder.fix.fixflow.core.task.CommentQueryTo;



/**
 * @author kenshin
 */
public class GetProcessInstanceCommentsCmd implements Command<List<CommentQueryTo>> {

  protected String processInstanceId;
  
  public GetProcessInstanceCommentsCmd(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }


  public List<CommentQueryTo> execute(CommandContext commandContext) {
    return commandContext
      .getCommentManager()
      .findCommentsByProcessInstanceId(processInstanceId);
  }
}
