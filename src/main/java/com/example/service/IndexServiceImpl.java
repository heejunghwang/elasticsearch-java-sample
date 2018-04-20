package com.example.service;

import com.example.config.ElasticsearchConfig;
import com.example.model.Review;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class IndexServiceImpl implements IndexService{

    @Autowired
    ElasticsearchConfig elasticsearchConfig;

    /**
     * 인덱스를 삭제한다.
     *
     * @param indexName
     * @return
     */
    @Override
    public DeleteIndexResponse deleteIndex(String indexName) throws IOException {
        RestHighLevelClient client = elasticsearchConfig.getRestClient();
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        DeleteIndexResponse deleteIndexResponse = client.indices().delete(request);
        client.close();
        return deleteIndexResponse;
    }

    /**
     * 인덱스를 생성한다.
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    @Override
    public CreateIndexResponse createIndex(String indexName, String typeName) throws IOException {
        RestHighLevelClient client = elasticsearchConfig.getRestClient();

        //인덱스 생성
        CreateIndexRequest request = new CreateIndexRequest(indexName);

        //인덱스 설정
        //인덱스에서 사용할 분석기 설정
        XContentBuilder settingsBuilder = XContentFactory.jsonBuilder()
                .startObject()
                    .startObject("analysis")
                        .startObject("filter")
                            .startObject("synonym_filter")
                                .field("type","synonym")
                                .field("filter", "lowercase")
                                .field("synonyms_path","synonym.txt")

                            .endObject()
                        .endObject()
                        .startObject("analyzer")
                            .startObject("my_analyzer")
                                .field("tokenizer", "whitespace")
                                .field("filter", "synonym_filter")
                            .endObject()
                        .endObject()
                    .endObject()
                .endObject();

        request.settings(settingsBuilder);

        //필드 property 설정
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                //부서명
                builder.startObject("departmentName");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();
                //제목
                builder.startObject("title");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "my_analyzer");
                    builder.field("search_analyzer", "my_analyzer");
                }
                builder.endObject();

                //리뷰
                builder.startObject("reviewText");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "my_analyzer");
                    builder.field("search_analyzer", "my_analyzer");
                }
                builder.endObject();

            }
            builder.endObject();
        }
        builder.endObject();

        request.mapping(typeName, builder);

        //인덱스 생성 요청
        CreateIndexResponse createIndexResponse = client.indices().create(request);
        client.close();
        return createIndexResponse;
    }

    /**
     * Bulk 색인을 한다.
     * @param indexName
     * @param typeName
     * @return
     */
    @Override
    public BulkResponse bulkIndex(String indexName, String typeName, List<Review> reviewList) throws IOException{
        RestHighLevelClient client = elasticsearchConfig.getRestClient();
        BulkRequest request = new BulkRequest();

        Optional.ofNullable(reviewList).ifPresent(o -> {
            reviewList.stream()
                    .forEach(review -> {
                        try{
                            XContentBuilder xContentBuilder = buildReviewField(review);
                            request.add(new IndexRequest(indexName, typeName)
                                    .source(xContentBuilder));
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    });
            }
        );

        BulkResponse bulkResponse = client.bulk(request);
        client.close();
        return bulkResponse;

    }

    private XContentBuilder buildReviewField(Review review) throws IOException{
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("departmentName", review.getDepartmentName());
            builder.field("title", review.getTitle());
            builder.field("reviewText", review.getReviewText());
        }
        builder.endObject();
        return builder;
    }
}
