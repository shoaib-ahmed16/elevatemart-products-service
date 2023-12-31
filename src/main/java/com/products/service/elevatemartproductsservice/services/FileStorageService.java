package com.products.service.elevatemartproductsservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public sealed interface FileStorageService permits FileStorageServiceImpl {
    Path storeFile(MultipartFile multipartFile);
}
