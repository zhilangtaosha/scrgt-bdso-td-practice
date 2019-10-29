package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {
    private static final String S3_KEY = "test_key";
    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private S3Service s3Service;

    @Test
    public void testGetImageAsBase64() {
        String str = "testImage";
        byte[] imageBytes = str.getBytes();
        String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);

        given(s3Service.getAsBytes(S3_KEY)).willReturn(imageBytes);
        String retVal = imageService.getImageAsBase64(S3_KEY);
        assertNotNull(retVal);
        assertEquals(base64Encoded, retVal);
    }

    @Test
    public void testGetImageAsBase64IsNull() {
        given(s3Service.getAsBytes(S3_KEY)).willReturn(null);
        String retVal = imageService.getImageAsBase64(S3_KEY);
        assertNull(retVal);
    }

    @Test
    public void testGetImageAsUrl() throws MalformedURLException {
        URL url = new URL("http://url");

        given(s3Service.generateUrl(S3_KEY)).willReturn(url);
        String retVal = imageService.getImageAsUrl(S3_KEY);
        assertNotNull(retVal);
        assertEquals(url.toString(), retVal);
    }

    @Test
    public void testGetImageAsUrlIsNull() throws MalformedURLException {
        given(s3Service.generateUrl(S3_KEY)).willReturn(null);
        String retVal = imageService.getImageAsUrl(S3_KEY);
        assertNull(retVal);
    }

}
