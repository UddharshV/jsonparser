package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.uddharshcodes.jsonparser.pojo.DatePOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

public class DateJsonTest {
    private static final String dateTest = """
    {
        "date": "2026-05-20",
        "day": "Wednesday"
    }
""";

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = DateJsonParser.parse(dateTest);
        Assertions.assertEquals("2026-05-20", node.get("date").asText());
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = DateJsonParser.parse(dateTest);
        DatePOJO pojo = DateJsonParser.fromJson(node, DatePOJO.class);
        Assertions.assertEquals("2026-05-20", pojo.getDate().toString());
    }

    @Test
    void toJson() throws JsonProcessingException, ParseException {
        DatePOJO pojo = new DatePOJO();
        pojo.setDate(LocalDate.of(2000,03,19));
        pojo.setDay("Sunday");
        JsonNode node = DateJsonParser.toJson(pojo);
        System.out.println(node.get("date").asText());
    }

    @Test
    void generateString() throws ParseException, JsonProcessingException {
        DatePOJO pojo = new DatePOJO();
        pojo.setDate(LocalDate.of(2000, 3, 19));
        pojo.setDay("Sunday");
        JsonNode node = DateJsonParser.toJson(pojo);
        Assertions.assertNotNull(DateJsonParser.generateString(node, true));
    }
}
