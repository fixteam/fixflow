package com.founder.fix.fixflow.core.impl.db;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kenshin
 */
public interface PersistentObject extends Serializable{

	String getId();

	Map<String, Object> getPersistentState();
}
