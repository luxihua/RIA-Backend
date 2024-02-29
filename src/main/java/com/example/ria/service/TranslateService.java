package com.example.ria.service;

import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Component
public class TranslateService {

    @Value("${gcs.projectId}")
    private String projectId;


    public String translate(String targetLanguage, String text) throws IOException {
        // 클라이언트 초기화
        try (TranslationServiceClient client = TranslationServiceClient.create()) {
            // 번역 요청 환경설정
            LocationName parent = LocationName.of(projectId, "global");
            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent.toString())
                    .setMimeType("text/plain")
                    .setTargetLanguageCode(targetLanguage)
                    .addContents(text)
                    .build();

            // 번역 수행
            TranslateTextResponse response = client.translateText(request);

            System.out.println(response.getTranslations(0).getTranslatedText());

            return response.getTranslations(0).getTranslatedText();
        }
    }
}
