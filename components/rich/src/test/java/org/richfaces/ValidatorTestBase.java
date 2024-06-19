package org.richfaces;

import jakarta.el.ValueExpression;

import org.richfaces.test.faces.mock.Environment;
import org.richfaces.test.faces.mock.Environment.Feature;
import org.richfaces.test.faces.mock.Mock;
import org.richfaces.test.faces.mock.MockController;
import org.richfaces.test.faces.mock.MockFacesEnvironment;
import org.richfaces.test.faces.mock.Stub;
import org.junit.After;
import org.richfaces.application.ServicesFactory;

public class ValidatorTestBase {
    @Mock()
    @Environment({ Feature.APPLICATION, Feature.RENDER_KIT, Feature.EL_CONTEXT })
    protected MockFacesEnvironment environment;
    protected MockController controller;
    @Mock
    protected ValueExpression expression;
    @Stub
    protected ServicesFactory factory;

    public ValidatorTestBase() {
        super();
    }

    @After
    public void tearDown() throws Exception {
        controller.release();
    }
}