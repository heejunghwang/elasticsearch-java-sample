package com.example.service;

import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public interface SearchService {
    /**
     * 검색 결과를 가져온다.
     * @param indexName
     * @param param
     * @return
     */
    SearchResponse searchIndex(String indexName, String param) throws IOException;
}
