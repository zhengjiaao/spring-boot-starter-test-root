package com.zja.service;

import com.alibaba.fastjson.JSONObject;
import com.zja.model.BasePageRequest;
import com.zja.model.PageData;

/**
 * mongo非结构化json数据管理
 *
 * @Author: zhengja
 * @Date: 2024-09-25 15:39
 */
public interface NonStructuredJsonService {

    String add(JSONObject jsonData);

    JSONObject update(String id, JSONObject jsonData);

    JSONObject queryById(String id);

    PageData<JSONObject> pageList(BasePageRequest pageRequest);

    boolean exists(String id);

    boolean deleteById(String id);
}
