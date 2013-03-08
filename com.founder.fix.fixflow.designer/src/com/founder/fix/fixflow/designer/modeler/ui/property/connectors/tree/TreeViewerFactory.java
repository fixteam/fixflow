/**
 * 
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import com.founder.fix.bpmn2extensions.connector.Connector;
import com.founder.fix.bpmn2extensions.connectormenu.Menu;
import com.founder.fix.bpmn2extensions.connectormenu.MenuConnector;
import com.founder.fix.bpmn2extensions.connectormenu.Node;
import com.founder.fix.fixflow.designer.util.ConnectorUtil;
import com.founder.fix.fixflow.designer.util.EMFUtil;

/**
 * @author wangzhiwei
 *
 */
public class TreeViewerFactory {

	/**
	 * 
	 */
	public TreeViewerFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 加载树数据
	 * @return
	 */
	public static List<ITreeElement> reloadTree() {
		List<ITreeElement> elements = new ArrayList<ITreeElement>();
		
		Menu root=EMFUtil.getConnectorMenuConfig(ConnectorUtil.getMenuConnectorPath());
		
		

		
		/*
		//读取文件
		XMIResource menuresource = (XMIResource) new ResourceSetImpl()
				.getResource(URI.createFileURI(ConnectorUtil.getMenuConnectorPath()), true);
		menuresource.setEncoding("UTF-8");
		Menu root = (Menu) menuresource.getContents().get(0);
		*/
		//分拆menu成为ITreeElement树
		if(root != null) {
			EList<Node> nodes = root.getNode();
			if(nodes != null && nodes.size() > 0) {
				for (Iterator iterator = nodes.iterator(); iterator.hasNext();) {
					
					Node node = (Node) iterator.next();
					ITreeElement parentElement = new EntityElement(null, node.getId(), node.getName(), node.getName(),
							ConnectorUtil.getMenuConnectorIconPathByIconName(node.getIco()), "");
					
					EList<MenuConnector> connectors = node.getMenuConnector();
					if(connectors != null && connectors.size() > 0) {
						for (Iterator iterator2 = connectors.iterator(); iterator2
								.hasNext();) {
							
							MenuConnector menuConnector = (MenuConnector) iterator2
									.next();
							ITreeElement childElement = new EntityElement(parentElement, menuConnector.getId(), menuConnector.getName(),
									menuConnector.getName(), ConnectorUtil.getConnectorIconPathByIconName(menuConnector.getId(),
											ConnectorUtil.getConnectorByMenuConnectorId(menuConnector.getId()).getIcon()), ConnectorUtil.getConnectorByMenuConnectorId(menuConnector.getId()).getConnectorNote());
					//懒得改TO，把连接器的描述放在了realpath里面		
							parentElement.addChild(childElement);
						}
					}
					elements.add(parentElement);
				}
			}
		}
		
		return elements;
	}
	
	/**
	 * 获取连接器
	 * @param url
	 * @return
	 */
	public static Connector getConnector(String connectorId) {
		return ConnectorUtil.getConnectorByMenuConnectorId(connectorId);
	}

}
