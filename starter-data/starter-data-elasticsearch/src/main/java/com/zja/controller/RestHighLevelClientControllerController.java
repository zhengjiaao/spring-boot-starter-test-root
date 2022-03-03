/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-01 15:53
 * @Since:
 */
package com.zja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zja.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 参考地址：https://blog.csdn.net/CSDN877425287/article/details/119858002
 */
@Slf4j
@RestController
@RequestMapping("/rest/client")
public class RestHighLevelClientControllerController {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @ApiOperation(value = "创建索引")
    @PostMapping(value = "/index/create")
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("user");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        log.info("索引操作===>" + acknowledged);
    }

    @ApiOperation(value = "搜索索引")
    @GetMapping(value = "/index/get")
    public void selectIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);
        Map<String, List<AliasMetadata>> aliases = getIndexResponse.getAliases();
        Map<String, MappingMetadata> mappings = getIndexResponse.getMappings();
        Map<String, Settings> settings = getIndexResponse.getSettings();
        log.info("aliases===>{}",aliases);
        log.info("mappings===>{}",mappings);
        log.info("settings===>{}",settings);
    }

    @ApiOperation(value = "删除索引")
    @DeleteMapping(value = "/index/del")
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user");
        AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        log.info("索引操作===>{}",acknowledgedResponse.isAcknowledged());
    }

    //-------------------------文档操作------------------------

    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/document/save")
    public void insertDoc() throws IOException {
        IndexRequest request = new IndexRequest();
        request.index("user").id("1");
        User user = new User("tao",18,"男");
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        log.info("Result===>{}",indexResponse.getResult());
    }

    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/document/save/v2")
    public void insertDoc2() throws IOException{
        IndexRequest indexRequest = new IndexRequest("user");
        indexRequest.id("1");
        indexRequest.source("userName", "tao", "age", 18, "sex", "M");
        IndexResponse index = restHighLevelClient.index((indexRequest),  RequestOptions.DEFAULT);
        log.info("===>", index.getResult());
    }

    @ApiOperation(value = "查询数据")
    @PostMapping(value = "/document/get")
    public void selectDoc() throws IOException{
        GetRequest request = new GetRequest();
        request.index("user").id("1");
        GetResponse documentFields = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        log.info("===>{}",documentFields.getSourceAsString());
    }

    @ApiOperation(value = "批量添加数据")
    @PostMapping(value = "/document/batch/add")
    public void bulkInsertDoc() throws IOException{
        BulkRequest request = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        User user1 = new User("tao",18,"男");
        User user2 = new User("tao",17,"'女'");
        User user3 = new User("tao",16,"'女'");

        request.add(new IndexRequest().index("user").id("1").source(mapper.writeValueAsString(user1),XContentType.JSON));
        request.add(new IndexRequest().index("user").id("2").source(mapper.writeValueAsString(user2),XContentType.JSON));
        request.add(new IndexRequest().index("user").id("3").source(mapper.writeValueAsString(user3),XContentType.JSON ));

        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        log.info("Took===>{}",response.getTook());
        log.info("Items===>{}",response.getItems());
    }

    @ApiOperation(value = "批量删除数据")
    @DeleteMapping(value = "/document/batch/del")
    public void bulkDeleteDoc() throws IOException{
        BulkRequest request = new BulkRequest();

        request.add(new DeleteRequest().index("user").id("1"));
        request.add(new DeleteRequest().index("user").id("2"));
        request.add(new DeleteRequest().index("user").id("3"));

        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        log.info("Took===>{}",response.getTook());
        log.info("Items===>{}",response.getItems());
    }


    @ApiOperation(value = "高级查询-查询全部")
    @GetMapping(value = "/document/highlevel/find/all")
    public void searchDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-分页查询")
    @GetMapping(value = "/document/highlevel/page")
    public void searchPageDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.from(0);
        builder.size(2);
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-排序查询")
    @GetMapping(value = "/document/highlevel/sort")
    public void searchOrderDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.ASC);
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-查询指定字段")
    @GetMapping(value = "/document/highlevel/specified/field")
    void searchSourceDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        builder.sort("age", SortOrder.ASC);
        String[] excludes = {"sex"};
        String[] includes = {};
        builder.fetchSource(includes, excludes);
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-组合查询")
    @GetMapping(value = "/document/highlevel/combination/match")
    public void searchBoolDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 18));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "女"));

        builder.query(boolQueryBuilder);

        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-范围查询")
    @GetMapping(value = "/document/highlevel/range")
    public void searchRangeDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gte(20);
        rangeQuery.lte(22);
        builder.query(rangeQuery);

        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-模糊查询")
    @GetMapping(value = "/document/highlevel/fuzzy")
    public void searchFuzzyDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "毛金").fuzziness(Fuzziness.ONE));
        //.fuzziness(Fuzziness.ONE)匹配多少个

        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h.getSourceAsString());
        });
    }

    @ApiOperation(value = "高级查询-高亮查询")
    @GetMapping(value = "/document/highlevel/highlight")
    public void searchHighlighterDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery("name", "金");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");

        builder.highlighter(highlightBuilder);
        builder.query(termQueryBuilder);


        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());

        hits.forEach(h ->{
            log.info("===>"+h);
        });
    }

    @ApiOperation(value = "高级查询-聚合查询")
    @GetMapping(value = "/document/highlevel/aggre")
    public void searchAggregationDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        MaxAggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());
        log.info("Aggregations===>"+response.getAggregations());
    }

    @ApiOperation(value = "高级查询-聚合查询-分组")
    @GetMapping(value = "/document/highlevel/aggre/group")
    public void searchAggregationGroupDoc() throws IOException{
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");

        builder.aggregation(aggregationBuilder);

        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();

        log.info("TotalHits===>{}",hits.getTotalHits());
        log.info("Took===>{}",response.getTook());
        log.info("Aggregations===>"+response.getAggregations());
    }

}
