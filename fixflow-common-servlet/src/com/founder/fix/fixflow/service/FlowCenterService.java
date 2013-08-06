package com.founder.fix.fixflow.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.pojo.PageResultTo;

public interface FlowCenterService {
	public static final String LOGIN_USER_ID="LOGIN_USER_ID";
	
	public PageResultTo queryMyTask(Map<String,String> filter) throws SQLException;
	
	public List<Map<String,String>> queryStartProcess(String userId) throws SQLException;
}
