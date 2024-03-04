package com.example.ria.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class TranslateServiceTests {

    @Autowired
    private TranslateService translateService;

    @Test
    public void testTranslate() throws IOException {

        // 테스트 값 설정
        String targetLanguage = "ko";
        String text = "Hongdae Street";

        String translatedText = translateService.translate(targetLanguage, text);
        log.info("TranslateServiceTests: {}", translatedText);
    }
}
