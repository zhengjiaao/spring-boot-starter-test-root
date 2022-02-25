/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 10:58
 * @Since:
 */
package com.zja.constants;

public final class CacheKeyConstants {

    static final String SYSTEM_PREFIX = "cache:";

    //缓存组
    public static final class EntityCacheKey {
        public static final String DATA = SYSTEM_PREFIX + "data";
    }

    //临时缓存 key
    //public static final String TEMP_DATA_KEY = SYSTEM_PREFIX+"temp:data";

}
