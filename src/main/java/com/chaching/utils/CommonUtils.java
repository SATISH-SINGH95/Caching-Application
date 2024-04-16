package com.chaching.utils;


import org.springframework.http.HttpStatus;

import com.chaching.exception.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

    public static String toJson(Object object){
        String jsonString = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error("toJson || failed while converting object into json {}", ex);
        }

        return jsonString;
    }

    public static <T> T toObject(String jsonString, Class<T> clz){
        T classObj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            classObj = mapper.readValue(jsonString, clz);
        } catch (JsonProcessingException ex) {
            log.error("toObject || failed while converting string into Class object {}", ex);
            throw new ValidationException(HttpStatus.BAD_REQUEST, "failed while converting string into Class object");
        }

        return classObj;
    }

}
