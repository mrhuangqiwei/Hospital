package com.wx.hospital;

import bean.WeiXinUserBean;
import checkutil.IsWeixinUser;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.sword.wechat4j.WechatSupport;
import org.sword.wechat4j.user.User;
import org.sword.wechat4j.user.UserManager;
import po.AccessToken;
import utils.WeiXinUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hzy on 1/15/17.
 * 请填写接口配置信息，此信息需要你有自己的服务器资源，填写的URL需要正确响应微信发送的Token验证，请阅读消息接口使用指南。
 */
public class HospitalSupport extends WechatSupport {
    /**
     * 构建微信处理
     *
     * @param request 微信服务发过来的http请求
     */
    public HospitalSupport(HttpServletRequest request) {
        super(request);
    }

    @Override
    protected void onText() {
        String content = "您发来的消息是: " + wechatRequest.getContent();
        responseText(content);
    }

    @Override
    protected void onImage() {

    }

    @Override
    protected void onVoice() {

    }

    @Override
    protected void onVideo() {

    }

    @Override
    protected void onShortVideo() {

    }

    @Override
    protected void onLocation() {

    }

    @Override
    protected void onLink() {

    }

    @Override
    protected void onUnknown() {

    }

    @Override
    protected void click() {

    }

    @Override
    protected void subscribe() {
        String openId = wechatRequest.getFromUserName();
        UserManager manager = new UserManager();
        User user = manager.getUserInfo(openId);
        WeiXinUserBean bean = new WeiXinUserBean();
        bean.setNickname(user.getNickName());
        bean.setOpenid(openId);
        bean.setCity(user.getCity());
        bean.setCountry(user.getCountry());
        bean.setGroupid(String.valueOf(user.getGroupId()));
        bean.setHeadimgurl(user.getHeadimgUrl());
        bean.setLanguage(user.getLanguage().toString());
        bean.setRemark(user.getRemark());
        bean.setSex(String.valueOf(user.getSex()));
        bean.setSubscribe(String.valueOf(user.getSubscribe()));
        bean.setSubscribe_time(user.getSubscribeTime());
        boolean k1 = IsWeixinUser.Isweixinuser(bean);
        responseText("您好，欢迎你关注普定县人民医院！我们将为你提供医学专业知识，分享品质生活资讯，" +
                "让您了解医院新闻和动态，还可推荐医院相关专家，为您提供更专业的治疗方案！");
    }

    @Override
    protected void unSubscribe() {
        String content = "用户取消关注了: " + wechatRequest.getFromUserName();
        responseText(content);
    }

    @Override
    protected void scan() {

    }

    @Override
    protected void location() {

    }

    @Override
    protected void view() {

    }

    @Override
    protected void templateMsgCallback() {

    }

    @Override
    protected void scanCodePush() {

    }

    @Override
    protected void scanCodeWaitMsg() {

    }

    @Override
    protected void picSysPhoto() {

    }

    @Override
    protected void picPhotoOrAlbum() {

    }

    @Override
    protected void picWeixin() {

    }

    @Override
    protected void locationSelect() {

    }

    @Override
    protected void kfCreateSession() {

    }

    @Override
    protected void kfCloseSession() {

    }

    @Override
    protected void kfSwitchSession() {

    }
}
