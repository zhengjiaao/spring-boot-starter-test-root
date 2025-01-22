package com.zja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: zhengja
 * @Date: 2025-01-22 14:36
 */
@Controller
@RequestMapping("/locale")
public class LocaleController {

    @Autowired
    private MessageSource messageSource;

    // @Autowired
    // private LocaleResolver localeResolver;

    // http://127.0.0.1:8080/locale/hello?name=zhengja
    // http://127.0.0.1:8080/locale/hello?name=zhengja&lang=zh_CN
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name,
                        @RequestParam(value = "lang", defaultValue = "en") String lang,
                        Model model,
                        HttpServletRequest request) {
        // 自动识别
        // Locale locale = RequestContextUtils.getLocale(request);
        // 手动切换
        Locale locale = new Locale(lang); // messages_zh.properties
        // Locale locale = new Locale(lang,"CN"); // messages_zh_CN.properties

        String country = locale.getCountry();
        String language = locale.getLanguage();
        System.out.println("country:" + country);
        System.out.println("language:" + language);

        String greeting = messageSource.getMessage("greeting", new Object[]{name}, locale);
        model.addAttribute("greeting", greeting);
        return "hello"; // 返回 Thymeleaf 模板名称
    }

    @GetMapping("/locale")
    public String changeLocale(@RequestParam("lang") String lang,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        return "redirect:/locale/hello?name=" + request.getParameter("name") + "&lang=" + lang;
    }
}
