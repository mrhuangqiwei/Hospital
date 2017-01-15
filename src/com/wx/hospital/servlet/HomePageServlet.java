package com.wx.hospital.servlet;

import org.apache.http.util.TextUtils;
import org.sword.wechat4j.oauth.OAuthManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wx.hospital.HospitalConfig.SERVER_URL;


/**
 * Created by hzy on 1/15/17.
 * 这个页面用于显示index.html，但是显示前会先判断cookies是否存在openid
 * 不存在的话会发起认证请求重定向到OAuth2Servlet去验证用户信息
 */
@WebServlet("/homepage")
public class HomePageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String openId = getOpenId(req);
        if (openId == null || TextUtils.isBlank(openId)) {
            String redirectUrl = OAuthManager.generateRedirectURI(SERVER_URL + "oauth2",
                    "snsapi_userinfo", "index.html");
            resp.sendRedirect(redirectUrl);
            return;
        }
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
