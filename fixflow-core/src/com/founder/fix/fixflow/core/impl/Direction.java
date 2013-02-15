package com.founder.fix.fixflow.core.impl;


import java.util.HashMap;
import java.util.Map;


/**
 * @author kenshin
 */
public class Direction {

  private static final Map<String, Direction> directions = new HashMap<String, Direction>();

  public static final Direction ASCENDING = new Direction("asc");
  public static final Direction DESCENDING = new Direction("desc");
  
  private String name;
  
  public Direction(String name) {
    this.name = name;
    directions.put(name, this);
  }

  public String getName() {
    return name;
  }
  
  public static Direction findByName(String directionName) {
    return directions.get(directionName);
  }
}
