package com.example.ria.controller.map;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PublicDataMapController.class)
@AutoConfigureMockMvc
class PublicDataMapControllerTest {
    @Autowired
    private MockMvc mvc;


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



}