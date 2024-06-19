package org.richfaces.cdk.templatecompiler.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlMixed;

public interface LeafModelElement extends ModelElement {
    /*
     * (non-Javadoc)
     *
     * @see org.richfaces.cdk.templatecompiler.model.ModelElement#getChildren()
     */
    @XmlAnyElement(lax = true, value = ElementsHandler.class)
    @XmlMixed
    List<Object> getChildren();
}