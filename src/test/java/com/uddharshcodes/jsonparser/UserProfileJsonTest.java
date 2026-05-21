package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.uddharshcodes.jsonparser.pojo.UserProfilePOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserProfileJsonTest {
    private String testCase01 = """
            {
              "name": "Peter Parker",
              "age": 21,
              "email": "peter@dailybugle.com",
              "nickname": "Spiderman",
              "city": "New York"
            }
            """;

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = UserProfileParser.parse(testCase01);
        Assertions.assertEquals("Peter Parker", node.get("name").asText());
    }

    @Test
    void fromNode() throws JsonProcessingException {
        JsonNode node = UserProfileParser.parse(testCase01);
        UserProfilePOJO pojo = UserProfileParser.fromNode(node, UserProfilePOJO.class);
        Assertions.assertEquals(21, pojo.getAge());
    }

    @Test
    void toNode() {
        UserProfilePOJO pojo = new UserProfilePOJO();
        pojo.setName("Uddharsh Vasili");
        pojo.setAge(26);
        pojo.setEmail("uddharsh_vasili@outlook.com");
        JsonNode node = UserProfileParser.toNode(pojo);
        Assertions.assertEquals("uddharsh_vasili@outlook.com", node.get("email").asText());
    }

    @Test
    void prettyPrint() throws JsonProcessingException {
        UserProfilePOJO pojo = new UserProfilePOJO();
        pojo.setName("Uddharsh Vasili");
        pojo.setAge(26);
        pojo.setEmail("uddharsh_vasili@outlook.com");
        JsonNode node = UserProfileParser.toNode(pojo);
        System.out.println(UserProfileParser.prettyPrint(node));
    }
}
