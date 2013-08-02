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
package com.founder.fix.fixflow.expand.identity;

import com.founder.fix.apputil.OnlineUser;
import com.founder.fix.fixflow.core.impl.identity.AbstractAuthentication;
import com.founder.fix.webcore.Current;
import com.founder.fix.webcore.DataView;

public class FixAuthenticationImpl extends AbstractAuthentication {


	@Override
	public String getAuthenticatedUserId() {
		// TODO 自动生成的方法存根
		
		
		DataView dataView = Current.getDataView();
		if(dataView != null){
			OnlineUser user = dataView.getUser();
			if(user != null){
				return user.getUserID();
			}
		}
		return null;
	}

	@Override
	public String getAdminId() {
		return "1200119390";
	}

	@Override
	public String getSystemId() {
		return "1200119390";
	}
	
	

}
