package com.founder.fix.fixflow.core.impl.runtime;

import java.util.HashMap;
import java.util.Map;


import com.founder.fix.fixflow.core.query.QueryProperty;

public class TokenQueryProperty implements QueryProperty {

	 private static final Map<String, TokenQueryProperty> properties = new HashMap<String, TokenQueryProperty>();

	  public static final TokenQueryProperty PROCESSINSTANCE_ID = new TokenQueryProperty("PROCESSINSTANCE_ID");
	  public static final TokenQueryProperty TOKEN_ID = new TokenQueryProperty("TOKEN_ID");

	  private String name;

	  public TokenQueryProperty(String name) {
	    this.name = name;
	    properties.put(name, this);
	  }

	  public String getName() {
	    return name;
	  }
	  
	  public static TokenQueryProperty findByName(String propertyName) {
	    return properties.get(propertyName);
	  }


}
