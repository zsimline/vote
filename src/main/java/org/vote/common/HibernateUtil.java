package org.vote.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  // Hibernate 会话工厂
  private static final SessionFactory sessionFactory = buildSessionFactory();

  /**
   * 建立 Hibernate 会话工厂
   * 
   * @return Hibernate 会话工厂
   */
  private static SessionFactory buildSessionFactory() {
    try {
      // 获取加载配置管理类
      Configuration configuration = new Configuration();

      // 不给参数就默认加载hibernate.cfg.xml文件
      configuration.configure();

      // 创建Session工厂对象
      SessionFactory sessionFactory = configuration.buildSessionFactory();
      
      return sessionFactory;
    } catch (Throwable e) {
      e.printStackTrace();
      throw new ExceptionInInitializerError(e);
    }
  }

  /**
   * 获取 Hibernate 会话工厂
   * 
   * @return Hibernate 会话工厂
   */
  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  /**
   * 清空缓存并关闭连接池
   */
  public static void shutdown() {
    sessionFactory.close();
  }
}
