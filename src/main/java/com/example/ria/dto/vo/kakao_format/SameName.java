package com.example.ria.dto.vo.kakao_format;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
public class SameName {
    private List<String> region;
    private String keyword;

    @JsonProperty("selected_region")
    private String selectedRegion;
}
