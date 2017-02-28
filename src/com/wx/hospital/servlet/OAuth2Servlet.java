package com.wx.hospital.servlet;

import bean.WeiXinUserBean;
import com.wx.hospital.HospitalConfig;
import org.sword.wechat4j.oauth.OAuthException;
import org.sword.wechat4j.oauth.OAuthManager;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenResponse;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoRequest;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.wx.hospital.HospitalConfig.SERVER_URL;

/**
 * Created by hzy on 1/15/17.
 * 用于接收微信服务器认证后的回调， 主要是收取code和state，然后再去拿token，拿到token后将用户信息保存再跳转页面
 */
@WebServlet("/oauth2")
public class OAuth2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String state = req.getParameter("state");
        if (code == null) {
            System.out.println("不同意授权!");
        } else {
            GetAccessTokenResponse token;
            try {
                token = OAuthManager.getAccessToken(new GetAccessTokenRequest(code));
                getUserInfo(token,req, resp);
                resp.sendRedirect(SERVER_URL + state);
            } catch (OAuthException e) {
                e.printStackTrace();
                System.out.println("获取token出错!");
            }
        }
    }

    protected void getUserInfo(GetAccessTokenResponse token,HttpServletRequest req,  HttpServletResponse resp) throws OAuthException {
        GetUserinfoRequest request = new GetUserinfoRequest(token.getAccess_token(), token.getOpenid());
        GetUserinfoResponse user = OAuthManager.getUserinfo(request);

        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(3600);
        session.setAttribute("token", token);
        session.setAttribute("userInfo", user);

        WeiXinUserBean bean = new WeiXinUserBean();
        bean.setNickname(user.getNickname());
        bean.setOpenid(request.getOpenid());
        bean.setCity(user.getCity());
        bean.setCountry(user.getCountry());
        bean.setHeadimgurl(user.getHeadimgurl());
        bean.setLanguage(request.getLang());
        bean.setSex(String.valueOf(user.getSex()));

        Cookie cookie = new Cookie(HospitalConfig.COOKIES_OPENID, token.getOpenid());
        cookie.setMaxAge(14400);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
