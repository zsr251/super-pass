package com.javasoso.pass.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加解密
 *
 * @author jasonzhu
 * @date 2019-06-09
 */
@Slf4j
public class AESUtil {
    /**
     * 加密
     *
     * @param src 加密字符串
     * @param key 密钥
     * @return 加密后的字符串
     */
    public static String encrypt(String src, String key) throws Exception {
        // 判断密钥是否为空
        if (key == null) {
            log.error("密钥不能为空");
            return null;
        }

        // 密钥补位
        int plus = 16 - key.length();
        byte[] data = key.getBytes("utf-8");
        byte[] raw = new byte[16];
        byte[] plusbyte = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
        for (int i = 0; i < 16; i++) {
            if (data.length > i)
                raw[i] = data[i];
            else
                raw[i] = plusbyte[0];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // 算法/模式/补码方式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
        //十六进制
        return HexUtil.encode(encrypted);
    }

    /**
     * 解密
     *
     * @param src 解密字符串
     * @param key 密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String src, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }

            // 密钥补位
            int plus = 16 - key.length();
            byte[] data = key.getBytes("utf-8");
            byte[] raw = new byte[16];
            byte[] plusbyte = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
            for (int i = 0; i < 16; i++) {
                if (data.length > i)
                    raw[i] = data[i];
                else
                    raw[i] = plusbyte[0];
            }

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            //byte[] encrypted1 = new Base64().decode(src);//base64
            //十六进制
            byte[] encrypted1 = HexUtil.decode(src);

            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        String c = "Zsr25!e1em";
        String key = "E874ADEB-6A70-41FB-80AF-C62A7699BA4B";

        String s = encrypt(c,key);
        System.out.println(s);
        System.out.println(decrypt(s,key));

         c = "abc";
         key = "123";
         s = encrypt(c,key);
        System.out.println(s);
        System.out.println(decrypt(s,key));


    }

}
