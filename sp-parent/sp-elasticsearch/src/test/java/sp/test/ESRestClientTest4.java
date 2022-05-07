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
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * es自定义分词器 利用中文分词器(ik)得出的词条在进一步做拼音分词(pinyin)----利用completion类型字段做自动补全
 * @author lyq
 * @date 2022/1/15 8:46
 */
@Slf4j
public class ESRestClientTest4 {

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
    public void suggestionQuery() throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        /**
         * 限定查询不起作用
         * */
        //request.source().query(QueryBuilders.matchAllQuery());
        //request.source().query(QueryBuilders.matchQuery("info", "nihao")).size(0);
        //request.source().query(QueryBuilders.multiMatchQuery("好好学习","info","info1"));

        CompletionSuggestionBuilder suggestionBuilder = SuggestBuilders.completionSuggestion("buquan")
                .prefix("s")
                .skipDuplicates(true)
                .size(10);

        request.source().suggest(new SuggestBuilder().addSuggestion("buquan_sugg",suggestionBuilder));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);


        CompletionSuggestion suggestion = response.getSuggest().getSuggestion("buquan_sugg");
        for (CompletionSuggestion.Entry.Option option : suggestion.getOptions()) {
            log.debug("test:{}", option.getText().string());
        }
    }
}
