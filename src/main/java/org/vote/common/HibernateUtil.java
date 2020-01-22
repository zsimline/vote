package org.vote.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {  
      // 获取加载配置管理类
      Configuration configuration = new Configuration();

      // 不给参数就默认加载hibernate.cfg.xml文件，
      configuration.configure();

      // 创建Session工厂对象
      SessionFactory factory = configuration.buildSessionFactory();
      
      return factory;
      
    } catch (Throwable ex) {
      ex.printStackTrace();
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    // 清空缓存并关闭连接池
    getSessionFactory().close();
  }
}
