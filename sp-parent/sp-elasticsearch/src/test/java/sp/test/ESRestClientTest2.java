package sp.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import com.esdsl.DSLDoc;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * es条件查询测试
 * @author lyq
 * @date 2022/1/15 8:46
 */

@Slf4j
public class ESRestClientTest2 {

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

    @Test
    public void getDocById() throws IOException {
        GetRequest request = new GetRequest(INDEX).id("3");
        String jsonStr = client.get(request, RequestOptions.DEFAULT).getSourceAsString();
        log.info("source   {}", jsonStr);
        //gson转换
        //DSLDoc dslDoc = new Gson().fromJson(jsonStr, DSLDoc.class);
        //fastjson转换
        DSLDoc dslDoc = JSON.parseObject(jsonStr, DSLDoc.class);
        log.info("{}", dslDoc);
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
    public void query() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);

        //request.source().query(QueryBuilders.matchAllQuery());
        //request.source().query(QueryBuilders.matchQuery("info", "好好学习"));
        request.source().query(QueryBuilders.multiMatchQuery("好好学习","info","info1"));

        printResult(request);
    }



    @Test
    public void boolQuery() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.multiMatchQuery("好好学习","info","info1"));
        boolQuery.mustNot(QueryBuilders.matchQuery("number", "1"));

        request.source().query(boolQuery);

        printResult(request);
    }

    @Test
    public void sortAndPageQuery() throws IOException {
        int currentPage = 1,size = 5;
        int form = (currentPage-1)*size;
        if(form>10000){
            throw new RuntimeException("超出最大分页");
        }

        SearchRequest request = new SearchRequest(INDEX);
        request.source().query(QueryBuilders.matchAllQuery());
        request.source().sort("number", SortOrder.ASC);
        request.source().from(form).size(size);

        printResult(request);
    }

    @Test
    public void highLightQuery() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        request.source().query(QueryBuilders.matchQuery("info", "好好学习"));
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("info")
                .requireFieldMatch(false)
                .preTags("<H1>")
                .postTags("</H1>");
        request.source().highlighter(highlightBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Iterator<SearchHit> iterator = response.getHits().iterator();
        while (iterator.hasNext()) {
            SearchHit s = iterator.next();
            String jsonDoc = s.getSourceAsString();
            DSLDoc dslDoc = JSON.parseObject(jsonDoc, DSLDoc.class);
            Map<String, HighlightField> highlightFields = s.getHighlightFields();
            if(!CollectionUtils.isEmpty(highlightFields)){
                HighlightField highlightField = highlightFields.get("info");
                if(highlightField!=null){
                    String infoStr = highlightField.getFragments()[0].string();
                    dslDoc.setInfo(infoStr);
                }
            }
            log.debug("{}",dslDoc);
        }
    }


}
