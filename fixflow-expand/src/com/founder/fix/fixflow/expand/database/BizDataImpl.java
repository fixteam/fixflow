package com.founder.fix.fixflow.expand.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.apputil.log.LogFactory;
import com.founder.fix.apputil.log.OnlineLog;
import com.founder.fix.apputil.to.bizobj.BizObjTo;
import com.founder.fix.apputil.to.bizobj.ObjFlow;
import com.founder.fix.apputil.util.BizObjectUtil;
import com.founder.fix.apputil.util.ListUtil;
import com.founder.fix.dbcore.DataTable;
import com.founder.fix.dbcore.DataTableUtil;
import com.founder.fix.fixflow.api.task.FlowUtil;
import com.founder.fix.fixflow.core.exception.FixFlowException;
import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;
import com.founder.fix.fixflow.core.variable.BizData;
import com.founder.fix.webcore.DataView;
import com.founder.fix.webcore.interfaceLayer.BizObjectProxy;

public class BizDataImpl implements BizData {

	private static final OnlineLog log = LogFactory
			.getOnlineLog(BizDataImpl.class);

	public Object getMasterValue(String processDefinitionKey, String bizkey, String field) {
		BizObjTo bizObjTo = getBizObject(processDefinitionKey);
		String bizField = getBizField(processDefinitionKey, bizObjTo);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(field);
		Map<String, Object>  returnVal = getData(bizObjTo, fieldList, bizField, bizkey);
		
		return returnVal.get(field);
	}

	public Map<String, Object> getMasterMap(String processDefinitionKey,
			String bizkey) {
		BizObjTo bizObjTo = getBizObject(processDefinitionKey);
		String bizField = getBizField(processDefinitionKey, bizObjTo);
		Map<String, Object>  returnVal = getData(bizObjTo, null, bizField, bizkey);
		
		return returnVal;
	}

	public List<Map<String, Object>> getDetailAll(String processDefinitionKey, String bizkey,
			String detailName) {
		BizObjTo bizObjTo = getBizObject(processDefinitionKey);
		String bizField = getBizField(processDefinitionKey, bizObjTo);
		String masterObjId = bizObjTo.getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(bizField, bizkey);
		DataView dataView = DataView.buildDataView();
		try {
			DataTable dataTable = BizObjectProxy.getRelObjectData(masterObjId,detailName, map, dataView);
			List<Map<String, Object>> detailDatas = DataTableUtil.parseDataTable2List(dataTable);
			return detailDatas;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e);
		}
		// TODO 自动生成的方法存根
		return null;
	}

	public Map<String, Object> getDetailRows(String processDefinitionKey, String bizkey,
			String detailName, int rowNum) {
		List<Map<String, Object>> detailDatas = getDetailAll(processDefinitionKey,bizkey, detailName);		
		return detailDatas.get(rowNum);
	}

	public Object getDetailValue(String processDefinitionKey, String bizkey,
			String detailName, int rowNum, String field) {
		Map<String, Object> detailTableData = getDetailRows(processDefinitionKey, bizkey, detailName, rowNum);
		return detailTableData.get(field);
	}

	public List<Object> getDetailColumnValue(String processDefinitionKey, String bizkey,
			String detailName, String field) {
		List<Map<String, Object>> detailDatas = getDetailAll(processDefinitionKey,bizkey, detailName);	
		
		List<Object> fieldDatas = new ArrayList<Object>();
		
		for(Map<String, Object> tmpMap: detailDatas){
			fieldDatas.add(tmpMap.get(field));
		}
		return fieldDatas;
	}

	public static String buildSql(String tableName, String bizField) {
		return buildSql(tableName, bizField, null);
	}

	private static String buildSql(String tableName, String bizField,
			List<String> filedList) {
		StringBuffer tmpExpress = new StringBuffer();

		String fileds = ListUtil.listToStr(filedList, ",");

		if (ListUtil.isEmpty(filedList))
			tmpExpress.append("select * from ");
		else
			tmpExpress.append("select " + fileds + " from ");

		tmpExpress.append("" + tableName + " where " + bizField + "=?");

		return tmpExpress.toString();
	}

	private static BizObjTo getBizObject(String processDefinitionKey) {
		String bizObjId = BizObjectUtil.flowBizObj.get(processDefinitionKey);

		if (bizObjId == null) {
			throw new FixFlowException("processDefinitionKey 为空");
		}

		BizObjTo bizObjTo;
		try {
			bizObjTo = BizObjectUtil.getBizObject(bizObjId);
			if (bizObjTo == null) {
				throw new FixFlowException("找不到业务对象定义:" + bizObjId);
			} else {
				return bizObjTo;
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;

	}

	private static String getBizField(String processDefinitionKey,
			BizObjTo bizObjTo) {
		String bizField;
		String bizObjId = bizObjTo.getId();
		try {
			ObjFlow objFlow = FlowUtil.getObjFlowByKey(bizObjId,
					processDefinitionKey);

			bizField = objFlow.getProcessBusinessField();
			return bizField;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	private static Map<String, Object> getData(BizObjTo bizObjTo, List<String> filedList ,String bizField,String bizkey){
		String tableName = bizObjTo.getTableName();
		String sqlStr = buildSql(tableName, bizField,filedList);
		Map<String, Object> returnVal = new HashMap<String, Object>();
		try {
			String dbSource = bizObjTo.getDbsource();
			SqlCommand sqlCmd = new SqlCommand(Context.getDbConnection(dbSource));
			List<Object> data = new ArrayList<Object>();
			data.add(bizkey);
			List<Map<String, Object>> bizData = sqlCmd.queryForList(sqlStr,
					data);
			int dataSize = bizData.size();
			if (dataSize != 1) {
				throw new FixFlowException("数据查询结果应该只返回一条数据，而此处返回了 " + dataSize
						+ " 条记录");
			}
			returnVal = bizData.get(0);
		} catch (Exception e) {
			log.error(e);
		} finally {
			
		}
		return returnVal;
	}
}
