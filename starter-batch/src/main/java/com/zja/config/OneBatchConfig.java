/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-24 16:50
 * @Since:
 */
package com.zja.config;

import com.zja.dto.OneDTO;
import com.zja.entity.OneEntity;
import com.zja.listeners.OneJobExecutionListener;
import com.zja.listeners.OneReadListener;
import com.zja.listeners.OneWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class OneBatchConfig extends DefaultBatchConfigurer {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    DataSource dataSource;

    @Resource
    private PlatformTransactionManager transactionManager;
    @Resource
    private EntityManagerFactory entityManagerFactory;

/*
    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() {
        final JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(dataSource);
        return tm;
    }*/

    /**
     * 任务启动器
     */
  /*  @Override
    protected JobLauncher createJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(createJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }*/

    /**
     * 任务存储
     */
  /*  @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDatabaseType("onesql");
        factory.setTransactionManager(transactionManager);
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
*/

    /**
     * 定义Job
     */
    @Bean("oneJob")
    public Job job(JobBuilderFactory builder, @Qualifier("oneStep") Step step) {
        return builder.get("oneJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .listener(new OneJobExecutionListener())
                .build();
    }

    @Bean("oneItemReader")
    public ItemReader<OneDTO> reader() {
        FlatFileItemReader<OneDTO> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("csv/one.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<OneDTO>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer(",") {
                    {
                        setNames("uid", "name");
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<OneDTO>() {
                    {
                        setTargetType(OneDTO.class);
                    }
                });
            }
        });
        return reader;
    }

    @Bean("oneItemProcessor")
    public ItemProcessor<OneDTO, OneEntity> processor() {
        return new ItemProcessor<OneDTO, OneEntity>() {
            @Override
            public OneEntity process(OneDTO item) throws Exception {
                OneEntity p = new OneEntity();
                p.setUid(item.getUid());
                p.setName(item.getName());
                return p;
            }
        };
    }

    @Bean("oneItemWriter")
    public ItemWriter<OneEntity> itemWriter() {
        JpaItemWriterBuilder<OneEntity> builder = new JpaItemWriterBuilder<>();
        builder.entityManagerFactory(entityManagerFactory);
        return builder.build();
    }

    @Bean("oneStep")
    public Step step(StepBuilderFactory stepBuilderFactory,
                     @Qualifier("oneItemReader") ItemReader<OneDTO> reader,
                     @Qualifier("oneItemWriter") ItemWriter<OneEntity> writer,
                     @Qualifier("oneItemProcessor") ItemProcessor<OneDTO, OneEntity> processor) {
        return stepBuilderFactory
                .get("oneStep")
                .<OneDTO, OneEntity>chunk(2) // Chunk的机制(即每次读取一条数据，再处理一条数据，累积到一定数量后再一次性交给writer进行写入操作)
//                .reader(reader).faultTolerant().retryLimit(3).retry(Exception.class).skip(Exception.class).skipLimit(2)
                .reader(reader)
                .listener(new OneReadListener())
                .processor(processor)
//                .writer(writer).faultTolerant().skip(Exception.class).skipLimit(2)
                .writer(writer)
                .listener(new OneWriteListener())
                .transactionManager(transactionManager)
                .build();
    }

}
