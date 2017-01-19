package com.wx.hospital.servlet;

import org.apache.http.util.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hzy on 1/18/17.
 * 这个是医生排班表相关的Servlet
 */
@WebServlet("/arrange")
public class ArrangeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classId = req.getParameter("id");
        if(!TextUtils.isEmpty(classId)) {
            Cookie cookie = new Cookie("class_id", classId);
            resp.addCookie(cookie);
            req.getRequestDispatcher("arrange.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
