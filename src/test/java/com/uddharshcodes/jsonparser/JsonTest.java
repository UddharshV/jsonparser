package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.uddharshcodes.jsonparser.pojo.SimpleTestCaseJsonPOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonTest {
    private final String simpleTestCaseJsonSource = "{\"name\": \"Uddharsh Vasili\", \"author\": \"Uddharsh\"}";

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        Assertions.assertEquals("Uddharsh Vasili", node.get("name").asText());
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        Assertions.assertEquals("Uddharsh Vasili", pojo.getName());
    }

    @Test
    void toJson() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setName("Peter Parker");
        JsonNode node = Json.toJson(pojo);
        Assertions.assertEquals("Peter Parker", node.get("name").asText());
    }

    @Test
    void stringify() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setName("Peter Parker");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.stringify(node));

    }

    @Test
    void prettyPrint() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setName("Peter Parker");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.prettyPrint(node));
    }
}
