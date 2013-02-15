package com.founder.fix.fixflow.core.runtime;

import java.io.Serializable;
import java.util.Date;


import com.founder.fix.fixflow.core.impl.db.PersistentObject;



public interface Token  extends PersistentObject, Serializable {



	
	String getName();
	
	
	Date getStartTime();

	Date getEndTime();
	
	
	
	Date getNodeEnterTime();
	
	boolean isSuspended();
	
	boolean getlock();
	
	String getNodeId();
	
	
	String getProcessInstanceId();
	
	String getParentTokenId();
	
	boolean isAbleToReactivateParent();


	


}
