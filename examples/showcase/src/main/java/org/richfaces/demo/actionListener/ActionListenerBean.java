package org.richfaces.demo.actionListener;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;

/**
 * @author Nick Belaevski
 *
 */
@ApplicationScoped
public class ActionListenerBean {
    public static final class ActionListenerImpl implements ActionListener {
        public void processAction(ActionEvent event) throws AbortProcessingException {
            addFacesMessage("Implementation of ActionListener created and called: " + this);
        }
    }

    private static final class BoundActionListener implements ActionListener {
        public void processAction(ActionEvent event) throws AbortProcessingException {
            addFacesMessage("Bound listener called");
        }
    }

    private ActionListener actionListener = new BoundActionListener();

    private static void addFacesMessage(String messageText) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(messageText));
    }

    public void handleActionMethod(ActionEvent event) throws AbortProcessingException {
        addFacesMessage("Method expression listener called");
    }

    public void handleActionMethodComposite(ActionEvent event) throws AbortProcessingException {
        addFacesMessage("Method expression listener called from composite component");
    }

    public ActionListener getActionListener() {
        return actionListener;
    }
}
