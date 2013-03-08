package com.founder.fix.fixflow.designer.preferences;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.founder.fix.fixflow.designer.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		/*
		 * store.setDefault(PreferenceConstants.P_BOOLEAN, true);
		 * store.setDefault(PreferenceConstants.P_CHOICE, "choice2");
		 */
		store.setDefault(PreferenceConstants.DBDRIVERNAME, "oracle.jdbc.driver.OracleDriver");// //com.mysql.jdbc.Driver
		store.setDefault(PreferenceConstants.DBURL, "jdbc:oracle:thin:@172.29.128.91:1521:orcl");// jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useUnicode=true
		store.setDefault(PreferenceConstants.DBUSER, "idbase");// root
		store.setDefault(PreferenceConstants.DBPASSWORD, "idbase");// jiangnan
		
		
		
		//store.setDefault(PreferenceConstants.DBDRIVERNAME, "com.mysql.jdbc.Driver");// //com.mysql.jdbc.Driver
		//store.setDefault(PreferenceConstants.DBURL, "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useUnicode=true");// jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useUnicode=true
		//store.setDefault(PreferenceConstants.DBUSER, "root");// root
		//store.setDefault(PreferenceConstants.DBPASSWORD, "jiangnan");// jiangnan
		
		store.setDefault(PreferenceConstants.CONNECTORPATH, "/connector");
		
	}

}
