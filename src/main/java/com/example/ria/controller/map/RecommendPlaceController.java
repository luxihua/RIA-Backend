package com.example.ria.controller.map;

import com.example.ria.dto.vo.PlaceList;
import com.example.ria.service.GCSService;
import com.example.ria.service.VisionService;
import com.example.ria.service.map.KakaoMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class RecommendPlaceController {

    @Autowired
    GCSService gcsService;

    @Autowired
    VisionService visionService;

    @Autowired
    KakaoMapService kakaoMapService;

    @PostMapping("/recommend-place")
    public ResponseEntity<String> recommendPlace(@RequestParam("image")MultipartFile image) throws IOException {
        try {
            String gcsPath = gcsService.uploadFileToGCS(image);
            String result = visionService.detectWebDetectionsGcs(gcsPath);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (IOException e) {
            log.error("이미지 업로드 or 분석 중 오류 발생 : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 or 분석 중 오류 발생");
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<PlaceList> searchPlaceByKeyword(@RequestParam("keyword") String keyword, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("userId") String userId) throws Exception {

        log.info(" /search api start. user [{}], keyword [{}], page [{}], size [{}]", userId, keyword, page, size);

        PlaceList list = kakaoMapService.searchPlaceByKeyword(keyword, page, size, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
