package org.richfaces.demo.togglepanel;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;

@SessionScoped
public class DynamicPanelBean implements Serializable {

    private static final long serialVersionUID = 7666337275711147848L;
    
    private String activeTab;

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }
}
