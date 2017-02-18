package com.wx.hospital.servlet;

import com.alibaba.fastjson.JSON;

import org.sword.wechat4j.jsapi.JsApiManager;
import org.sword.wechat4j.jsapi.JsApiParam;
import org.sword.wechat4j.pay.PayManager;
import org.sword.wechat4j.pay.exception.PayApiException;
import org.sword.wechat4j.pay.exception.PayBusinessException;
import org.sword.wechat4j.pay.exception.SignatureException;
import org.sword.wechat4j.pay.protocol.unifiedorder.UnifiedorderRequest;
import org.sword.wechat4j.pay.protocol.unifiedorder.UnifiedorderResponse;
import org.sword.wechat4j.util.RandomStringGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wx.hospital.HospitalConfig.SERVER_URL;

/**
 * Created by hzy on 1/20/17.
 * 用于处理预约挂号订单相关的事物
 */
@WebServlet("/fillorder")
public class OrderServlet extends BaseServlet {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

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
        /** 微信支付*/
        if (reqType.equals("weixin_pay")) {
        	System.out.println("请求支付");
            UnifiedorderRequest request = new UnifiedorderRequest();
            String openId = req.getParameter("openid");
            request.setOpenid(openId);
            request.setBody("挂号费用");
            String tradeNo = simpleDateFormat.format(new Date()) + openId + RandomStringGenerator.generate(5);
            request.setOut_trade_no(tradeNo);
            int totalFee = (int) (Float.parseFloat(req.getParameter("money")) * 100);
            request.setTotal_fee(totalFee);
            String spbill_create_ip = req.getRemoteAddr();
            request.setSpbill_create_ip(spbill_create_ip);
            request.setTrade_type("JSAPI");
            String notifyUrl = SERVER_URL + "wcPayNotify";
            request.setNotify_url(notifyUrl);
            try {
                UnifiedorderResponse respond = PayManager.unifiedorder(request);
                String resultString = JSON.toJSONString(respond);
                System.out.println(resultString);
                resp.getWriter().write(resultString);
            } catch (SignatureException | PayApiException | PayBusinessException e) {
                e.printStackTrace();
            }
        } else if (reqType.equals("get_jsapi_params")) {
            String url = SERVER_URL + "fillorder";
            JsApiParam params = JsApiManager.signature(url);
            resp.getWriter().write(JSON.toJSONString(params));
            System.out.println("请求jsapi参数");
        }
    }
}
