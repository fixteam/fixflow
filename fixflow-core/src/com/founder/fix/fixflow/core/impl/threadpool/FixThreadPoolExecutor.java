package com.founder.fix.fixflow.core.impl.threadpool;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


import com.founder.fix.fixflow.core.impl.log.LogFactory;

public class FixThreadPoolExecutor extends ThreadPoolExecutor {
	
	private String threadPoolKey;
	
	private String threadPoolName;
	
	
	
	private static com.founder.fix.fixflow.core.impl.log.DebugLog debugLog = LogFactory.getDebugLog(FixThreadPoolExecutor.class);

	private boolean isPaused;
	private ReentrantLock pauseLock = new ReentrantLock();
	private Condition unpaused = pauseLock.newCondition();
	


	public FixThreadPoolExecutor(String threadPoolKey,String threadPoolName,int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.threadPoolKey=threadPoolKey;
		this.threadPoolName=threadPoolName;

	}

	

	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		pauseLock.lock();
		try {
			while (isPaused)
				unpaused.await();
		} catch (InterruptedException ie) {
			t.interrupt();
		} finally {
			pauseLock.unlock();
		}
		debugLog.debug("线程 ["+threadPoolName +"] 开始执行.");
		debugLog.debug("开始时间 ["+new Date()+"]");
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		debugLog.debug("线程 ["+threadPoolName +"] 执行结束.");
		debugLog.debug("结束时间 ["+new Date()+"]");
	}
	
	/**
	 * 暂停线程池
	 */
	public void pause() {
		pauseLock.lock();
		debugLog.debug("线程 ["+threadPoolName +"] 暂停.");
		debugLog.debug("暂停时间 ["+new Date()+"]");
		try {
			isPaused = true;
		} finally {
			pauseLock.unlock();
		}
	}

	/**
	 * 恢复线程池
	 */
	public void resume() {
		pauseLock.lock();
		debugLog.debug("线程 ["+threadPoolName +"] 恢复.");
		debugLog.debug("恢复时间 ["+new Date()+"]");
		try {
			isPaused = false;
			unpaused.signalAll();
		} finally {
			pauseLock.unlock();
		}
	}
	
	public String getThreadPoolKey() {
		return threadPoolKey;
	}
	public String getThreadPoolName() {
		return threadPoolName;
	}


}
