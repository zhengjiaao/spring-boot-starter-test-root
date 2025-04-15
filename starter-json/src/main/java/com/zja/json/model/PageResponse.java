package com.zja.json.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: zhengja
 * @Date: 2025-04-14 13:57
 */
@Data
public class PageResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PageResponse(List<T> content, int page, int size, long totalElements, int totalPages) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}