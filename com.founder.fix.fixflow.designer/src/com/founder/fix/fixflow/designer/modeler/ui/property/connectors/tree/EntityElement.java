/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * EntityElement.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorInput;

/**
 * [类名]<br>
 * EntityElement.java<br>
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
public class EntityElement implements ITreeElement {
	
	//树节点图标
	public static final int PROJECT = 1;
	
	//节点对象
	private Object object;
	
	//父节点
	private ITreeElement parentElement;
	
	//节点的id标示，表明这个节点的类型、一般为字符串常量
	private String id;

	//需要打开的编辑器
	private IEditorInput editorInput;
	
	//节点名称、即该节点显示的名称
	private String name;
	
	//与上面的name相对应，节点对应的真实文件的名称
	private String realName;
	
	//节点对应的真实目录文件的路径
	private String realPath;
	
	//节点所用图片的标识
	private String icon;
	
	//根节点包含的集合，封装在list中、标示子节点
	private List<ITreeElement> list = new ArrayList<ITreeElement>();
	
	/**
	 * 构造器
	 */
	public EntityElement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造器
	 */
	public EntityElement(String name, String icon) {
		this.name = name;
		this.icon = icon;
	}
	
	/**
	 * 构造器
	 */
	public EntityElement(ITreeElement parentElement, String id, String name, String icon, String realPath) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.realPath = realPath;
		this.parentElement = parentElement;
	}
	
	/**
	 * 构造器
	 */
	public EntityElement(ITreeElement parentElement, String id, String name, String realName, String icon, String realPath) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.realPath = realPath;
		this.parentElement = parentElement;
		this.realName = realName;
	}

	/* (non-Javadoc)
	 * @see prespectve.tree.ITreeElement#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see prespectve.tree.ITreeElement#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see prespectve.tree.ITreeElement#setChildren(java.util.List)
	 */
	public void setChildren(List<ITreeElement> children) {
		this.list = children;
	}

	/* (non-Javadoc)
	 * @see prespectve.tree.ITreeElement#getChildren()
	 */
	public List<ITreeElement> getChildren() {
		return list;
	}

	/* (non-Javadoc)
	 * @see prespectve.tree.ITreeElement#hasChildren()
	 */
	public boolean hasChildren() {
		if(list.size() > 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see prespectve.tree.ITreeElement#addChild(prespectve.tree.ITreeElement)
	 */
	public void addChild(ITreeElement treeElement) {
		list.add(treeElement);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public ITreeElement getParent() {
		return this.parentElement;
	}

	public void removeChild(ITreeElement treeElement) {
		list.remove(treeElement);
	}
	
	public IEditorInput getEditorInput() {
		return editorInput;
	}

	public void setEditorInput(IEditorInput editorInput) {
		this.editorInput = editorInput;
	}


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Object getData() {
		return object;
	}

	public void setData(Object object) {
		this.object = object;
	}

	public ITreeElement getParentElement() {
		return parentElement;
	}

	public void setParent(ITreeElement parentElement) {
		this.parentElement = parentElement;
	}

	@Override
	public String getIcon() {
		return this.icon;
	}

	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
