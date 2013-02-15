package com.founder.fix.fixflow.core.impl.log;

/**
 * [クラス名]<br>
 * ErrorMessageRepository<br>
 * <br>
 * [機能概要]<br>
 * LOG使用的消息使用<br>
 * <br>
 * [変更履歴]<br>
 * 2007/04/23 新規作成 weifeng<br>
 * 
 * @author weifeng
 * @version 1.00
 */
public class ErrorMessageRepository {

    public static void init() {

    }

    public static String getMessage(String msgID) {
        return "";
    }

    public static String getMessage(String msgID, String... args) {
        return args[0];
    }
}
