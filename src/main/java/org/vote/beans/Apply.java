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
  
  // 是否通过审核
  private boolean reviewed;
  
  // 条目标题
  private String title;
  
  // 条目描述
  private String description;
  
  // 图片地址
  private String imgName;

  // 人物性别
  private byte sex;
  
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
    this.reviewed = false;
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
   * @return reviewed
   */
  public boolean getReviewed() {
    return reviewed;
  }

  /**
   * @param reviewed 要设置的 reviewed
   */
  public void setReviewed(boolean reviewed) {
    this.reviewed = reviewed;
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
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description 要设置的 description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return imgName
   */
  public String getImgName() {
    return imgName;
  }

  /**
   * @param imgName 要设置的 imgName
   */
  public void setImgName(String imgName) {
    this.imgName = imgName;
  }

  /**
   * @return sex
   */
  public byte getSex() {
    return sex;
  }

  /**
   * @param sex 要设置的 sex
   */
  public void setSex(byte sex) {
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
