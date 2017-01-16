/**
 *
 */
package org.sword.wechat4j.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 用于微信的前面验证
 *
 * @author ChengNing
 * @date 2014-12-4
 */
public class ValidateSignature {

    private String signature;
    private String timestamp;
    private String nonce;
    private String token;

    /**
     * 前面验证构造
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     */
    public ValidateSignature(String signature, String timestamp, String nonce, String token) {
        this.signature = signature;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.token = token;
    }

    /**
     * 验证
     *
     * @return true 验证通过，false 验证失败
     */
    public boolean check() {
        String sha1;
        try {
            sha1 = encode();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return sha1.equals(this.signature);
    }

    /**
     * 得到加密后的数据
     *
     * @return
     */
    private String encode() {
        List<String> params = new LinkedList<String>();
        if (this.token != null) {
            params.add(this.token);
        }
        if (this.timestamp != null) {
            params.add(this.timestamp);
        }
        if (this.nonce != null) {
            params.add(this.nonce);
        }
        Collections.sort(params);
        StringBuilder buffer = new StringBuilder();
        for (String param : params) {
            buffer.append(param);
        }
        return DigestUtils.sha1Hex(buffer.toString());
    }
}
