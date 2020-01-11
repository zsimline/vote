package org.vote.beans;

/**
 * 报名信息表模型
 */
public class SignUp {
  // 报名ID
  private String id;
  
  // 活动ID
  private String aid;
  
  // 是否通过审核
  private boolean reviewed;
  
  // 条目标题
  private String title;
  
  // 条目描述
  private String description;
  
  // 图片地址
  private String imgAddr;

  // 人物性别
  private boolean sex;
  
  // 人物年龄
  private int age;
  
  // 人物名字
  private String name;
  
  // 电话号码
  private String telephone;
  
  // 电子邮件
  private String email;
  
  // 微信号
  private String wechat;
  
  // 学校名
  private String school;
  
  // 院系[,专业[,班级]描述
  private String classdesc;
  
  // 公司名
  private String company;
  
  // 收货地址
  private String address;

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id 要设置的 id
   */
  public void setId(String id) {
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
   * @return imgAddr
   */
  public String getImgAddr() {
    return imgAddr;
  }

  /**
   * @param imgAddr 要设置的 imgAddr
   */
  public void setImgAddr(String imgAddr) {
    this.imgAddr = imgAddr;
  }

  /**
   * @return sex
   */
  public boolean getSex() {
    return sex;
  }

  /**
   * @param sex 要设置的 sex
   */
  public void setSex(boolean sex) {
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
   * @return wechat
   */
  public String getWechat() {
    return wechat;
  }

  /**
   * @param wechat 要设置的 wechat
   */
  public void setWechat(String wechat) {
    this.wechat = wechat;
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
   * @return classdesc
   */
  public String getClassdesc() {
    return classdesc;
  }

  /**
   * @param classdesc 要设置的 classdesc
   */
  public void setClassdesc(String classdesc) {
    this.classdesc = classdesc;
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
