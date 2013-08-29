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
package com.founder.fix.fixflow.core.impl.log;

import org.apache.log4j.Level;


/**
 * [クラス名]<br>
 * 認証ログ出力クラス<br>
 * <br>
 * [機能概要]<br> 
 * 認証ログ機能を提供するクラスを提供します。<br>
 * <br>
 * [変更履歴]<br>
 * 2007/02/28 ver1.00 新規作成 weifeng<br>
 * 2007/04/13 ver1.10 LogUtilクラスに結びつけをキャンセルします<br>
 * <br>
 * 
 * @author weifeng
 * @version 1.10
 */
public class AuthLog {

    /**
     * ロギングラッパーのクラス名
     */
    private static final String FQCN = AuthLog.class.getName();

    /**
     * ロギング本体
     */
    private org.apache.log4j.Logger logger;

    /**
     * コンストラクタ
     * 
     * @param cls
     *            ログを出力するクラス
     */
    @SuppressWarnings("rawtypes")
    protected AuthLog(Class cls) {
        logger = org.apache.log4j.Logger.getLogger("auth." + cls.getName());
    }

    /**
     * ログを出力する。
     * 
     * @param msgID
     *            出力するログメッセージID
     */
    public void info(String msgID) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID);
        logger.log(FQCN, Level.INFO, outputText, null);
    }

    /**
     * ログを出力する。
     * 
     * @param msgID
     *            出力するログメッセージID
     * @param args
     *            消息参数
     */
    public void info(String msgID, String... args) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID, args);
        logger.log(FQCN, Level.INFO, outputText, null);
    }

}
