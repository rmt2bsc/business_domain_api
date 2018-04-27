package org.rmt2.api.entity.adapter;

import org.dao.mapping.orm.rmt2.Content;
import org.dao.mapping.orm.rmt2.MimeTypes;
import org.dto.ContentDto;
import org.dto.MimeTypeDto;
import org.dto.adapter.orm.Rmt2MediaDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rmt2.api.document.DocumentMediaMockDataFactory;

/**
 * Test adapters pertaining to the Project Administation module.
 * 
 * @author roy.terrell
 *
 */
public class DocumentMediaAdapterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrmContent() {
        Content o1 = DocumentMediaMockDataFactory.createOrmContent(1000, 101, "tmp/",
                "image.jpg", 1024, 5555, "Media");
        ContentDto dto = Rmt2MediaDtoFactory.getContentInstance(o1);
        
        Assert.assertEquals(1000, dto.getContentId());
        Assert.assertEquals(101, dto.getMimeTypeId());
        Assert.assertEquals("tmp/", dto.getFilepath());
        Assert.assertEquals("image.jpg", dto.getFilename());
        Assert.assertEquals(1024, dto.getSize());
        Assert.assertEquals(5555, dto.getProjectId());
        Assert.assertEquals("ModuleCode", dto.getModuleCode());
        Assert.assertEquals("TextData", dto.getTextData());
        Assert.assertEquals("ImageData".length(), dto.getImageData().length);
        
        try {
            Content nullParm = null;
            dto = Rmt2MediaDtoFactory.getContentInstance(nullParm);
            dto.setContentId(1000);
            dto.setMimeTypeId(101);
            dto.setFilepath("tmp/");
            dto.setFilename("image.jpg");
            dto.setSize(1024);
            dto.setProjectId(5555);
            dto.setModuleCode("ModuleCode");
            dto.setTextData("TextData");
            dto.setImageData("ImageData".getBytes());
            
            Assert.assertEquals(1000, dto.getContentId());
            Assert.assertEquals(101, dto.getMimeTypeId());
            Assert.assertEquals("tmp/", dto.getFilepath());
            Assert.assertEquals("image.jpg", dto.getFilename());
            Assert.assertEquals(1024, dto.getSize());
            Assert.assertEquals(5555, dto.getProjectId());
            Assert.assertEquals("ModuleCode", dto.getModuleCode());
            Assert.assertEquals("TextData", dto.getTextData());
            Assert.assertEquals("ImageData".length(), dto.getImageData().length);
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for Content Adapater");
        }
    }
 
    @Test
    public void testOrmMimeTypes() {
        MimeTypes o1 = DocumentMediaMockDataFactory.createOrmMimeTypes(101, ".jpg", "image/jpg");
        MimeTypeDto dto = Rmt2MediaDtoFactory.getMimeTypeInstance(o1);
        
        Assert.assertEquals(101, dto.getMimeTypeId());
        Assert.assertEquals(".jpg", dto.getFileExt());
        Assert.assertEquals("image/jpg", dto.getMediaType());
        
        try {
            MimeTypes nullParm = null;
            dto = Rmt2MediaDtoFactory.getMimeTypeInstance(nullParm);
            dto.setMimeTypeId(101);
            dto.setFileExt(".jpg");
            dto.setMediaType("image/jpg");
            
            Assert.assertEquals(101, dto.getMimeTypeId());
            Assert.assertEquals(".jpg", dto.getFileExt());
            Assert.assertEquals("image/jpg", dto.getMediaType());
        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An exception occurred setting properties for MimeTypes Adapater");
        }
    }
}
