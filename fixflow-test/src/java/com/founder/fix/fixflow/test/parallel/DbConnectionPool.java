package com.founder.fix.fixflow.test.parallel;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;  

 
public class DbConnectionPool {  
    private List<Connection> pool;
    private int poolSize = 1;  
    private Connection conn = null;  
    private static DbConnectionPool instance = null;  
    /**  
     *单粒模式私有构造方法，获得本类的对象，通过getIstance方法。  
     */ 
    private DbConnectionPool() {  
       pool = new ArrayList<Connection>();  
       this.createConnection();  
    }  
    /**  
     *得到当前连接池的一个实例  
     */ 
    public static DbConnectionPool getInstance() {  
       if (instance == null) {  
           instance = new DbConnectionPool();  
       }  
       return instance;  
    }  
    /**  
     *得到连接池中的一个连接  
     */ 
    public synchronized Connection getConnection() {  
       if (pool.size() > 0) {  
           Connection conn = pool.get(0);  
           pool.remove(conn);  
           return conn;  
       } else {  
    	   try {
			wait(50000);
			return getConnection();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return null;
		} // 等 5 秒 
       }  
    }  
    /**  
     *创建初始的数据库连接  
     */ 
    private void createConnection() {  
       int temp = ResourceManager.getPoolSize();  
       if(temp > 0){  
           this.poolSize = temp;  
       }  
       for (int i = 0; i < poolSize; i++) {  
           try {  
              Class.forName(ResourceManager.getDriverClass());  
              conn = DriverManager.getConnection(ResourceManager.getUrl(),ResourceManager.getUsername(),ResourceManager.getPassword());  
              pool.add(conn);  
           } catch (ClassNotFoundException e) {  
              e.printStackTrace();  
           } catch (SQLException e) {  
              e.printStackTrace();  
           }  
       }  
    }  
    /**  
     *用完将连接放回到连接池中  
     *  
     *@paramconn  
     */ 
    public synchronized void release(Connection conn) {  
       pool.add(conn);  
    }  
    
    /**  
     *关闭连接池中的所有连接  
     */ 
    public synchronized void closePool() {  
       for (int i = 0; i < pool.size(); i++) {  
           try {  
              conn = ((Connection) pool.get(i));  
              conn.close();  
              pool.remove(i);  
           } catch (SQLException e) {  
              e.printStackTrace();  
           }  
       }  
    }  
} 