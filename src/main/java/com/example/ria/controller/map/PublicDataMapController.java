package com.example.ria.controller.map;

import com.example.ria.dto.vo.PublicMapItem;
import com.example.ria.service.map.PublicDataMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class PublicDataMapController {

    @Autowired
    PublicDataMapService publicDataMapService;


    @GetMapping(path = "/searchPlace")
    public ResponseEntity<List<PublicMapItem>> searchLocationBasedList(@RequestParam("mapX") double mapX, @RequestParam("mapY") double mapY, @RequestParam("radius") double radius, @RequestParam("userId") String userId) throws Exception {

        log.info(" /search api start. user [{}], mapX [{}], mapY [{}], radius [{}]", userId, mapX, mapY, radius);

        List<PublicMapItem> list = publicDataMapService.searchLocationBasedList1(mapX, mapY, radius, userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
