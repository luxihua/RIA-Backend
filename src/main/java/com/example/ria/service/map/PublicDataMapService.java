package com.example.ria.service.map;

import com.example.ria.dto.vo.PublicMapItems;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class PublicDataMapService {
    public PublicMapItems parsingJsonObject(String json) {
        PublicMapItems items = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            items = mapper.readValue(json, PublicMapItems.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
