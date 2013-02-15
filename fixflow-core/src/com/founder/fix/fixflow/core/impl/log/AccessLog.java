package com.founder.fix.fixflow.core.impl.log;

import org.apache.log4j.Level;



/**
 * [类名]<br>
 * AccessLog<br>
 * <br>
 * [功能概要]<br>
 * 客户的访问记录<br>
 * <br>
 * [变更履历]<br>
 * 2009/7/1 ver1.00 新建 weifeng<br>
 * <br>
 * 
 * @author FOUNDER CORPORATION
 */
public class AccessLog {

    /**
     * ロギングラッパーのクラス名
     */
    private static final String FQCN = AccessLog.class.getName();

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
    protected AccessLog(Class cls) {
        logger = org.apache.log4j.Logger.getLogger("access." + cls.getName());
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
