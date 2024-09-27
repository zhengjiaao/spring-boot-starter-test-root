package com.zja.controller;

import com.alibaba.fastjson.JSONObject;
import com.zja.model.BasePageRequest;
import com.zja.model.PageData;
import com.zja.service.NonStructuredJsonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 非结构化数据 接口层（一般与页面、功能对应）
 *
 * @author: zhengja
 * @since: 2024/09/26 9:15
 */
@Validated
@RestController
@RequestMapping("/rest/non/structured/json")
@Api(tags = {"BASE-非结构化JSON数据管理页面"})
public class NonStructuredJsonController {

    @Autowired
    NonStructuredJsonService service;

    @GetMapping("/query/{id}")
    @ApiOperation("查询单个非结构化数据详情")
    public JSONObject queryById(@NotBlank @PathVariable("id") String id) {
        return service.queryById(id);
    }

    @GetMapping("/page/list")
    @ApiOperation("分页查询非结构化数据列表")
    public PageData<JSONObject> pageList(@Valid BasePageRequest pageRequest) {
        return service.pageList(pageRequest);
    }

    @PostMapping("/add")
    @ApiOperation("添加非结构化数据")
    public String add(@Valid @NotEmpty @RequestBody JSONObject jsonData) {
        return service.add(jsonData);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新非结构化数据")
    public JSONObject update(@NotBlank @PathVariable("id") String id,
                             @Valid @NotEmpty @RequestBody JSONObject jsonData) {
        return service.update(id, jsonData);
    }

    @GetMapping("/exists/{id}")
    @ApiOperation("存在非结构化数据")
    public boolean exists(@NotBlank @PathVariable("id") String id) {
        return service.exists(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除非结构化数据")
    public boolean deleteById(@NotBlank @PathVariable("id") String id) {
        return service.deleteById(id);
    }

}