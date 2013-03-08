/**
 * Copyright c FOUNDER CORPORATION 2011 All Rights Reserved.
 * TreeViewerLabelProvider.java
 */
package com.founder.fix.fixflow.designer.modeler.ui.property.connectors.tree;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.PlatformUI;

/**
 * [类名]<br>
 * TreeViewerLabelProvider.java<br>
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
public class TreeViewerLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

	/**
	 * 
	 */
	public TreeViewerLabelProvider() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object element) {
		ITreeElement tElement = (ITreeElement) element;
		try {
			String imagePath = tElement.getIcon(); // 要读取的图片文件的路径
			return new Image(PlatformUI.getWorkbench().getDisplay(),
					new ImageData(imagePath).scaledTo(16, 16));
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		// TODO Auto-generated method stub
		ITreeElement tElement = (ITreeElement) element;
		return tElement.getName();
	}

	@Override
	public void update(ViewerCell cell) {
		// TODO Auto-generated method stub
		if (cell.getElement() instanceof EntityElement) {
			EntityElement e = (EntityElement) cell.getElement();
			StyledString styledString = new StyledString();
			String decoration = " -- " + e.getRealPath();
			styledString.append(e.getName());
			if(!(e.getRealPath().equals(""))) {
				styledString.append(decoration, StyledString.DECORATIONS_STYLER);
			}
			cell.setText(styledString.getString());
			cell.setImage(getImage(e)) ;
			cell.setStyleRanges(styledString.getStyleRanges());
		}
	}

}
