package com.example.ria.dto.vo.kakao_format;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Meta {
    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("same_name")
    private SameName sameName;

    public boolean isEnd() {
        return isEnd;
    }
}
