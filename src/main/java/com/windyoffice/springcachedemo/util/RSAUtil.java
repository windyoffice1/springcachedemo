package com.windyoffice.springcachedemo.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    private static String KEY_ALGORITHM = "RSA";
    private static String CHARSET_NAME = "UTF-8";
    private static String PUBLIC_KEY = "RSAPublicKey";
    private static String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 根据keyMap获取公钥字符串
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 根据keyMap获取私钥字符串
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化秘钥
     */
    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY,rsaPublicKey);
        keyMap.put(PRIVATE_KEY,rsaPrivateKey);
        return keyMap;
    }

    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception{
            byte[ ] keyBytes= Base64.decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[ ] keyBytes= Base64.decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 公钥加密
     */
    public static String encrypt(String content,String public_key) throws Exception {
        PublicKey publicKey = getPublicKey(public_key);
        Cipher cipher=Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(content.getBytes(CHARSET_NAME));
        return Base64.encode(result);
    }

    /**
     * 私钥解密
     */
    public static String decrypt(String content,String private_key) throws Exception {
        byte[] decodeContent = Base64.decode(content);
        PrivateKey privateKey = getPrivateKey(private_key);
        Cipher cipher=Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(decodeContent);
        return new String(result);
    }


    /**
     * 编码返回字符串
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encode(key);
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> keyMap = initKey();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        String content = "zhangsan";
        String encryptContent = encrypt(content,publicKey);
        String decryptContent = decrypt(encryptContent,privateKey);
        System.out.println("生成的公钥：" + publicKey);
        System.out.println("生成的私钥：" + privateKey);
        System.out.println("加密后的数据：" + encryptContent);
        System.out.println("解密后的数据：" + decryptContent);
        String sign = SignUtil.signWithRSA(content,privateKey);
        boolean check = SignUtil.checkSignWithRSA(content,publicKey,sign);
        System.out.println("签名结果，sign:"+sign);
        System.out.println("验签结果，result:"+check);
    }
}