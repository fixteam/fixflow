/**
 * Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author shao
 */
package com.founder.fix.fixflow.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.founder.fix.fixflow.core.impl.util.DateUtil;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.impl.util.XmlUtil;

/**
 * @author shao
 * 文件操作工具类
 */
public class FileUtil
{	
	public static final String DEFAULT_ITEM_ID="1";
	
	public static final String ATTACHMENTDB="AttachmentDB";
	
	public static final String STORETYPE="Store";
	

	/**
	 * 删除一个文件，以路径作为参数。
	 * @param filePath
	 */
	public static void deleteFile(String filePath){
		File file = new File(filePath);
		deleteFile(file);
	}
	
	/**
	 * 删除一个文件，以文件作为参数。
	 * @param file
	 */
	private static void deleteFile(File file){
	   if(file.exists()){                    //判断文件是否存在
		    if(file.isFile()){                    //判断是否是文件
		     file.delete();                       //delete()方法 你应该知道 是删除的意思;
		    }else if(file.isDirectory()){              //否则如果它是一个目录
		     File files[] = file.listFiles();               //声明目录下所有的文件 files[];
		     for(int i=0;i<files.length;i++){            //遍历目录下所有的文件
		      deleteFile(files[i]);             //把每个文件 用这个方法进行迭代
		     } 
		    } 
		    file.delete(); 
		   }else{ 
		    System.out.println("所删除的文件不存在！"+'\n'); 
		   } 
	}
	
	/**
	 * 获取一个文件的后缀
	 * @param filePath
	 * @return
	 */
	public static String getFileEx(String filePath){
		String result = null;
		int index = filePath.lastIndexOf(".");
		if(index>-1){
			result = filePath.substring(index+1,filePath.length());
		}else{
			result = "noEx";
		}
		
		return result;
	}
	
	/**
	 * 检查文件是否存在
	 * @param path
	 * @return
	 */
	public static boolean checkFileExist(String path){
		File file = new File(path);
		return file.exists();
	}
	
	/**
	 * 创建一个文件，联同父目录一起创建
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean makeFile(File file) throws IOException {
		boolean result = false;
		makeParent(file);
		if(!file.exists()){
			result = file.createNewFile();
		}
		
		return result;
	}
	
	/**
	 * 从一个文件全路径中截取文件名
	 * @param path
	 * @return
	 */
	public static String getFileName(String path){
		String result = null;
		if(StringUtil.isNotEmpty(path)){
			int num = path.lastIndexOf("/");
			
			int tnum = path.lastIndexOf("\\");
			if(tnum >num)
				num=tnum;
			
			if(num>0)
				return path.substring(num+1,path.length());
		}
		
		return result;
	}
	
	/**
	 * 从一个文件路径中获取路径部分
	 * @param path
	 * @return
	 */
	public static String getFilePath(String path){
		String result = null;
		if(StringUtil.isNotEmpty(path)){
			int num = path.lastIndexOf("/");
			
			int tnum = path.lastIndexOf("\\");
			if(tnum >num)
				num=tnum;
			
			if(num>0)
				return path.substring(0,num+1);
		}
		
		return result;
	}

	
	public static void main(String[] args)throws Exception{
		String path = "D:\\目录";
		String tpath = "D:\\menu2";
		List<String> str= new ArrayList<String>();
		str.add("eer");
		copyFolder(path,tpath,str);
	}
	
	/**
	 * 处理附件名
	 * @param tmp
	 * @return
	 */
	public static String processAttachmentName(String tmp){
		if(StringUtil.isNotEmpty(tmp)){
			if(tmp.lastIndexOf("/")!=-1){
				tmp = tmp.substring(tmp.lastIndexOf("/")+1);
			}else if(tmp.lastIndexOf("\\")!=-1){
				tmp = tmp.substring(tmp.lastIndexOf("\\")+1);
			}
		}		
		return tmp;
	}
	
	/**
	 * 创建一个路径的所有的父目录
	 * @param file
	 */
	public static void makeParent(String file){
		makeParent(new File(file));
	}
	
