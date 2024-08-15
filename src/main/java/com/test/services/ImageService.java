package com.test.services;

import com.test.compressImage.ImageCompresseurs;
import com.test.entities.Image;
import com.test.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .filename(file.getOriginalFilename())
                .filetype(file.getContentType())
                .imageData(ImageCompresseurs.compressImage(file.getBytes())).build();
        return imageRepository.save(image);

    }

    public byte[] downloadImage(String filename) {
        Optional<Image> image = imageRepository.findImageByFilename(filename);
        byte[] dbimage = ImageCompresseurs.decompressImage(image.get().getImageData());
        return dbimage;
    }
}
