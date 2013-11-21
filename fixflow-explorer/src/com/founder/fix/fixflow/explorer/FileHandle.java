package com.founder.fix.fixflow.explorer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@SuppressWarnings("unchecked")
public class FileHandle {
	private static List<FileItem> fi = new LinkedList<FileItem>();
	public static void whenUploadFileBindParameter(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			fi.clear();fi.removeAll(fi);
			Iterator<FileItem> iterator = createFactory(request, response);
			while (iterator.hasNext()) {
				FileItem fileItem = (FileItem) iterator.next();
				if (fileItem.isFormField()) { // 是否是表单提交域，可以分区是否上传的附件
					String name = fileItem.getFieldName(); // input标签的name
					String value = fileItem.getString(); // input表单的value
					request.setAttribute(name, value);
				}else{
					fi.add(fileItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("附件上传时表单基本数据绑定出错!");
		}
	}

	public static String updload(HttpServletRequest request,
			HttpServletResponse response, String autoPath,String showFileName) throws Exception {
	    String message = "";
		 
		// 如果附件地址不存在 就创建一下
		File uploadPath = new File(autoPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		for (int i = 0; i < fi.size(); i++) {
			FileItem fileItem = fi.get(i);
			if (!fileItem.isFormField()) {
				//String fieldName = fileItem.getFieldName(); // 表单提交过来的file  input标签中name的属性值
				String fileName = fileItem.getName(); // file input上传的文件名
				//String contentType = fileItem.getContentType(); // 获得上传文件的类型
				//long size = fileItem.getSize(); // 上传文件的笑答

				String filePath = autoPath + File.separator+showFileName;
				// org.apache.commons.fileupload.FileItem 提供write方法写入磁盘中
				// fileItem.write(new File(filePath));
				// 使用字节流读取二进制格式的附件传给文件流 然后 写入磁盘
				OutputStream outputStream = new FileOutputStream(new File(filePath));// 这里传递父亲的文件夹路径和当前文件的名称
				InputStream inputStream = fileItem.getInputStream();
				int length = 0;

				byte[] buf = new byte[1024];
				while ((length = inputStream.read(buf)) != -1) { // 首先根据传递的字节数组将读取的字节的数量返回，在判断是否读取的空
					outputStream.write(buf, 0, length);
				}
				inputStream.close();
				outputStream.close();
				message = "附件("+fileName+")上传成功,保存的地址为:"+filePath;
			}
		}
		return message;
	}
	
	public static Iterator<FileItem>  createFactory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
		response.setContentType("text/html;charset=UTF-8");
		// 创建一个磁盘文件的工厂，然后将它 传递到servletFileUplaod的实例中
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				diskFileItemFactory);
		// 根据request对象获取所有的文件集合，这里包括input标签输入的值也属于FileInput
		List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
		return  fileItemList.iterator();
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("磁盘文件的工厂创建失败!");
	}
	}
}
