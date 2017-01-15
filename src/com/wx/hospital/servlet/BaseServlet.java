package com.wx.hospital.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import static com.wx.hospital.HospitalConfig.COOKIES_OPENID;

/**
 * Created by hzy on 1/15/17.
 * 基本的Servlet， 增加了从cookie获取openid的通用方法而已
 */
public class BaseServlet extends HttpServlet {

    protected String getCookie(HttpServletRequest req, String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    protected String getOpenId(HttpServletRequest req) {
        return getCookie(req, COOKIES_OPENID);
    }
}
