package org.sword.wechat4j.oauth;

/**
 * OAuth API 调用异常
 * Created by xuwen on 2015-12-11.
 */
public class OAuthException extends Exception {

    private String errcode;
    private String errmsg;

    public OAuthException() {
    }
    public OAuthException(String errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "OAuthException{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
