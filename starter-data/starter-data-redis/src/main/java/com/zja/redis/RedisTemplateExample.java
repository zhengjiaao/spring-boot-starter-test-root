/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-26 15:34
 * @Since:
 */
package com.zja.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate 示例
 *
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 *
 * redisTemplate.opsForGeo()
 * redisTemplate.opsForStream()
 * redisTemplate.opsForCluster()
 * redisTemplate.opsForHyperLogLog()
 *
 * RedisTemplate 使用 JdkSerializationRedisSerializer 将数据先序列化成字节数组，然后存入Redis数据库
 * RedisTemplate 最好操作的数据是java对象(RedisTemplate省略泛型，存入数据库的字符串前面就会有ASCII码的东西)
 * RedisTemplate 默人序列化 操作字符串时，key(前缀)和value乱码
 */
@Component
public class RedisTemplateExample {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redisTemplate 常用方法
     */
    private void redisTemplateTest() {

        //给指定键设置过期时间
        redisTemplate.expire("redis-key", 12, TimeUnit.SECONDS);
        //删除指定键
        redisTemplate.delete("redis-key");
        //查找 指定的 键
        redisTemplate.keys("*");
        //判断是否存在键值
        redisTemplate.hasKey("redis-key");
        //获取过期时间
        redisTemplate.getExpire("redis-key");
        //获取指定格式的过期时间
        redisTemplate.getExpire("redis-key", TimeUnit.SECONDS);
        //获取当前传入的key的值序列化为byte[]类型
        redisTemplate.dump("redis-key");
        //修改指定键的名字  如果该键不存在则报错
        redisTemplate.rename("redis-key", "redis--new-key");
        //旧值存在时，将旧值改为新值
        redisTemplate.renameIfAbsent("redis-key", "redis--new-key");
        //获取指定键的类型
        redisTemplate.type("redis-key");
        //将指定的键移动到指定的库中
        redisTemplate.move("redis-key", 2);
        //随机取一个key
        redisTemplate.randomKey();
        //将key持久化保存 就是把过期或者设置了过期时间的key变为永不过期
        redisTemplate.persist("redis-key");
    }

    /**
     * redis 操作字符串
     */
    private void opsForValueTest() {
        //存入数据
        redisTemplate.opsForValue().set("name", "lzj");
        //获取数据
        System.out.println(redisTemplate.opsForValue().get("name"));
        //获取多个数据
        System.out.println(redisTemplate.opsForValue().multiGet(Arrays.asList("lzj", "lyx", "3333")));
        //存入数据并且设置过期时间
        redisTemplate.opsForValue().set("num", "123", 10, TimeUnit.SECONDS);
        //给指定键值 4 偏移量的位置开始替换内容
        redisTemplate.opsForValue().set("name", "lzj", 2);
        //设置键的字符串值并返回其旧值
        System.out.println(redisTemplate.opsForValue().getAndSet("name", "lyx"));
        //给指定键 的值追加字符串
        redisTemplate.opsForValue().append("test", "Hello");
        //存入数据 如果不存在则存入数据返回true 否则不覆盖数据返回false
        System.out.println(redisTemplate.opsForValue().setIfAbsent("lzj", "1234"));
        //存入数据并设置过期时间 如果不存在则存入数据返回true 否则不覆盖数据返回false
        System.out.println(redisTemplate.opsForValue().setIfAbsent("lzj", "1234", 200, TimeUnit.SECONDS));
        //存入数据 如果存在键则覆盖数据 返回true 不存在则不作任何操作 返回false
        System.out.println(redisTemplate.opsForValue().setIfPresent("lyx", "1234"));
        //存入数据并设置过期时间 如果存在键则覆盖数据 返回true 不存在则不作任何操作 返回false
        System.out.println(redisTemplate.opsForValue().setIfPresent("lyx", "1234", 200, TimeUnit.SECONDS));

        Map<String, String> map = new HashMap<>();
        map.put("1", "123");
        map.put("2", "123");
        //多个键值的插入
        redisTemplate.opsForValue().multiSet(map);
        //多个键值的插入 如果不存在则存入数据返回true 否则不覆盖数据返回false
        redisTemplate.opsForValue().multiSetIfAbsent(map);

        //返回键的值的长度
        System.out.println(redisTemplate.opsForValue().size("lzj"));
        System.out.println(redisTemplate.opsForValue().multiGet(Arrays.asList("lzj", "lyx", "3333")));
        //给指定键 加1如果值不是数字则抛出异常 不存在指定键创建一个初始为0的加1 增加成功则返回增加后的值
        System.out.println(redisTemplate.opsForValue().increment("lzj"));
        //给指定键 加指定整数如果值不是数字则抛出异常 不存在指定键创建一个初始为0的加指定整数 增加成功则返回增加后的值
        System.out.println(redisTemplate.opsForValue().increment("1", 1));
        //给指定键 加指定浮点数如果值不是数字则抛出异常 不存在指定键创建一个初始为0的加指定浮点数 增加成功则返回增加后的值
        System.out.println(redisTemplate.opsForValue().increment("1", 1.2));
        //给指定键 减1如果值不是数字则抛出异常 不存在指定键创建一个初始为0的减1 减少成功则返回增加后的值
        System.out.println(redisTemplate.opsForValue().decrement("1"));
        //给指定键 减指定整数如果值不是数字则抛出异常 不存在指定键创建一个初始为0的减指定整数 减少成功则返回增加后的值
        System.out.println(redisTemplate.opsForValue().decrement("1", 3));

    }

