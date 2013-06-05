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
		// TODO 自动生成的方法存根
		return "1200119390";
	}
	
	

}
