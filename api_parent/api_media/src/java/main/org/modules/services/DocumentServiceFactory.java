package org.modules.services;

import com.api.config.old.ProviderConfig;
import com.api.messaging.MessagingResourceFactory;

/**
 * Creates common objects related to web services
 * 
 * @author RTerrell
 * 
 */
public class DocumentServiceFactory extends MessagingResourceFactory {

    /**
     * Creates an instance of ProviderConfig containing information about the
     * media inbound directory
     * 
     * @param host
     *            the name of the host computer to find the inbound directory
     *            containing the media files to be processed.
     * @return An instance of {@link ProviderConfig}
     */
    public static ProviderConfig getInboundFileServiceConfigInstance(String host) {
        String server = host;
        ProviderConfig config = MessagingResourceFactory.getConfigInstance();
        config.setHost(server);
        return config;
    }

}
