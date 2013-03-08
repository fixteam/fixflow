package com.founder.fix.fixflow.designer.persistence;

import java.util.Map;

/**
 * @author kenshin
 */
public interface PersistentObject {

	String getId();

	Map<String, Object> getPersistentState();
}
