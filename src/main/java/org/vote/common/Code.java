package org.vote.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Servlet 返回码类
 */
public class Code {
  // 返回码
  private int code;

  // 返回码说明
  private String codeDesc;

  private static Map<Integer, String> codes;

  public static void initCodes() {
    codes = new HashMap<Integer, String>();
    
    codes.put(1000, "发布投票成功");

    codes.put(1001, "发布投票失败原因未知");
    codes.put(1002, "图片文件后缀名只能为.jpg/.png");
    System.out.println("\n\n\n\n\nxxx\n\n\n\n\n");
  }

  public Code(int code) {
    System.out.println(code);
    System.out.println(codes.get(code));
    this.code = code;
    codeDesc = codes.get(code);
  }
}