    /**
     * redis 操作list
     */
    private void opsForListTest() {
        //存入List数据 做左边推入一个 如果键不存在 则创建一个空的并左推入
        redisTemplate.opsForList().leftPush("list1", "1");
        //存入List数据 做左边推入多个 如果键不存在 则创建一个空的并左推入
        redisTemplate.opsForList().leftPushAll("list1", "88", "999");
        //存入List数据 做右边推入一个 如果键不存在 则创建一个空的并右推入
        redisTemplate.opsForList().rightPush("list1", "3");
        //存入List数据 做右边推入多个 如果键不存在 则创建一个空的并右推入
        redisTemplate.opsForList().leftPushAll("list1", "77", "6666");
        //返回指定List数据下标的值
        System.out.println(redisTemplate.opsForList().index("", 2));
        //移除2个指定List数据元素内容为1
        redisTemplate.opsForList().remove("list1", 2, "1");
        //左边推出一个
        System.out.println(redisTemplate.opsForList().leftPop("list1"));
        //移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
        System.out.println(redisTemplate.opsForList().leftPop("list1", 2, TimeUnit.SECONDS));
        //右边推出一个
        System.out.println(redisTemplate.opsForList().rightPop("list1"));
        //移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
        System.out.println(redisTemplate.opsForList().rightPop("list1", 2, TimeUnit.SECONDS));
        //给指定键List数据下标为1的元素替换成2
        redisTemplate.opsForList().set("list1", 1, "2");
        //查看指定键List数据元素个数
        redisTemplate.opsForList().size("list1");
        //获取指定健List数据 从开始到结束下标
        redisTemplate.opsForList().range("list1", 0, -1).forEach(System.out::println);
        //移除列表的最后一个元素，并将该元素添加到另一个列表(如果这另一个List不存在就创建一个空的添加)并返回
        redisTemplate.opsForList().rightPopAndLeftPush("list1", "list2");
        // 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
        redisTemplate.opsForList().rightPopAndLeftPush("list1", "list2", 80, TimeUnit.SECONDS);

        //指定键List数据右边推出一个元素
        System.out.println(redisTemplate.opsForList().rightPop("list1"));
        //指定键List数据右边推出一个元素，如果List元素 等待10秒 10秒内没有元素就不操作，有就推出
        System.out.println(redisTemplate.opsForList().rightPop("list1", 10, TimeUnit.SECONDS));
        //指定键List数据左边推出一个元素，如果List元素 等待10秒 10秒内没有元素就不操作，有就推出
        System.out.println(redisTemplate.opsForList().leftPop("list1"));
        //指定键List数据左边推出一个元素
        System.out.println(redisTemplate.opsForList().leftPop("list1", 10, TimeUnit.SECONDS));
        //给指定键List数据下标为1的元素替换成2
        redisTemplate.opsForList().set("list1", 1, "2");
        //查看指定键List数据元素个数
        redisTemplate.opsForList().size("list1");
        //如果存在该键的List数据 则向左推入一个元素 不存在的话不操作
        redisTemplate.opsForList().leftPushIfPresent("list1", "1");
        //如果存在该键的List数据 则向右推入一个元素 不存在的话不操作
        redisTemplate.opsForList().rightPushIfPresent("list1", "1");
    }

