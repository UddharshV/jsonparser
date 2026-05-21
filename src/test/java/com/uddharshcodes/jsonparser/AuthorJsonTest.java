package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.uddharshcodes.jsonparser.pojo.AuthorPOJO;
import com.uddharshcodes.jsonparser.pojo.BookPOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorJsonTest {
    private String authorBookScenario = """
            {
              "authorName": "Stephen King",
              "books": [
                {
                  "title": "You Like It Darker",
                  "inPrint": true,
                  "publishDate": "2024-05-21"
                },
                {
                  "title": "The Shining",
                  "inPrint": true,
                  "publishDate": "1977-01-28"
                }
              ]
            }
            """;

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = AuthorJsonParser.parse(authorBookScenario);
        Assertions.assertEquals("Stephen King", node.get("authorName").asText());
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = AuthorJsonParser.parse(authorBookScenario);
        AuthorPOJO pojo = AuthorJsonParser.fromJson(node, AuthorPOJO.class);
        Assertions.assertEquals("You Like It Darker", pojo.getBooks().get(0).getTitle());
    }

    @Test
    void toJson() {
        AuthorPOJO authPojo = new AuthorPOJO();
        authPojo.setAuthorName("Uddharsh Vasili");
        List <BookPOJO> books = new ArrayList<>();

        BookPOJO bookPojo = new BookPOJO();

        bookPojo.setTitle("Cloud Effects on Edge Computing");
        bookPojo.setInPrint(false);
        bookPojo.setPublishDate(LocalDate.of(2022,05,13));

        books.add(bookPojo);

        authPojo.setBooks(books);

        JsonNode node = AuthorJsonParser.toJson(authPojo);
        Assertions.assertEquals("Cloud Effects on Edge Computing", node.get("books").get(0).get("title").asText());
    }

    @Test
    void generateString() throws JsonProcessingException {
        AuthorPOJO authPojo = new AuthorPOJO();
        authPojo.setAuthorName("Uddharsh Vasili");
        List <BookPOJO> books = new ArrayList<>();

        BookPOJO bookPojo = new BookPOJO();

        bookPojo.setTitle("Cloud Effects on Edge Computing");
        bookPojo.setInPrint(false);
        bookPojo.setPublishDate(LocalDate.of(2022,05,13));

        books.add(bookPojo);

        authPojo.setBooks(books);

        JsonNode node = AuthorJsonParser.toJson(authPojo);
        Assertions.assertNotNull(AuthorJsonParser.generateString(node, true));
    }
}
