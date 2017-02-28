package com.wx.hospital.servlet;

import com.alibaba.fastjson.JSON;
import org.sword.wechat4j.jsapi.JsApiManager;
import org.sword.wechat4j.jsapi.JsApiParam;
import org.sword.wechat4j.pay.H5PayParam;
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
 * Created by hzy on 2/28/17.
 */
@WebServlet("wechatPay")
public class WechatPayServlet extends BaseServlet {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqType = req.getParameter("type");
        if (reqType == null) {
            resp.getWriter().write("error request!");
            return;
        }
        if (reqType.equals("weixin_pay")) {
            UnifiedorderRequest request = new UnifiedorderRequest();
            String openId = req.getParameter("openid");
            request.setOpenid(openId);
            request.setBody("Pay to Hospital!");
            String nonce_str = RandomStringGenerator.generate();
            request.setNonce_str(nonce_str);
            String tradeNo = simpleDateFormat.format(new Date()) + RandomStringGenerator.generate(10);
            request.setOut_trade_no(tradeNo);
            int totalFee = (int) (Float.parseFloat(req.getParameter("money")) * 100);
            totalFee = 1;
            request.setTotal_fee(totalFee);
            String spbill_create_ip = req.getRemoteAddr();
            request.setSpbill_create_ip(spbill_create_ip);
            request.setTrade_type("JSAPI");
            String notifyUrl = SERVER_URL + "wcPayNotify";
            request.setNotify_url(notifyUrl);
            try {
                /** 开始请求*/
                UnifiedorderResponse respond = PayManager.unifiedorder(request);
                String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
                String nonceStr = respond.getNonce_str();
                String prepayId = respond.getPrepay_id();
                H5PayParam param = PayManager.buildH5PayConfig(timeStamp, nonceStr, prepayId);
                String resultString = JSON.toJSONString(param);
                resp.getWriter().write(resultString);
            } catch (SignatureException | PayApiException | PayBusinessException e) {
                e.printStackTrace();
            }
        } else if (reqType.equals("get_jsapi_params")) {
            String url = req.getParameter("url");
            JsApiParam params = JsApiManager.signature(url);
            String jsonString = JSON.toJSONString(params);
            resp.getWriter().write(jsonString);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