    /**
     * redis 操作Hash
     */
    private void opsForHashTest() {
        //向指定键推入一个元素（指定键不存在就创建一个空的推入）
        redisTemplate.opsForHash().put("hash1", "lzj", "1234");
        Map<String, String> map = new HashMap<>();
        map.put("lxy", "123445");
        map.put("lhm", "434564");
        //向指定键推入多个元素（指定键不存在就创建一个空的推入）
        redisTemplate.opsForHash().putAll("hash1", map);
        //向指定键推入一个元素（仅当lzj不存在时才设置）
        redisTemplate.opsForHash().putIfAbsent("hash1", "lzj", "1234");
        //获取指定键里面单个元素key为lzj的值
        System.out.println(redisTemplate.opsForHash().get("hash1", "lzj"));
        //获取指定键里面多个元素key为特定的值
        redisTemplate.opsForHash().multiGet("hash1", Arrays.asList("lzj", "num")).forEach(System.out::println);
        //查看指定键内有没有元素的key是lzj的
        System.out.println(redisTemplate.opsForHash().hasKey("hash1", "lzj"));
        //查看键所有元素的Key
        redisTemplate.opsForHash().keys("hash1").forEach(System.out::println);
        //查看键所有的元素
        redisTemplate.opsForHash().entries("hash1").forEach((k, v) -> {
            System.out.println("k" + k + " _ " + "v" + v);
        });
        //查看键所有元素的值
        redisTemplate.opsForHash().values("hash1").forEach(System.out::println);
        ;
        //查看指定键的元素的key为lzj的值的长度
        System.out.println(redisTemplate.opsForHash().lengthOfValue("hash1", "lzj"));
        //查看指定键有多少个元素
        System.out.println(redisTemplate.opsForHash().size("hash1"));
        //指定键的元素的Key为num的值加整数（如果key不存在创建一个初始为0加整数）
        redisTemplate.opsForHash().increment("hash1", "num", 1);
        //指定键的元素的Key为num的值加浮点数（如果key不存在创建一个初始为0加浮点数）
        redisTemplate.opsForHash().increment("hash1", "num", 3.2);
        //指定键 根据key值删除元素
        redisTemplate.opsForHash().delete("hash", "lzj");
        //获取集合的游标。通过游标可以遍历整个集合。
        Cursor<Map.Entry<Object, Object>> curosr = redisTemplate.opsForHash().scan("hash1", ScanOptions.NONE);
        while (curosr.hasNext()) {
            Map.Entry<Object, Object> entry = curosr.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * redis 操作set
     */
    private void opsForSetTest() {
        //向键为set1的添加元素1（若没有该键，创建一个新的，并加入元素）
        redisTemplate.opsForSet().add("set1", "1");
        //查询指定键的包含所有元素
        System.out.println(redisTemplate.opsForSet().members("set1"));
        //查询指定键的包含元素个数
        System.out.println(redisTemplate.opsForSet().size("set1"));
        //查询指定键是否有该元素
        System.out.println(redisTemplate.opsForSet().isMember("set1", "1"));
        //指定键随机推出一个元素 并返回
        System.out.println(redisTemplate.opsForSet().pop("set1"));
        //移除指定键里面的指定元素
        redisTemplate.opsForSet().remove("set1", "1", "2");
        //将指定键的指定元素移动到指定键中
        redisTemplate.opsForSet().move("set1", "1", "set3");

        //获取两个集合的差集
        redisTemplate.opsForSet().difference("set1", "set2").forEach(System.out::println);
        //获取两个集合的差集，并存入一个集合中
        redisTemplate.opsForSet().differenceAndStore("set1", "set2", "set3");
        //求指定键与另外一个集合的差集
        redisTemplate.opsForSet().difference("set1", Arrays.asList("1", "2", "3")).forEach(System.out::println);
        //求指定键与另外一个集合的差集，并存入一个集合中
        redisTemplate.opsForSet().differenceAndStore("set1", Arrays.asList("1", "2", "3"), "set3");

        //获取两个集合的交集
        redisTemplate.opsForSet().intersect("set1", "set2").forEach(System.out::println);
        //获取两个集合的交集，并存入一个集合中
        redisTemplate.opsForSet().intersectAndStore("set1", "set2", "set3");
        //求指定键与另外一个集合的交集
        redisTemplate.opsForSet().intersect("set1", Arrays.asList("1", "2", "3"));
        //求指定键与另外一个集合的交集，并存入一个集合中
        redisTemplate.opsForSet().intersectAndStore("set1", Arrays.asList("1", "2", "3"), "set3");

        //获取两个集合的并集
        redisTemplate.opsForSet().union("set1", "set2").forEach(System.out::println);
        //获取两个集合的并集，并存入一个集合中
        redisTemplate.opsForSet().unionAndStore("set1", "set2", "set3");
        //求指定键与另外一个集合的并集
        redisTemplate.opsForSet().union("set1", Arrays.asList("1", "2", "3")).forEach(System.out::println);
        //求指定键与另外一个集合的并集，并存入一个集合中
        redisTemplate.opsForSet().unionAndStore("set1", Arrays.asList("1", "2", "3"), "set3");

        //随机获取集合中的一个元素
        redisTemplate.opsForSet().randomMember("set1");
        //随机返回集合中指定数量的元素。随机的元素可能重复
        redisTemplate.opsForSet().randomMembers("set1", 2);
        //随机返回集合中指定数量的元素。随机的元素不会重复
        redisTemplate.opsForSet().distinctRandomMembers("set1", 2);
        //获取集合的游标。通过游标可以遍历整个集合
        Cursor<String> curosr = redisTemplate.opsForSet().scan("set1", ScanOptions.NONE);
        while (curosr.hasNext()) {
            String item = curosr.next();
            System.out.println(item);
        }
    }

    /**
     * redis 操作zset 有序的
     */
    private void opsForZSetTest() {
        //向指定键插入元素 和 分数
        redisTemplate.opsForZSet().add("zset1", "lzj", 20.5);
        //向指定键插入元素 和 分数
        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>("zset-1", 9.6);
        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<String>("zset-2", 9.9);
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<ZSetOperations.TypedTuple<String>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        redisTemplate.opsForZSet().add("zset1", tuples);
        //获取指定键内指定元素的分数
        redisTemplate.opsForZSet().score("zset1", "zset-1");
        //指定键的移除指定元素
        redisTemplate.opsForZSet().remove("zset1", "lzj", "zset-1");
        //通过分数返回有序集合指定区间内的成员个数
        System.out.println(redisTemplate.opsForZSet().count("zset1", 10, 20));
        //通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)排序
        redisTemplate.opsForZSet().range("zset1", 0, -1).forEach(System.out::println);
        //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().rank("zset1", "zset-1"));
        //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从大到小)顺序排列
        redisTemplate.opsForZSet().reverseRank("zset1", "zset-1");
        //通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        redisTemplate.opsForZSet().rangeByScore("zset1", 10, 20).forEach(System.out::println);
        //通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列 取下标1开始2个元素
        redisTemplate.opsForZSet().rangeByScore("zset1", 10, 20, 1, 2).forEach(System.out::println);
        //通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
        redisTemplate.opsForZSet().reverseRange("zset1", 0, -1).forEach(System.out::println);
        //指定键的分数在10到20之间的元素(从大到小排序)
        redisTemplate.opsForZSet().reverseRangeByScore("zset1", 10, 20).forEach(System.out::println);
        //指定键的分数在10到20之间的元素(从大到小排序) 取下标1开始2个元素
        redisTemplate.opsForZSet().reverseRangeByScore("zset1", 10, 20, 1, 2).forEach(System.out::println);


        //通过索引区间内的成员按分数值递增(从小到大)顺序排列 并且带有分数
        Set<ZSetOperations.TypedTuple<String>> zset1 = redisTemplate.opsForZSet().rangeWithScores("zset1", 0, -1);
        Iterator<ZSetOperations.TypedTuple<String>> iterator1 = zset1.iterator();
        while (iterator1.hasNext()) {
            ZSetOperations.TypedTuple<String> typedTuple = iterator1.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }

        //指定键的分数在10到20之间的元素(从小到大排序)并且带有分数
        Set<ZSetOperations.TypedTuple<String>> zset2 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1", 10, 20);
        Iterator<ZSetOperations.TypedTuple<String>> iterator2 = zset2.iterator();
        while (iterator2.hasNext()) {
            ZSetOperations.TypedTuple<String> typedTuple = iterator2.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }

        //通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小)顺序排列
        Set<ZSetOperations.TypedTuple<String>> zset3 = redisTemplate.opsForZSet().reverseRangeWithScores("zset1", 0, -1);
        Iterator<ZSetOperations.TypedTuple<String>> iterator3 = zset3.iterator();
        while (iterator3.hasNext()) {
            ZSetOperations.TypedTuple<String> typedTuple = iterator3.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }

        //指定键的分数在10到20之间的元素(从小到大排序)并且带有分数
        Set<ZSetOperations.TypedTuple<String>> zset4 = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("zset1", 10, 20);
        Iterator<ZSetOperations.TypedTuple<String>> iterator4 = zset4.iterator();
        while (iterator4.hasNext()) {
            ZSetOperations.TypedTuple<String> typedTuple = iterator4.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }

        //遍历zset
        Cursor<ZSetOperations.TypedTuple<String>> cursor5 = redisTemplate.opsForZSet().scan("zzset1", ScanOptions.NONE);
        while (cursor5.hasNext()) {
            ZSetOperations.TypedTuple<String> item = cursor5.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }
    }

}
