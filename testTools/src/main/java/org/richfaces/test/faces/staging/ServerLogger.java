/**
 * 
 */
package org.richfaces.test.faces.staging;

import java.util.logging.Logger;

/**
 * @author asmirnov
 *
 */
public enum ServerLogger {
	
	RESOURCE("resource"),
	CONNECTION("connection"),
	SERVER("server");	
	
	private static final String PREFIX="org.richfaces.test.faces.";
	
	private static final String LOGGING_BUNDLE="org.richfaces.test.faces.LogMessages";
	private final String name;

	/**
	 * @param name
	 */
	private ServerLogger(String name) {
		this.name = PREFIX+name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public Logger getLogger(){
		return Logger.getLogger(name, LOGGING_BUNDLE);
	}

}
