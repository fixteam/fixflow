/**
 * Copyright 1995-2014 Wisedu Co.,Ltd.
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
 * @author ych
 */
package com.founder.fix.fixflow.core.internationalization;

/**
 * 异常信息的国际化key
 * @author ych
 *
 */
public interface ExceptionCode {
	public static String EXCEPTION_DEFAULT = "exception_default";
	
	/**
	 * 文件加载类异常
	 */
	public static String CLASSLOAD_EXCEPTION_DEFAULT = "classloadException_default";
	public static String CLASSLOAD_EXCEPTION_FILENOTFOUND = "classloadException_filenotfound";
	public static String CLASSLOAD_EXCEPTION_ENCODING = "classloadException_encoding";
	public static String CLASSLOAD_EXCEPTION = "classloadException";
	public static String CLASSLOAD_EXCEPTION_DCUMENT = "classloadException_document";
	public static String JOB_EXCEPTION_DEFAULT = "jobException_default";
	
	
	/**
	 * 归档异常
	 */
	/* 表达式执行异常 */
	
	public static String EXCEPTION_ARCHIVE = "archive";
	public static String EXCEPTION_ARCHIVE_PARAMS = "archive_params";
	
	/**
	 * 跳过策略表达式异常编码
	 */
	public static String EXPRESSION_EXCEPTION_SKIPSTRATEGY = "expressionException_skipStrategy";
	
	/**
	 * 输出结果集表达式集合验证
	 */
	public static String EXPRESSION_EXCEPTION_LOOPDATAOUTPUTCOLLECTION_COLLECTIONCHECK = "expressionException_loopDataOutputCollection_collectionCheck";
	
	/**
	 * 多实例输入数据集异常编码
	 */
	public static String EXPRESSION_EXCEPTION_LOOPDATAINPUTCOLLECTIONEXPRESSION = "expressionException_loopDataInputCollectionExpression";
	
	/**
	 * 多实例输入数据集集合数量为0
	 */
	public static String EXPRESSION_EXCEPTION_LOOPDATAINPUTCOLLECTIONEMPTY = "expressionException_loopDataInputCollectionEmpty";
	
	/**
	 * 多实例输入数据集集合循环插入输入项目
	 */
	public static String EXPRESSIONEXCEPTION_COLLECTIONININPUTDATAITEM = "expressionException_collectionInInputDataItem";
	
	
	/**
	 * 完成条件表达式为空
	 */
	public static String EXPRESSIONEXCEPTION_CONDITIONEXPRESSIONEMPTY = "expressionException_conditionExpressionEmpty";
	
	/**
	 * 完成条件表达式执行出错
	 */
	public static String EXPRESSIONEXCEPTION_CONDITIONEXPRESSIONERROR = "expressionException_conditionExpressionError";
	
	
	/**
	 * 节点离开的时候清理quartz出错
	 */
	public static String QUARZTEXCEPTION_NODELEAVECLEANQUARTZ = "quarztException_nodeLeaveCleanQuartz";
	
	
	/**
	 * 节点后面没有配置线条
	 */
	public static String SEQUENCEFLOWEXCEPTION_NODENOSEQUENCEFLOW = "sequenceFlowException_nodeNoSequenceFlow";
	

	/**
	 * 节点后面没有验证通过的线条
	 */
	public static String SEQUENCEFLOWEXCEPTION_NODENOVERIFIEDSEQUENCEFLOW = "sequenceFlowException_nodeNoVerifiedSequenceFlow";
	
	
	
}
