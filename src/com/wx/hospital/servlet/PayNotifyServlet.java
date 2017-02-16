package com.wx.hospital.servlet;

import org.apache.log4j.Logger;
import org.sword.wechat4j.pay.PayManager;
import org.sword.wechat4j.pay.exception.PayApiException;
import org.sword.wechat4j.pay.exception.PayBusinessException;
import org.sword.wechat4j.pay.exception.SignatureException;
import org.sword.wechat4j.pay.protocol.pay_result_notify.PayResultNotifyResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzy on 2/16/17.
 */

@WebServlet("wcPayNotify")
public class PayNotifyServlet extends BaseServlet {

    Logger logger = Logger.getLogger(PayNotifyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PayResultNotifyResponse respond = null;
        try {
            PayManager.parsePayResultNotify(req, resp);
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (PayApiException e) {
            e.printStackTrace();
        } catch (PayBusinessException e) {
            e.printStackTrace();
        }
        String openId = respond.getOpenid();
        int totalFee = respond.getTotal_fee();
        logger.debug(openId + "已经成功支付了" + totalFee + "分！");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
