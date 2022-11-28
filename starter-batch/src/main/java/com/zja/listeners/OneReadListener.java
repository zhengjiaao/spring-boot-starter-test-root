/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-11-25 13:44
 * @Since:
 */
package com.zja.listeners;

import com.zja.dto.OneDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

public class OneReadListener implements ItemReadListener<OneDTO> {

    private Logger logger = LoggerFactory.getLogger(OneReadListener.class);

    @Override
    public void beforeRead() {
        logger.info("beforeRead");
    }

    @Override
    public void afterRead(OneDTO item) {
        logger.info("afterRead：{}", item);
    }

    @Override
    public void onReadError(Exception ex) {
        System.out.println("onReadError");
        logger.error("读取数据错误：{}", ex);
    }
}
