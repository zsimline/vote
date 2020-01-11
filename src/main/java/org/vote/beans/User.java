package org.vote.beans;

public class User {
  // 用户ID
  private long id;

  // 用户电子邮件
  private String email;

  // 用户密码
  private String password;

  // 用户所属组织
  private String organnization;

  // 是否为管理人员
  private boolean isStaff;

  // 该账号是否可用
  private boolean isActive;

  /**
   * @return id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id 要设置的 id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email 要设置的 email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password 要设置的 password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return organnization
   */
  public String getOrgannization() {
    return organnization;
  }

  /**
   * @param organnization 要设置的 organnization
   */
  public void setOrgannization(String organnization) {
    this.organnization = organnization;
  }

  /**
   * @return isStaff
   */
  public boolean getIsStaff() {
    return isStaff;
  }

  /**
   * @param isStaff 要设置的 isStaff
   */
  public void setIsStaff(boolean isStaff) {
    this.isStaff = isStaff;
  }

  /**
   * @return isActive
   */
  public boolean getIsActive() {
    return isActive;
  }

  /**
   * @param isActive 要设置的 isActive
   */
  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }
}
