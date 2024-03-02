# RIA Backend Structure


---------------------


## com.example.ria


---------------------

* common
    + Constants
    + GCSConstants

* config
    + GoogleStorageConfig
    + RestClientConfig


* controller
    + UserController(CRUD)
    + map(package)
        - RecommendPlaceController
            * Image 업로드 하기
                - Input : Image(MultiPartFile)
                - Output : uuid(String)
            * Image Web-Detection 결과 보여주기
                - Input : uuid(String)
                - Output : bestGuessLabel(String)
            * keyword로 장소 찾기
                - Input : Keyword(String)
                - Output : List<placeSearchDto>
        - PublicDataMapController
            * 공공데이터 전달 받기
                - Input : placeSearchDto
                - Output : List<placeSearchDto>

* domain

* dto
    + request(package)
        - PlaceSearchDto
    + response(package)
        - PlaceResultDto

    
* repository


* service
    + UserService(CRUD)
    + GCSService(Upload)
    + VisionService(Web Detection 사용)
    + map(package)
        - KaKaoMapService
            * keyword로 장소 찾기 서비스
                - Input : Keyword= best guess LABEL(String)
                - Output : List<placeSearchDto>
        - PulicDataMapService
            * placeSearchDto의 x, y 값을 활용하여 추천 지역 정보 전달하기
                - Input : placeSearchDto
                - Output : List <placeResultDto>
    + TranslateService
        - Google Translate API로 번역 결과 GET
        - Input : productId(String), targetLanguage(String), text(String)
        - Output : String



        
--------------------------

1. 제약 사항
    - 패키지는 소문자
    - 클래스는 단어별 첫 글자 대문자
    - 필드 및 메소드는 camel 기법 사용
    - 기능별 test 작성 코드 작성하기
    -  log : 자율 선택

                
