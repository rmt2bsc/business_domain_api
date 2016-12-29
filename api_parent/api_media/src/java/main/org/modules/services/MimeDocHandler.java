package org.modules.services;

import org.apache.log4j.Logger;

/**
 * Back end web service implementation that serves the request of retrieving a
 * single record from the document table of the MIME database. The incoming and
 * outgoing data is expected to be in the form of XML.
 * 
 * @author Roy Terrell
 * @deprecated This class will need to be renamed so that it will exist at the
 *             level of and be viewed as an API receptor. Basically, this class
 *             will perform 3/4 of what the method, doFetch, does. The
 *             converting of the results to XML will be deferred to the Service
 *             Router.
 * 
 *             Uncomment code when ready to commence code rework.
 * 
 */
public class MimeDocHandler {
    // public class MimeDocHandler extends SoapMessageHandlerImpl {

    private static Logger logger = Logger.getLogger(MimeDocHandler.class);

    // private RQContentSearch reqMessage;

    /**
     * Default constructor
     * 
     */
    protected MimeDocHandler() {
        super();
    }

    // public MimeDocHandler(DatabaseConnectionBean con, Request request) {
    // super(con, request);
    // }
    //
    // /* (non-Javadoc)
    // * @see
    // com.api.messaging.webservice.soap.AbstractSoapService#doExecuteRequest(java.util.Properties)
    // */
    // @Override
    // public String execute(Properties parms) throws MessagingHandlerException
    // {
    // String xml = null;
    // this.reqMessage = (RQContentSearch)
    // parms.get(SoapProductBuilder.PAYLOADINSTANCE);
    // try {
    // xml = this.doFetch(this.reqMessage.getContentId().intValue());
    // return xml;
    // }
    // catch (MimeException e) {
    // throw new MessagingHandlerException(e);
    // }
    //
    // }
    //
    // /**
    // * Fetch a single record from the document table using the primary key
    // (document id).
    // *
    // * @param contentId
    // * the primary key
    // * @return String
    // * the raw XML data representing the single document record.
    // * @throws SoapProcessorException
    // * when the document record is not found.
    // */
    // protected String doFetch(int contentId) throws MimeException {
    // MimeContentApi api =
    // MimeFactory.getMimeContentApiInstance("com.api.db.DefaultSybASABinaryImpl");
    // api.initApi(this.con);
    //
    // //Send document to Browser
    // try {
    // Content document = (Content) api.fetchContent(contentId);
    // // copy DAO data to XML instance
    // ObjectFactory f = new ObjectFactory();
    // MimeContentType mct = f.createMimeContentType();
    // mct.setContentId(BigInteger.valueOf(document.getContentId()));
    // mct.setAppCode(document.getAppCode());
    // mct.setModuleCode(document.getModuleCode());
    // mct.setFilename(document.getFilename());
    // mct.setFilepath(document.getFilepath());
    // mct.setFilesize(BigInteger.valueOf(document.getSize()));
    // mct.setTextData(document.getTextData());
    //
    // // Manaage MIME Type/Sub Type details
    // String filename = document.getFilename();
    // if (filename != null) {
    // String fileExt = filename.substring(filename.indexOf("."));
    // List<MimeTypes> list = (List<MimeTypes>) api.getMimeType(fileExt);
    // if (list != null && list.size() > 0) {
    // // In our database, a MIME filename extension could exist multiple times
    // for (MimeTypes item : list) {
    // MimeType mt = f.createMimeType();
    // mt.setMimeTypeId(BigInteger.valueOf(item.getMimeTypeId()));
    // mt.setFileExt(item.getFileExt());
    // mt.setMediaType(item.getMediaType());
    // mct.getMimeTypeDetails().add(mt);
    // }
    // }
    // else {
    // MimeType mt = f.createMimeType();
    // mct.getMimeTypeDetails().add(mt);
    // }
    // }
    // else {
    // MimeType mt = f.createMimeType();
    // mct.getMimeTypeDetails().add(mt);
    // }
    //
    // // Set up attachment. Force document type to be text/plain since image
    // will be base64 encoded.
    // DataHandler dh = new DataHandler(document.getImageData(), "text/plain");
    // this.attachments = new ArrayList<Object>();
    // this.attachments.add(dh);
    //
    // // Set response message id so that ancestor can include the correct
    // service identifier for SOAP response.
    // // this.responseMsgId = "RS_content_search";
    //
    // // Create Payload instance for response SOAP message body
    // RSContentSearch ws = f.createRSContentSearch();
    // ws.getContent().add(mct);
    // HeaderType header = PayloadFactory.createHeader(this.getRespMsgId(),
    // this.reqMessage.getHeader().getDeliveryMode(), "RESPONSE",
    // this.getUserId());
    // ws.setHeader(header);
    // ReplyStatusType rst = PayloadFactory.createReplyStatus(true, null, null,
    // ws.getContent().size());
    // ws.setReplyStatus(rst);
    // String rawXml =
    // ResourceFactory.getJaxbMessageBinder().marshalMessage(ws);
    // return rawXml;
    // }
    // catch (Exception e) {
    // this.msg =
    // "Unable to execute MIME Document Handler due to MIME Content error";
    // logger.error(this.msg);
    // throw new MimeException(this.msg, e);
    // }
    // }
}