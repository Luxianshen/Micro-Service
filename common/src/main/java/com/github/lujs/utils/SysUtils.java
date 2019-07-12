package com.github.lujs.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import org.bouncycastle.util.encoders.Base64;

/**
 * @Description: 系统工具类
 * @Author: lujs
 * @Data: 2019/5/18 8:34
 * @version: 1.0.0
 */

public class SysUtils {

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/NOPadding";

    /**
     * des解密
     *
     * @param data data
     * @param pass pass
     * @return String
     * @author tangyi
     * @date 2019/03/18 11:39
     */
    public static String decryptAES(String data, String pass) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM), new IvParameterSpec(pass.getBytes()));
        byte[] result = cipher.doFinal(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return new String(result, StandardCharsets.UTF_8);
    }
}
