package com.wx.hospital.servlet;

import org.apache.http.util.TextUtils;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hzy on 2/25/17.
 */
@WebServlet("/getOpenId")
public class OpenIdServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");//浏览器编码
        String openId = getOpenId(req);
        if (TextUtils.isEmpty(openId)) {
            try {
                HttpSession session = req.getSession();
                if (session != null) {
                    GetUserinfoResponse user = (GetUserinfoResponse) session.getAttribute("userInfo");
                    openId = user.getOpenid();
                }
            } catch (Exception e) {
            }
        }
        resp.getWriter().print(String.format("{\"openId\":\"%s\"}", openId));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
