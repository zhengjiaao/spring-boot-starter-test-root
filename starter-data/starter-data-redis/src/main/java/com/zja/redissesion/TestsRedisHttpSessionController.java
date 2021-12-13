///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2021-10-31 16:47
// * @Since:
// */
//package com.zja.redissesion;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.session.SessionRepository;
//import org.springframework.session.data.redis.RedisOperationsSessionRepository;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
///**
// * 测试 会话示例
// *
// * swagger docs api：http://localhost:19000/swagger-ui.html#/
// *
// * 每次请求，同一个session过期时间会重新刷新(redis session也会刷新)
// */
//@Deprecated
//@RestController
//public class TestsRedisHttpSessionController {
//
//    @Autowired
//    @Qualifier("sessionRedisTemplate")
//    private RedisTemplate sessionRedisTemplate;
//
//    @Autowired
//    private SessionRepository sessionRepository;
//
//    @Autowired
//    private RedisOperationsSessionRepository redisOperationsSessionRepository;
//
//    /**
//     * session 属性设置
//     */
//    @ApiOperation(value = "session 属性设置", notes = "自动创建session")
//    @GetMapping("/session/setAttribute")
//    public Object setAttribute(HttpServletRequest request) {
//
//        HttpSession httpSession = request.getSession(true);
//
//        System.out.println("设置属性httpSession-id: " + httpSession.getId());
//
//        /**
//         * session 属性会同时保存redis中
//         */
//        httpSession.setAttribute("name", "李四");
//        httpSession.setAttribute("age", 22);
//
//        /**
//         * {@link EnableRedisHttpSession}
//         *      FlushMode.ON_SAVE return 后将HttpSession 数据保存到reddis
//         *      FlushMode.IMMEDIATE return 前将HttpSession 数据保存到reddis
//         */
//        return httpSession.getId();
//    }
//
//    /**
//     * session 获取属性
//     */
//    @ApiOperation(value = "session 获取属性", notes = "每次访问接口session过期时间会重新设置为MaxInactiveInterval值，包括redis session key也会刷新")
//    @GetMapping("/session/getAttribute")
//    public Object getAttribute(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession(true);
//        return HttpSessionDTO.builder()
//                .sessionId(httpSession.getId())
//                .attributeName((String) httpSession.getAttribute("name"))
//                .attributeAge((Integer) httpSession.getAttribute("age"))
//                .maxInactiveInterval(httpSession.getMaxInactiveInterval())
//                .lastAccessedTime(httpSession.getLastAccessedTime())
//                .build();
//    }
//
//    /**
//     * session 属性移除
//     */
//    @ApiOperation(value = "session 属性移除")
//    @GetMapping("/session/removeAttribute")
//    public Object removeAttribute(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession(true);
//        httpSession.removeAttribute("age");
//
//        return HttpSessionDTO.builder()
//                .sessionId(httpSession.getId())
//                .attributeName((String) httpSession.getAttribute("name"))
//                .attributeAge((Integer) httpSession.getAttribute("age"))
//                .maxInactiveInterval(httpSession.getMaxInactiveInterval())
//                .lastAccessedTime(httpSession.getLastAccessedTime())
//                .build();
//    }
//
//    /**
//     * session 立刻销毁
//     * 销毁的方式
//     *      默认时间到期
//     *      自己设定到期时间
//     *      立刻失效
//     *      关闭浏览器
//     *      关闭服务器
//     */
//    @ApiOperation(value = "session 销毁")
//    @GetMapping("/session/invalidate")
//    public Object invalidate(HttpServletRequest request) {
//        HttpSession httpSession = request.getSession(true);
//
//        //设置session时间失效
//        //注意：仅删除redis session过期，不会立即删除清空redis session存储的属性信息
////        httpSession.setMaxInactiveInterval(0);
//
//        //session销毁: 销毁浏览器Cookie与header中的sessionId
//        //注意：仅删除redis session过期，不会立即删除清空redis session存储的属性信息
//        httpSession.invalidate();
//
//        //session 销毁 获取不到属性信息，会报错
//        try {
//            return HttpSessionDTO.builder()
//                    .sessionId(httpSession.getId())
//                    .attributeName((String) httpSession.getAttribute("name"))
//                    .attributeAge((Integer) httpSession.getAttribute("age"))
//                    .maxInactiveInterval(httpSession.getMaxInactiveInterval())
//                    .lastAccessedTime(httpSession.getLastAccessedTime())
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "已销毁sessionId：" + httpSession.getId();
//        }
//    }
//
//    /**
//     * session 获取redis中的session
//     */
//    @ApiOperation(value = "Redis session 获取属性")
//    @GetMapping("/session/{sessionId}")
//    public Object invalidate(@PathVariable String sessionId) {
//        //redis 中的session key
//        String sessionIdKey = "spring:session:sessions:" + sessionId;
//
//        //不允许 此方法获取redis session 的相关属性
//        //原因：设置session失效后，redis session也会失效，但redis session属性 不会立即失效删除
//        Object name = sessionRedisTemplate.opsForHash().get(sessionIdKey, "sessionAttr:name");
//        Object age = sessionRedisTemplate.opsForHash().get(sessionIdKey, "sessionAttr:age");
//        Long expire = sessionRedisTemplate.getExpire(sessionIdKey);
//
//        return HttpSessionDTO.builder()
//                .sessionId(sessionId)
//                .attributeName((String) name)
//                .attributeAge((Integer) age)
//                .maxInactiveInterval(Math.toIntExact(expire))
//                .build();
//    }
//
//}
