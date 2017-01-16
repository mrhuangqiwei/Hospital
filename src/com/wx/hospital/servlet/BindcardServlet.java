package com.wx.hospital.servlet;

import bean.Brjbxxbean;
import checkutil.IsWeixinUser;

import com.alibaba.fastjson.JSON;

import jdbc.weinxinsql;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.oauth.OAuthManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.wx.hospital.HospitalConfig.SERVER_URL;

/**
 * Created by hzy on 1/16/17.
 * 绑卡的servlet， 如果使用bindcard?action=idcard将会提示输入身份证信息
 */
@WebServlet("/bindcard")
public class BindcardServlet extends BaseServlet {

    private static Logger logger = Logger.getLogger(BindcardServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String state = "bindcard";
        String page = "bind_card.html";
        if (action != null) {
            if (action.equals("idcard")) {
                state = "bindcard?action=idcard";
                page = "bind_idcard.html";
            }
        }
        String openId = getOpenId(req);
        if (openId == null || TextUtils.isBlank(openId)) {
            String redirectUrl = OAuthManager.generateRedirectURI(SERVER_URL + "oauth2",
                    "snsapi_userinfo", state);
            resp.sendRedirect(redirectUrl);
            return;
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String openId = getOpenId(req);
        if (openId == null || TextUtils.isBlank(openId)) {
            return;
        }
        String ylkh = getParam(req, "ylkh");
        String sfzh = getParam(req, "sfzh");
        System.out.print("dopost方法被调用");

        if (IsWeixinUser.IsFriend(openId, sfzh, ylkh)) {
            String nldw = "1";
            String brxm = getParam(req, "brxm");
            String brxb = getParam(req, "brxb");
            String brjtzz = getParam(req, "brjtzz");
            String lxdh = getParam(req, "lxdh");
            String nl = getParam(req, "nl");
            weinxinsql weinxinsql = new weinxinsql();
            boolean status = false;
            if (TextUtils.isEmpty(ylkh)) {
                status = weinxinsql.insertfriend(sfzh, brxm, nl, brxb, brjtzz, openId, lxdh, ylkh, nldw);
                
                
                System.out.print("----'"+sfzh+"'------'"+brxm+"'-------'"+nl+"'----'"+brxb+"'-----'"+openId+"'-");
            } else if (IsWeixinUser.IsFriendRegster(ylkh)) {
                String json = weinxinsql.getfriendinfotocheck(ylkh);
                String  json1=json.substring(1, json.length() - 1);
                Brjbxxbean bean = JSON.parseObject(json1, Brjbxxbean.class);
                status = weinxinsql.insertfriend(bean.getSfzh(), bean.getBrxm(), bean.getBrnl(), bean.getBrxb(),
                        bean.getJtzz(), openId, bean.getSj(), ylkh, bean.getBrnldw());
                System.out.print("医疗卡号相等");
                System.out.print("----'"+sfzh+"'------'"+brxm+"'-------'"+nl+"'----'"+brxb+"'-----'"+openId+"'-");
            }
            if (status) {
                resp.sendRedirect("homepage");
            } else {
                resp.sendRedirect("homepage");
            }
        } else {
            resp.sendRedirect("homepage");
            
            System.out.print("不满足条件");
        }
    }
}