	/**
	 * 创建一个文件的所有父目录
	 * @param file
	 */
	public static void makeParent(File file){
		if(file.getParentFile()!=null && !file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	}
	
	/**
	 * 路径拷贝
	 * @param from
	 * @param target
	 * @param ingores
	 * @throws IOException
	 */
	public static void copyFolder(String from ,String target,List<String> ingores) throws IOException{
		File file = new File(from); 
		if (file.isFile()) {
			copyFile(from,target);
		}else{
			if(ingores!=null && !ingores.contains(file.getName())){
				if (!file.exists()) { 
					file.mkdir(); 
				} 
				String[] fileList; 
				fileList = file.list(); 
				for (int i = 0; i < fileList.length; i++) { 
					copyFolder(from + "/" + fileList[i], target + "/" + fileList[i],ingores); 
				} 
			}
		}
	}
	
	/**
	 * 文件拷贝
	 * @param from
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(String from ,String target) throws IOException{
		InputStream is = ConfFileLoader.getInputStreamForFullPath(from);
		
		copyFile(is,target);
	}
	
	/**
	 * 文件拷贝
	 * @param from
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(InputStream from ,String target) throws IOException{	
		FileUtil.makeParent(target);
		BufferedInputStream input = new BufferedInputStream(from);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream (target));
		try{
			byte[] buff = new byte[2048];
			int size = 0;
			while((size = input.read(buff))!=-1){
				out.write(buff,0,size);
				out.flush();
			}
		}finally{
			input.close();
			out.close();
		}
	}
	
	/**
	 * 判断是否是附件类型
	 * @param type
	 * @return
	 */
	public static boolean isAttachmentType(String type){
		boolean result = false;
		if(StringUtil.isNotEmpty(type)){
			if(type.equals(ATTACHMENTDB) || type.startsWith(STORETYPE)){
				result = true;
			}
		}
		return result;
	}

	
//	private static Column[] getExportColumns(DataTable dataTable,ObjDataTo objData){
//		List<Column> tmpResult = new ArrayList<Column>();
//		List<ObjColumnTo> realColumns = objData.getColumns();
//		
//		for(int i = 0;i<dataTable.Columns.length;i++){
//			DataTable.DataColumn dataColumn = dataTable.Columns[i];
//			String caption = dataColumn.Caption;
//			boolean isHiddenColumn = false;
//			for(ObjColumnTo realColumn :realColumns){
//				if(realColumn.getCCode().equalsIgnoreCase(dataColumn.ColumnName)){
//					if(!StringUtil.getString(realColumn.getIsDisplay()).equals("1")){
//						isHiddenColumn = true;
//						break;
//					}
//					caption = realColumn.getCName();
//					break;
//				}
//			}
//			
//			if(isHiddenColumn || StringUtil.isEmpty(caption)){
//				continue;
//			}
//			
//			Column tmpColumn =  new Column(caption, dataColumn.ColumnName, Column.ENUM_TYPE.VARCHAR2, 1000, false);
//			tmpResult.add(tmpColumn);
//		}
//		Column [] result = (Column []) tmpResult.toArray(new Column[tmpResult.size()]);  
//		return result;
//	}
//	
//	private static Column[] getImportColumns(ObjDataTo objTo){
//		Column[] result = new Column[objTo.getColumns().size()];
//		for(int i = 0;i<objTo.getColumns().size();i++){
//			ObjColumnTo dataColumn = objTo.getColumns().get(i);
//			
//			Column tmpColumn =  new Column(dataColumn.getCName(), dataColumn.getCCode(), Column.ENUM_TYPE.VARCHAR2, 1000, false);
//			result[i] = tmpColumn;
//		}
//		
//		return result;
//	}
//	
//	public static DataTable import07Excel(XSSFWorkbook wb,ObjDataTo objTo,Map<String,Object> param){
//		DataTable dataTable = new DataTable();
//		Column[] columns = getImportColumns(objTo);
//		DataTable.DataColumn dataColumns[] = new DataTable.DataColumn[columns.length];
//		
//		for(int i=0;i<columns.length;i++){
//			DataColumn dataColumn = dataTable.NewColumn();
//			dataColumn.ColumnIndex = i;
//			dataColumn.ColumnName  = columns[i].getCol();
//			dataColumns[i] = dataColumn;
//		}
//		dataTable.Columns = dataColumns;
//		int start = 0;
//		Object startObject = param.get("startRow");
//		Object importType = param.get("importType");
//		if(startObject != null){
//			start = Integer.valueOf(startObject.toString());
//		}
//		
//		XSSFSheet sheet = wb.getSheetAt(0);
//		if(importType == null || importType.equals("caption")){
//			XSSFRow stkRow = sheet.getRow(start);
//			List<String> columnCodes = new ArrayList<String>();
//			for(short i = 0;i<stkRow.getLastCellNum();i++){
//				String columnCode = null;
//				XSSFCell stkCell = stkRow.getCell(i);
//				for(int j = 0;j<columns.length;j++){
//					if(columns[j].getCol().equals(stkCell.getStringCellValue())){
//						columnCode = columns[j].getCol();
//						break;
//					}
//				}
//				
//				if(columnCode==null || columnCode.length()==0){
//					columnCodes.add("");
//				}else{
//					columnCodes.add(columnCode);
//				}
//			}
//			for(int i = start+1;i<=sheet.getLastRowNum();i++){
//				DataTable.DataRow dataRow = dataTable.NewRow();
//				XSSFRow row = sheet.getRow(i);
//				for(short j = 0;j<row.getLastCellNum();j++){
//					XSSFCell stkCellj = row.getCell(j);
//					DataTable.DataItem item = dataRow.Item(columnCodes.get(j));
//					if(item!=null){
//						if(HSSFCell.CELL_TYPE_NUMERIC == stkCellj.getCellType()){
//							item.Value = stkCellj.getNumericCellValue();
//						}else if(HSSFCell.CELL_TYPE_STRING == stkCellj.getCellType()){
//							item.Value = stkCellj.getStringCellValue();
//						}
//					}
//						
//				}
//				dataTable.AddRow(dataRow);
//			}
//			
//		}else{
//			for(int i = start;i<=sheet.getLastRowNum();i++){
//				DataTable.DataRow dataRow = dataTable.NewRow();
//				XSSFRow row = sheet.getRow(i);
//				for(short j = 0;j<row.getLastCellNum();j++){
//					XSSFCell stkCellj = row.getCell(j);
//					if(j<dataRow.Items.length){
//						DataTable.DataItem item = dataRow.Items[j];
//						if(item!=null){
//							if(HSSFCell.CELL_TYPE_NUMERIC == stkCellj.getCellType()){
//								item.Value = stkCellj.getNumericCellValue();
//							}else if(HSSFCell.CELL_TYPE_STRING == stkCellj.getCellType()){
//								item.Value = stkCellj.getStringCellValue();
//							}
//						}
//					}else{
//						break;
//					}
//				}
//				dataTable.AddRow(dataRow);
//			}
//		}
//		
//		return dataTable;
//	}
//	
//	public static DataTable import03Excel(HSSFWorkbook wb,ObjDataTo objTo,Map<String,Object> param){
//		DataTable dataTable = new DataTable();
//		Column[] columns = getImportColumns(objTo);
//		DataTable.DataColumn dataColumns[] = new DataTable.DataColumn[columns.length];
//		
//		for(int i=0;i<columns.length;i++){
//			DataColumn dataColumn = dataTable.NewColumn();
//			dataColumn.ColumnIndex = i;
//			dataColumn.ColumnName  = columns[i].getCol();
//			dataColumns[i] = dataColumn;
//		}
//		dataTable.Columns = dataColumns;
//		int start = 0;
//		Object startObject = param.get("startRow");
//		Object importType = param.get("importType");
//		if(startObject != null){
//			start = Integer.valueOf(startObject.toString());
//		}
//		
//		HSSFSheet sheet = wb.getSheetAt(0);
//		if(importType == null || importType.equals("caption")){
//			HSSFRow stkRow = sheet.getRow(start);
//			List<String> columnCodes = new ArrayList<String>();
//			for(short i = 0;i<stkRow.getLastCellNum();i++){
//				String columnCode = null;
//				HSSFCell stkCell = stkRow.getCell(i);
//				for(int j = 0;j<columns.length;j++){
//					if(columns[j].getCol().equals(stkCell.getStringCellValue())){
//						columnCode = columns[j].getCol();
//						break;
//					}
//				}
//				
//				if(columnCode==null || columnCode.length()==0){
//					columnCodes.add("");
//				}else{
//					columnCodes.add(columnCode);
//				}
//			}
//			for(int i = start+1;i<=sheet.getLastRowNum();i++){
//				DataTable.DataRow dataRow = dataTable.NewRow();
//				HSSFRow row = sheet.getRow(i);
//				for(int j = 0;j<row.getLastCellNum();j++){
//					HSSFCell stkCellj = row.getCell(j);
//					DataTable.DataItem item = dataRow.Item(columnCodes.get(j));
//					if(item!=null){
//						if(HSSFCell.CELL_TYPE_NUMERIC == stkCellj.getCellType()){
//							item.Value = stkCellj.getNumericCellValue();
//						}else if(HSSFCell.CELL_TYPE_STRING == stkCellj.getCellType()){
//							item.Value = stkCellj.getStringCellValue();
//						}
//					}
//						
//				}
//				dataTable.AddRow(dataRow);
//			}
//			
//		}else{
//			for(int i = start;i<=sheet.getLastRowNum();i++){
//				DataTable.DataRow dataRow = dataTable.NewRow();
//				HSSFRow row = sheet.getRow(i);
//				for(int j = 0;j<row.getLastCellNum();j++){
//					HSSFCell stkCellj = row.getCell(j);
//					if(j<dataRow.Items.length){
//						DataTable.DataItem item = dataRow.Items[j];
//						if(item!=null){
//							if(HSSFCell.CELL_TYPE_NUMERIC == stkCellj.getCellType()){
//								item.Value = stkCellj.getNumericCellValue();
//							}else if(HSSFCell.CELL_TYPE_STRING == stkCellj.getCellType()){
//								item.Value = stkCellj.getStringCellValue();
//							}
//						}
//					}else{
//						break;
//					}
//				}
//				dataTable.AddRow(dataRow);
//			}
//		}
//		
//		return dataTable;
//	}
//	
//	public static void exportCsv(DataTable dataTable,Map<String,Object> param) throws IOException{
//		File file = new File("\\", dataTable.TableName);
//		CSVWriter csv = new CSVWriter(new FileWriter(file));
//	}
//	
//	public static byte[] exportTxt(DataTable dataTable,Map<String,Object> param,ObjDataTo objData){
//		StringBuffer exportString = new StringBuffer();
//        Object startObject = param.get("startRow");
//		Integer startRow = startObject==null?0:Integer.valueOf(startObject.toString());
//		DataTable.DataColumn[] columns = dataTable.Columns;
//		List<ObjColumnTo> realColumns = objData.getColumns();
//		
//		int stkStartRowNo = 0;
//		if(startRow<1){
//			
//			for(int i = 0;i<columns.length;i++){
//				DataTable.DataColumn column = columns[i];
//				String caption = column.ColumnName;
//				
//				for(ObjColumnTo realColumn :realColumns){
//					if(realColumn.getCCode().equalsIgnoreCase(column.ColumnName)){
//						caption = realColumn.getCName();
//					}
//				}
//				
//				if(i!=0)
//					exportString.append("	");
//				exportString.append(caption);
//			}
//			exportString.append("\r\n");
//			stkStartRowNo++;
//		}
//		
//		for(int i = startRow;i<dataTable.Rows.length;i++){
//            stkStartRowNo++;
//          
//            DataTable.DataRow row = null;
//			if (dataTable!=null && dataTable.Rows != null) {
//			  row = dataTable.Rows[i];
//			}
//			
//			for (int j = 0; row!=null && j < columns.length; j++) {
//                if (dataTable!=null && dataTable.Rows != null) {
//                	if(j!=0)
//                		exportString.append("	");
//                	exportString.append(row.Item(columns[j].ColumnName).ExValue);
//                }
//			}
//			exportString.append("\r\n");
//		}
//		
//		return exportString.toString().getBytes();
//	}
//	
//	public static HSSFWorkbook exportExcel(DataTable dataTable,Map<String,Object> param,ObjDataTo objData) throws IOException{
//		Column[] columns = getExportColumns(dataTable,objData);
//        Object startObject = param.get("startRow");
//		Integer startRow = StringUtil.isEmpty(StringUtil.getString(startObject))?0:Integer.valueOf(startObject.toString());
//		
//		HSSFWorkbook wb = new HSSFWorkbook();// excel文件对象
//		
//        HSSFSheet sheet = wb.createSheet(dataTable.TableName);// 工作表对象
//        sheet.setDefaultColumnWidth(15);
//        sheet.setColumnWidth(0, 3600);
//        
//        StockPoolStkCellStyle stockPoolStkCellStyle = new StockPoolStkCellStyle(wb);
//
//        int stkStartRowNo = 0;
////        if (havePoolProperties) {
////            stkStartRowNo = stockPoolProperties.length + 2;
////        }
//        int recordLength =  (dataTable == null ||  dataTable.Rows==null) ? 20 :  dataTable.Rows.length;
//        if(startRow<1){
//	        HSSFRow stkStartRow = sheet.createRow(stkStartRowNo);
//
//	
//	        for (int i = 0; i < columns.length; i++) {
//	            Column colum = columns[i];
//	
//	            HSSFCell cellI = stkStartRow.createCell(i);
//	            cellI.setCellValue(new HSSFRichTextString(colum.getName()).getString());
//	            if (i == columns.length-1) {
//	                cellI.setCellStyle(stockPoolStkCellStyle.getStyleTopRight());
//	            } else if(i==0){
//	            	cellI.setCellStyle(stockPoolStkCellStyle.getStyleTopLeft());
//	            }else {
//	                cellI.setCellStyle(stockPoolStkCellStyle.getStyleTop());
//	            }
//	        }
//	        stkStartRowNo++;
//        }
//        for (int i = startRow; i < recordLength; i++) {
//            HSSFRow stkRow = sheet.createRow(stkStartRowNo);
//            stkStartRowNo++;
////            HSSFCell stkCell0 = stkRow.createCell((short) 0);
////            stkCell0.setCellValue(i);
////
////            if (i == recordLength-1) {
////                stkCell0.setCellStyle(stockPoolStkCellStyle.getStyleBottomLeft());
////            } else if(i==0){
////            	stkCell0.setCellStyle(stockPoolStkCellStyle.getStyleTopLeft());
////            }else {
////                stkCell0.setCellStyle(stockPoolStkCellStyle.getStyleLeft());
////            }
//            
//            
//            DataTable.DataRow row = null;
//            if (dataTable!=null && dataTable.Rows != null) {
//            	row = dataTable.Rows[i];
//            }
//
//            for (int j = 0; row!=null && j < columns.length; j++) {
//                HSSFCell stkCellJ = stkRow.createCell(j);
////                Column colum = columns[j - 1];
//
//                if (dataTable!=null && dataTable.Rows != null) {
//                	Object value = row.Item(columns[j].getCol()).ExValue;
//                	
//                	HSSFRichTextString frs = new HSSFRichTextString(value==null?"":value.toString());
//                	stkCellJ.setCellValue(frs.getString());
//                }
//                if (i == recordLength) {
//                    if (j == columns.length) {
//                        stkCellJ.setCellStyle(stockPoolStkCellStyle.getStyleBottomRight());
//                    } else {
//                        stkCellJ.setCellStyle(stockPoolStkCellStyle.getStyleBottom());
//                    }
//                } else {
//                    if (j == columns.length) {
//                        stkCellJ.setCellStyle(stockPoolStkCellStyle.getStyleRight());
//                    } else {
//                        stkCellJ.setCellStyle(stockPoolStkCellStyle.getStyleMiddle());
//                    }
//                }
////                PoiUtil.modifyCellType(wb, stkCellJ, colum.getType());
//            }
//
//        }
//
//        return wb;
//	}
	
	 public   static  String toUtf8String(String s){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
