package com.windyoffice.springcachedemo.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.Signature;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    /**
     * 使用RSA签名
     */
    public static String signWithRSA(String content, String privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(RSAUtil.getPrivateKey(privateKey));
        signature.update(content.getBytes("utf-8"));
        byte[] signed = signature.sign();
        return RSAUtil.encryptBASE64(signed);
    }

    /**
     * 使用RSA验签
     */
    public static Boolean checkSignWithRSA(String content, String publicKey, String sign) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify(RSAUtil.getPublicKey(publicKey));
        signature.update(content.getBytes("utf-8"));
        return signature.verify(Base64.decode(sign));
    }
    /**
     * map 排序
     */
    public static Map sortMap(Map <String,String>map) {
        Map<String, String> sortedParams = new TreeMap<String, String>();
        sortedParams.putAll(map);
        return sortedParams;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap = RSAUtil.initKey();
        String publicKey = PropertiesUtil.getProperty("sign.publickey");
        String privateKey = PropertiesUtil.getProperty("sign.privatekey");
        String content = "zhangsan";
        String encryptContent = RSAUtil.encrypt(content,publicKey);
        String decryptContent = RSAUtil.decrypt(encryptContent,privateKey);
        System.out.println("生成的公钥：" + publicKey);
        System.out.println("生成的私钥：" + privateKey);
        System.out.println("加密后的数据：" + encryptContent);
        System.out.println("解密后的数据：" + decryptContent);

        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");

        String signcontent = content;
        String sign = signWithRSA(signcontent,privateKey);
        boolean check = checkSignWithRSA(signcontent,publicKey,sign);
        System.out.println("签名结果，sign:"+sign);
        System.out.println("验签结果，result:"+check);
    }
}
