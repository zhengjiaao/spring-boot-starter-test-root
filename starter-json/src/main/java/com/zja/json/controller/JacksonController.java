package com.zja.json.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhengja
 * @Date: 2025-04-14 13:49
 **/
@RequestMapping("/jackson")
@RestController
public class JacksonController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/show-config")
    public String showConfig() {
        StringBuilder result = new StringBuilder();

        // 获取序列化特性
        List<String> serializationFeatures = Arrays.stream(SerializationFeature.values())
                .filter(feature -> objectMapper.getSerializationConfig().isEnabled(feature))
                .map(Enum::name)
                .collect(Collectors.toList());
        result.append("Serialization Features: ").append(serializationFeatures).append("\n");

        // 获取反序列化特性
        List<String> deserializationFeatures = Arrays.stream(DeserializationFeature.values())
                .filter(feature -> objectMapper.getDeserializationConfig().isEnabled(feature))
                .map(Enum::name)
                .collect(Collectors.toList());
        result.append("Deserialization Features: ").append(deserializationFeatures);

        return result.toString();
    }
}
