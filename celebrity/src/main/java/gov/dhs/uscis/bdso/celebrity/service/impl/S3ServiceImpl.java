package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import gov.dhs.uscis.bdso.celebrity.converter.CamelCaseKeySerializer;
import gov.dhs.uscis.bdso.celebrity.property.AWSProperties;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;

@Named
public class S3ServiceImpl implements S3Service {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // one hour
    private static final long DURATION_MILLISECONDS = 3600000L;
    private static ObjectMapper mapper = mapper();
    
    private static final String ERROR_S3 ="Amazon S3 couldn't process the submitted request.";

    @Inject
    private AWSProperties awsProperties;

    @Inject
    private AmazonS3 s3Client;
    

    @Override
    public String put(InputStream is, long contentLength) {
        return putObject(is, contentLength, null, null);
    }

    @Override
    public String put(InputStream is, long contentLength, Map<String, Object> metadata) {
        return putObject(is, contentLength, metadata, null);
    }

    @Override
    public String put(String key, byte[] content) {
        InputStream is = new ByteArrayInputStream(content);
        return putObject(is, content.length, null, key);
    }

    @Override
    public String put(String key, byte[] content, Map<String, Object> metadata) {
        InputStream is = new ByteArrayInputStream(content);
        return putObject(is, content.length, metadata, key);
    }

    @Override
    public S3Object get(String key) {
        try {
            return s3Client.getObject(new GetObjectRequest(awsProperties.getBucket(), key));
        } catch (AmazonServiceException e) {
        	LOG.error(ERROR_S3, e);
            return null;
        }
    }

    @Override
    public byte[] getAsBytes(String key) {
        try {
            S3Object s3Obj = get(key);

            if (s3Obj != null) {
                return IOUtils.toByteArray(s3Obj.getObjectContent());
            }

            return null; //NOSONAR
        } catch (IOException e) {
            LOG.error("IO exception.", e);
            return null; //NOSONAR
        }
    }



    @Override
    public boolean doesObjectExist(String key) {
        return s3Client.doesObjectExist(awsProperties.getBucket(), key);
    }

    @Override
    public URL generateUrl(String key) {
        try {
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += DURATION_MILLISECONDS;
            expiration.setTime(expTimeMillis);

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(awsProperties.getBucket(), key).withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        } catch (AmazonServiceException e) {
            LOG.error(ERROR_S3, e);
            return null;
        } catch (SdkClientException e) {
            LOG.error("Could not process response from Amazon S3 or response was not rceived.", e);
            return null;
        }
    }

    @Override
    public <T> List<T> loadDataSource(String prefix, Class<T> documentClass) {
        LOG.debug("Listing objects");
        List<T> documents = new ArrayList<>();

        try {
            // maxKeys is set to 2 to demonstrate the use of
            // ListObjectsV2Result.getNextContinuationToken()
        	ListObjectsV2Request request  = new ListObjectsV2Request().withBucketName(awsProperties.getBucket())
                    .withPrefix(prefix);
            ListObjectsV2Result result;

            int i = 0;

            do {
                result = s3Client.listObjectsV2(request);

                for (S3ObjectSummary os : result.getObjectSummaries()) {
                    String key = os.getKey();
                    S3Object obj = get(key);

                    if (obj != null) {
                        StringWriter writer = new StringWriter();
                        IOUtils.copy(obj.getObjectContent(), writer, StandardCharsets.UTF_8);
                        String camelCaseJson = convertJsonProperties(writer.toString());
                        documents.add(convertToDocument(camelCaseJson, documentClass));
                        obj.close();
                    }

                    i++;
                }

                // If there are more than maxKeys keys in the bucket, get a continuation token
                // and list the next objects.
                String token = result.getNextContinuationToken();
                request.setContinuationToken(token);
            } while (result.isTruncated());

            LOG.debug("Total documents retrieved: {}", i);
        } catch (IOException e) {
            LOG.error("Error deserializing json", e);
        }

        return documents;
    }

    protected static ObjectMapper mapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addKeyDeserializer(String.class, new CamelCaseKeySerializer());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    protected String convertJsonProperties(String json) throws JsonParseException, JsonMappingException, IOException {
        Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        StringWriter sw = new StringWriter();
        mapper.writerWithDefaultPrettyPrinter().writeValue(sw, map);
        String camelCaseJson = sw.toString();
        LOG.trace("camelCaseJson: {}", camelCaseJson);
        return camelCaseJson;
    }

    private <T> T convertToDocument(String json, Class<T> toType) throws IOException {
        T imdbDocument = mapper.reader().forType(toType).readValue(json);
        LOG.trace("processed: {}", imdbDocument);
        return imdbDocument;
    }

    private String putObject(InputStream is, long contentLength, Map<String, Object> metadata, String key) {
        String awsKey = key == null ? UUID.randomUUID().toString() : key;

        try {
            ObjectMetadata objectMetadata = setMetaData(metadata);
            objectMetadata.setContentLength(contentLength);
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(awsProperties.getBucket(), awsKey, is, objectMetadata);
         
            putObjectRequest.getRequestClientOptions().setReadLimit((int)contentLength);
            s3Client.putObject(putObjectRequest);
        } catch (AmazonServiceException e) {
            LOG.error("Failed to upload object to S3 bucket [" + awsProperties.getBucket() + "]", e);
            throw new RuntimeException("Failed to upload object to S3 bucket [" + awsProperties.getBucket() + "]", e);
            
        }

        return awsKey;
    }

    private ObjectMetadata setMetaData(Map<String, Object> metadata) {
        ObjectMetadata objectMetadata = new ObjectMetadata();

        if (metadata != null) {
            for (Map.Entry<String, Object> entry : metadata.entrySet()) {
                if (Headers.CONTENT_ENCODING.equals(entry.getKey())) {
                    objectMetadata.setContentEncoding(entry.getValue().toString());
                }

                if (Headers.CONTENT_TYPE.equals(entry.getKey())) {
                    objectMetadata.setContentType(entry.getValue().toString());
                }

                objectMetadata.addUserMetadata(entry.getKey(), entry.getValue().toString());
            }
        }

        return objectMetadata;
    }
}
