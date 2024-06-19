package org.richfaces.component;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named("dropDownMenuBean")
public class DropDownMenuBean implements Serializable {
    private static final long serialVersionUID = -6179276171758128782L;
    private static String _current = "none";

    public void doAction() {
        _current = "action";
    }

    public static String getCurrent() {
        return _current;
    }

    public static void setCurrent(String current) {
        _current = current;
    }
}