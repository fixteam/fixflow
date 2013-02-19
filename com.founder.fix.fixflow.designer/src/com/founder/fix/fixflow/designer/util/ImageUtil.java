package com.founder.fix.fixflow.designer.util;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ImageUtil {
	
	/**
	 * 获取插件项目下(jar包中)的某个文件目录路径
	 * @param filePath
	 * @return
	 */
	public static String getPluginDevelopRealPath(String filePath) {
		//Bundle bundle = Platform.getBundle(com.founder.fix.fixflow.designer.Activator.PLUGIN_ID);
		
		
		
		URL url = ReflectUtil.getResource("com/founder/fix/fixflow/designer"+filePath);
		
		
		
		try {
			return FileLocator.toFileURL(url).getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 根据图片名称得到一个图像
	 * 
	 * @param fileName
	 * @return
	 */
	public static Image getImageFromURL(String fileName) {
		try {
			String imagePath = "/icon" + fileName; // 要读取的图片文件的路径
			ImageDescriptor descriptor = AbstractUIPlugin
					.imageDescriptorFromPlugin(com.founder.fix.fixflow.designer.Activator.PLUGIN_ID, imagePath); 
			return descriptor.createImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取得图片路径
	 * @param imageName
	 * @return
	 */
	public static String getFixFlowImage(String imageName) {
		return getPluginDevelopRealPath("/image/" + imageName);
	}
	
	/**
	 * 取得图标路径
	 * @param iconName
	 * @return
	 */
	public static String getFixFlowIcon(String iconName) {
		return getPluginDevelopRealPath("/icon/" + iconName);
	}
}
