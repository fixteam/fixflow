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
 * @author kenshin
 */
package com.founder.fix.fixflow.expand.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.db.SqlCommand;

public class SaleProcessJudge {
	private static boolean flag1 = false;
	private static boolean flag2 = false;
	private static boolean flag3 = false;
	private static int flag4 = 0;
	
	private static void init() {
		flag1 = false;
		flag2 = false;
		flag3 = false;
		flag4 = 0;
	}
	
	private static void JudgeAll(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String type, String jine) {
		init();
		
		Float jineFloat = 0f;
		String productid = "";
		boolean enough = true;
		int amount = 0;
		Float je = 0f;
		HashSet<String> hashSet = new HashSet<String>();
		 
		//得到客户的信用账期
		int detailzq = Integer.parseInt(customMapList.get(0).get("PERIOD").toString());
		je = Float.parseFloat(jine);
		
		//得到明细表中金额总和
		for(int i=0;i<detailMapList.size();i++) {
			jineFloat += Float.parseFloat(detailMapList.get(i).get("MONEY").toString());
			productid = detailMapList.get(i).get("PRODUCT_ID").toString();
			amount = Integer.parseInt(detailMapList.get(i).get("AMOUNT").toString());
			if(!hashSet.contains(productid)) {
				hashSet.add(productid);
			}
		}
		
		//拼SQL语句，查出所有明细表中对应商品ID的商品信息
		Object[] strs = hashSet.toArray();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("select * from PRODUCT_INFO where product_id='");
		for(int i=0; i<strs.length; i++)
		{
			if(i==0) {
				stringBuffer.append(strs[i].toString());
			}else {
				stringBuffer.append("' or product_id = '"+strs[i].toString());
			}
		}
		
		stringBuffer.append("'");
		
		SqlCommand sqlCommand=new SqlCommand(Context.getDbConnection());
		List<Map<String, Object>> productMapList= sqlCommand.queryForList(stringBuffer.toString());
		
		List<String> quehuoproductlist = new ArrayList<String>();
		
		//查看库存是否足够
		for(Map<String, Object> detailmap : detailMapList) {
			for (Map<String, Object> productmap : productMapList) {
				String detailid = detailmap.get("PRODUCT_ID").toString();
				String proid = productmap.get("PRODUCT_ID").toString();
				int shuliang = Integer.parseInt(detailmap.get("AMOUNT").toString());
				int kucun = Integer.parseInt(productmap.get("AMOUNT").toString());
				if(detailid.equals(proid) && kucun<shuliang) {
					enough = false;
					quehuoproductlist.add(proid);
				}
			}
		}
		
		if(type.equals("zizeng") && enough==false) {
			StringBuffer stringBuffer1 = new StringBuffer();
			stringBuffer1.append("update product_info set amount = amount + 10 where product_id = '");
			for(int i=0; i<quehuoproductlist.size(); i++)
			{
				if(i==0) {
					stringBuffer1.append(quehuoproductlist.get(i));
				}else {
					stringBuffer1.append("' or product_id = '"+quehuoproductlist.get(i));
				}
			}
			
			stringBuffer1.append("'");
			
			SqlCommand sqlCommand1=new SqlCommand(Context.getDbConnection());
			sqlCommand1.execute(stringBuffer1.toString());
		}
		
		if(je < Float.parseFloat(customedu) && detailzq >= Integer.parseInt(zhangqi) && enough == false) {
			flag1 = true;
		}
		
		if((je > Float.parseFloat(customedu) || detailzq < Integer.parseInt(zhangqi))){
			flag2 = true;
		}
		
		if((je > Float.parseFloat(customedu) || detailzq < Integer.parseInt(zhangqi)) && enough == false) {
			flag3 = true;
		}
		if(enough == false) {
			flag4 = 1;
		}
		
	}
	
	public static boolean NotEnough(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String jine) {
		JudgeAll(detailMapList, customMapList, customedu, zhangqi, "", jine);
		return flag1;
	}
	
	public static boolean OverZqOrEd(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String jine) {
		JudgeAll(detailMapList, customMapList, customedu, zhangqi, "", jine);
		return flag2;
	}
	
	public static boolean OverZqOrEdAndNotEnough(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String jine) {
		JudgeAll(detailMapList, customMapList, customedu, zhangqi, "", jine);
		return flag3;
	}
	
	public static boolean ThroughAll(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String jine) {
		JudgeAll(detailMapList, customMapList, customedu, zhangqi, "", jine);
		if(flag1 == false && flag2 == false && flag3 ==false) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void AddProduct(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String jine) {
		JudgeAll(detailMapList, customMapList, customedu, zhangqi, "zizeng", jine);
	}
	
	public static int IsEnough(List<Map<String, Object>> detailMapList, List<Map<String, Object>> customMapList, String customedu, String zhangqi, String jine) {
		JudgeAll(detailMapList, customMapList, customedu, zhangqi, "", jine);
		return flag4;
	}
}
