package com.founder.fix.fixflow.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ConfFileLoader{
	

	/**
	 * 根据文件的全路径获取资源
	 * @param url
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getInputStreamForFullPath(String url) throws FileNotFoundException{
		InputStream in;
		File file = new File(url);
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw e;
		}

		return in;
	}
	

}
