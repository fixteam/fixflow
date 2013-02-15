/**
 * Copyright c FOUNDER CORPORATION 2006 All Rights Reserved.
 * DebugLog.java
 */
package com.founder.fix.fixflow.core.impl.log;

import org.apache.log4j.Level;

/**
 * [クラス名]<br>
 * タイマーログ出力クラス<br>
 * <br>
 * [機能概要]<br>
 * タイマーログ機能を提供するクラスを提供します。<br>
 * <br>
 * [変更履歴]<br>
 * 2007/10/22 ver1.00 新規作成 wangsh<br>
 * 
 * @author wangsh
 * @version 1.10
 */
public class DebugLog {

    /**
     * ロギングラッパーのクラス名
     */
    private static final String FQCN = DebugLog.class.getName();

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
    protected DebugLog(Class cls) {
        logger = org.apache.log4j.Logger.getLogger("debug." + cls.getName());
        // FQCN = this.getClass().getName();
    }

    /**
     * デバックログを出力する。
     * 
     * @param msg
     *            出力するログメッセージ
     */
    public void debug(Object msg) {
        logger.log(FQCN, Level.DEBUG, msg, null);
    }

    /**
     * 情報ログを出力する。
     * 
     * @param msg
     *            出力するログメッセージ
     */
    public void info(Object msg) {
        logger.log(FQCN, Level.INFO, msg, null);
    }

    /**
     * 警告ログを出力する。
     * 
     * @param msg
     *            出力するログメッセージ
     */
    public void warn(Object msg) {
        logger.log(FQCN, Level.WARN, msg, null);
    }

    /**
     * 警告ログを出力する。
     * 
     * @param cause
     *            出力するエラー
     * @param msg
     *            出力するログメッセージ
     */
    public void warn(Throwable cause, Object msg) {
        logger.log(FQCN, Level.WARN, msg, cause);
    }

    /**
     * エラーログを出力する。
     * 
     * @param msg
     *            出力するログメッセージ
     */
    public void error(Object msg) {
        logger.log(FQCN, Level.ERROR, msg, null);
    }

    /**
     * エラーログを出力する。
     * 
     * @param cause
     *            出力するエラー
     * @param msg
     *            出力するログメッセージ
     */
    public void error(Throwable cause, Object msg) {
        logger.log(FQCN, Level.ERROR, msg, cause);
    }

    /**
     * Check whether this category is enabled for the <code>DEBUG</code>
     * 
     * @return boolean - <code>true</code> if this category is debug enabled, <code>false</code> otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

}
