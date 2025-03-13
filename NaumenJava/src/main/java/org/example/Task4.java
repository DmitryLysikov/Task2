package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Task4 {
    public static void UrlGet() {
        try {
            URL url = new URL("https://httpbin.org/headers");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(conn.getInputStream());

            JsonNode headersNode = rootNode.get("headers");

            Iterator<String> fieldNamesIterator = headersNode.fieldNames();
            List<String> headersList = new ArrayList<>();
            while (fieldNamesIterator.hasNext()) {
                headersList.add(fieldNamesIterator.next());
            }

            System.out.println("Заголовки запроса: " + String.join(", ", headersList));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
