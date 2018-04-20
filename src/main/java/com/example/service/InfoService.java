package com.example.service;

import org.elasticsearch.action.main.MainResponse;

public interface InfoService {
    /**
     * 클러스터 정보를 얻는다.
     * @return
     * @throws Exception
     */
    public MainResponse getClusterInfo() throws Exception;
}
