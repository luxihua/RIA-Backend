package com.example.ria.controller.map;

import com.example.ria.RiaApplication;
import com.example.ria.common.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RiaApplication.class)
@AutoConfigureMockMvc
@DisplayName("검색테스트")
class KakaoMapControllerTest  {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    @DisplayName("검색")
    void searchByKeywordTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        for(int i = 0 ; i < 5 ; i++)
            searchByKeyword("kakao", mockHttpSession);

        for(int i = 0 ; i < 3 ; i++)
            searchByKeyword("스타벅스", mockHttpSession);

    }

    void searchByKeyword(String keyword, MockHttpSession mockHttpSession) throws Exception {

        this.mockMvc.perform(get("/search")
                        .param("keyword", keyword)
                        .param("page", "1")
                        .param("size", "10")
                        .param("userId",Constants.SESSION_USER_ID )
                        .session(mockHttpSession))
                .andExpect(status().isOk());

    }
}