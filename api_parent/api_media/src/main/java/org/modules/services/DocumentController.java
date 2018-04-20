package org.modules.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.modules.document.DocumentContentApiFactory;

/**
 * Servlet responsible for starting and managaing any docuement media related services.
 */
public class DocumentController extends HttpServlet {

    private static final long serialVersionUID = 6183498279595854520L;

//    private DocumentInboundDirectoryListener listener = null;

    /**
     * Creates and starts an instance of the DocumentInboundDirectoryListener
     * runnable interface as the File Handler thread.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        
        // Start Media inbound directory file listener
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        f.createMediaContentApi().startMediaFileListener();
        
//        // Initialize logging. Each application should have its own logging
//        // configuration
//        this.listener = new DocumentInboundDirectoryListener();
//        Thread t = new Thread(this.listener);
//        t.start();
    }

    /**
     * Terminates the File Handler thread.
     */
    @Override
    public void destroy() {
        super.destroy();
        
        // Stop Media inbound directory file listener
        DocumentContentApiFactory f = new DocumentContentApiFactory();
        f.createMediaContentApi().stopMediaFileListener();
        
//        this.listener.stop();
    }

}
