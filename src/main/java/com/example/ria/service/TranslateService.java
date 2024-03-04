package com.example.ria.service;

import com.example.ria.common.GCSConstants;
import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class TranslateService {

    public String translate(String targetLanguage, String text) throws IOException {
        // 클라이언트 초기화
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            // 번역 요청 환경설정
            LocationName parent = LocationName.of(GCSConstants.PROJECT_ID, "global");
            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent.toString())
                    .setMimeType("text/plain")
                    .setTargetLanguageCode(targetLanguage)
                    .addContents(text)
                    .build();

            // 번역 수행
            TranslateTextResponse response = client.translateText(request);

            log.debug(response.getTranslations(0).getTranslatedText());

            return response.getTranslations(0).getTranslatedText();
        }
    }
}
