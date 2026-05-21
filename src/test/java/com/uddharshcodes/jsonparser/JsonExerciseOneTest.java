package com.uddharshcodes.jsonparser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.uddharshcodes.jsonparser.pojo.ExerciseOneTestPOJO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonExerciseOneTest {
    private final String TestOne = """
            {
            "name": "Uddharsh Vasili",
            "rollNumber": 36,
            "passed": true
            }
            """;
    @Test
    public void parse() throws JsonProcessingException {
        JsonNode node = JsonExerciseOne.parse(TestOne);
//        assertTrue(node.get("passed").asBoolean());
        if(node.get("passed").asBoolean())
            System.out.println("Student has passed");
        else
            System.out.println("Student has not passed");
    }

    @Test
    public void fromJson() throws JsonProcessingException {
        JsonNode node = JsonExerciseOne.parse(TestOne);
        ExerciseOneTestPOJO pojo = JsonExerciseOne.fromJson(node, ExerciseOneTestPOJO.class);
//        assertEquals("Uddharsh Vasili", pojo.getName());
        System.out.println(pojo.getName());
        System.out.println(pojo.getRollNumber());
    }
}
