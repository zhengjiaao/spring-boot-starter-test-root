/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 13:20
 * @Since:
 */
package com.zja.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class BaseModel implements Serializable {

    //主键
    @Id
    private String id;

    //创建者
    private String cjz;
    //创建时间
    private String cjsj;
    //更新者
    private String gxz;
    //更新时间
    private String gxsj;

}
