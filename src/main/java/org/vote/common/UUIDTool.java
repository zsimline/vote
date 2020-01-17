package org.vote.common;

import java.util.UUID;
 
public class UUIDTool {
	public UUIDTool() {
  }

	// 生成并返回32位的UUID
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
