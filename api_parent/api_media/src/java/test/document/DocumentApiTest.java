package document;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.Assert;

import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.MediaModuleException;
import org.modules.document.DocumentContentApi;
import org.modules.document.DocumentContentApiFactory;

import com.util.RMT2Base64Encoder;
import com.util.RMT2File;

/**
 * Test the implementation of the {@link DocumentContentApi}
 * 
 * @author rterrell
 * 
 */
public class DocumentApiTest {

    private DocumentContentApiFactory f;

    private DocumentContentApi api;

    private int contentIdArray[];

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        RMT2File.loadSystemProperties("config.MultimediaTestSystemParms");
        this.f = new DocumentContentApiFactory();
        this.api = this.f.createMediaContentApi();
        this.api.setApiUser("testuser");

        this.contentIdArray = new int[2];
        String testDataPath = "C:\\ProjectServer\\BusinessDomainApi\\rmt2bsc\\multimedia\\src\\test\\data\\";
        this.contentIdArray[0] = this.createTestData(testDataPath,
                "acct_cd_77777.jpg");
        this.contentIdArray[1] = this.createTestData(testDataPath,
                "timesheet_20091220.pdf");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.deleteTestData();
        this.api = null;
        this.f = null;
    }

    private int createTestData(String path, String fileName) {
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
        dto.setFilename(fileName);
        dto.setFilepath(path);
        String data = RMT2File.getFileContentAsBase64(path + fileName);
        dto.setImageData(data);
        int newId = 0;

        // Add document record to the database
        try {
            newId = this.api.addMedia(dto, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(newId > 0);
        Assert.assertEquals(newId, dto.getContentId());
        Assert.assertTrue(dto.getSize() > 0);

        return newId;
    }

    private void deleteTestData() {
        // Delete document from the database
        for (int ndx = 0; ndx < this.contentIdArray.length; ndx++) {
            int contentId = this.contentIdArray[ndx];
            int rc = 0;
            try {
                rc = this.api.deleteMedia(contentId);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
            Assert.assertTrue(rc > 0);
            Assert.assertEquals(1, rc);
        }

    }

    @Test
    public void testFetchImageContent() {
        ContentDto dto = null;
        int contentId = this.contentIdArray[0];
        // int contentId = 4838;
        try {
            dto = this.api.getMedia(contentId, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getContentId(), contentId);

        // Output file to disk for manual verification
        byte binaryData[] = (byte[]) dto.getImageData();
        RMT2File.outputFile(binaryData,
                "c:\\tmp\\testApiFetchImageContentResults-" + dto.getFilename());
    }

    @Test
    public void testFetchPdfContent() {
        ContentDto dto = null;
        int contentId = this.contentIdArray[1];
        try {
            dto = this.api.getMedia(contentId, true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getContentId(), contentId);

        // Output file to disk for manual verification
        byte binaryData[] = (byte[]) dto.getImageData();
        RMT2File.outputFile(binaryData,
                "c:\\tmp\\testApiFetchPdfContentResults-" + dto.getFilename());

    }

    @Test
    public void testFetchSingleMimeType() {
        String mediaTypeName = "application/postscript";
        MimeTypeDto dto = null;
        try {
            dto = this.api.getMimeType(10);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(mediaTypeName, dto.getMediaType());
    }

    @Test
    public void testCreateWithInvalidFilename() {
        String path = "C:\\ProjectServer\\BusinessDomainApi\\rmt2bsc\\multimedia\\src\\test\\data\\";
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
        dto.setFilename(null);
        dto.setFilepath(path);
        // Add document record to the database
        try {
            this.api.addMedia(dto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed due to the API should have rejected DTO for null file name parameter");
    }

    @Test
    public void testCreateWithInvalidFilePath() {
        String fileName = "acct_cd_77777.jpg";
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
        dto.setFilename(fileName);
        dto.setFilepath(null);

        // Add document record to the database
        try {
            this.api.addMedia(dto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed due to the API should have rejected DTO for null file path parameter");
    }

    @Test
    public void testCreateWithoutRequiredParams() {
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
        // Add document record to the database
        try {
            this.api.addMedia(dto, true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed due to the API should have rejected DTO for not having any of the required parameters");
    }

    @Test
    public void testAddMediaAsExternalFile() {
        int newContentId = 0;
        try {
            ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
            String srcFilePath = "C:\\ProjectServer\\BusinessDomainApi\\rmt2bsc\\multimedia\\src\\test\\data\\The 16th Hour.mp3";
            dto.setFilename("The 16th Hour.mp3");
            File file = new File(srcFilePath);
            FileInputStream fis = new FileInputStream(file);
            byte binaryData[] = RMT2File.getStreamByteData(fis);
            String data = RMT2Base64Encoder.encode(binaryData);
            dto.setImageData(data);
            newContentId = this.api.addMedia(dto, false);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        ;
        Assert.assertTrue(newContentId > 0);

        // Since the test case destroys all objects at end, force thread to
        // sleep
        // so that you give a large file a chance to be saved.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Delete media document and external file
        int rc = 0;
        try {
            rc = this.api.deleteMedia(newContentId);
        } catch (MediaModuleException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        Assert.assertEquals(1, rc);
        return;
    }
}
