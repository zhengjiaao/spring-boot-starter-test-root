/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 13:25
 * @Since:
 */
package com.zja.config;

import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 自定义 MongoConfig
 * 参考 MongoDatabaseFactoryDependentConfiguration
 */
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnBean(MongoDatabaseFactory.class)
public class MongoConfig {

    private final MongoProperties properties;

    MongoConfig(MongoProperties properties) {
        this.properties = properties;
    }

    @Primary
    @Bean
    @ConditionalOnMissingBean(MongoOperations.class)
    MongoTemplate mongoTemplate(MongoDatabaseFactory factory, MongoConverter converter) {
        return new MongoTemplate(factory, null);
    }

    /**
     * 由于需要配置多个 Gridfs 的 Bucket
     */
    @Primary
    @Bean
    @ConditionalOnMissingBean(GridFsOperations.class)
    GridFsTemplate gridFsTemplate(MongoDatabaseFactory factory, MongoTemplate mongoTemplate) {
        return new GridFsTemplate(new MongoConfig.GridFsMongoDatabaseFactory(factory, this.properties),
                mongoTemplate.getConverter(), this.properties.getGridfs().getBucket());
    }

    static class GridFsMongoDatabaseFactory implements MongoDatabaseFactory {

        private final MongoDatabaseFactory mongoDatabaseFactory;

        private final MongoProperties properties;

        GridFsMongoDatabaseFactory(MongoDatabaseFactory mongoDatabaseFactory, MongoProperties properties) {
            Assert.notNull(mongoDatabaseFactory, "MongoDatabaseFactory must not be null");
            Assert.notNull(properties, "Properties must not be null");
            this.mongoDatabaseFactory = mongoDatabaseFactory;
            this.properties = properties;
        }

        @Override
        public MongoDatabase getMongoDatabase() throws DataAccessException {
            String gridFsDatabase = this.properties.getGridfs().getDatabase();
            if (StringUtils.hasText(gridFsDatabase)) {
                return this.mongoDatabaseFactory.getMongoDatabase(gridFsDatabase);
            }
            return this.mongoDatabaseFactory.getMongoDatabase();
        }

        @Override
        public MongoDatabase getMongoDatabase(String dbName) throws DataAccessException {
            return this.mongoDatabaseFactory.getMongoDatabase(dbName);
        }

        @Override
        public PersistenceExceptionTranslator getExceptionTranslator() {
            return this.mongoDatabaseFactory.getExceptionTranslator();
        }

        @Override
        public ClientSession getSession(ClientSessionOptions options) {
            return this.mongoDatabaseFactory.getSession(options);
        }

        @Override
        public MongoDatabaseFactory withSession(ClientSession session) {
            return this.mongoDatabaseFactory.withSession(session);
        }

    }

}
