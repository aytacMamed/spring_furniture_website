package com.changeside.demo_furniture_website.services.image;

import com.changeside.demo_furniture_website.models.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {
    void uploadImage(Long id, MultipartFile file) throws IOException;

    ImageData getInfoByProductId(Long id);

    byte[] getImage(Long id);

    void removeImageByProductId(Long id);

}
