package com.zja.service;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import com.zja.model.BasePageRequest;
import com.zja.model.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mongo非结构化json数据管理实现类
 *
 * @Author: zhengja
 * @Date: 2024-09-25 15:44
 */
@Slf4j
@Component
public class NonStructuredJsonServiceImpl implements NonStructuredJsonService {

    @Autowired
    private MongoTemplate mongoTemplate;

    // 集合名称
    private static final String COLLECTION_NAME = "non_structured_json";

    private void checkJsonData(JSONObject jsonData) {
        if (jsonData == null) {
            throw new IllegalArgumentException("jsonData cannot be null");
        }
    }

    @Override
    public String add(JSONObject jsonData) {
        checkJsonData(jsonData);

        // 添加数据
        JSONObject addData = new JSONObject();
        addData.put("data", jsonData);
        addData.put("createdAt", LocalDateTime.now());
        addData.put("sort", System.currentTimeMillis());
        // 保存添加的数据
        JSONObject entity = mongoTemplate.save(addData, COLLECTION_NAME);

        return entity.getString("_id");
    }

    @Override
    public JSONObject update(String id, JSONObject jsonData) {
        checkJsonData(jsonData);

        if (!exists(id)) {
            throw new RuntimeException("不存在的记录，更新失败.");
        }

        // 查询原有数据
        Query query = new Query(Criteria.where("_id").is(id));
        JSONObject updatedData = mongoTemplate.findOne(query, JSONObject.class, COLLECTION_NAME);
        if (updatedData == null) {
            throw new RuntimeException("找不到对应的记录，更新失败.");
        }

        // 更新数据
        updatedData.put("data", jsonData);
        updatedData.put("updatedAt", LocalDateTime.now());
        // 保存更新后的数据
        JSONObject entity = mongoTemplate.save(updatedData, COLLECTION_NAME);

        return entity.getJSONObject("data");
    }

    @Override
    public JSONObject queryById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        List<JSONObject> jsonDocuments = mongoTemplate.find(query, JSONObject.class, COLLECTION_NAME);
        if (!jsonDocuments.isEmpty()) {
            return jsonDocuments.get(0).getJSONObject("data");
        } else {
            return null;
        }
    }

    @Override
    public PageData<JSONObject> pageList(BasePageRequest pageRequest) {
        Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by(Sort.Direction.DESC, "sort"));
        Query query = new Query().with(pageable);

        List<JSONObject> result = mongoTemplate.find(query, JSONObject.class, COLLECTION_NAME);
        long totalSize = mongoTemplate.count(new Query(), COLLECTION_NAME);

        List<JSONObject> resultData = result.stream()
                .map(this::convertId)
                .collect(Collectors.toList());

        return PageData.of(resultData, pageRequest.getPage(), pageRequest.getSize(), totalSize);
    }

    private JSONObject convertId(JSONObject jsonObject) {
        JSONObject convertedJson = new JSONObject();
        convertedJson.putAll(jsonObject);
        String id = convertedJson.getString("_id");
        convertedJson.put("id", id);
        convertedJson.remove("_id");
        return convertedJson;
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, JSONObject.class, COLLECTION_NAME);
    }

    @Override
    public boolean deleteById(String id) {
        if (!exists(id)) {
            return true;
        }

        try {
            Query query = new Query(Criteria.where("_id").is(id));
            DeleteResult deleteResult = mongoTemplate.remove(query, COLLECTION_NAME);

            if (deleteResult.wasAcknowledged() && deleteResult.getDeletedCount() > 0) {
                log.debug("删除成功，id={}", id);
                return true;
            } else {
                log.warn("删除失败，可能是由于数据不存在或数据库问题，id={}", id);
                return false;
            }
        } catch (MongoException me) {
            log.error("MongoDB异常：删除失败，请排查id={}，异常信息：{}", id, me.getMessage(), me);
            return false;
        } catch (Exception e) {
            log.error("未知异常：删除失败，请排查id={}，异常信息：{}", id, e.getMessage(), e);
            return false;
        }
    }
}
