package com.example.ria.service;

import com.example.ria.common.GCSConstants;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GCSService {

    private final Storage storage;

    public String uploadFileToGCS(MultipartFile image) throws IOException {
        if (!isImage(image)) {
            throw new IllegalArgumentException("이미지 파일이 아닙니다.");
        }

        String uuid = UUID.randomUUID().toString();
        String ext = image.getContentType();

        BlobId blobId = BlobId.of(GCSConstants.BUCKET_NAME, uuid);
        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(blobId)
                .setContentType(ext)
                .build(),
        image.getBytes()
        );
        System.out.println("GCSService : " + blobInfo);
        return uuid;
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("image/");
    }



}
