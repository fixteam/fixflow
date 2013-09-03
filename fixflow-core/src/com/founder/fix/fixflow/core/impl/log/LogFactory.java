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
