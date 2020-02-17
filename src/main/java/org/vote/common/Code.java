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
    codes.put(1002, "无权执行操作");
    codes.put(1003, "数据传输错误");

    // 删除投票功能返回码
    codes.put(1900, "删除投票成功");
    codes.put(1901, "删除投票失败原因未知");
    codes.put(1902, "无权执行操作");

    // 用户注册功能返回码
    codes.put(1100, "注册账户成功，我们已向您的邮箱发了一封验证消息，请及时查收并验证账户");
    codes.put(1101, "注册账户失败原因未知");
    codes.put(1102, "配置用户邮件出错");

    // 用户登录功能返回码
    codes.put(1200, "登录成功即将跳转");
    codes.put(1201, "登录账户失败原因未知");
    codes.put(1202, "账户不存在");
    codes.put(1203, "密码错误");
    codes.put(1204, "您的账户还未验证或已被冻结");

    // 用户验证功能返回码
    codes.put(1300, "验证账户成功");
    codes.put(1301, "验证账户失败原因未知");
    codes.put(1302, "邮件地址不存在");
    codes.put(1303, "邮件验证码错误");

    // 报名活动功能返回码
    codes.put(1401, "报名失败原因未知");
    codes.put(1402, "报名未开始或报名已结束"); 
    codes.put(1402, "报名通达已关闭"); 
    codes.put(1402, "无权操作"); 
    codes.put(1405, "投票已截止不可继续添加"); 

    // 更新报名信息功能返回码
    codes.put(1501, "更新报名信息失败原因未知");
    codes.put(1502, "报名ID不存在");
    codes.put(1503, "无权执行操作");
    codes.put(1503, "解析更新数据失败");

    // 审核报名功能返回码
    codes.put(1600, "审核成功");
    codes.put(1601, "审核失败原因未知");
    codes.put(1602, "无权执行操作");

    // 更新活动信息功能状态码
    codes.put(1700, "更新成功");
    codes.put(1701, "更新失败原因未知");
    codes.put(1702, "无权执行操作");
    codes.put(1703, "解析更新数据失败");
    codes.put(1704, "活动不存在");

    // 用户注销功能状态码
    codes.put(1800, "注销成功");
    codes.put(1801, "注销失败原因未知");

    // 投票功能返回码
    codes.put(2000, "投票成功"); 
    codes.put(2001, "投票失败原因未知"); 
    codes.put(2002, "解析用户上传的数据失败"); 
    codes.put(2003, "活动不存在"); 
    codes.put(2004, "投票未开始或投票已结束"); 
    codes.put(2005, "无权投票"); 
    codes.put(2006, "今日您已投过票，明日再来吆"); 
    codes.put(2007, "超过最多选择的数量"); 
    codes.put(2008, "条目信息异常"); 

    // 文件上传功能返回码
    codes.put(3000, "文件上传成功");
    codes.put(3001, "文件上传失败原因未知");
    codes.put(3002, "解析文件流失败");
    codes.put(3003, "未发现文件");
    codes.put(3004, "非法的文件名");
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