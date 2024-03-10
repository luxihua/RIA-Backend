package com.example.ria.dto.vo.kakao_format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.client.util.DateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Document {
    private String id;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("category_group_code")
    private String categoryGroupCode;

    @JsonProperty("category_group_name")
    private String categoryGroupName;
    private String phone;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("road_address_name")
    private String roadAddressName;
    private String x;
    private String y;
    @JsonProperty("place_url")
    private String placeUrl;
    private String distance;

    @JsonProperty("redirect_url")
    @JsonIgnoreProperties(ignoreUnknown=true)
    private String redirectUrl;

    // image api document
    @JsonProperty("collection")
    private String collection;

    @JsonProperty("thumbnail_url")
    private String thumbnail_url;

    @JsonProperty("image_url")
    private String image_url;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("display_sitename")
    private String display_sitename;

    @JsonProperty("doc_url")
    private String doc_url;

    @JsonProperty("datetime")
    private DateTime datetime;

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
