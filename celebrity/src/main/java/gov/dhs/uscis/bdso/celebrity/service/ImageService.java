package gov.dhs.uscis.bdso.celebrity.service;

public interface ImageService {
    String getImageAsBase64(String key);

    String getImageAsUrl(String key);
}
