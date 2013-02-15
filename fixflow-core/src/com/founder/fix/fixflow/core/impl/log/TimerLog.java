package com.founder.fix.fixflow.core.impl.log;

import org.apache.log4j.Level;


/**
 * [クラス名]<br>
 * TimerLog<br>
 * <br>
 * [機能概要]<br>
 * 记录操作花费的时间<br>
 * <br>
 * [変更履歴]<br>
 * 2007/02/28 ver1.00 新規作成 weifeng<br>
 * <br>
 * 
 * @author weifeng
 * @version 1.10
 */
public class TimerLog {

    /**
     * ロギングラッパーのクラス名
     */
    private static final String FQCN = TimerLog.class.getName();

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
    protected TimerLog(Class cls) {
        logger = org.apache.log4j.Logger.getLogger("timer." + cls.getName());
        // FQCN = this.getClass().getName();
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
