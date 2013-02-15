/**
 * Copyright c FOUNDER CORPORATION 2006 All Rights Reserved.
 * LogFactory.java
 */
package com.founder.fix.fixflow.core.impl.log;

/**
 * [クラス名]<br>
 * ログ出力のFactory機能を提供します。<br>
 * <br>
 * [機能概要]<br>
 * ログ出力のFactoryクラス。<br>
 * <br>
 * [変更履歴]<br>
 * 2007/02/27 ver1.00 新規作成 weifeng<br>
 * <br>
 * 
 * @author weifeng
 * @version 1.10
 */
public final class LogFactory {

    private LogFactory() {

    }

    /**
     * 運用監視ログを得ます
     * 
     * @param cls
     *            ログを出力するクラス
     * @return OnlineLog 運用監視ログ出力クラス
     */
    @SuppressWarnings("rawtypes")
    public static OnlineLog getOnlineLog(Class cls) {
        return new OnlineLog(cls);
    }

    /**
     * 認証ログを得ます
     * 
     * @param cls
     *            ログを出力するクラス
     * @return AuthLog 認証ログ出力クラス
     */
    @SuppressWarnings("rawtypes")
    public static AuthLog getAuthLog(Class cls) {
        return new AuthLog(cls);
    }

    /**
     * 操作ログを得ます
     * 
     * @param cls
     *            ログを出力するクラス
     * @return OperationLog 操作ログ出力クラス
     */
    @SuppressWarnings("rawtypes")
    public static OperationLog getOperationLog(Class cls) {
        return new OperationLog(cls);
    }

    /**
     * Accessログを得ます
     * 
     * @param cls
     *            ログを出力するクラス
     * @return AccessLogログ出力クラス
     */
    @SuppressWarnings("rawtypes")
    public static AccessLog getAccessLog(Class cls) {
        return new AccessLog(cls);
    }

    /**
     * 解析ログを得ます
     * 
     * @param cls
     *            ログを出力するクラス
     * @return DebugLog 解析ログ出力クラス
     */
    @SuppressWarnings("rawtypes")
    public static DebugLog getDebugLog(Class cls) {
        return new DebugLog(cls);
    }

    /**
     * Timerログを得ます
     * 
     * @param cls
     *            ログを出力するクラス
     * @return DebugLog Timerログ出力クラス
     */
    @SuppressWarnings("rawtypes")
    public static TimerLog getTimerLog(Class cls) {
        return new TimerLog(cls);
    }
}
