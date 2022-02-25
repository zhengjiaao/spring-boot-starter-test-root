/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 16:50
 * @Since:
 */
package com.zja.dao;

import com.zja.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsDao extends MongoRepository<Comment,String> {

}
