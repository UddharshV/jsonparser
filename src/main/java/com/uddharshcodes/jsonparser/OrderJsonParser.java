package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class OrderJsonParser {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        //configuration of ObjectMapper
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    //Convert given input String into a JSON node
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    //Convert intermediate JSON node into a POJO
    public static <T> T fromNode(JsonNode node, Class <T> MyClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, MyClass);
    }

    //Convert POJO back into an intermediate JSON node
    public static JsonNode toNode(Object obj){
        return objectMapper.valueToTree(obj);
    }

    //Convert intermediate JSON node back into a String format
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
