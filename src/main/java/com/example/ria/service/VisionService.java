package com.example.ria.service;

import com.google.cloud.vision.v1.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class VisionService {

    @Value("${gcs.imagePath}")
    private String imagePath;

    // Detects whether the remote image on Google Cloud Storage has features you would want to
    // moderate.
    public void detectWebDetectionsGcs(String gcsPath) throws IOException {

        List<AnnotateImageRequest> requests = getImageRequests(gcsPath);

        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            AnnotateImageResponse res = response.getResponses(0);

            // 에러 처리
            if (res.hasError()) {
                System.out.format("Error: %s%n", res.getError().getMessage());
                return;
            }

            WebDetection annotation = res.getWebDetection();

            System.out.println("Entity:Id:Score");
            System.out.println("===============");
            Map<String,Float> entityData = getEntityData(annotation);
            printMapData(entityData);
            List<String> bestGuessLabel = getBestGuessLabelsList(annotation);

            for (String label : bestGuessLabel) {
                System.out.println("Best Guess label = " + label);
            }

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
        for (Map.Entry<String, Float> entry : entityData.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
