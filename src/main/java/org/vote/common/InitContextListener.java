package org.vote.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.vote.common.Code;

/**
 * 初始化投票系统上下文变量
 */
public class InitContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    Code.initialize();
    System.out.println("初始化投票系统上下文成功");
  }

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    System.out.println("投票系统上下文已销毁");
  }
}
