package com.wx.hospital.servlet;

import com.wx.hospital.HospitalConfig;
import org.apache.log4j.Logger;
import org.sword.wechat4j.oauth.OAuthException;
import org.sword.wechat4j.oauth.OAuthManager;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wx.hospital.HospitalConfig.SERVER_URL;

/**
 * Created by hzy on 1/15/17.
 * 用于接收微信服务器认证后的回调， 主要是收取code和state，然后再去拿token，拿到token后将用户信息保存再跳转页面
 */
@WebServlet("/oauth2")
public class OAuth2Servlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(OAuthManager.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String state = req.getParameter("state");
        logger.info("call oath2: " + state);
        if (code == null) {
            logger.error("不同意授权!");
        } else {
            GetAccessTokenResponse token;
            try {
                token = OAuthManager.getAccessToken(new GetAccessTokenRequest(code));
                logger.info(token);
                Cookie cookie = new Cookie(HospitalConfig.COOKIES_OPENID, token.getOpenid());
                resp.addCookie(cookie);
                //resp.sendRedirect(SERVER_URL + state);
                resp.sendRedirect(SERVER_URL + state);
            } catch (OAuthException e) {
                e.printStackTrace();
                logger.error("获取token出错!");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
