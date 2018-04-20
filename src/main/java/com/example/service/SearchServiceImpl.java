package com.example.service;

import com.example.config.ElasticsearchConfig;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    ElasticsearchConfig elasticsearchConfig;

    /**
     * 검색 결과를 가져온다.
     *
     * @param indexName
     * @param param
     * @return
     */
    @Override
    public SearchResponse searchIndex(String indexName, String param) throws IOException {
        RestHighLevelClient client = elasticsearchConfig.getRestClient();
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", param).boost(1.3f))
                .should(QueryBuilders.matchPhraseQuery("departmentName",param))
                .should(QueryBuilders.matchPhraseQuery("reviewText",param));

        searchRequest.indices(indexName);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        return searchResponse;
    }
}
