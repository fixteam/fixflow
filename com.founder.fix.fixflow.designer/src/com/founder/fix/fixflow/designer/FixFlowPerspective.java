/**
 * 
 */
package com.founder.fix.fixflow.designer;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author wangzhiwei
 *
 */
public class FixFlowPerspective implements IPerspectiveFactory {
	
	public static final String PROJECT_VIEW = "com.founder.fix.studio.platformdesigner.views.ProjectView";
	
	public static final String DATASOURCE_VIEW = "com.founder.fix.studio.platformdesigner.views.DataSourceView";

	/**
	 * 
	 */
	public FixFlowPerspective() {
		// TODO Auto-generated constructor stub
	}

	public void createInitialLayout(IPageLayout layout) {
		//获取透明视图的编辑空间标示
		String editerArea = layout.getEditorArea();
		
		// 编辑器左上部视图
		IFolderLayout leftTop = layout.createFolder("leftTop", IPageLayout.LEFT,
				0.25f, editerArea); //相对于‘editerArea’编辑器的位置left
//		leftTop.addView(IPageLayout.ID_PROJECT_EXPLORER); //工程视图
		leftTop.addView(FixFlowPerspective.PROJECT_VIEW); // fix工程视图
		
		// 编辑器左下角视图
		IFolderLayout leftBottom = layout.createFolder("leftBottom",
				IPageLayout.BOTTOM, 0.5f, FixFlowPerspective.PROJECT_VIEW); //相对于上面‘left’视图的位置在底部
		leftBottom.addView(IPageLayout.ID_PROJECT_EXPLORER); // 导航视图
		leftBottom.addView(FixFlowPerspective.DATASOURCE_VIEW); //视图
		
		//编辑器底部视图
		IFolderLayout bottom = layout.createFolder("bottom", 
				IPageLayout.BOTTOM, 0.75f, editerArea); //相对于‘editerArea’编辑器的位置底部
		bottom.addView(IPageLayout.ID_PROP_SHEET); //属性视图
	}

}
