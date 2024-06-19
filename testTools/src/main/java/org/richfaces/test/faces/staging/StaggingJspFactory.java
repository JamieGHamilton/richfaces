/**
 * 
 */
package org.richfaces.test.faces.staging;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.jsp.JspApplicationContext;
import jakarta.servlet.jsp.JspEngineInfo;
import jakarta.servlet.jsp.JspFactory;
import jakarta.servlet.jsp.PageContext;

/**
 * Stagging implementation of the {@link JspFactory} to simulate JSP processing in the stagging server.
 * TODO - implement JSP functionality.
 * @author asmirnov
 * 
 */
public class StaggingJspFactory extends JspFactory {

	private static final JspEngineInfo engineInfo = new JspEngineInfo() {

		@Override
		public String getSpecificationVersion() {
			return "2.1";
		}

	};

	private final JspApplicationContext context;

	public StaggingJspFactory(ServletContext servletContext) {
		this.context = new StaggingJspApplicationContext(servletContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jakarta.servlet.jsp.JspFactory#getEngineInfo()
	 */
	@Override
	public JspEngineInfo getEngineInfo() {
		return engineInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejakarta.servlet.jsp.JspFactory#getJspApplicationContext(jakarta.servlet.
	 * ServletContext)
	 */
	@Override
	public JspApplicationContext getJspApplicationContext(ServletContext context) {
		return this.context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jakarta.servlet.jsp.JspFactory#getPageContext(jakarta.servlet.Servlet,
	 * jakarta.servlet.ServletRequest, jakarta.servlet.ServletResponse,
	 * java.lang.String, boolean, int, boolean)
	 */
	@Override
	public PageContext getPageContext(final Servlet servlet, final ServletRequest request,
			final ServletResponse response, String errorPageURL,
			final boolean needsSession, int buffer, boolean autoflush) {
		PageContextExtension pageContextExtension = new PageContextExtension();
		try {
			pageContextExtension.initialize(servlet, request, response, errorPageURL, needsSession, buffer, autoflush);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return pageContextExtension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jakarta.servlet.jsp.JspFactory#releasePageContext(jakarta.servlet.jsp.PageContext
	 * )
	 */
	@Override
	public void releasePageContext(PageContext pc) {
		pc.release();

	}

}
