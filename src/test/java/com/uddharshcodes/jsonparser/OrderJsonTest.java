package com.uddharshcodes.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.uddharshcodes.jsonparser.pojo.CustomerPOJO;
import com.uddharshcodes.jsonparser.pojo.ItemPojo;
import com.uddharshcodes.jsonparser.pojo.OrderPOJO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderJsonTest {
    private final String orderTestCase01 = """
            {
              "orderId": "ORD-101",
              "customer": {
                "name": "Peter Parker",
                "email": "peter@dailybugle.com",
                "loyaltyTier": "gold"
              },
              "items": [
                {
                  "name": "Notebook",
                  "quantity": 2
                },
                {
                  "name": "Pen",
                  "quantity": 3
                }
              ],
              "internalNotes": "rush delivery"
            }
            """;

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = OrderJsonParser.parse(orderTestCase01);
        Assertions.assertEquals("Peter Parker", node.get("customer").get("name").asText());
    }

    @Test
    void fromNode() throws JsonProcessingException {
        JsonNode node = OrderJsonParser.parse(orderTestCase01);
        OrderPOJO pojo = OrderJsonParser.fromNode(node, OrderPOJO.class);
        Assertions.assertEquals("Notebook", pojo.getItems().getFirst().getName());
    }

    @Test
    void toNode() {
        ItemPojo itemPojo1 = new ItemPojo();
        itemPojo1.setName("Diet Coke");
        itemPojo1.setQuantity(12);
        ItemPojo itemPojo2 = new ItemPojo();
        itemPojo2.setName("Eggs");
        itemPojo2.setQuantity(36);

        List<ItemPojo> items = new ArrayList<>();
        items.add(itemPojo1);
        items.add(itemPojo2);

        CustomerPOJO customerPojo = new CustomerPOJO();
        customerPojo.setName("Uddharsh Vasili");
        customerPojo.setEmail("uddharsh_vasili@outlook.com");

        OrderPOJO orderPojo = new OrderPOJO();
        orderPojo.setOrderId("999");
        orderPojo.setCustomer(customerPojo);
        orderPojo.setItems(items);

        JsonNode node = OrderJsonParser.toNode(orderPojo);
        Assertions.assertEquals(12, node.get("items").get(0).get("quantity").asInt());
    }

    @Test
    void prettyPrint() throws JsonProcessingException {
        ItemPojo itemPojo1 = new ItemPojo();
        itemPojo1.setName("Diet Coke");
        itemPojo1.setQuantity(12);
        ItemPojo itemPojo2 = new ItemPojo();
        itemPojo2.setName("Eggs");
        itemPojo2.setQuantity(36);

        List<ItemPojo> items = new ArrayList<>();
        items.add(itemPojo1);
        items.add(itemPojo2);

        CustomerPOJO customerPojo = new CustomerPOJO();
        customerPojo.setName("Uddharsh Vasili");
        customerPojo.setEmail("uddharsh_vasili@outlook.com");

        OrderPOJO orderPojo = new OrderPOJO();
        orderPojo.setOrderId("999");
        orderPojo.setCustomer(customerPojo);
        orderPojo.setItems(items);

        JsonNode node = OrderJsonParser.toNode(orderPojo);
        System.out.println(OrderJsonParser.prettyPrint(node));
    }
}
