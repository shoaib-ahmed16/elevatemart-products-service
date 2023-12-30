package com.products.service.elevatemartproductsservice.services.impl;

import com.products.service.elevatemartproductsservice.config.FileStorageConfig;
import com.products.service.elevatemartproductsservice.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired(required=true)
    private FileStorageConfig fileStorageConfig;
    @Override
    public Path storeFile(MultipartFile multipartFile) {
        try{
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Path uploadPath = Paths.get(fileStorageConfig.getUploadDir()).resolve(fileName);
            Files.copy(multipartFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return  uploadPath;
        }catch (IOException io){

        }
        return null;
    }
}
