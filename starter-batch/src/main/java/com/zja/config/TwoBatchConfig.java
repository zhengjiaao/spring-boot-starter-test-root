///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2022-03-11 11:11
// * @Since:
// */
//package com.zja.config;
//
//import com.zja.entity.User;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.sql.DataSource;
//
////@Configuration
//public class UserBatchConfig {
//
//    @Autowired
////    JobBuilderFactory jobBuilderFactory;
////    @Autowired
//    StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    DataSource dataSource;
//
//    /**
//     * Spring Batch提供了一些常用的ItemReader，
//     * 例如JdbcPagingItemReader用来读取数据库中的数据，
//     * StaxEventItemReader用来读取XML数据，
//     * 本案例中的FlatFileItemReader则是一个加载普通文件的ItemReader
//     */
//    @Bean
//    @StepScope
//    FlatFileItemReader<User> itemReader() {
//        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
//        reader.setLinesToSkip(1);//跳过一行
//        reader.setResource(new ClassPathResource("csv/user.csv"));//配置data.csv文件的位置
//        reader.setLineMapper( // 通过setLineMapper方法设置每一行的数据信息
//                new DefaultLineMapper<User>() {{
//                    setLineTokenizer(new DelimitedLineTokenizer() {{
//                        setNames("id", "username", "address"); // setNames方法配置了data.csv文件一共有4列
//                        setDelimiter(","); // setDelimiter则是配置列与列之间的间隔符
//                    }});
//                    // 设置要映射的实体类属性
//                    setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
//                        setTargetType(User.class);
//                    }});
//                }});
//        return reader;
//    }
//
//    /**
//     * Spring Batch也提供了多个ItemWriter的实现，
//     * 常见的如FlatFileItemWriter，表示将数据写出为一个普通文件，
//     * StaxEventItemWriter表示将数据写出为XML。
//     * 另外，还有针对不同数据库提供的写出操作支持类，如MongoItemWriter、JpaItemWriter、Neo4jItemWriter以及HibernateItemWriter等，
//     * 本案例使用的JdbcBatchItemWriter则是通过JDBC将数据写出到一个关系型数据库中。
//     */
//    @Bean
//    JdbcBatchItemWriter jdbcBatchItemWriter() {
//        JdbcBatchItemWriter writer = new JdbcBatchItemWriter();
//        writer.setDataSource(dataSource);
//        // JdbcBatchItemWriter主要配置数据以及数据插入SQL，注意占位符的写法是“:属性名”
//        writer.setSql("insert into user(id,username,address) values(:id,:username,:address)");
//        // 通过BeanPropertyItemSqlParameterSourceProvider实例将实体类的属性和SQL中的占位符一一映射
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        return writer;
//    }
//
//    /**
//     * 配置一个Step
//     */
//    @Bean
//    Step csvStep() {
//        /*
//         * Step通过stepBuilderFactory进行配置，首先通过get获取一个StepBuilder，
//         * get方法的参数就是该Step的name
//         * 然后调用chunk方法的参数2，表示每读取到两条数据就执行一次write操作，
//         * 最后分别配置reader和writer。
//         */
//        return stepBuilderFactory.get("csvStep")
//                .<User, User>chunk(2)
//                .reader(itemReader())
//                .writer(jdbcBatchItemWriter())
//                .build();
//    }
//
//    /**
//     * 配置一个Job
//     */
//    @Bean
//    Job csvJob() {
//        /*
//         * 通过jobBuilderFactory构建一个Job，
//         * get方法的参数为Job的name，
//         * 然后配置该Job的Step即可
//         */
//        return jobBuilderFactory.get("csvJob")
//                .start(csvStep())
//                .build();
//    }
//
//
//}
