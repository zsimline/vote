package org.vote.common;



public class AutoUpdateTimeInterceptor extends EmptyInterceptor {
  private static final long serialVersionUID = -8597658125309889388L;

  /*
   * entity - POJO对象 id - POJO对象的主键 state - POJO对象的每一个属性所组成的集合(除了ID) propertyNames
   * - POJO对象的每一个属性名字组成的集合(除了ID) types - POJO对象的每一个属性类型所对应的Hibernate类型组成的集合(除了ID)
   */
  @Override
  public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
    if (entity instanceof People) {
      for (int index = 0; index < propertyNames.length; index++) {
        /* 找到名为"checkinTime"的属性 */
        if ("checkinTime".equals(propertyNames[index])) {
          /* 使用拦截器将People对象的"checkinTime"属性赋上值 */
          state[index] = new Timestamp(new Date().getTime());
          return true;
        }
      }
    }

    return false;
  }
}