package com.example.ria.service;

import com.example.ria.common.Constants;
import com.example.ria.dto.vo.PublicMapItem;
import com.example.ria.dto.vo.PublicMapItems;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class PublicDataMapService {
    @Autowired
    private RestClient restClient;
    private static final String MOBILEOS = "MobileOS";
    private static final String MOBILEAPP = "MobileApp";
    private static final String TYPE = "_type";
    private static final String MAP_X = "mapX";
    private static final String MAP_Y = "mapY";
    private static final String RADIUS = "radius";
    private static final String CONTENT_TYPE_ID = "contentTypeId";
    private static final String SERVICE_KEY = "serviceKey";

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    private static final String moblicOs = "win";
    private static final String mobileApp = "ria";
    /*
    관광타입(12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점) ID
     */
    private static final String contentTypeId = "12";




    public List<PublicMapItem> searchLocationBasedList1(double mapX, double mapY, double radius, String userId) throws IOException {

        log.info(" PUBLIC SERVICE KEY [{}]", serviceKey);

        // uri 카카오 검색 api의 기본 url을 사용하여 url 빌더를 생성
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(Constants.PUBLIC_DATA_API)
                .path(Constants.LOCATION_BASED_PATH)
                .queryParam(MOBILEOS, moblicOs)
                .queryParam(MOBILEAPP, mobileApp)
                .queryParam(TYPE, Constants.PUBLIC_API_TYPE)
                .queryParam(MAP_X, mapX)
                .queryParam(MAP_Y, mapY)
                .queryParam(RADIUS, radius)
                .queryParam(CONTENT_TYPE_ID, contentTypeId)
                .queryParam(SERVICE_KEY, serviceKey)
                .build(true); // 이중 에러가 나지 않도록 이미 인코딩이 되어 있음을 명시.

        log.info("uriComponent : {}", uriComponents);

        // request 요청 설정
        // api 호출, 생성된 url로 get 요청을 수행.
        HttpEntity<String> response = restClient.get().
                uri(uriComponents.toUri()).
                retrieve()
                .toEntity(String.class);

        log.info("Request headers: {}", response.getHeaders());
        // json 응답 파싱
        ObjectMapper mapper = new ObjectMapper();
        PublicMapItems publicMapItems = mapper.readValue(response.getBody(), PublicMapItems.class);

        log.info("placeList : {}", publicMapItems.getPublicMapItems());

        // output setting
        return publicMapItems.getPublicMapItems();
    }
}
