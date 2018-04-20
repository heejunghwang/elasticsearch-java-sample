package com.example.service;

import com.example.config.ElasticsearchConfig;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    ElasticsearchConfig elasticsearchConfig;

    @Override
    public MainResponse getClusterInfo() throws Exception {
        RestHighLevelClient client = elasticsearchConfig.getRestClient();
        MainResponse mainResponse = client.info();
        client.close();
        return mainResponse;

    }
}
