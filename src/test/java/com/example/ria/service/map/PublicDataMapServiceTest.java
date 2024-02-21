package com.example.ria.service.map;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import com.example.ria.dto.vo.PublicMapItems;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PublicDataMapServiceTest {
    @InjectMocks
    private PublicDataMapService publicDataMapService; // Mock 객체 주입

    @Test
    void parsingJsonObject() {

        String json = "{\"response\": {\"header\":{\"resultCode\":\"0000\",\"resultMsg\":\"OK\"},\"body\": {\"items\": {\"item\":[{\"addr1\":\"서울특별시 마포구 서교동\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030600\",\"contentid\":\"3082405\",\"contenttypeid\":\"12\",\"createdtime\":\"20231207141501\",\"dist\":\"213.601759272119\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/99/3082399_image2_1.JPG\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/99/3082399_image3_1.JPG\",\"cpyrhtDivCd\":\"Type3\",\"mapx\":\"126.9237957624\",\"mapy\":\"37.5555489875\",\"mlevel\":\"6\",\"modifiedtime\":\"20240117104018\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"홍대걷고싶은거리\"},{\"addr1\":\"서울특별시 마포구 동교동\",\"addr2\":\"176-13\",\"areacode\":\"1\",\"booktour\":\"\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030400\",\"contentid\":\"2805902\",\"contenttypeid\":\"12\",\"createdtime\":\"20220128100721\",\"dist\":\"305.04885021496926\",\"firstimage\":\"\",\"firstimage2\":\"\",\"cpyrhtDivCd\":\"\",\"mapx\":\"126.9273148289\",\"mapy\":\"37.5575543730\",\"mlevel\":\"6\",\"modifiedtime\":\"20230523152213\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"레치키치 스튜디오\"},{\"addr1\":\"서울특별시 마포구 서교동 345-16\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"\",\"cat1\":\"A05\",\"cat2\":\"A0502\",\"cat3\":\"A05020900\",\"contentid\":\"2946108\",\"contenttypeid\":\"12\",\"createdtime\":\"20230128200904\",\"dist\":\"308.8552054333894\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/74/2947474_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/74/2947474_image3_1.jpg\",\"cpyrhtDivCd\":\"Type1\",\"mapx\":\"126.9233847123\",\"mapy\":\"37.5547185211\",\"mlevel\":\"6\",\"modifiedtime\":\"20231211145543\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"반지캠퍼스카페\"},{\"addr1\":\"서울특별시 마포구 홍익로 20\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030400\",\"contentid\":\"781031\",\"contenttypeid\":\"12\",\"createdtime\":\"20090820010620\",\"dist\":\"358.5549128195101\",\"firstimage\":\"\",\"firstimage2\":\"\",\"cpyrhtDivCd\":\"\",\"mapx\":\"126.9227542239\",\"mapy\":\"37.5543713280\",\"mlevel\":\"6\",\"modifiedtime\":\"20231113134950\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"홍대\"},{\"addr1\":\"서울특별시 마포구 양화로 지하 188\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"0\",\"cat1\":\"A04\",\"cat2\":\"A0401\",\"cat3\":\"A04010600\",\"contentid\":\"2390957\",\"contenttypeid\":\"12\",\"createdtime\":\"20160701031215\",\"dist\":\"375.81848655106717\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/29/2391429_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/29/2391429_image2_1.jpg\",\"cpyrhtDivCd\":\"Type3\",\"mapx\":\"126.9279647971\",\"mapy\":\"37.5565850529\",\"mlevel\":\"6\",\"modifiedtime\":\"20230921171358\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"라온트래블스토리지\"},{\"addr1\":\"서울특별시 마포구 동교동 179-38\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"\",\"cat1\":\"A04\",\"cat2\":\"A0401\",\"cat3\":\"A04010700\",\"contentid\":\"2946238\",\"contenttypeid\":\"12\",\"createdtime\":\"20230129235031\",\"dist\":\"410.9626792270409\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/43/2947643_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/43/2947643_image3_1.jpg\",\"cpyrhtDivCd\":\"Type1\",\"mapx\":\"126.9285063921\",\"mapy\":\"37.5573821924\",\"mlevel\":\"6\",\"modifiedtime\":\"20231207150936\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"도자기카페 줄\"},{\"addr1\":\"서울특별시 마포구 와우산로37길 35\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030600\",\"contentid\":\"2500229\",\"contenttypeid\":\"12\",\"createdtime\":\"20170718230034\",\"dist\":\"470.5686963682182\",\"firstimage\":\"\",\"firstimage2\":\"\",\"cpyrhtDivCd\":\"\",\"mapx\":\"126.9290369242\",\"mapy\":\"37.5564989709\",\"mlevel\":\"6\",\"modifiedtime\":\"20231218170713\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"경의선책거리\"},{\"addr1\":\"서울특별시 마포구 서교동\",\"addr2\":\"357-1\",\"areacode\":\"1\",\"booktour\":\"\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030400\",\"contentid\":\"2852296\",\"contenttypeid\":\"12\",\"createdtime\":\"20220908160614\",\"dist\":\"475.9549891711005\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/99/2849799_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/99/2849799_image3_1.jpg\",\"cpyrhtDivCd\":\"Type3\",\"mapx\":\"126.9217154529\",\"mapy\":\"37.5535519602\",\"mlevel\":\"6\",\"modifiedtime\":\"20240103171254\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"코코넛박스\"},{\"addr1\":\"서울특별시 마포구 성미산로28길 18\",\"addr2\":\"\",\"areacode\":\"1\",\"booktour\":\"0\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030600\",\"contentid\":\"2500201\",\"contenttypeid\":\"12\",\"createdtime\":\"20170718194621\",\"dist\":\"536.5316619353456\",\"firstimage\":\"\",\"firstimage2\":\"\",\"cpyrhtDivCd\":\"\",\"mapx\":\"126.9236205874\",\"mapy\":\"37.5623009774\",\"mlevel\":\"6\",\"modifiedtime\":\"20230515111837\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"연남동 공방거리\"},{\"addr1\":\"서울특별시 마포구 와우산로21길 20-5 (서교동)\",\"addr2\":\"홍대프라자 3층\",\"areacode\":\"1\",\"booktour\":\"\",\"cat1\":\"A02\",\"cat2\":\"A0203\",\"cat3\":\"A02030400\",\"contentid\":\"3082398\",\"contenttypeid\":\"12\",\"createdtime\":\"20231207142152\",\"dist\":\"541.6161044827121\",\"firstimage\":\"http://tong.visitkorea.or.kr/cms/resource/95/3082395_image2_1.jpg\",\"firstimage2\":\"http://tong.visitkorea.or.kr/cms/resource/95/3082395_image3_1.jpg\",\"cpyrhtDivCd\":\"Type3\",\"mapx\":\"126.9226848333\",\"mapy\":\"37.5526881889\",\"mlevel\":\"6\",\"modifiedtime\":\"20240117103752\",\"sigungucode\":\"13\",\"tel\":\"\",\"title\":\"엑스이스케이프 홍대놀이터점\"}]},\"numOfRows\":10,\"pageNo\":1,\"totalCount\":14}}}\n";

        // 테스트 실행
        PublicMapItems result = publicDataMapService.parsingJsonObject(json);

        // 결과 확인
        Assertions.assertThat(result).isNotNull();


    }

}