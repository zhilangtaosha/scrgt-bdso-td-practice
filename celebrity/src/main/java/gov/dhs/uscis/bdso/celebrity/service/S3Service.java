package gov.dhs.uscis.bdso.celebrity.service;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import com.amazonaws.services.s3.model.S3Object;

public interface S3Service {
    String put(InputStream is, long contentLength);

    String put(InputStream is, long contentLength, Map<String, Object> metadata);

    String put(String key, byte[] content);

    String put(String key, byte[] content, Map<String, Object> metadata);

    S3Object get(String key);
    
    byte[] getAsBytes(String key);

    <T> List<T> loadDataSource(String prefix, Class<T> documentClass);

    boolean doesObjectExist(String key);
    
    URL generateUrl(String key);
}
