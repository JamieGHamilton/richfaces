package org.richfaces.demo.togglepanel;

import jakarta.enterprise.context.SessionScoped;

@SessionScoped
public class DynamicPanelBean {

    private String activeTab;

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }
}
