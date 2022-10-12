package com.talentport.serverless.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TalentportObjectMapper {


    private static ObjectMapper objectMapper;

    public static final ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        }
        return objectMapper;

    }

    public final static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return getObjectMapper().convertValue(fromValue, toValueType);
    }


}
