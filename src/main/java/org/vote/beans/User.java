package org.vote.beans;

import java.io.Serializable;

/**
 * 用户信息表模型
 */
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  // 用户ID
  private long id;

  // 用户电子邮件
  private String email;

  // 用户密码
  private String password;

  // 用户昵称
  private String nickname;

  // 用户所属组织
  private String organization;

  // 是否为管理人员
  private boolean isStaff;

  // 该账号是否可用
  private boolean isActive;

  public User() {
    this.isStaff = false;
    this.isActive = false;
  }

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
   * @return nickname
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * @param nickname 要设置的 nickname
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * @return organization
   */
  public String getOrganization() {
    return organization;
  }

  /**
   * @param organization 要设置的 organization
   */
  public void setOrganization(String organization) {
    this.organization = organization;
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
