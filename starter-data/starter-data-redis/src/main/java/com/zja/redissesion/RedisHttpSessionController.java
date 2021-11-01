/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-31 16:47
 * @Since:
 */
package com.zja.redissesion;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * http://localhost:8080/swagger-ui/index.html#/
 *
 * 每次请求，同一个session过期时间会重新刷新
 */
@RestController
public class RedisHttpSessionController {

    @Autowired
    @Qualifier("redisSessionTemplate")
    private RedisTemplate redisSessionTemplate;

    /**
     * session 属性设置
     */
    @ApiOperation(value = "session 属性设置", notes = "自动创建session")
    @GetMapping("/session/setAttribute")
    public Object setAttribute(HttpServletRequest request) {

        HttpSession httpSession = request.getSession(true);

        System.out.println("设置属性httpSession-id: " + httpSession.getId());

        /**
         * session 属性会同时保存redis中
         */
        httpSession.setAttribute("name", "李四");
        httpSession.setAttribute("age", 22);

        /**
         * {@link EnableRedisHttpSession}
         *      FlushMode.ON_SAVE return 后将HttpSession 数据保存到reddis
         *      FlushMode.IMMEDIATE return 前将HttpSession 数据保存到reddis
         */
        return httpSession.getId();
    }

    /**
     * session 获取属性
     */
    @ApiOperation(value = "session 获取属性")
    @GetMapping("/session/getAttribute")
    public Object getAttribute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);
        System.out.println("获取属性：httpSession-id: " + httpSession.getId());

        System.out.println("name:" + httpSession.getAttribute("name"));
        System.out.println("age:" + httpSession.getAttribute("age"));
        return httpSession.getId();
    }

    /**
     * session 属性移除
     */
    @ApiOperation(value = "session 属性移除")
    @GetMapping("/session/removeAttribute")
    public Object removeAttribute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);

        System.out.println("属性移除 httpSession-id: " + httpSession.getId());
        //redis 中也会移除
        httpSession.removeAttribute("age");
        return httpSession.getId();
    }

    /**
     * session 立刻销毁
     */
    @ApiOperation(value = "session 销毁")
    @GetMapping("/session/invalidate")
    public Object invalidate(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(true);

        //redis 中也会删除
        httpSession.invalidate();
        System.out.println("销毁sessionid：" + httpSession.getId());
        return httpSession.getId();
    }

    /**
     * session 获取redis中的session
     */
    @ApiOperation(value = "Redis session 获取属性")
    @GetMapping("/session/{sessionId}")
    public Object invalidate(@PathVariable String sessionId) {
        //redis 中的session key
        String sessionIdKey = "spring:session:sessions:" + sessionId;

        Object name = redisSessionTemplate.opsForHash().get(sessionIdKey, "sessionAttr:name");
        Object age = redisSessionTemplate.opsForHash().get(sessionIdKey, "sessionAttr:age");
        System.out.println(name);
        System.out.println(age);
        return true;
    }

}
