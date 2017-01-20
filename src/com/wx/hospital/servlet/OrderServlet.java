package com.wx.hospital.servlet;

import org.sword.wechat4j.pay.PayManager;
import org.sword.wechat4j.pay.exception.PayApiException;
import org.sword.wechat4j.pay.exception.PayBusinessException;
import org.sword.wechat4j.pay.exception.SignatureException;
import org.sword.wechat4j.pay.protocol.unifiedorder.UnifiedorderRequest;
import org.sword.wechat4j.pay.protocol.unifiedorder.UnifiedorderResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzy on 1/20/17.
 * 用于处理预约挂号订单相关的事物
 */
@WebServlet("/fillorder")
public class OrderServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("fill_order.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqType = req.getParameter("type");
        if (reqType == null) {
            resp.getWriter().write("error request!");
            return;
        }
        if (reqType.equals("weixin_pay")) {
            String openId = req.getParameter("openid");
            UnifiedorderRequest request = new UnifiedorderRequest();
            request.setOpenid(openId);
            try {
                UnifiedorderResponse respond = PayManager.unifiedorder(request);
            } catch (SignatureException | PayApiException | PayBusinessException e) {
                e.printStackTrace();
            }
        }
    }
}
