package org.richfaces.test.faces.stub.faces;

import java.io.IOException;

import jakarta.faces.application.StateManager;
import jakarta.faces.context.FacesContext;

public class StubStateManager extends StateManager
{
    @Override
    public void writeState(FacesContext context, Object state) throws IOException {
    }

    @Override
    public boolean isSavingStateInClient(FacesContext context) {
        return false;
    }

    @Override
    public String getViewState(FacesContext context) {
        return null;
    }    
}
