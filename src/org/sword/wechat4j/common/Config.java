/**
 *
 */
package org.sword.wechat4j.common;

import com.wx.hospital.HospitalConfig;
import org.apache.log4j.Logger;

/**
 * @author ChengNing
 * @date 2014年12月8日
 */
public class Config {

    private static Logger logger = Logger.getLogger(Config.class);

    private static String url = "";
    private static String token = HospitalConfig.TOKEN;
    private static String encodingAESKey = "";
    private static String appid = HospitalConfig.APP_ID;
    private static String appSecret = HospitalConfig.APP_SECRET;

    //微信支付_商户ID
    private static String mchId = "";
    //微信支付_商户密钥
    private static String mchKey = "";

    private static String accessTokenServer = "";
    private static String jsApiTicketServer = "";
    private static Config config = new Config();

    private Config() {
    }

    public static Config instance() {
        return config;
    }

    public String getToken() {
        return token;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public String getUrl() {
        return url;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public String getAccessTokenServer() {
        return accessTokenServer;
    }

    public String getJsApiTicketServer() {
        return jsApiTicketServer;
    }


}
