package org.modules.services;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * A SOAP web service receiver designed to dispatch MIME document related to
 * SOAP requests.
 * 
 * @author Roy Terrell * @deprecated This class will no longer be needed since
 *         this logic will be included in the Service Router Uncomment code when
 *         ready to commence code rework.
 * 
 */
public class MimeDocSoapService {
    // public class MimeDocSoapService extends AbstractSoapServiceImpl {

    private static Logger logger = Logger.getLogger(MimeDocSoapService.class);

    /**
     * Default constructor
     * 
     */
    public MimeDocSoapService() {
        super();
    }

    // /**
    // * Invokes the process designed to serve the <i>RQ_content_search</i>
    // request.
    // * <p>
    // * This method contains functionality to recognizing the SOAP message
    // attachments that
    // * were processed in the MimeDocHandler class so that the attachment can
    // be captured
    // * during the SOAP message packaging phase of this class' process.
    // *
    // * @param inMsg
    // * the request message.
    // * @return String
    // * the web service's response.
    // * @throws SoapProcessorException
    // */
    // @Override
    // public String invokeSoapHandler(Properties parms) throws
    // SoapProcessorException {
    // MimeDocSoapService.logger.info("Prepare to call Fetch Content Producer");
    // DatabaseTransApi tx = DatabaseTransFactory.create();
    // MimeDocHandler srvc = new MimeDocHandler((DatabaseConnectionBean)
    // tx.getConnector(), this.request);
    // this.responseMsgId = "RS_content_search";
    // srvc.setResponseServiceId(this.responseMsgId);
    // try {
    // String xml = this.executeHandler(srvc, parms, tx);
    // this.outAttachments = srvc.getAttachments();
    // return xml;
    // }
    // catch (MessagingHandlerException e) {
    // throw new SoapProcessorException(e);
    // }
    // }
    //
    // private String executeHandler(MessageHandler handler, Properties parms,
    // DatabaseTransApi tx) throws MessagingHandlerException {
    // try {
    // String result = (String) handler.execute(parms);
    // tx.commitUOW();
    // return result;
    // }
    // catch (Exception e) {
    // tx.rollbackUOW();
    // this.msg = "Database transaction for Contact SOAP Service failed";
    // logger.error(this.msg);
    // throw new MessagingHandlerException(this.msg, e);
    // }
    // finally {
    // handler = null;
    // tx.close();
    // tx = null;
    // }
    // }

}