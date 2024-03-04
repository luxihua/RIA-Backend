package com.example.ria.service;

import com.google.cloud.vision.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class VisionService {
    @Autowired
    private TranslateService translateService;

    public String detectWebDetectionsGcs(String gcsPath) throws IOException {

        List<AnnotateImageRequest> requests = getImageRequests(gcsPath);


        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            AnnotateImageResponse res = response.getResponses(0);

            // 에러 처리
            if (res.hasError()) {
                log.error("Error: %s%n", res.getError().getMessage());
            }

            WebDetection annotation = res.getWebDetection();

           
            Map<String,Float> entityData = getEntityData(annotation);
            printMapData(entityData);
            List<String> bestGuessLabel = getBestGuessLabelsList(annotation);

            for (String label : bestGuessLabel) {
                log.debug("Best Guess label = " + label);
            }

            return translateService.translate("ko", bestGuessLabel.get(0));

        }
    }

    private List<String> getBestGuessLabelsList(WebDetection annotation) {
        List<String> labels = new ArrayList<>();
        for (WebDetection.WebLabel label : annotation.getBestGuessLabelsList()) {
            labels.add(label.getLabel());
        }
        return labels;
    }

    private Map<String,Float> getEntityData(WebDetection annotation) {
        Map<String, Float> entityData = new HashMap<>();

        for (WebDetection.WebEntity entity : annotation.getWebEntitiesList()) {
            entityData.put(entity.getDescription(), entity.getScore());
        }
        return entityData;
    }

    private static List<AnnotateImageRequest> getImageRequests(String gcsPath) {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ImageSource imgSource = ImageSource.newBuilder().setGcsImageUri(gcsPath).build();
        Image img = Image.newBuilder().setSource(imgSource).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.WEB_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);
        return requests;
    }

    public static void printMapData(Map<String, Float> entityData) {
         log.debug("Entity:Id:Score");
        log.debug("===============");
        for (Map.Entry<String, Float> entry : entityData.entrySet()) {
            log.debug(entry.getKey() + " : " + entry.getValue());
        }
    }
}
