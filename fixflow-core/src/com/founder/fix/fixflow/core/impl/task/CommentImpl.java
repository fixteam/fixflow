/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */

package com.founder.fix.fixflow.core.impl.task;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;



import com.founder.fix.fixflow.core.task.Comment;




/**
 * @author kenshin
 */
public class CommentImpl implements Comment {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public static final String TYPE_EVENT = "event";
  public static final String TYPE_COMMENT = "comment";
  
  protected String id;
  protected String type;
  protected String userId;
  protected Date time;
  protected String taskId;
  protected String processInstanceId;
  protected String action;
  protected String message;
  protected String fullMessage;
  
  

  public byte[] getFullMessageBytes() {
    return (fullMessage!=null ? fullMessage.getBytes() : null);
  }

  public void setFullMessageBytes(byte[] fullMessageBytes) {
    fullMessage = (fullMessageBytes!=null ? new String(fullMessageBytes) : null );
  }
  

  public Map<String, Object> getPersistentState() {

		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("type", this.type);
		persistentState.put("time", this.time);
		persistentState.put("userId", this.userId);
		persistentState.put("taskId", this.taskId);
		persistentState.put("processInstanceId", this.processInstanceId);
		persistentState.put("action", this.action);
		persistentState.put("message", this.message);				
		persistentState.put("fullMessage", this.fullMessage);
		return persistentState;
}

  
  // getters and setters //////////////////////////////////////////////////////

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getTaskId() {
    return taskId;
  }
  
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public Date getTime() {
    return time;
  }
  
  public void setTime(Date time) {
    this.time = time;
  }
  
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }

  public String getFullMessage() {
    return fullMessage;
  }

  public void setFullMessage(String fullMessage) {
    this.fullMessage = fullMessage;
  }

  public String getAction() {
    return action;
  }
  
  public void setAction(String action) {
    this.action = action;
  }
}
