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
package com.founder.fix.fixflow.expand.filter;

import com.founder.fix.fixflow.core.impl.filter.AbstractCommandFilter;
import com.founder.fix.fixflow.core.task.TaskInstance;

public class RollBackTaskFilter extends AbstractCommandFilter {

	@Override
	public boolean accept(TaskInstance taskInstance) {

		if(taskInstance==null){
			return false;
		}
		
		if(taskInstance.isSuspended()){
			return false;
		}
		
		if(taskInstance.hasEnded()){
			return false;
		}
		
		if(isProcessTracking()){
			return false;
		}
		
		if(taskInstance.getDelegationState()!=null){
			return false;
		}

		if(taskInstance.getAssignee()!=null){
			
			return true;
			
		}
		else{
			if(isAutoClaim()){
				return true;
			}else{
				return false;
			}
		}
	}

}
