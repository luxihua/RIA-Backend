package com.example.ria.service;

import com.example.ria.common.GCSConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;


@SpringBootTest
public class VisionServiceTest {

    @Autowired
    private VisionService googleVision;

    @Test
    void detectWebDetectionsGcs() throws IOException {
        String gcsPath = GCSConstants.IMAGE_PATH + "yZeKOQ6x8chba-r0OwsmZtUZEsGFGm-WGPAZyDd2b4mrdYypGDuIsavmRomoEo9XRsv0B3NuG8oP_GalDsfmpw.webp";
        System.out.println(gcsPath);
        googleVision.detectWebDetectionsGcs(gcsPath);
    }
}
