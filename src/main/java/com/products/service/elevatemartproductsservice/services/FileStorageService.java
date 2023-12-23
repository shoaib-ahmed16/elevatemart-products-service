package com.products.service.elevatemartproductsservice.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.nio.file.Path;

public interface FileStorageService {
    Path storeFile(MultipartFile multipartFile);
}
