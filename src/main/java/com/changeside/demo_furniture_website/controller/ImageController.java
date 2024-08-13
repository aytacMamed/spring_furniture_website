package com.changeside.demo_furniture_website.controller;

import com.changeside.demo_furniture_website.models.entity.ImageData;
import com.changeside.demo_furniture_website.services.image.ImageDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@CrossOrigin
@AllArgsConstructor
public class ImageController {

    private final ImageDataService service;

    @PostMapping("/{productId}")
    public ResponseEntity<Void> saveImage(@PathVariable("productId") Long id,@RequestParam("file") MultipartFile image) throws IOException {
        service.uploadImage(id,image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info/{productId}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("productId") Long id){
        ImageData image = service.getInfoByProductId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getImageByName(@PathVariable("productId") Long id){
        byte[] image = service.getImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

}
