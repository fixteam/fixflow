/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * ITreeElement.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree;

import java.util.List;

/**
 * [类名]<br>
 * ITreeElement.java<br>
 * <br>
 * [功能概要]<br>
 *
 * <br>
 * [变更履历]<br>
 *
 * <br>
 * 2011-6-20 ver1.0 <br>
 * <br>
 *
 * @作者 wangzhiwei
 *
 */
public interface ITreeElement {
	
	//获取节点对象值
	public Object getData();
	
	//设置节点对象值
	public void setData(Object object);
	
	//获取节点对应的真实文件的名称
	public String getRealName();
	
	//设置节点对应的真实文件的名称
	public void setRealName(String realName);
	
	//获取图标
	public String getIcon();

	//设置图标
	public void setIcon(String icon);
	
	//设置树的对应的真实目录
	public void setRealPath(String realPath);
	
	//获取树的对应的真实目录
	public String getRealPath();
	
	//设置树的节点id
	public void setId(String id);
	
	//获取树的节点id
	public String getId();
	
	//设置树的节点名称
	public void setName(String name);
	
	//获取树的节点名称
	public String getName();
	
	//设置子节点集合
	public void setChildren(List<ITreeElement> children);
	
	//获取子节点集合
	public List<ITreeElement> getChildren();
	
	//是否有子节点
	public boolean hasChildren();
	
	//添加子节点
	public void addChild(ITreeElement treeElement);
	
	//获取父节点
	public ITreeElement getParent();
	
	//获取父节点
	public void setParent(ITreeElement treeElement);
	
	//删除子节点
	public void removeChild(ITreeElement treeElement);
}
