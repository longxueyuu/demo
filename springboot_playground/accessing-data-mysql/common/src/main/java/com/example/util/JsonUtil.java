package com.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String toJsonStr(Object obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
