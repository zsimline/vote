package org.vote.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5通用处理器
 */
public class MD5 {
  // 混淆秘钥
  private static final String key = "B7A3BC022423ADA34E5138A5A1CFC845";

  /**
   * MD5加密方法
   * 
   * @param text 明文
   * @return 密文
   */
  public static String md5(String text) {
    return DigestUtils.md5Hex(text + key);
  }

  /**
   * MD5验证方法
   * 
   * @param text 明文
   * @param md5  密文
   * @return 验证成功/失败
   */
  public static boolean verify(String text, String md5){
    String encryptStr = md5(text);
    return encryptStr.equalsIgnoreCase(md5) ? true : false;
  }
}
