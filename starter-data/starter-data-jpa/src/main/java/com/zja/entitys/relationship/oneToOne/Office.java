/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-09-28 12:39
 * @Since:
 */
package com.zja.entitys.relationship.oneToOne;

import lombok.Data;

import javax.persistence.*;

/**
 * 办公室
 *
 * @author: zhengja
 * @since: 2023/09/28 12:39
 */
@Entity
@Table(name = "oo_offices")
@Data
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "officeCode")
    private Integer code;

    private String officeName; // 办公室名称
}
