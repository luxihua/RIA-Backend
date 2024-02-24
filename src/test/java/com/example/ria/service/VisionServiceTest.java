package com.example.ria.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;


class VisionServiceTest {


    public VisionService googleVision = new VisionService();
    @Test
    void detectWebDetectionsGcs() throws IOException {
        googleVision.detectWebDetectionsGcs(
                "gs://vision_practice_example/yZeKOQ6x8chba-r0OwsmZtUZEsGFGm-WGPAZyDd2b4mrdYypGDuIsavmRomoEo9XRsv0B3NuG8oP_GalDsfmpw.webp");

    }
}