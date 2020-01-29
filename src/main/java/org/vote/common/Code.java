package org.vote.common;

import java.util.HashMap;

/**
 * Servlet 返回码类
 */
public class Code {
  // 返回码
  private int code;

  // 返回码说明
  private String codeDesc;

  // 返回码映射
  private static HashMap<Integer, String> codes;

  public static void initialize() {
    codes = new HashMap<Integer, String>();

    // 发布投票功能返回码
    codes.put(1000, "发布投票成功");
    codes.put(1001, "发布投票失败原因未知");
    codes.put(1002, "图片文件后缀名只能为.jpg/.png");
    codes.put(1003, "无权执行操作");

    // 删除投票功能返回码
    codes.put(1900, "删除投票成功");
    codes.put(1901, "删除投票失败原因未知");
    codes.put(1902, "无权执行操作");

    // 用户注册功能返回码
    codes.put(1100, "注册账户成功，我们已向您的邮箱发了一封验证消息，请及时查收并验证账户");
    codes.put(1101, "注册账户失败原因未知");
    codes.put(1102, "提交数据为空");
    codes.put(1103, "配置用户密码出错");
    codes.put(1104, "配置用户邮件出错");

    // 用户登录功能返回码
    codes.put(1200, "登录成功即将跳转");
    codes.put(1201, "登录账户失败原因未知");
    codes.put(1202, "账户不存在");
    codes.put(1203, "密码错误");
    codes.put(1204, "您的账户还未验证或已被冻结");
    codes.put(1205, "生成登录令牌失效");

    // 用户验证功能返回码
    codes.put(1300, "验证账户成功");
    codes.put(1301, "验证账户失败原因未知");
    codes.put(1302, "邮件地址不存在");
    codes.put(1303, "邮件验证码错误");

    // 用户验证功能返回码
    codes.put(1400, "报名成功");
    codes.put(1401, "报名失败原因未知");
    codes.put(1402, "图片文件后缀名只能为.jpg/.png");
  }

  public Code(int code) {
    this.code = code;
    codeDesc = codes.get(code);
  }

  /**
   * @return code
   */
  public int getCode() {
    return code;
  }

  /**
   * @param code 要设置的 code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * @return codeDesc
   */
  public String getCodeDesc() {
    return codeDesc;
  }

  /**
   * @param codeDesc 要设置的 codeDesc
   */
  public void setCodeDesc(String codeDesc) {
    this.codeDesc = codeDesc;
  }
}
