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
