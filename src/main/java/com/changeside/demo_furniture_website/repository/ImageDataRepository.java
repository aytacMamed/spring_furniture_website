package com.changeside.demo_furniture_website.repository;

import com.changeside.demo_furniture_website.models.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findImageDataByProductId(Long id);

    void deleteImageDataByProductId(Long productId);

}
