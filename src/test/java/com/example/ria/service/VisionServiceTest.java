package com.example.ria.service;

import com.example.ria.common.GCSConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
@Slf4j
public class VisionServiceTest {

    @Autowired
    private VisionService googleVision;

    @Test
    void detectWebDetectionsGcs() throws IOException {
        String gcsPath = GCSConstants.IMAGE_PATH + "edc89b4d-e6a8-4a76-879e-decc4c522470";
        log.info("{}", gcsPath);
        String translatedlabel= googleVision.detectWebDetectionsGcs(gcsPath);
        log.info("VisionServiceTest: {}", translatedlabel);
    }
}
