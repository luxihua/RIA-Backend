package com.example.ria.service.map;

import com.example.ria.common.Constants;
import com.example.ria.dto.vo.kakao_format.Document;
import com.example.ria.dto.vo.kakao_format.KakaoResponse;
import com.example.ria.dto.vo.PlaceList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Service
public class KakaoMapService {

    private static final String QUERY = "query";
    private static final String PAGE = "page";
    private static final String SIZE = "size";
    private static final String AUTHORIZATION = "Authorization";
    private static final String KAKAO_APP_KEY_PREFIX = "KakaoAK ";

    @Autowired
    private RestClient restClient;


    @Value("${kakao.app.key}")
    private String APP_KEY;


    public PlaceList searchPlaceByKeyword(String keyword, int page, int size, String userId) throws IOException {

        log.info(" KAKAO APP KEY [{}]", APP_KEY);

        // header setting
        // HttpHeaders 설정을 위한 Consumer
        Consumer<HttpHeaders> headersConsumer = headers -> headers.add(AUTHORIZATION, KAKAO_APP_KEY_PREFIX + APP_KEY);
        log.info("http header: {}", headersConsumer);
        // uri 카카오 검색 api의 기본 url을 사용하여 url 빌더를 생성
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(Constants.KAKAO_SEARCH_API)
                .path(Constants.KAKAO_SEARCH_PATH)
                .queryParam(QUERY, keyword)
                .queryParam(PAGE, page)
                .queryParam(SIZE, size)
                .build();

        log.info("uriComponent : {}", uriComponents);

        // request 요청 설정
        // api 호출, 생성된 url로 get 요청을 수행.
        HttpEntity<String> response = restClient.get().
                uri(uriComponents.toUriString()).
                headers(headersConsumer).
                retrieve()
                .toEntity(String.class);

        log.info("Request headers: {}", response.getHeaders());

        // json 응답 파싱
        ObjectMapper mapper = new ObjectMapper();
        KakaoResponse kakaoResponse = mapper.readValue(response.getBody(), KakaoResponse.class);


        // redirectUrl setting, 검색된 각 장소에 대해 리다이렉트 url을 설정함.
        kakaoResponse.getDocuments().forEach(place ->
                place.setRedirectUrl(Constants.MAP_REDIRECT_URL + place.getId())

        );

        kakaoResponse.getDocuments().forEach(place -> {
            log.info(place.getPlaceName());
            try {
                // place.getPlaceName()을 이용하여 이미지 검색 메서드 호출
                List<Document> images = searchImageByKeyword(place.getPlaceName(), 1, 1);

                // 이미지 검색 결과를 각 Document 객체에 저장
                for (Document image : images) {
                    place.setCollection(image.getCollection());
                    place.setThumbnail_url(image.getThumbnail_url());
                    place.setImage_url(image.getImage_url());
                    place.setWidth(image.getWidth());
                    place.setHeight(image.getHeight());
                    place.setDisplay_sitename(image.getDisplay_sitename());
                    place.setDoc_url(image.getDoc_url());
                    place.setDatetime(image.getDatetime());

                }
            } catch (IOException e) {
                log.error("Error occurred while searching images for place {}: {}", place.getPlaceName(), e.getMessage());
            }
        });

        // 페이징 계산
        int pageableCount = kakaoResponse.getMeta().getPageableCount();
        int totalPage = (pageableCount % size == 0) ? pageableCount / size : (pageableCount / size) + 1;

        log.debug("placeList : {}, placeListCounts ={}", kakaoResponse.getDocuments(),kakaoResponse.getDocuments().size());
        // output setting

        return new PlaceList(page, totalPage, size, pageableCount, kakaoResponse.getDocuments());

    }

    public List<Document> searchImageByKeyword(String keyword, int page, int size) throws IOException {
        log.info("KAKAO APP KEY [{}]", APP_KEY);

        // HttpHeaders 설정을 위한 Consumer
        Consumer<HttpHeaders> headersConsumer = headers -> headers.add(AUTHORIZATION, KAKAO_APP_KEY_PREFIX + APP_KEY);
        log.info("HTTP Header: {}", headersConsumer);

        // Kakao 이미지 검색 API의 기본 URL을 사용하여 URL 빌더를 생성
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(Constants.KAKAO_SEARCH_API)
                .path(Constants.KAKAO_IMAGE_PATH)
                .queryParam(QUERY, keyword)
                .queryParam(PAGE, page)
                .queryParam(SIZE, size)
                .build();

        log.info("URI Component: {}", uriComponents);

        // API 호출 및 응답 수신
        ResponseEntity<String> responseEntity = restClient.get()
                .uri(uriComponents.toUriString())
                .headers(headersConsumer)
                .retrieve()
                .toEntity(String.class);

        log.info("Response headers: {}", responseEntity.getHeaders());

        // JSON 응답 파싱
        ObjectMapper mapper = new ObjectMapper();
        KakaoResponse kakaoResponse = mapper.readValue(responseEntity.getBody(), KakaoResponse.class);

        // 이미지 검색 결과인 Document 목록 반환
        List<Document> images = kakaoResponse.getDocuments();

        // 이미지 검색 결과의 각 Document 객체에 image search 결과 값을 저장
        for (Document image : images) {
            image.setCollection(image.getCollection());
            image.setThumbnail_url(image.getThumbnail_url());
            image.setImage_url(image.getImage_url());
            image.setWidth(image.getWidth());
            image.setHeight(image.getHeight());
            image.setDisplay_sitename(image.getDisplay_sitename());
            image.setDoc_url(image.getDoc_url());
            image.setDatetime(image.getDatetime());

        }

        return images;


    }



}
