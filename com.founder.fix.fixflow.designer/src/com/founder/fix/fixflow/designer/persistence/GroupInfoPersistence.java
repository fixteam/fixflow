package com.founder.fix.fixflow.designer.persistence;


import java.util.ArrayList;
import java.util.List;


import com.founder.fix.bpmn2extensions.coreconfig.GroupInfo;
import com.founder.fix.fixflow.designer.util.FixFlowConfigUtil;
import com.founder.fix.fixflow.designer.util.StringUtil;

public class GroupInfoPersistence {

	private static List<GroupInfoTo> groupInfoTos;

	public static List<GroupInfoTo> getGroupInfoTos() {
		if (groupInfoTos == null) {

			groupInfoTos = new ArrayList<GroupInfoTo>();

			List<GroupInfo> groupInfoList = FixFlowConfigUtil
					.getFixFlowConfig().getDesignerOrgConfig().getGroupInfo();
					

			GroupInfoTo groupInfoToUser = new GroupInfoTo();
			groupInfoToUser.setTypeId("user");
			groupInfoToUser.setTypeName("用户");
			groupInfoToUser.setOrderId(0);
			groupInfoTos.add(groupInfoToUser);

			for (int i = 0; i < groupInfoList.size(); i++) {

				GroupInfo groupInfo = groupInfoList.get(i);
				GroupInfoTo groupInfoTo = new GroupInfoTo();
				groupInfoTo.setTypeId(StringUtil.getString(groupInfo.getGroupId()));
				groupInfoTo.setTypeName(StringUtil.getString(groupInfo.getGroupName()));
				groupInfoTo.setOrderId(StringUtil.getInt(i + 1));
				groupInfoTos.add(groupInfoTo);
			}

			return groupInfoTos;
		} else {
			return groupInfoTos;
		}

	}

}
