package com.example.ria.controller;

import com.example.ria.service.GCSService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecommendPlaceControllerTests {
    private final GCSService gcsService = Mockito.mock(GCSService.class);
    private final RecommendPlaceController recommendPlaceController = new RecommendPlaceController(gcsService);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recommendPlaceController).build();

    @Test
    public void testUploadImage() throws Exception {
        // 정적으로 설정한 이미지 파일 생성
        MockMultipartFile file = new MockMultipartFile(
                "image",
                "hongdae.jpg",
                "image/jpeg",
                "image data".getBytes()
        );

        // 이미지 파일을 업로드하는 POST 요청 생성
        mockMvc.perform(multipart("/upload")
                        .file(file))
                .andExpect(status().isOk());
    }
}