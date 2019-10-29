package gov.dhs.uscis.bdso.celebrity.service.impl;

import java.net.URL;
import java.util.Base64;
import javax.inject.Inject;
import javax.inject.Named;
import gov.dhs.uscis.bdso.celebrity.service.ImageService;
import gov.dhs.uscis.bdso.celebrity.service.S3Service;

@Named
public class ImageServiceImpl implements ImageService {

    @Inject
    private S3Service s3Service;

    @Override
    public String getImageAsBase64(String key) {
        byte[] imageBytes = s3Service.getAsBytes(key);

        if (imageBytes != null) {
            return Base64.getEncoder().encodeToString(imageBytes);
        }

        return null;
    }

    @Override
    public String getImageAsUrl(String key) {
        URL url = s3Service.generateUrl(key);
        return url != null ? url.toString() : null;
    }
}
