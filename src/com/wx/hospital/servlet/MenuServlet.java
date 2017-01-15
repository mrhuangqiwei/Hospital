package com.wx.hospital.servlet;

import com.alibaba.fastjson.JSON;
import org.sword.wechat4j.event.EventType;
import org.sword.wechat4j.exception.WeChatException;
import org.sword.wechat4j.menu.Menu;
import org.sword.wechat4j.menu.MenuButton;
import org.sword.wechat4j.menu.MenuManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.wx.hospital.HospitalConfig.SERVER_URL;

/**
 * Created by hzy on 1/15/17.
 * 用于查看和配置按钮
 * 配置按钮用参数访问： /configMenu?action=create
 */

@WebServlet("/configMenu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionType = req.getParameter("action");
        if (actionType != null && actionType.equals("create")) {
            createMenus(resp);
        } else {
            showMenus(resp);
        }
    }

    private void createMenusFromString(HttpServletResponse resp) throws IOException {
        MenuManager manager = new MenuManager();
        String menuString = "";
        try {
            String result = manager.create(menuString);
            resp.getWriter().write(result);
        } catch (WeChatException e) {
            e.printStackTrace();
        }
    }

    private void showMenus(HttpServletResponse resp) throws IOException {
        MenuManager manager = new MenuManager();
        Menu menu = manager.getMenu();
        resp.getWriter().print(JSON.toJSONString(menu));
    }

    private void createMenus(HttpServletResponse resp) throws IOException {
        MenuManager manager = new MenuManager();
        Menu menu = new Menu();

        List<MenuButton> buttons = new LinkedList<MenuButton>();

        MenuButton button = new MenuButton();
        button.setName("首页");
        button.setType(EventType.view);
        button.setUrl(SERVER_URL + "homepage");
        buttons.add(button);

        button = new MenuButton();
        button.setName("百度");
        button.setType(EventType.view);
        button.setUrl("http://www.baidu.com");
        buttons.add(button);

        menu.setButton(buttons);

        try {
            String result = manager.create(menu);
            resp.getWriter().write(result);
        } catch (WeChatException e) {
            e.printStackTrace();
        }
    }
}
