package org.richfaces.demo.tree;

import java.net.URL;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.FacesException;
import jakarta.inject.Named;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Named("cdsParser")
@ApplicationScoped
public class CDParser {
    private List<CDXmlDescriptor> cdsList;

    @XmlRootElement(name = "CATALOG")
    private static final class CDsHolder {
        private List<CDXmlDescriptor> cds;

        @XmlElement(name = "CD")
        public List<CDXmlDescriptor> getCds() {
            return cds;
        }

        @SuppressWarnings("unused")
        public void setCds(List<CDXmlDescriptor> cds) {
            this.cds = cds;
        }
    }

    public synchronized List<CDXmlDescriptor> getCdsList() {
        if (cdsList == null) {
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            URL resource = ccl.getResource("org/richfaces/demo/tree/CDCatalog.xml");
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(CDsHolder.class);
                CDsHolder cdsHolder = (CDsHolder) context.createUnmarshaller().unmarshal(resource);
                cdsList = cdsHolder.getCds();
            } catch (JAXBException e) {
                throw new FacesException(e.getMessage(), e);
            }
        }

        return cdsList;
    }
}
