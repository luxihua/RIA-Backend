package com.example.ria.service;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GCSServiceTests {

    @Autowired
    private GCSService gcsService;

    @Value("${gcs.bucketName}")
    private String bucketName;

    @Test
    public void testFileUpload() throws IOException {
        // mock 파일 생성
        FileInputStream input = new FileInputStream("/Users/msy/ai-sample-photo/hongdae.jpg"); // 절대경로로 파일 삽입
        MultipartFile multipartFile = new MockMultipartFile("file", "hongdae.jpg", "image/jpeg", input);

        // 파일 업로드
        String uuid = gcsService.uploadFileToGCS(multipartFile);

        // BlobInfo 검색
        BlobId blobId = BlobId.of(bucketName, uuid);
        Blob blob = gcsService.getStorage().get(blobId);
        assertNotNull(blob);

        System.out.println("GCSServiceTests : " + blob);

    }

}





