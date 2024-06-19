/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.myfaces.context.servlet.FacesContextImpl;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.richfaces.application.DefaultModule;
import org.richfaces.application.Module;
import org.richfaces.application.ServiceException;
import org.richfaces.application.ServiceLoader;
import org.richfaces.application.ServiceTracker;
import org.richfaces.application.ServicesFactoryImpl;
import org.richfaces.test.faces.FacesEnvironment;
import org.richfaces.test.faces.FacesEnvironment.FacesRequest;

import com.google.common.collect.Maps;

import jakarta.faces.FacesException;
import jakarta.faces.context.FacesContext;

/**
 * @author Nick Belaevski
 *
 */
public class FacesRequestSetupRule implements MethodRule {
    private FacesEnvironment environment;
    private FacesRequest facesRequest;

    public Statement apply(final Statement base, final FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                starting(method);
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    throw t;
                } finally {
                    finished(method);
                }
            }
        };
    }

    private Map<String, String> getInitParameters(FrameworkMethod method) {
        Map<String, String> result = Maps.newHashMap();

        ContextInitParameter parameter = method.getAnnotation(ContextInitParameter.class);
        if (parameter != null) {
            result.put(parameter.name(), parameter.value());
        }

        ContextInitParameters parameters = method.getAnnotation(ContextInitParameters.class);
        if (parameters != null) {
            for (ContextInitParameter param : parameters.value()) {
                if (result.put(param.name(), param.value()) != null) {
                    throw new IllegalArgumentException("Parameter " + param + " specified twice");
                }
            }
        }

        return result;
    }

    protected void starting(FrameworkMethod method) throws Exception {
        createFacesEnvironment();
        setUpFacesEnvironment(method);

        createFacesRequest();
        setUpFacesRequest();
    }

    protected void finished(FrameworkMethod method) throws Exception {
        tearDownFacesRequest();
        tearDownFacesEnvironment();
    }

    protected void setUpFacesRequest() {
        facesRequest.start();
    }

    protected void createFacesRequest() throws Exception {
        facesRequest = environment.createFacesRequest();
    }

    protected void setUpFacesEnvironment(FrameworkMethod method) {
        for (Entry<String, String> paramEntry : getInitParameters(method).entrySet()) {
            environment.getServer().addInitParameter(paramEntry.getKey(), paramEntry.getValue());
        }

        environment.start();
        initRichFaces();
    }
    
    private void initRichFaces() {
        FacesContext facesContext = new FacesContextImpl(environment.getServer().getContext(), null, null);  
        try {
            ServicesFactoryImpl injector = new ServicesFactoryImpl();
            ServiceTracker.setFactory(injector);
            ArrayList<Module> modules = new ArrayList<Module>();
            modules.add(new DefaultModule());
            try {
                modules.addAll(ServiceLoader.loadServices(Module.class));
                injector.init(modules);
            } catch (ServiceException e) {
                throw new FacesException(e);
            }
        }
        finally {
            facesContext.release();
        }
    }

    protected void createFacesEnvironment() {
        environment = FacesEnvironment.createEnvironment();
    }

    protected void tearDownFacesEnvironment() {
        if (environment != null) {
            environment.release();
            environment = null;
        }
    }

    protected void tearDownFacesRequest() {
        if (facesRequest != null) {
            facesRequest.release();
            facesRequest = null;
        }
    }

    public FacesRequest getFacesRequest() {
        return facesRequest;
    }
}
