package sp.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * es聚合
 * @author lyq
 * @date 2022/1/15 8:46
 */
@Slf4j
public class ESRestClientTest3 {

    private final String INDEX = "index_test";

    private RestHighLevelClient client;

    @BeforeEach
    public void init() {
        log.info("初始化");
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.0.203:9200")));
    }

    @AfterEach
    public void distroy() throws IOException {
        this.client.close();
        log.info("销毁");
    }


    public void printResult(SearchRequest request) throws IOException {
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        long value = response.getHits().getTotalHits().value;
        Iterator<SearchHit> iterator = response.getHits().iterator();
        while (iterator.hasNext()) {
            log.debug("{}",iterator.next().getSourceAsString());
        }

    }

    @Test
    public void aggregationQuery() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        /**
         * 限定查询
         * */
        //request.source().query(QueryBuilders.matchAllQuery());
        request.source().query(QueryBuilders.matchQuery("info", "好好学习"));
        //request.source().query(QueryBuilders.multiMatchQuery("好好学习","info","info1"));

        SearchSourceBuilder sourceBuilder = request.source().size(0);//不需要任何文档结果;
        sourceBuilder.aggregation(
                     AggregationBuilders.terms("aggNumber")//聚合字段
                     .field("number")
                    .size(10)//展现条数
                    .order(BucketOrder.count(false))//是否升序
                );
        sourceBuilder.aggregation(
                AggregationBuilders.terms("aggNumber1")//聚合字段
                        .field("number")
                        .size(10)//展现条数
                        .order(BucketOrder.count(false))//是否升序
        );

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();


        log.info("size:{}",aggregations.asList().size());

//        ParsedTerms parsedTerms = aggregations.get("aggNumber");
//        List<? extends Terms.Bucket> buckets = parsedTerms.getBuckets();
//        for (Terms.Bucket b: buckets) {
//            log.debug("key:{},count:{}", b.getKeyAsString(),b.getDocCount());
//        }
        List<Aggregation> aggList = aggregations.asList();
        for (Aggregation agg : aggList){
            ParsedTerms parsedTerms = (ParsedLongTerms) agg;
            List<? extends Terms.Bucket> buckets = parsedTerms.getBuckets();
            for (Terms.Bucket b: buckets) {
                log.debug("key:{},count:{}", b.getKeyAsString(),b.getDocCount());
            }
        }

    }
}
