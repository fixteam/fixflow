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
package com.founder.fix.fixflow.core.impl.identity;

import java.util.Comparator;

/**
 * 组级别比较
 * @author kenshin
 *
 */
public class ComparatorGroupTo implements Comparator<GroupTo> {

	public int compare(GroupTo o1, GroupTo o2) {

		int x=o1.getLevelNum();
		int y=o2.getLevelNum();
		
		if(x>y){
			return 1;
		}
		if(x==y){
			return 0;
		}
		
		if(x<y){
			return -1;
		}
		
		return 0;
	}



}
