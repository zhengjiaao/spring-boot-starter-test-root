package com.zja.json.config;

/**
 * @Author: zhengja
 * @Date: 2025-04-14 13:58
 */
class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}