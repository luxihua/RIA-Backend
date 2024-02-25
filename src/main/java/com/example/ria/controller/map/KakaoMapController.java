package com.example.ria.controller.map;

import com.example.ria.dto.vo.PlaceList;
import com.example.ria.service.map.KakaoMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class KakaoMapController {

    @Autowired
    KakaoMapService kakaoMapService;

    @GetMapping(path = "/search")
    public ResponseEntity<PlaceList> searchPlaceByKeyword(@RequestParam("keyword") String keyword, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("userId") String userId) throws Exception {

        log.info(" /search api start. user [{}], keyword [{}], page [{}], size [{}]", userId, keyword, page, size);

        PlaceList list = kakaoMapService.searchPlaceByKeyword(keyword, page, size, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
