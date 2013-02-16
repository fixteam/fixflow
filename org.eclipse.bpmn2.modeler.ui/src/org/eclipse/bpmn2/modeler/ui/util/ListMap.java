/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A little bit of LISP.
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 19, 2006
 *
 */
public class ListMap {
	
	/**
	 *
	 */	
	static final public Object IGNORE = new Object();
	
	/**
	 * Interface visitor. The object returned from visit need not be the same
	 * object as passed to visit. If IGNORE is returned (above), then
	 * the resulting value will not be added to the resulting list. 
	 */
	
	static public interface Visitor {
		/**
		 * @param obj the object to visit. 
		 * @return some other object, the object itself, or IGNORE
		 */
		public Object visit ( Object obj );		
	}
	 		
	
	/**
	 * Map the visitor "visitor" on the list "list" and return the result as a list.
	 * 
	 * @param list the list of objects
	 * @param visitor the visitor
	 * @return the resulting (new) list 
	 */
	
	static public Object Map (List<?> list, Visitor visitor) {
		return Map(list,visitor,null);
	}


	
	/**
	 *  Map the visitor "visitor" on the list "list" and return the result as a list. If the list argument
	 *  is passed (ret) the result is returned as an array of the results, otherwise it is a list.
	 * 
	 * @param list the list of objects
	 * @param visitor the visitor
	 * @param ret the array class to convert the results into.
	 * @return the resulting (new) list  
	 */
	static public Object Map ( List<?> list, Visitor visitor , Object[] ret ) {
		List<Object> output = new ArrayList<Object>(list.size());
		for(Object n : list) {
			Object result = visitor.visit(n);
			if (result != IGNORE) {
				output.add( result );
			}
		}			
		if (ret == null) {
			return output;
		}
		
		return output.toArray(ret);		
	}
	
	
	/**
	 * Map the visitor "visitor" on the list "list" and return the result as a list.
	 * 
	 * @param list the list of objects
	 * @param visitor the visitor
	 * @return the resulting (new) list
	 *  
	 */
	static public Object Map ( Object[] list, Visitor visitor ) {
		return Map(list,visitor,null);
	}
	
	
	/**
	 *  Map the visitor "visitor" on the list "list" and return the result as a list. If the list argument
	 *  is passed (ret) the result is returned as an array of the results, otherwise it is a list.
	 * 
	 * @param list the list of objects
	 * @param visitor the visitor
	 * @param ret the array class to convert the results into.
	 * @return the resulting (new) list  
	 */
	
	static public Object Map (Object[] list, Visitor visitor, Object[] ret) {
		List<Object> l2 = new ArrayList<Object>(list.length);
		for( Object n : list) {
			l2.add(n);
		}
		return Map ( l2, visitor , ret);
	}


	/**
	 * @param list the list of objects.
	 * @param visitor the visitor
	 * @return the object found or null
	 */
	static public final Object Find (List<?> list, Visitor visitor) {
		for(Object n : list) {				
			Object result = visitor.visit ( n );
			if (result != IGNORE) {
				return result;
			}
		}
		return null;					
	}
	
	
	
	
	
	/**
	 * Find the parameter using the comparator in the list.
	 * 
	 * @param list
	 * @param key
	 * @param comparator
	 * @return the parameter found or null
	 */
	
	public static Object findElement (List<?> list, Object key, Comparator<Object> comparator) {
		for (Object n : list) {
			if (comparator.compare(n, key) == 0) {
				return n;
			}						
		}
		return null;			
	}
	
	
}
