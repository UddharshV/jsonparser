package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class AuthorJsonParser {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    public static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    //convert given input json string to a JSON node and then into a POJO

    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    public static <T> T fromJson(JsonNode node, Class <T> MyClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, MyClass);
    }

    //convert created POJO back into a JSON node and then back into a String

    public static JsonNode toJson(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }
    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }
    public static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(node);
    }
}
