package com.example.ria.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class TranslateServiceTests {

    @Autowired
    private TranslateService translateService;

    @Value("${gcs.projectId}")
    private String projectId;

    @Test
    public void testTranslate() throws IOException {

        // 테스트 값 설정
        String targetLanguage = "ko";
        String text = "Hongdae Street";

        String translatedText = translateService.translate(targetLanguage, text);
    }
}