package com.founder.fix.fixflow.core.impl;


/**
 * 
 * 
 * @author kenshin
 */
public class Page {
  
  protected int firstResult;
  protected int maxResults;
  
  public Page(int firstResult, int maxResults) {
    this.firstResult = firstResult;
    this.maxResults = maxResults;
  }
  
  public int getFirstResult() {
    return firstResult;
  }

  public int getMaxResults() {
    return maxResults;
  }
}
