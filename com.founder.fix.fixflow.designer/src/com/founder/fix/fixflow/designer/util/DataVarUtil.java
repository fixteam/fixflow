package com.founder.fix.fixflow.designer.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.impl.ProcessImpl;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.jface.dialogs.MessageDialog;

import com.founder.fix.bpmn2extensions.coreconfig.AllUserInfo;
import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.bpmn2extensions.coreconfig.UserInfo;
import com.founder.fix.bpmn2extensions.fixflow.DataVariable;
import com.founder.fix.bpmn2extensions.fixflow.FixFlowPackage;
import com.founder.fix.bpmn2extensions.variableconfig.DataTypeDef;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableConfig;
import com.founder.fix.bpmn2extensions.variableconfig.DataVariableDataType;
import com.founder.fix.bpmn2extensions.variableconfig.FixFlowDataVariable;
import com.founder.fix.bpmn2extensions.variableconfig.VariableconfigFactory;
import com.founder.fix.fixflow.designer.modeler.ui.common.DataVarTo;
import com.founder.fix.fixflow.designer.modeler.ui.common.OrgInfoEntity;
import com.founder.fix.fixflow.designer.modeler.ui.common.UserInfoEntity;
import com.founder.fix.fixflow.designer.modeler.ui.property.SectionBpmnElement;
import com.founder.fix.fixflow.designer.usercontrol.ExpressionTo;

/**
 * 
 * @author wangyu
 * 
 */
public class DataVarUtil {
	// 缓存机制
	public static DataVariableConfig dataVariableConfig;

	public static HashMap<String, List> map;

	public static Map<String,List<UserInfoEntity>> userInforeturnListMap;
	
	public static List<UserInfoEntity> userInforeturnList;
	/**
	 * 取得ExpressionTo
	 * 
	 * @return
	 */
	public static List<ExpressionTo> getExpressionTos() {
		List<ExpressionTo> expressionTos = new ArrayList<ExpressionTo>();

		if (SectionBpmnElement.process == null) {
			return expressionTos;
		}

		/*
		 * for(ExtensionAttributeValue eav :
		 * SectionBpmnElement.process.getExtensionValues()) { FeatureMap
		 * extensionElements = eav.getValue(); Object objectElement =
		 * extensionElements
		 * .get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
		 * if(objectElement != null){
		 * 
		 * @SuppressWarnings("unchecked") java.util.List<DataVariable>
		 * dataVariableList=(java.util.List<DataVariable>) objectElement; for
		 * (DataVariable dataVariable : dataVariableList) { ExpressionTo
		 * expressionTo = new ExpressionTo();
		 * expressionTo.setName(dataVariable.getId());
		 * //dataVarTo.setName(dataVariable.getExpression().getName());
		 * expressionTo
		 * .setExpressionText(dataVariable.getExpression().getValue());
		 * //dataVarTo.setType(dataVariable.getDataType());
		 * if(dataVariable.getDocumentation() != null &&
		 * dataVariable.getDocumentation().size() > 0){
		 * //dataVarTo.setDoc(dataVariable
		 * .getDocumentation().get(0).getValue()); }
		 * expressionTos.add(expressionTo); }
		 * 
		 * } }
		 * 
		 * //私有 if(!(SectionBpmnElement.sectionElement instanceof Process) ||
		 * !(SectionBpmnElement.sectionElement instanceof ProcessImpl)){
		 * BaseElement
		 * baseElement=(BaseElement)SectionBpmnElement.sectionElement;
		 * if(baseElement.getExtensionValues().size()>0) {
		 * for(ExtensionAttributeValue extensionAttributeValue :
		 * baseElement.getExtensionValues()){ FeatureMap extensionElements =
		 * extensionAttributeValue.getValue(); Object objectElement =
		 * extensionElements
		 * .get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
		 * if(objectElement != null){
		 * 
		 * @SuppressWarnings("unchecked") java.util.List<DataVariable>
		 * dataVariableList=(java.util.List<DataVariable>) objectElement; for
		 * (DataVariable dataVariable : dataVariableList) { ExpressionTo
		 * expressionTo = new ExpressionTo();
		 * expressionTo.setName(dataVariable.getId());
		 * //dataVarTo.setName(dataVariable.getExpression().getName());
		 * expressionTo
		 * .setExpressionText(dataVariable.getExpression().getValue());
		 * //dataVarTo.setType(dataVariable.getDataType());
		 * //dataVarTo.setDoc(dataVariable
		 * .getDocumentation().get(0).getValue());
		 * expressionTos.add(expressionTo); }
		 * 
		 * } } } }
		 */
		return expressionTos;
	}

