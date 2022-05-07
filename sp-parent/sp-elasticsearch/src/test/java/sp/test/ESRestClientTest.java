package sp.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.esdsl.DSLDoc;
import com.esdsl.DSLTemplete;

import java.io.IOException;

/**
 * es索引以及文档测试
 * @author lyq
 * @date 2022/1/15 8:46
 */

@Slf4j
public class ESRestClientTest {

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

    /**
     * client.indices()操作索引库
     */
    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(INDEX);
        request.source(DSLTemplete.CREATE_MAPPING, XContentType.JSON);

        if (!client.indices().exists(new GetIndexRequest("index_test"), RequestOptions.DEFAULT)) {
            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
            log.info("是否创建成功:{}", response.isAcknowledged());
        }
        log.info("已经存在");
    }


    /**
     * 全量更新：先删除在创建新文档，文档不存在则创建新文件
     */
    @Test
    public void createDoc() throws IOException {
        IndexRequest request = new IndexRequest(INDEX).id("3");
        DSLDoc dslDoc = new DSLDoc("好好学习天天向上","好好学习天天向上",1, "361234567@qq.com", new DSLDoc.Name("qiu", "li"));
        request.source(JSON.toJSONString(dslDoc), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 批量全量更新，或者批量创建文件
     */
    @Test
    public void bulkCreateDoc() throws IOException {
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 4; i++) {
            DSLDoc dslDoc = new DSLDoc("好好学习天天向上","少装不努力",i, "361234567@qq.com", new DSLDoc.Name("qiu", "li"));
            request.add(new IndexRequest(INDEX).id(i+"").source(JSON.toJSONString(dslDoc), XContentType.JSON));
        }
        client.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 局部更新
     */
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest(INDEX, "3");
        DSLDoc dslDoc = new DSLDoc("好好学习天天向上","少装不努力",1, "361234567@qq.com", new DSLDoc.Name("qiu", "li"));
        log.info("{}", JSON.toJSONString(dslDoc));
        request.doc(JSON.toJSONString(dslDoc), XContentType.JSON);
        client.update(request, RequestOptions.DEFAULT);
    }


    @Test
    public void getDoc() throws IOException {
        GetRequest request = new GetRequest(INDEX).id("3");
        String jsonStr = client.get(request, RequestOptions.DEFAULT).getSourceAsString();
        log.info("source   {}", jsonStr);
        //gson转换
        //DSLDoc dslDoc = new Gson().fromJson(jsonStr, DSLDoc.class);
        //fastjson转换
        DSLDoc dslDoc = JSON.parseObject(jsonStr, DSLDoc.class);
        log.info("{}", dslDoc);
    }

    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest(INDEX).id("1");
        client.delete(request, RequestOptions.DEFAULT);
    }


}
