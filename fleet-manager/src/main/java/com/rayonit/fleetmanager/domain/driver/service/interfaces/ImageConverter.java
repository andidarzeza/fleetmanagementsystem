package com.rayonit.fleetmanager.domain.driver.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageConverter {
    String convert(MultipartFile file);
}
