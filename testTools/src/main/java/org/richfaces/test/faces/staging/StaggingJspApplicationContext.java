/**
 * 
 */
package org.richfaces.test.faces.staging;

import jakarta.el.ELContextListener;
import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.jsp.JspApplicationContext;

import org.richfaces.test.faces.TestException;


/**
 * @author asmirnov
 *
 */
public class StaggingJspApplicationContext implements JspApplicationContext {
	
	public static final String FACES_EXPRESSION_FACTORY = "com.sun.faces.expressionFactory";
    public static final String SUN_EXPRESSION_FACTORY="com.sun.el.ExpressionFactoryImpl";
	
	private ExpressionFactory expressionFactory ;
	private final ServletContext servletContext;
	

	public StaggingJspApplicationContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		String elFactoryClass = servletContext.getInitParameter(FACES_EXPRESSION_FACTORY);
		if(null == elFactoryClass){
			elFactoryClass = servletContext.getInitParameter("com.sun.el.ExpressionFactoryImpl");
		}
        try {
		if(null == elFactoryClass){
		    expressionFactory = instantiate(SUN_EXPRESSION_FACTORY);
		} else {
			expressionFactory = instantiate(elFactoryClass);
		}
		} catch (Exception e) {
			throw new TestException("Couldn't instantiate EL expression factory",e);
		}
	}

    private ExpressionFactory instantiate(String elFactoryClass) throws InstantiationException, IllegalAccessException,
        ClassNotFoundException {
        return Class.forName(elFactoryClass).asSubclass(ExpressionFactory.class).newInstance();
    }

	/* (non-Javadoc)
	 * @see jakarta.servlet.jsp.JspApplicationContext#addELContextListener(jakarta.el.ELContextListener)
	 */
	public void addELContextListener(ELContextListener listener) {

	}

	/* (non-Javadoc)
	 * @see jakarta.servlet.jsp.JspApplicationContext#addELResolver(jakarta.el.ELResolver)
	 */
	public void addELResolver(ELResolver resolver) {
	}

	/* (non-Javadoc)
	 * @see jakarta.servlet.jsp.JspApplicationContext#getExpressionFactory()
	 */
	public ExpressionFactory getExpressionFactory() {
		return expressionFactory;
	}

}
