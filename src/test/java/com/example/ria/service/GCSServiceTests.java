package com.example.ria.service;


import com.example.ria.common.GCSConstants;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GCSServiceTests {

    @Autowired
    private GCSService gcsService;

    @Autowired
    private Storage storage;

    @Test
    public void testFileUpload() throws IOException {
        // mock 파일 생성
        Resource imageResource = new ClassPathResource("/hongdae.jpg");
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                imageResource.getFilename(),
                "image/jpeg",
                imageResource.getInputStream().readAllBytes());

        // 파일 업로드
        String uuid = gcsService.uploadFileToGCS(multipartFile);

        // BlobInfo 검색
        BlobId blobId = BlobId.of(GCSConstants.BUCKET_NAME, uuid);

        Blob blob = storage.get(blobId);

        assertNotNull(blob);

        System.out.println("GCSServiceTests : " + blob);

    }

}





