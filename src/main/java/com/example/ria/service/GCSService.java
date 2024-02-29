package com.example.ria.service;

import com.google.cloud.storage.*;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GCSService {

    @Value("${gcs.bucketName}")
    private String bucketName;

    private Storage storage;

    @PostConstruct
    public void init() {
        // Google Cloud Storage 객체 초기화
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String uploadFileToGCS(MultipartFile image) throws IOException {
        if (!isImage(image)) {
            throw new IllegalArgumentException("이미지 파일이 아닙니다.");
        }

        String uuid = UUID.randomUUID().toString();
        String ext = image.getContentType();

        BlobId blobId = BlobId.of(bucketName, uuid);
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

    // storage 필드에 대한 접근자
    public Storage getStorage() {
        return storage;
    }


}
