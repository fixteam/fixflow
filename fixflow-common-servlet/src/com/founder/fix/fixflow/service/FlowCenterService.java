package com.founder.fix.fixflow.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FlowCenterService {
	public static final String LOGIN_USER_ID = "LOGIN_USER_ID";

	public Map<String,Object> queryMyTaskNotEnd(Map<String,String> map)
		throws SQLException;

	public Map<String,Object> queryMyTaskEnded(Map<String,String> map)
		throws SQLException;

	public List<Map<String, String>> queryStartProcess(String s)
		throws SQLException;

	public InputStream queryStartProcessImage(String s)
		throws SQLException;

	public List queryTaskParticipants(Map<String,Object> map)
		throws SQLException;

	public List queryTaskInitiator(Map<String,Object> map)
		throws SQLException;
}
