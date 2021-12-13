///**
// * @Company: 上海数慧系统技术有限公司
// * @Department: 数据中心
// * @Author: 郑家骜[ào]
// * @Email: zhengja@dist.com.cn
// * @Date: 2021-11-22 16:11
// * @Since:
// */
//package com.zja.redissesion;
//
//import org.springframework.session.Session;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * 同时支持：cookies和header策略 存储session信息
// *
// * 在写sso单点登录时发现spring session往客户端写sessionID的策略要么是cookies要么是header，不能同时支持
// *
// * PC端: 一般是使用cookies,这样可以实现单点登录(不过还是没有解决跨域，要跨域只能用cas单点登录方案)
// * 手机app: 不支持cookies，只能使用header
// *
// * A {@link HttpSessionStrategy} that uses a header to obtain the session from.
// *
// * Specifically, this implementation will allow specifying a header name using
// *  * {@link HeaderAndCookieHttpSessionStrategy#setHeaderName(String)}. The default is "x-auth-token".
// *
// *  Specifically, this implementation will allow specifying a cookie name using
// *  * {@link HeaderAndCookieHttpSessionStrategy#setCookieName(String)}. The default is "SESSION".
// *
// * 参考：{@link org.springframework.session.web.http.HttpSessionStrategy}
// *      {@link org.springframework.session.web.http.CookieHttpSessionStrategy} 默认策略
// *      {@link org.springframework.session.web.http.HeaderHttpSessionStrategy} 可选策略
// *
// */
//public class HeaderAndCookieHttpSessionStrategy implements HttpSessionStrategy, MultiHttpSessionStrategy, HttpSessionManager {
// /*   *//**
//     * 默认headerName：存储sessionId信息
//     *//*
//    private String headerName = "x-auth-token";
//
//    *//**
//     * The default delimiter for both serialization and deserialization.
//     *//*
//    private static final String DEFAULT_DELIMITER = " ";
//
//    private static final String SESSION_IDS_WRITTEN_ATTR = CookieHttpSessionStrategy.class
//            .getName().concat(".SESSIONS_WRITTEN_ATTR");
//
//    static final String DEFAULT_ALIAS = "0";
//
//    static final String DEFAULT_SESSION_ALIAS_PARAM_NAME = "_s";
//
//    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[\\w-]{1,50}$");
//
//    private String sessionParam = DEFAULT_SESSION_ALIAS_PARAM_NAME;
//
//    private CookieSerializer cookieSerializer = new DefaultCookieSerializer();
//
//    *//**
//     * The delimiter between a session alias and a session id when reading a cookie value.
//     * The default value is " ".
//     *//*
//    private String deserializationDelimiter = DEFAULT_DELIMITER;
//
//    *//**
//     * The delimiter between a session alias and a session id when writing a cookie value.
//     * The default is " ".
//     *//*
//    private String serializationDelimiter = DEFAULT_DELIMITER;
//
//    *//**
//     * 获取session信息
//     * @param request
//     * @return sessionId
//     *//*
//    @Override
//    public String getRequestedSessionId(HttpServletRequest request) {
//        //获取请求头中的session信息
//        String header = request.getHeader(this.headerName);
//        if (!StringUtils.isEmpty(header)) {
//            return header;
//        }
//        //获取cookie中的session信息
//        Map<String, String> sessionIds = getSessionIds(request);
//        String sessionAlias = getCurrentSessionAlias(request);
//        return sessionIds.get(sessionAlias);
//    }
//
//    *//**
//     * session 新创建时执行
//     * @param session
//     * @param request
//     * @param response
//     *//*
//    @Override
//    public void onNewSession(Session session, HttpServletRequest request,
//                             HttpServletResponse response) {
//        //将session信息设置到请求头中
//        response.setHeader(this.headerName, session.getId());
//
//        //将session信息放到Cookie中
//        Set<String> sessionIdsWritten = getSessionIdsWritten(request);
//        if (sessionIdsWritten.contains(session.getId())) {
//            return;
//        }
//        sessionIdsWritten.add(session.getId());
//
//        Map<String, String> sessionIds = getSessionIds(request);
//        String sessionAlias = getCurrentSessionAlias(request);
//        sessionIds.put(sessionAlias, session.getId());
//
//        String cookieValue = createSessionCookieValue(sessionIds);
//        this.cookieSerializer
//                .writeCookieValue(new CookieSerializer.CookieValue(request, response, cookieValue));
//    }
//
//    *//**
//     * session销毁时执行
//     * @param request
//     * @param response
//     *//*
//    @Override
//    public void onInvalidateSession(HttpServletRequest request,
//                                    HttpServletResponse response) {
//        //将header中的session信息删除
//        response.setHeader(this.headerName, "");
//
//        //将Cookie中的session信息删除
//        Map<String, String> sessionIds = getSessionIds(request);
//        String requestedAlias = getCurrentSessionAlias(request);
//        sessionIds.remove(requestedAlias);
//
//        String cookieValue = createSessionCookieValue(sessionIds);
//        this.cookieSerializer
//                .writeCookieValue(new CookieSerializer.CookieValue(request, response, cookieValue));
//    }
//
//
//    //--------------------------------支持header-------------------------------
//
//    *//**
//     * The name of the header to obtain the session id from. Default is "x-auth-token".
//     *
//     * @param headerName the name of the header to obtain the session id from.
//     *//*
//    public void setHeaderName(String headerName) {
//        Assert.notNull(headerName, "headerName cannot be null");
//        this.headerName = headerName;
//    }
//
//
//    //--------------------------------支持cookie-------------------------------
//
//    *//**
//     * Sets the name of the HTTP parameter that is used to specify the session alias. If
//     * the value is null, then only a single session is supported per browser.
//     *
//     * @param sessionAliasParamName the name of the HTTP parameter used to specify the
//     * session alias. If null, then ony a single session is supported per browser.
//     *//*
//    public void setSessionAliasParamName(String sessionAliasParamName) {
//        this.sessionParam = sessionAliasParamName;
//    }
//
//    *//**
//     * Sets the {@link CookieSerializer} to be used.
//     *
//     * @param cookieSerializer the cookieSerializer to set. Cannot be null.
//     *//*
//    public void setCookieSerializer(CookieSerializer cookieSerializer) {
//        Assert.notNull(cookieSerializer, "cookieSerializer cannot be null");
//        this.cookieSerializer = cookieSerializer;
//    }
//
//    *//**
//     * Sets the name of the cookie to be used.
//     * @param cookieName the name of the cookie to be used
//     * @deprecated use {@link #setCookieSerializer(CookieSerializer)}
//     *//*
//    @Deprecated
//    public void setCookieName(String cookieName) {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName(cookieName);
//        this.cookieSerializer = serializer;
//    }
//
//    @SuppressWarnings("unchecked")
//    private Set<String> getSessionIdsWritten(HttpServletRequest request) {
//        Set<String> sessionsWritten = (Set<String>) request
//                .getAttribute(SESSION_IDS_WRITTEN_ATTR);
//        if (sessionsWritten == null) {
//            sessionsWritten = new HashSet<String>();
//            request.setAttribute(SESSION_IDS_WRITTEN_ATTR, sessionsWritten);
//        }
//        return sessionsWritten;
//    }
//
//    private String createSessionCookieValue(Map<String, String> sessionIds) {
//        if (sessionIds.isEmpty()) {
//            return "";
//        }
//        if (sessionIds.size() == 1 && sessionIds.keySet().contains(DEFAULT_ALIAS)) {
//            return sessionIds.values().iterator().next();
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        for (Map.Entry<String, String> entry : sessionIds.entrySet()) {
//            String alias = entry.getKey();
//            String id = entry.getValue();
//
//            buffer.append(alias);
//            buffer.append(this.serializationDelimiter);
//            buffer.append(id);
//            buffer.append(this.serializationDelimiter);
//        }
//        buffer.deleteCharAt(buffer.length() - 1);
//        return buffer.toString();
//    }
//
//    //----------------------CookieHttpSessionStrategy implements MultiHttpSessionStrategy 功能
//
//    @Override
//    public HttpServletRequest wrapRequest(HttpServletRequest request,
//                                          HttpServletResponse response) {
//        request.setAttribute(HttpSessionManager.class.getName(), this);
//        return request;
//    }
//
//    @Override
//    public HttpServletResponse wrapResponse(HttpServletRequest request,
//                                            HttpServletResponse response) {
//        return new HeaderAndCookieHttpSessionStrategy.MultiSessionHttpServletResponse(response, request);
//    }
//
//    *//**
//     * A {@link HeaderAndCookieHttpSessionStrategy} aware {@link HttpServletResponseWrapper}.
//     *//*
//    class MultiSessionHttpServletResponse extends HttpServletResponseWrapper {
//        private final HttpServletRequest request;
//
//        MultiSessionHttpServletResponse(HttpServletResponse response,
//                                        HttpServletRequest request) {
//            super(response);
//            this.request = request;
//        }
//
//        private String getCurrentSessionAliasFromUrl(String url) {
//            String currentSessionAliasFromUrl = null;
//            int queryStart = url.indexOf("?");
//
//            if (queryStart >= 0) {
//                String query = url.substring(queryStart + 1);
//                Matcher matcher = Pattern
//                        .compile(String.format("%s=([^&]+)",
//                                HeaderAndCookieHttpSessionStrategy.this.sessionParam))
//                        .matcher(query);
//
//                if (matcher.find()) {
//                    currentSessionAliasFromUrl = matcher.group(1);
//                }
//            }
//
//            return currentSessionAliasFromUrl;
//        }
//
//        @Override
//        public String encodeRedirectURL(String url) {
//            String encodedUrl = super.encodeRedirectURL(url);
//            String currentSessionAliasFromUrl = getCurrentSessionAliasFromUrl(encodedUrl);
//            String alias = (currentSessionAliasFromUrl != null)
//                    ? currentSessionAliasFromUrl : getCurrentSessionAlias(this.request);
//
//            return HeaderAndCookieHttpSessionStrategy.this.encodeURL(encodedUrl, alias);
//        }
//
//        @Override
//        public String encodeURL(String url) {
//            String encodedUrl = super.encodeURL(url);
//            String currentSessionAliasFromUrl = getCurrentSessionAliasFromUrl(encodedUrl);
//            String alias = (currentSessionAliasFromUrl != null)
//                    ? currentSessionAliasFromUrl : getCurrentSessionAlias(this.request);
//
//            return HeaderAndCookieHttpSessionStrategy.this.encodeURL(encodedUrl, alias);
//        }
//    }
//
//    //----------------------CookieHttpSessionStrategy implements HttpSessionManager 功能
//
//    @Override
//    public String encodeURL(String url, String sessionAlias) {
//        String encodedSessionAlias = urlEncode(sessionAlias);
//        int queryStart = url.indexOf("?");
//        boolean isDefaultAlias = DEFAULT_ALIAS.equals(encodedSessionAlias);
//        if (queryStart < 0) {
//            return isDefaultAlias ? url
//                    : url + "?" + this.sessionParam + "=" + encodedSessionAlias;
//        }
//        String path = url.substring(0, queryStart);
//        String query = url.substring(queryStart + 1, url.length());
//        String replacement = isDefaultAlias ? "" : "$1" + encodedSessionAlias;
//        query = query.replaceFirst("((^|&)" + this.sessionParam + "=)([^&]+)?",
//                replacement);
//        String sessionParamReplacement = String.format("%s=%s", this.sessionParam,
//                encodedSessionAlias);
//
//        if (!isDefaultAlias && !query.contains(sessionParamReplacement)
//                && url.endsWith(query)) {
//            // no existing alias
//            if (!(query.endsWith("&") || query.length() == 0)) {
//                query += "&";
//            }
//            query += sessionParamReplacement;
//        }
//
//        return path + "?" + query;
//    }
//
//    @Override
//    public String getNewSessionAlias(HttpServletRequest request) {
//        Set<String> sessionAliases = getSessionIds(request).keySet();
//        if (sessionAliases.isEmpty()) {
//            return DEFAULT_ALIAS;
//        }
//        long lastAlias = Long.decode(DEFAULT_ALIAS);
//        for (String alias : sessionAliases) {
//            long selectedAlias = safeParse(alias);
//            if (selectedAlias > lastAlias) {
//                lastAlias = selectedAlias;
//            }
//        }
//        return Long.toHexString(lastAlias + 1);
//    }
//
//    @Override
//    public Map<String, String> getSessionIds(HttpServletRequest request) {
//        List<String> cookieValues = this.cookieSerializer.readCookieValues(request);
//        String sessionCookieValue = cookieValues.isEmpty() ? ""
//                : cookieValues.iterator().next();
//        Map<String, String> result = new LinkedHashMap<String, String>();
//        StringTokenizer tokens = new StringTokenizer(sessionCookieValue,
//                this.deserializationDelimiter);
//        if (tokens.countTokens() == 1) {
//            result.put(DEFAULT_ALIAS, tokens.nextToken());
//            return result;
//        }
//        while (tokens.hasMoreTokens()) {
//            String alias = tokens.nextToken();
//            if (!tokens.hasMoreTokens()) {
//                break;
//            }
//            String id = tokens.nextToken();
//            result.put(alias, id);
//        }
//        return result;
//    }
//
//    @Override
//    public String getCurrentSessionAlias(HttpServletRequest request) {
//        if (this.sessionParam == null) {
//            return DEFAULT_ALIAS;
//        }
//        String u = request.getParameter(this.sessionParam);
//        if (u == null) {
//            return DEFAULT_ALIAS;
//        }
//        if (!ALIAS_PATTERN.matcher(u).matches()) {
//            return DEFAULT_ALIAS;
//        }
//        return u;
//    }
//
//    private String urlEncode(String value) {
//        try {
//            return URLEncoder.encode(value, "UTF-8");
//        }
//        catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private long safeParse(String hex) {
//        try {
//            return Long.decode("0x" + hex);
//        }
//        catch (NumberFormatException notNumber) {
//            return 0;
//        }
//    }*/
//}
