package org.vote.common;

import java.util.UUID;

/**
 * UUID生成器
 */
public class UUIDTool {
	public UUIDTool() {
  }

	// 生成并返回32位的UUID
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
