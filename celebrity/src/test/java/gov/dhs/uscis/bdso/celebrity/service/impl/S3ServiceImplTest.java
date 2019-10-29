package gov.dhs.uscis.bdso.celebrity.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.dhs.uscis.bdso.celebrity.domain.elastic.ImdbActorDocument;
import gov.dhs.uscis.bdso.celebrity.property.AWSProperties;

@RunWith(MockitoJUnitRunner.class)
public class S3ServiceImplTest {
    private static final String AWS_KEY = "test_key";

    @InjectMocks
    private S3ServiceImpl s3Service;

    @Mock
    private AmazonS3 s3Client;

    @Mock
    private AWSProperties awsProperties;
     
    @Mock
    ListObjectsV2Request request;
     
    @Mock
    ListObjectsV2Result result;
   

    @Before
    public void init() {
        given(awsProperties.getBucket()).willReturn("rdso-bucket");
    }

    @Test
    public void testLoadDataSource() throws Exception {
        List<S3ObjectSummary> list = new ArrayList<>();
        S3ObjectSummary os = new S3ObjectSummary();
        // String json = "{\n" + " \"test\": \"Celebrity\"\n" + "}";
        ImdbActorDocument imdbDoc = new ImdbActorDocument();
        String str = "John Doe";
        imdbDoc.setBirthName(str);
        StringWriter sw = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(sw, imdbDoc);
        String json = sw.toString();
        S3Object s3Object = new S3Object();
        InputStream inputStream = new ByteArrayInputStream(json.getBytes(Charset.forName("UTF-8")));
        s3Object.setObjectContent(inputStream);



        os.setKey("test");
        list.add(os);
        given(s3Client.listObjectsV2(Mockito.any(ListObjectsV2Request.class))).willReturn(result);
        given(result.getObjectSummaries()).willReturn(list);

        given(s3Client.getObject(any(GetObjectRequest.class))).willReturn(s3Object);

        List<ImdbActorDocument> retList = s3Service.loadDataSource("person", ImdbActorDocument.class);

        assertEquals(1, retList.size());
        assertEquals(str, retList.get(0).getBirthName());
    }
    
    @Test
    public  void testMapper() throws Exception{ 
    	ObjectMapper mapper = S3ServiceImpl.mapper();
    	assertNotNull(mapper);
    }
    
    @Test
    public void testPut() {
        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        InputStream is = mock(InputStream.class);
        long contentLength = 10L;

        s3Service.put(is, contentLength);
        verify(s3Client, times(1)).putObject(captor.capture());

        PutObjectRequest request = captor.getValue();
        assertEquals("rdso-bucket", request.getBucketName());
        assertEquals(10L, request.getMetadata().getContentLength());
    }

    @Test
    public void testPutWithMetadata() {
        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        InputStream is = mock(InputStream.class);
        long contentLength = 10L;
        Map<String, Object> map = new HashMap<>();
        map.put("key", "new metadatavalue");

        String awsKey = s3Service.put(is, contentLength, map);

        assertNotNull(awsKey);
        verify(s3Client).putObject(captor.capture());

        PutObjectRequest request = captor.getValue();
        assertEquals("rdso-bucket", request.getBucketName());
        assertEquals(10L, request.getMetadata().getContentLength());
    }

    @Test
    public void testPutByteContent() {
        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        InputStream is = mock(InputStream.class);
        long contentLength = 10L;
        String awsKey = "test-key";
        byte[] content = "content".getBytes();
        String returnAwsKey = s3Service.put(awsKey, content);

        assertNotNull(returnAwsKey);
        verify(s3Client).putObject(captor.capture());

        PutObjectRequest request = captor.getValue();
        assertEquals("rdso-bucket", request.getBucketName());
        assertEquals(content.length, request.getMetadata().getContentLength());
    }

