package com.zja.amqp.rabbit.send.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhengja
 * @Date: 2025-01-10 13:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization implements Serializable {
    private String id;
    private String name;
}
