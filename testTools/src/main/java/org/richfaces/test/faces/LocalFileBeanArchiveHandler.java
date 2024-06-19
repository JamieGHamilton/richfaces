package org.richfaces.test.faces;

import org.jboss.weld.environment.deployment.discovery.FileSystemBeanArchiveHandler;
import org.jboss.weld.environment.logging.CommonLogger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.Priority;

import org.jboss.weld.environment.deployment.discovery.BeanArchiveBuilder;

@Priority( 100 )
public class LocalFileBeanArchiveHandler extends FileSystemBeanArchiveHandler 
{
    private List< String > TEST_PATHS = Arrays.asList("/test-classes", "/classes");
    
    @Override
    public BeanArchiveBuilder handle(String path) {
        BeanArchiveBuilder builder = new BeanArchiveBuilder();
        TEST_PATHS.forEach( p -> handlePath( path + p, builder ) );
        return builder;
    }
    
    private void handlePath(String path, BeanArchiveBuilder builder) {
        File file = new File(path);
        if (!file.canRead()) {
            return;
        }        

        try {
            if (file.isDirectory()) {
                handleDirectory(new DirectoryEntry().setFile(file), builder);
            } else {
                handleFile(file, builder);
            }
        } catch (Exception e) {
            CommonLogger.LOG.cannotHandleFilePath(file, path, e);
        }
    }

    private void handleDirectory(DirectoryEntry entry, BeanArchiveBuilder builder) throws IOException {
        File[] files = entry.getFile().listFiles();
        String parentPath = entry.getName();
        for (File child : files) {
            if (entry.getName() != null) {
                entry.setPath(entry.getName() + "/" + child.getName());
            } else {
                entry.setPath(child.getName());
            }
            entry.setFile(child);
            if (child.isDirectory()) {
                handleDirectory(entry, builder);
            } else {
                add(entry, builder);
            }
            entry.setPath(parentPath);
        }
    }
    
    private static class DirectoryEntry implements Entry {

        private String path;

        private File file;

        @Override
        public String getName() {
            return path;
        }

        @Override
        public URL getUrl() throws MalformedURLException {
            return file.toURI().toURL();
        }

        public DirectoryEntry setPath(String path) {
            this.path = path;
            return this;
        }

        public File getFile() {
            return file;
        }

        public DirectoryEntry setFile(File dir) {
            this.file = dir;
            return this;
        }

    }
}
