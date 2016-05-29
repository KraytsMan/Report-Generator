package com.softjourn.report.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.io.IOException;
import java.util.Map;

public class WordsGenerator {

    private WebResource webResource;
    private ObjectMapper mapper;

    public WordsGenerator(String link) {
        Client client = Client.create();
        webResource = client.resource(link);
        mapper = new ObjectMapper();
    }

    public String generateWord() throws IOException {
        String response = webResource.get(String.class);
        Map<String, Object> map = mapper.readValue(response, Map.class);
        return (String) map.get("word");
    }

}
