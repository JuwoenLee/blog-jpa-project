package com.estsoft.blogjpaproject.controller;

import com.estsoft.blogjpaproject.external.ExternalApiParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonParseTestController {
    private final ExternalApiParser parser;


    public JsonParseTestController(ExternalApiParser parser) {
        this.parser = parser;
    }

    @GetMapping("/api/test")
    public void test() {
//        List<Article> list = parser.parserAndSave();
//        service.save(list);
//
//        return "";

        parser.parserAndSave();
    }
}
