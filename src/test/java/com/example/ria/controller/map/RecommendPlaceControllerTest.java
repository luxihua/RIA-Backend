package com.example.ria.controller.map;

import com.example.ria.RiaApplication;
import com.example.ria.common.Constants;
import com.example.ria.common.GCSConstants;
import com.example.ria.service.GCSService;
import com.example.ria.service.VisionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RiaApplication.class)
@AutoConfigureMockMvc
@DisplayName("RecommendPlaceController 테스트")
class RecommendPlaceControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private GCSService gcsService;

    @MockBean
    private VisionService visionService;

   /* @Test
    @DisplayName("RecommendPlaceController test")
    void recommendPlaceTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        Resource imageResource = new ClassPathResource("/hongdae.jpg");

        MockMultipartFile image = new MockMultipartFile(
                "image",
                imageResource.getFilename(),
                "image/jpeg",
                imageResource.getInputStream().readAllBytes()
        );

        this.mockMvc.perform(multipart("/recommend_place")
                        .file(image)
                        .param("userId", Constants.SESSION_USER_ID)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }*/

    @Test
    @DisplayName("이미지 업로드 및 분석 테스트")
    void recommendPlaceSampleTest() throws Exception {

        Resource imageResource = new ClassPathResource("/hongdae.jpg");

        MockMultipartFile image = new MockMultipartFile(
                "image",
                imageResource.getFilename(),
                "image/jpeg",
                imageResource.getInputStream().readAllBytes()
        );

        String gcsPath = GCSConstants.IMAGE_PATH +
                "yZeKOQ6x8chba-r0OwsmZtUZEsGFGm-WGPAZyDd2b4mrdYypGDuIsavmRomoEo9XRsv0B3NuG8oP_GalDsfmpw.webp";

        String visionResult = "홍대 거리";

        when(gcsService.uploadFileToGCS(any(MultipartFile.class))).thenReturn(gcsPath);
        when(visionService.detectWebDetectionsGcs(anyString())).thenReturn(visionResult);

        this.mockMvc.perform(multipart("/recommend-place_Test")
                        .file(image))
                .andExpect(status().isOk())
                .andExpect(content().string(visionResult))
                .andDo(print());

    }

    @Test
    @DisplayName("단일 카카오 검색 Test")
    void searchByKeywordSampleTest() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        searchByKeyword("kakao", mockHttpSession);

        searchByKeyword("스타벅스", mockHttpSession);

    }

    @Test
    @DisplayName("다중 카카오 검색 test")
    void searchByKeywordTest2() throws Exception {

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute(Constants.SESSION_USER_ID, "USER00");

        for (int i = 0; i < 5; i++)
            searchByKeyword("kakao", mockHttpSession);

        for (int i = 0; i < 3; i++)
            searchByKeyword("스타벅스", mockHttpSession);

    }

    void searchByKeyword(String keyword, MockHttpSession mockHttpSession) throws Exception {

        this.mockMvc.perform(get("/search")
                        .param("keyword", keyword)
                        .param("page", "1")
                        .param("size", "10")
                        .param("userId", Constants.SESSION_USER_ID)
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(print());

    }
}