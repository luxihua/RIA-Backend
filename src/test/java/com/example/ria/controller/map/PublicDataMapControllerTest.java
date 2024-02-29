package com.example.ria.controller.map;


import com.example.ria.RiaApplication;
import com.example.ria.common.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RiaApplication.class)
@AutoConfigureMockMvc
@DisplayName("한국 관광 공사 api test")
class PublicDataMapControllerTest {
    @Autowired
    private MockMvc mvc;
    // 테스트에 필요한 입력값 설정
    private String mapX = "126.923885";
    private String mapY = "37.557472";
    private String radius = "1000";

    @Test
    @DisplayName("Open API 통신 테스트")
    public void callOpenApi() throws Exception {
        String mobileOS = "WIN";
        String mobileApp = "RIA";
        String mapX = "126.923885"; // 홍대 좌표를 기준으로 검색함.
        String mapY = "37.557472";
        String radius = "1000";
        String contentTypeId = "12";


        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("MobileOS", mobileOS);
        param.add("MobileApp", mobileApp);
        param.add("mapX", mapX);
        param.add("mapY", mapY);
        param.add("radius", radius);
        param.add("contentTypeId", contentTypeId);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/locationBasedList1").params(param))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print(System.out));
    }

    @Test
    @DisplayName("위치기반장소 검색")
    void searchByKeywordTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        this.mvc.perform(get("/api/searchPlace")
                        .param("mapX", mapX)
                        .param("mapY", mapY)
                        .param("radius", radius)
                        .param("userId", Constants.SESSION_USER_ID)
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print(System.out));


    }


}