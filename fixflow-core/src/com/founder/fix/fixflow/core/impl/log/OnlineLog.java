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



public class OnlineLog {

    /**
     * ロギングラッパーのクラス名
     */
    private static final String FQCN = OnlineLog.class.getName();

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
    protected OnlineLog(Class cls) {
        logger = org.apache.log4j.Logger.getLogger("online." + cls.getName());
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

    /**
     * ログを出力する。
     * 
     * @param msgID
     *            出力するログメッセージID
     */
    public void warn(String msgID) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID);
        logger.log(FQCN, Level.WARN, outputText, null);
    }

    /**
     * ログを出力する。
     * 
     * @param msgID
     *            出力するログメッセージID
     * @param args
     *            消息参数
     */
    public void warn(String msgID, String... args) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID, args);
        logger.log(FQCN, Level.WARN, outputText, null);
    }

    /**
     * ログを出力する。
     * 
     * @param msgID
     *            出力するログメッセージID
     */
    public void error(String msgID) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID);
        logger.log(FQCN, Level.ERROR, outputText, null);
    }

    /**
     * ログを出力する。
     * 
     * @param msgID
     *            出力するログメッセージID
     * @param args
     *            消息参数
     */
    public void error(String msgID, String... args) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID, args);
        logger.log(FQCN, Level.ERROR, outputText, null);
    }

    /**
     * ログを出力する。
     * 
     * @param cause
     *            出力するエラー
     * @param msgID
     *            出力するログメッセージID
     */
    public void error(Throwable cause, String msgID) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID);
        logger.log(FQCN, Level.ERROR, outputText, cause);
    }

    /**
     * ログを出力する。
     * 
     * @param cause
     *            出力するエラー
     * @param msgID
     *            出力するログメッセージID
     * @param args
     *            消息参数
     */
    public void error(Throwable cause, String msgID, String... args) {
        String outputText = msgID + "|" + ErrorMessageRepository.getMessage(msgID, args);
        logger.log(FQCN, Level.ERROR, outputText, cause);
    }
}