    @Test
    public void testPutByteContentWithMetaData() {
        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        InputStream is = mock(InputStream.class);
        long contentLength = 10L;
        String awsKey = "test-key";
        byte[] content = "content".getBytes();
        Map<String, Object> map = new HashMap<>();
        map.put(Headers.CONTENT_ENCODING, "content encoding");
        map.put(Headers.CONTENT_TYPE, "content type");

        String returnAwsKey = s3Service.put(awsKey, content, map);

        assertNotNull(returnAwsKey);
        verify(s3Client).putObject(captor.capture());

        PutObjectRequest request = captor.getValue();
        assertEquals("rdso-bucket", request.getBucketName());
        assertEquals(content.length, request.getMetadata().getContentLength());
    }

    @Test
    public void testGet() {
        ArgumentCaptor<GetObjectRequest> captor = ArgumentCaptor.forClass(GetObjectRequest.class);
        S3Object s3Object = mock(S3Object.class);

        given(s3Client.getObject(any(GetObjectRequest.class))).willReturn(s3Object);
        S3Object s3Obj = s3Service.get(AWS_KEY);

        verify(s3Client, times(1)).getObject(captor.capture());

        GetObjectRequest request = captor.getValue();
        assertEquals(s3Object, s3Obj);
        assertEquals(AWS_KEY, request.getKey());
    }

    @Test
    public void testGetError() {
        ArgumentCaptor<GetObjectRequest> captor = ArgumentCaptor.forClass(GetObjectRequest.class);

        given(s3Client.getObject(any(GetObjectRequest.class))).willThrow(new AmazonServiceException("test"));
        S3Object s3Object = s3Service.get(AWS_KEY);
        verify(s3Client, times(1)).getObject(captor.capture());
        assertNull(s3Object);
    }

    @Test(expected = RuntimeException.class)
    public void testPutError() {
        ArgumentCaptor<PutObjectRequest> captor = ArgumentCaptor.forClass(PutObjectRequest.class);
        InputStream is = mock(InputStream.class);
        long contentLength = 10L;

        given(s3Client.putObject(any(PutObjectRequest.class))).willThrow(new AmazonServiceException("test"));
        String awsKey = s3Service.put(is, contentLength);
    }

    @Test
    public void testDoesObjectExist() throws MalformedURLException {
        given(s3Client.doesObjectExist(anyString(), anyString())).willReturn(true);
        boolean retVal = s3Service.doesObjectExist("key");
        assertTrue(retVal);
    }

    @Test
    public void testGenerateUrl() throws MalformedURLException {
        URL url = new URL("http://example.com");
        given(s3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class))).willReturn(url);
        URL returnUrl = s3Service.generateUrl("key");
        assertNotNull(returnUrl);
        assertEquals(url, returnUrl);
    }

    @Test // (expected = AmazonServiceException.class)
    public void testGenerateUrlAmazonException() throws MalformedURLException {
        URL url = new URL("http://example.com");
        given(s3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
                .willThrow(new AmazonServiceException("Exception"));
        URL returnUrl = s3Service.generateUrl("key");
        assertNull(returnUrl);
    }

    @Test // (expected = SdkClientException.class)
    public void testGenerateUrlSdkException() throws MalformedURLException {
        URL url = new URL("http://example.com");
        given(s3Client.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
                .willThrow(new SdkClientException("Exception"));
        URL returnUrl = s3Service.generateUrl("key");
        assertNull(returnUrl);
    }
    
    @Test
    public void testGetAsBytes() {
        S3Object s3Object = new S3Object();
        InputStream inputStream = new ByteArrayInputStream("Test String".getBytes(Charset.forName("UTF-8")));
        s3Object.setObjectContent(inputStream);

        given(s3Client.getObject(any(GetObjectRequest.class))).willReturn(s3Object);
        assertNotNull(s3Service.getAsBytes("test"));
    }

    @Test
    public void testGetAsBytesWithNull() {
        S3Object s3Object = null;

        given(s3Client.getObject(any(GetObjectRequest.class))).willReturn(s3Object);
        assertNull(s3Service.getAsBytes("test"));
    }
}
