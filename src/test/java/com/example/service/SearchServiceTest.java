package com.example.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InfoServiceTest.class)
@ComponentScan(basePackages = "com.example")
public class SearchServiceTest {
    @Autowired
    SearchService searchService;

    @Test
    public void searchIndexTest() throws IOException {
        SearchResponse response = searchService.searchIndex("shopping", "dress");
        SearchHit[] hits = response.getHits().getHits();
    }
}
