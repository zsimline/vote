package org.vote.beans;

import java.io.Serializable;

/**
 * 报名信息表模型
 */
public class Apply implements Serializable {
  private static final long serialVersionUID = 1L;

  // 报名ID
  private long id;
  
  // 活动ID
  private String aid;
  
  // 审核状态
  private char status;
  
  // 条目标题
  private String title;
  
  // 条目介绍
  private String introduction;

  // 图片地址
  private String imgEntry;

  // 人物性别
  private String sex;
  
  // 人物年龄
  private int age;
  
  // 人物名字
  private String name;
  
  // 电话号码
  private String telephone;
  
  // 电子邮件
  private String email;

  // 学校名
  private String school;
  
  // 公司名
  private String company;
  
  // 收货地址
  private String address;

  public Apply() {
    this.status = 'w';
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
   * @return aid
   */
  public String getAid() {
    return aid;
  }

  /**
   * @param aid 要设置的 aid
   */
  public void setAid(String aid) {
    this.aid = aid;
  }
  
  /**
   * @return status
   */
  public char getStatus() {
    return status;
  }

  /**
   * @param status 要设置的 status
   */
  public void setStatus(char status) {
    this.status = status;
  }

  /**
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title 要设置的 title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return introduction
   */
  public String getIntroduction() {
    return introduction;
  }

  /**
   * @param introduction 要设置的 introduction
   */
  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  /**
   * @return imgEntry
   */
  public String getImgEntry() {
    return imgEntry;
  }

  /**
   * @param imgEntry 要设置的 imgEntry
   */
  public void setImgEntry(String imgEntry) {
    this.imgEntry = imgEntry;
  }

  /**
   * @return sex
   */
  public String getSex() {
    return sex;
  }

  /**
   * @param sex 要设置的 sex
   */
  public void setSex(String sex) {
    this.sex = sex;
  }

  /**
   * @return age
   */
  public int getAge() {
    return age;
  }

  /**
   * @param age 要设置的 age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name 要设置的 name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return telephone
   */
  public String getTelephone() {
    return telephone;
  }

  /**
   * @param telephone 要设置的 telephone
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone;
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
   * @return school
   */
  public String getSchool() {
    return school;
  }

  /**
   * @param school 要设置的 school
   */
  public void setSchool(String school) {
    this.school = school;
  }

  /**
   * @return company
   */
  public String getCompany() {
    return company;
  }

  /**
   * @param company 要设置的 company
   */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address 要设置的 address
   */
  public void setAddress(String address) {
    this.address = address;
  }
}