	/**
	 * 取得所有数据变量
	 * 
	 * @return
	 */
	public static List<DataVarTo> getDataVarTos() {
		List<DataVarTo> dataVarTos = new ArrayList<DataVarTo>();

		for (ExtensionAttributeValue eav : SectionBpmnElement.process.getExtensionValues()) {
			FeatureMap extensionElements = eav.getValue();
			Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
			if (objectElement != null) {

				@SuppressWarnings("unchecked")
				java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
				for (DataVariable dataVariable : dataVariableList) {
					DataVarTo dataVarTo = new DataVarTo();
					dataVarTo.setId(dataVariable.getId());
					dataVarTo.setName(dataVariable.getExpression() == null ? "" : dataVariable.getExpression().getName());
					dataVarTo.setValue(dataVariable.getExpression() == null ? "" : dataVariable.getExpression().getValue());
					dataVarTo.setType(dataVariable.getDataType());
					if (dataVariable.getDocumentation() != null && dataVariable.getDocumentation().size() > 0) {
						dataVarTo.setDoc(dataVariable.getDocumentation().get(0).getValue());
					}
					dataVarTos.add(dataVarTo);
				}

			}
		}

		// 私有
		if (!(SectionBpmnElement.sectionElement instanceof Process) || !(SectionBpmnElement.sectionElement instanceof ProcessImpl)) {
			BaseElement baseElement = (BaseElement) SectionBpmnElement.sectionElement;
			if (baseElement.getExtensionValues().size() > 0) {
				for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
					FeatureMap extensionElements = extensionAttributeValue.getValue();
					Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
					if (objectElement != null) {

						@SuppressWarnings("unchecked")
						java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
						for (DataVariable dataVariable : dataVariableList) {
							DataVarTo dataVarTo = new DataVarTo();
							dataVarTo.setId(dataVariable.getId());
							dataVarTo.setName(dataVariable.getExpression() == null ? "" : dataVariable.getExpression().getName());
							dataVarTo.setValue(dataVariable.getExpression() == null ? "" : dataVariable.getExpression().getValue());
							dataVarTo.setType(dataVariable.getDataType());
							if (dataVariable.getDocumentation() == null || dataVariable.getDocumentation().size() < 1) {
								// 什么都不做
							} else {
								dataVarTo.setDoc(dataVariable.getDocumentation().get(0).getValue());
							}
							dataVarTos.add(dataVarTo);
						}

					}
				}
			}
		}
		return dataVarTos;
	}

	/**
	 * 根据放入的参数取得所有数据变量
	 * 
	 * @param processObject
	 * @param selectedObject
	 * @return
	 */
	public static List<DataVarTo> getDataVarTos(org.eclipse.bpmn2.Process processObject, EObject selectedObject) {
		List<DataVarTo> dataVarTos = new ArrayList<DataVarTo>();

		for (ExtensionAttributeValue eav : processObject.getExtensionValues()) {
			FeatureMap extensionElements = eav.getValue();
			Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
			if (objectElement != null) {

				@SuppressWarnings("unchecked")
				java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
				for (DataVariable dataVariable : dataVariableList) {
					DataVarTo dataVarTo = new DataVarTo();
					dataVarTo.setId(dataVariable.getId());
					dataVarTo.setName(dataVariable.getExpression().getName());
					dataVarTo.setValue(dataVariable.getExpression().getValue());
					dataVarTo.setType(dataVariable.getDataType());
					if (dataVariable.getDocumentation() != null && dataVariable.getDocumentation().size() > 0) {
						dataVarTo.setDoc(dataVariable.getDocumentation().get(0).getValue());
					}
					dataVarTos.add(dataVarTo);
				}

			}
		}

		// 私有
		if (!(selectedObject instanceof Process) || !(selectedObject instanceof ProcessImpl)) {
			BaseElement baseElement = (BaseElement) selectedObject;
			if (baseElement.getExtensionValues().size() > 0) {
				for (ExtensionAttributeValue extensionAttributeValue : baseElement.getExtensionValues()) {
					FeatureMap extensionElements = extensionAttributeValue.getValue();
					Object objectElement = extensionElements.get(FixFlowPackage.Literals.DOCUMENT_ROOT__DATA_VARIABLE, true);
					if (objectElement != null) {

						@SuppressWarnings("unchecked")
						java.util.List<DataVariable> dataVariableList = (java.util.List<DataVariable>) objectElement;
						for (DataVariable dataVariable : dataVariableList) {
							DataVarTo dataVarTo = new DataVarTo();
							dataVarTo.setId(dataVariable.getId());
							dataVarTo.setName(dataVariable.getExpression().getName());
							dataVarTo.setValue(dataVariable.getExpression().getValue());
							dataVarTo.setType(dataVariable.getDataType());
							dataVarTo.setDoc(dataVariable.getDocumentation().get(0).getValue());
							dataVarTos.add(dataVarTo);
						}

					}
				}
			}
		}
		return dataVarTos;
	}

	/**
	 * 通过数据变量的类型找到对应的数据变量(通过读文件)
	 * 
	 * @param dataType
	 * @return
	 */
	public static FixFlowDataVariable getfixFlowDataVariableByDataType(String dataType) {

		DataVariableConfig dataVariableConfig = getFixFlowDataVariableConfig();
		for (FixFlowDataVariable fixFlowDataVariable : dataVariableConfig.getFixFlowDataVariable()) {
			if (fixFlowDataVariable.getType() != null && fixFlowDataVariable.getType().equals(dataType)) {
				return fixFlowDataVariable;
			}
		}
		return null;
	}

	/**
	 * 得到数据变量所存放的路径
	 * 
	 * @return
	 */
	public static String getDataVariablePath() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject("fixflow-expand").getLocation().toString() + "/src/com/founder/fix/fixflow/expand/config/";
	}

	/**
	 * 得到数据变量XML所存放的路径
	 * 
	 * @return
	 */
	public static String getDataVariableXMLPath() {
		return getDataVariablePath() + "datavariableconfig.xml";
	}

	/**
	 * 通过数据变量的类型找到对应的数据变量(在内存中)
	 * 
	 * @param dataVariableConfig
	 * @param dataType
	 * @return
	 */
	public static FixFlowDataVariable getfixFlowDataVariableByDataType(DataVariableConfig dataVariableConfig, String dataType) {
		for (FixFlowDataVariable fixFlowDataVariable : dataVariableConfig.getFixFlowDataVariable()) {
			if (fixFlowDataVariable.getType() != null && fixFlowDataVariable.getType().equals(dataType)) {
				return fixFlowDataVariable;
			}
		}
		return null;
	}

	/**
	 * 加载所有的数据变量
	 * 
	 * @return
	 */
	public static DataVariableConfig getFixFlowDataVariableConfig() {

		if (dataVariableConfig == null) {

			dataVariableConfig = EMFUtil.getFixFlowDataVariableConfig(getDataVariableXMLPath());

		}
		return dataVariableConfig;
	}

	/**
	 * 获取数据类型维护
	 * 
	 * @return
	 */
	public static DataVariableDataType getDataVariableDataType() {
		return getFixFlowDataVariableConfig().getDataVariableDataType();
	}

	/**
	 * 通过数据变量的数据类型获取数据类型维护对象
	 * 
	 * @return
	 */
	public static DataTypeDef getDataTypeDefByDataVariableDataType(String dataType) {
		DataTypeDef dataTypeDef = VariableconfigFactory.eINSTANCE.createDataTypeDef();
		dataTypeDef.setName(dataType);
		for (DataTypeDef dataTypeDef1 : getFixFlowDataVariableConfig().getDataVariableDataType().getDataTypeDef()) {
			if (dataTypeDef1.getTypeValue().equals(dataType)) {
				return dataTypeDef1;
			}
		}
		return dataTypeDef;
	}

	/**
	 * 得到所有用户的DataVarTO
	 * 
	 * @return
	 */
	public static DataVarTo getAllUserDataVarTo() {
		DataVarTo dataVarTo = new DataVarTo();
		dataVarTo.setId("所有用户");
		dataVarTo.setCantbechoose("alluser");
		dataVarTo.setType("分类");

		// 循环遍历所有的用户信息
		for (UserInfoEntity userInfoEntity : getAllUserInfo()) {
			DataVarTo userdataVarTo = new DataVarTo();
			// 由于树是用ID显示，ID里面存用户名
			userdataVarTo.setId(userInfoEntity.getUserName() == null ? "" : userInfoEntity.getUserName());
			// Value里面存用户的id
			userdataVarTo.setValue("\"" + userInfoEntity.getUserId() + "\"");
			// 设置分类为用户
			userdataVarTo.setType("用户");
			dataVarTo.addChild(userdataVarTo);
		}

		return dataVarTo;
	}

	/**
	 * 返回平台的所有用户信息
	 * 
	 * @return
	 */
	public static List<UserInfoEntity> getAllUserInfo() {
		AllUserInfo allUserInfo = FixFlowConfigUtil.getFixFlowConfig().getDesignerOrgConfig().getAllUserInfo();
		if (userInforeturnList == null) {
			
			userInforeturnList = new ArrayList<UserInfoEntity>();
			if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true"))
				return userInforeturnList;
			Connection connection = null;
			try {
				connection = FixFlowConfigUtil.createConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(allUserInfo.getSqlText());

				while (resultSet.next()) {
					UserInfoEntity userInfoEntity = new UserInfoEntity();
					userInfoEntity.setUserId(resultSet.getString(allUserInfo.getUserIdField()));
					userInfoEntity.setUserName(resultSet.getString(allUserInfo.getUserNameField()));
					userInforeturnList.add(userInfoEntity);
				}
			} catch (Exception e) {

			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {

					}
				}
			}

			return userInforeturnList;
		} else {
			return userInforeturnList;
		}
	}

	/**
	 * 根据组中的用户信息返回平台的用户信息
	 * 
	 * @return
	 */
	public static List<UserInfoEntity> getUserInfoByUserInfo(String type,UserInfo userInfo) {
		
		
		if (userInforeturnListMap!=null&&userInforeturnListMap.get(type)!=null) {
			return userInforeturnListMap.get(type);
			
		} else {
			
			userInforeturnListMap=new HashMap<String, List<UserInfoEntity>>();
			List<UserInfoEntity> userInforeturnList= new ArrayList<UserInfoEntity>();
			if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true"))
				return userInforeturnList;
			Connection connection = null;
			try {
				connection = FixFlowConfigUtil.createConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(userInfo.getSqlText());

				while (resultSet.next()) {
					UserInfoEntity userInfoEntity = new UserInfoEntity();
					userInfoEntity.setOrgId(resultSet.getString(userInfo.getGroupIdField()));
					userInfoEntity.setUserId(resultSet.getString(userInfo.getUserIdField()));
					userInfoEntity.setUserName(resultSet.getString(userInfo.getUserNameField()));
					userInforeturnList.add(userInfoEntity);
				}
				
				userInforeturnListMap.put(type, userInforeturnList);
			} catch (Exception e) {

			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {

					}
				}
			}

			return userInforeturnList;
			
		
			
		}
	}

	/**
	 * 得到所有组织结构的DataVarTO
	 * 
	 * @return
	 */
	public static List<DataVarTo> getGroupDataVarTo() {
		List<DataVarTo> dataVarTos = new ArrayList<DataVarTo>();
		List<GroupInfo> groupInfos = FixFlowConfigUtil.getFixFlowConfig().getDesignerOrgConfig().getGroupInfo();

		for (GroupInfo groupInfo : groupInfos) {
			List<DataVarTo> userdaDataVarTos = new ArrayList<DataVarTo>();
			List<DataVarTo> deptdaDataVarTos = new ArrayList<DataVarTo>();

			// 如果既不启用也不显示用户则直接跳出
			if (groupInfo.getIsEnabled().equals("false")) {
				continue;
			}
			DataVarTo dataVarTo = new DataVarTo();
			dataVarTo.setId(groupInfo.getGroupName());
			dataVarTo.setCantbechoose(groupInfo.getGroupId());
			dataVarTo.setType("分类");

			if (groupInfo.getIsDisplayUser().equals("true")) {
				// 循环遍历所有的用户信息
				for (UserInfoEntity userInfoEntity : getUserInfoByUserInfo(groupInfo.getGroupId(),groupInfo.getUserInfo())) {
					DataVarTo userdataVarTo = new DataVarTo();
					// 由于树是用ID显示，ID里面存用户名
					userdataVarTo.setId(userInfoEntity.getUserName() == null ? "" : userInfoEntity.getUserName());
					// Name里面存用户的部门ID
					userdataVarTo.setName(userInfoEntity.getOrgId());
					// Value里面存用户的id
					userdataVarTo.setValue("\"" + userInfoEntity.getUserId() + "\"");
					// 设置分类为用户
					userdataVarTo.setType("用户");

					userdaDataVarTos.add(userdataVarTo);
				}
			}

			if (groupInfo.getIsEnabled().equals("true")) {
				// 循环遍历所有的部门信息
				for (OrgInfoEntity orgInfoEntity : getGroupInfo(groupInfo)) {
					DataVarTo deptdataVarTo = new DataVarTo();
					// 由于树是用ID显示，ID里面存部门名
					deptdataVarTo.setId(orgInfoEntity.getOrgName());
					// Name里面存部门的上级部门ID
					deptdataVarTo.setName(orgInfoEntity.getSupOrgId());
					// Value里面存部门的id
					deptdataVarTo.setValue("\"" + orgInfoEntity.getOrgId() + "\"");
					// 设置分类为部门
					deptdataVarTo.setType(groupInfo.getGroupName());

					deptdaDataVarTos.add(deptdataVarTo);
				}

				// 部门间结构
				for (DataVarTo deptDataVarTo : deptdaDataVarTos) {
					for (DataVarTo deptDataVarTo1 : deptdaDataVarTos) {
						if (deptDataVarTo.getName() != null && (!("\"" + deptDataVarTo.getName() + "\"").equals(deptDataVarTo.getValue()))) {
							if (("\"" + deptDataVarTo.getName() + "\"").equals(deptDataVarTo1.getValue())) {
								deptDataVarTo1.addChild(deptDataVarTo);
							}
						}
					}

					// 循环遍历，如果用户的部门ID与部门ID一致，则加入到该部门的节点中去
					for (DataVarTo udataVarTo : userdaDataVarTos) {
						//if (udataVarTo.getName() != null && (!("\"" + deptDataVarTo.getName() + "\"").equals(deptDataVarTo.getValue()))) {
						if (udataVarTo.getName() != null ) {
							if (("\"" + udataVarTo.getName() + "\"").equals(deptDataVarTo.getValue())) {
								deptDataVarTo.addChild(udataVarTo);
							}
						}
					}

					// 把部门添加到组织结构的节点中去
					if (deptDataVarTo.getName() == null || ("\"" + deptDataVarTo.getName() + "\"").equals(deptDataVarTo.getValue()) || deptDataVarTo.getName().equals("")) {
						dataVarTo.addChild(deptDataVarTo);
					}
				}
			}

			dataVarTos.add(dataVarTo);
		}
		return dataVarTos;
	}

	/**
	 * 返回平台的部门及角色（组）信息
	 * 
	 * @return
	 */
	public static List<OrgInfoEntity> getGroupInfo(GroupInfo groupInfo) {
		List<OrgInfoEntity> list = new ArrayList<OrgInfoEntity>();
		if (map == null || map.size() < 1)
			map = new HashMap<String, List>();
		if(FixFlowConfigUtil.getFixFlowConfig().getDataBaseConfig().getIsEnableDesCon().equals("true"))
			return list;
		Connection connection = null;
		// 根据数据源信息连接数据库
		try {
			connection = FixFlowConfigUtil.createConnection();
			if (connection == null)
				return list;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(groupInfo.getSqlText());

			while (resultSet.next()) {
				OrgInfoEntity orgInfoEntity = new OrgInfoEntity();
				orgInfoEntity.setOrgId(resultSet.getString(groupInfo.getGroupIdField()));
				orgInfoEntity.setOrgName(resultSet.getString(groupInfo.getGroupNameField()));
				orgInfoEntity.setSupOrgId(resultSet.getString(groupInfo.getSupGroupIdField()));
				list.add(orgInfoEntity);
			}

			map.put(groupInfo.getGroupId(), list);
		} catch (Exception e) {
			MessageDialog.openInformation(null, "错误", "错误原因是 " + e.toString());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {

				}
			}
		}
		return map.get(groupInfo.getGroupId());
	}
}
