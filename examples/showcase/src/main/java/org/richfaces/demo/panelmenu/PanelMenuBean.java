package org.richfaces.demo.panelmenu;

import java.io.Serializable;

import org.richfaces.event.ItemChangeEvent;

import jakarta.faces.view.ViewScoped;

@ViewScoped
public class PanelMenuBean implements Serializable {
    private String current;
    private boolean singleMode;

    public boolean isSingleMode() {
        return singleMode;
    }

    public void setSingleMode(boolean singleMode) {
        this.singleMode = singleMode;
    }

    public String getCurrent() {
        return this.current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public void updateCurrent(ItemChangeEvent event) {
        setCurrent(event.getNewItemName());
    }
}
