package com.example.service;

import com.example.model.Review;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkResponse;

import java.io.IOException;
import java.util.List;

public interface IndexService {
    /**
     * 인덱스를 삭제한다.
     * @param indexName
     * @return
     */
    DeleteIndexResponse deleteIndex(String indexName) throws IOException;

    /**
     * 인덱스를 생성한다.
     * @param indexName
     * @return
     * @throws IOException
     */
    CreateIndexResponse createIndex(String indexName, String typeName) throws IOException;

    /**
     * Bulk 색인을 한다.
     * @param indexName
     * @param type
     * @return
     */
    BulkResponse bulkIndex(String indexName, String typeName, List<Review> reviewList) throws IOException;

}
