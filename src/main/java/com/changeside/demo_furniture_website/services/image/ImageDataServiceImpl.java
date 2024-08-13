package com.changeside.demo_furniture_website.services.image;

import com.changeside.demo_furniture_website.exception.ImageNotFoundException;
import com.changeside.demo_furniture_website.models.entity.ImageData;
import com.changeside.demo_furniture_website.repository.ImageDataRepository;
import com.changeside.demo_furniture_website.utils.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageDataServiceImpl implements ImageDataService{
    private final ImageDataRepository imageDataRepository;
    public void uploadImage(Long id,MultipartFile file) throws IOException {

        imageDataRepository.save(
                new ImageData(id,
                        file.getContentType(),
                        ImageUtil.compressImage(file.getBytes())));


    }

    @Transactional
    public ImageData getInfoByProductId(Long id) {
        Optional<ImageData> dbImage = imageDataRepository.findImageDataByProductId(id);

        return new ImageData(
                dbImage.get().getProductId(),
                dbImage.get().getType(),
                ImageUtil.decompressImage(dbImage.get().getImageData()));

    }

    @Transactional
    public byte[] getImage(Long id) {
        Optional<ImageData> dbImage = imageDataRepository.findImageDataByProductId(id);
        return ImageUtil.decompressImage(dbImage
                .orElseThrow(() -> new ImageNotFoundException("Image not found for id : " + id))
                .getImageData());
    }

    public void removeImageByProductId(Long id){
        imageDataRepository.deleteImageDataByProductId(id);
    }
}
