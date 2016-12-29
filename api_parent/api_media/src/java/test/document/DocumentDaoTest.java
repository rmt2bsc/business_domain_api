package document;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.Assert;

import org.dao.document.ContentDao;
import org.dao.document.ContentDaoFactory;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.RMT2Base64Encoder;
import com.util.RMT2File;

/**
 * Test the Sybase Adaptive Server Anywhere implementation of the
 * {@link ContentDao}
 * 
 * @author rterrell
 * 
 */
public class DocumentDaoTest {

    private ContentDaoFactory f;

    private ContentDao dao;

    private ContentDao fileDao;

    private int contentIdArray[];

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.f = new ContentDaoFactory();
        this.dao = this.f.createEmbeddedMediaDaoInstance();
        this.fileDao = this.f.createExternalFileMediaDaoInstance();
        this.dao.setDaoUser("testuser");
        this.fileDao.setDaoUser("testuser");

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
        this.dao.close();
        this.dao = null;
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
            newId = this.dao.addContent(dto);
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
            Object rc = null;
            try {
                rc = this.dao.deleteContent(contentId);
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail(e.getMessage());
            }
            Assert.assertNotNull(rc);
        }

    }

    @Test
    public void testFetchImageContent() {
        ContentDto dto = null;
        int contentId = this.contentIdArray[0];
        try {
            dto = this.dao.fetchContent(contentId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getContentId(), contentId);

        // Output file to disk for manual verification
        byte binaryData[] = (byte[]) dto.getImageData();
        RMT2File.outputFile(binaryData,
                "c:\\tmp\\testFetchImageContentResults-" + dto.getFilename());

    }

    @Test
    public void testFetchPdfContent() {
        ContentDto dto = null;
        int contentId = this.contentIdArray[1];
        try {
            dto = this.dao.fetchContent(contentId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getContentId(), contentId);

        // Output file to disk for manual verification
        byte binaryData[] = (byte[]) dto.getImageData();
        RMT2File.outputFile(binaryData, "c:\\tmp\\testFetchPdfContentResults-"
                + dto.getFilename());

    }

    @Test
    public void testFetchContentAsFileInstance() {
        int contentId = this.contentIdArray[1];
        ContentDto dto = null;
        try {
            dto = this.dao.fetchContentAsFile(contentId);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(dto);
        Assert.assertNotNull(dto.getFilename());
        Assert.assertNotNull(dto.getImageData());
        Assert.assertTrue(dto.getImageData() instanceof File);
    }

    @Test
    public void testFetchSingleMimeType() {
        String mediaTypeName = "application/postscript";
        MimeTypeDto dto = null;
        try {
            dto = this.dao.fetchMimeType(10);
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
            this.dao.addContent(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed due to the DAO should have rejected DTO for null file name parameter");
    }

    @Test
    public void testCreateWithInvalidFilePath() {
        String fileName = "acct_cd_77777.jpg";
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
        dto.setFilename(fileName);
        dto.setFilepath(null);

        // Add document record to the database
        try {
            this.dao.addContent(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed due to the DAO should have rejected DTO for null file path parameter");
    }

    @Test
    public void testCreateWithoutRequiredParams() {
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
        // Add document record to the database
        try {
            this.dao.addContent(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("Test failed due to the DAO should have rejected DTO for not having any of the required parameters");
    }

    @Test
    public void testAddMediaAsExternalFile() {
        int contentId = 0;
        try {
            ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(null);
            String srcFilePath = "C:\\ProjectServer\\BusinessDomainApi\\rmt2bsc\\multimedia\\src\\test\\data\\The 16th Hour.mp3";
            dto.setFilename("The 16th Hour.mp3");
            File file = new File(srcFilePath);
            FileInputStream fis = new FileInputStream(file);
            byte binaryData[] = RMT2File.getStreamByteData(fis);
            String data = RMT2Base64Encoder.encode(binaryData);
            dto.setImageData(data);
            contentId = fileDao.addContent(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;
        Assert.assertTrue(contentId > 0);

        // Since the test case destroys all objects at end, force thread to
        // sleep
        // so that you give a large file a chance to be saved.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ContentDto results = this.fileDao.deleteContent(contentId);
        Assert.assertEquals(contentId, results.getContentId());
        return;
    }

}
