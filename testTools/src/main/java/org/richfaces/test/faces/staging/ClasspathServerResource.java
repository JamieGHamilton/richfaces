/**
 * 
 */
package org.richfaces.test.faces.staging;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.richfaces.test.faces.TestException;


/**
 * This class represents file from classpath in the virtual web application
 * content.
 * 
 * @author asmirnov
 * 
 */
public class ClasspathServerResource extends AbstractServerResource {

	/**
	 * 
	 */
	private String classpath;

	private volatile URL url = null;

	private static final Logger log = ServerLogger.RESOURCE.getLogger();

	/**
	 * @param name
	 * @param classpath
	 */
	public ClasspathServerResource(String classpath) {
		this.classpath = classpath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.richfaces.test.faces.staging.ServerResource#getURL()
	 */
	public URL getURL() {
	    boolean parentPath = this.classpath.equals("..");
	    if ( parentPath )
	    {
	        this.classpath = "";
	    }
		if (url == null) {
			// JDK-5 singleton-like synchronization.
			// @see http://jeremymanson.blogspot.com/2008/05/double-checked-locking.html
			synchronized (this) {
				if(log.isLoggable(Level.FINEST)){
					log.finest("get classpath resource from "+this.classpath);
				}
				ClassLoader classLoader = Thread.currentThread()
						.getContextClassLoader();
				if (null == classLoader) {
					classLoader = this.getClass().getClassLoader();
				}				
				url = classLoader.getResource(classpath);
				if(null == url){
					throw new TestException("Virtual server resource can't be loaded from "+classpath);
				}
				if ( parentPath )
				{
				    try {
                        url = new URL( "file:" + url.getPath().substring( 0, 1 + url.getPath().lastIndexOf('/', url.getPath().length() - 2) ) );
                    } catch (MalformedURLException e) {
                    }
				}
			}
		}

		return url;
	}

}
