package org.richfaces.renderkit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named("dataScrollerBean")
public class DataScrollerBean implements Serializable {
    private static final long serialVersionUID = -7612187202173138967L;
    
    private List<String> content;
    private int pageNumber = 1;

    public DataScrollerBean() {
        content = new ArrayList<String>();
        content.add("1 page content");
        content.add("2 page content");
        content.add("3 page content");
        content.add("4 page content");
        content.add("5 page content");
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
