package com.wx.hospital.servlet;

import com.wx.hospital.HospitalSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzy on 1/15/17.
 * 这个是服务器验证接口，填写接口配置url的时候填上/support就行了
 * 请填写接口配置信息，此信息需要你有自己的服务器资源，填写的URL需要正确响应微信发送的Token验证，请阅读消息接口使用指南。
 */

@WebServlet("/support")
public class SupportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HospitalSupport support = new HospitalSupport(req);
        String result = support.execute();
        resp.getOutputStream().write(result.getBytes());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
