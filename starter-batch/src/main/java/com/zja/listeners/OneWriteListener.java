/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-25 13:45
 * @Since:
 */
package com.zja.listeners;

import com.zja.entity.OneEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

import static java.lang.String.format;

public class OneWriteListener implements ItemWriteListener<OneEntity> {

    private Logger logger = LoggerFactory.getLogger(OneWriteListener.class);

    @Override
    public void beforeWrite(List<? extends OneEntity> items) {
        logger.info("beforeWrite：{}", items);
    }

    @Override
    public void afterWrite(List<? extends OneEntity> items) {
        logger.info("afterWrite：{}", items);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends OneEntity> items) {
        try {
            logger.error("onWriteError:{}", exception.getMessage());
            for (OneEntity item : items) {
                logger.info(format("Failed writing BlogInfo : %s", item.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
