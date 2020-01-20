package org.vote.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.vote.common.Code;

/**
 * 初始化投票系统上下文
 */
public class InitContextListener implements ServletContextListener {
  public void contextDestroyed(ServletContextEvent arg0) {
  }

  public void contextInitialized(ServletContextEvent arg0) {
    Code.initCodes();
    System.out.println("初始化投票系统上下文成功");
  }
}
