# 鹿鸣远程投票系统
**Luming Remote Voting System**


>远程投票是比赛活动提高参与度，测试参赛人员社会认可度常用方法之一，现有远程投票系统存在水军重复投票、赛外利益无关人员参与度不高等问题。基于此，拟采用“充分宣传，一号一投，有奖投票，阐述理由”的管理策略。模块有投票模块、防止刷票模块、用户模块、奖励模块、汇总模块、微信模块、管理员模块等。

**指导思想**：简单；投票活动是借助社交网络传播的，因此应将投票范围限定为用户群体较大的社交平台；投票系统的主要职责是给用户一个链接，用户点击链接进入页面后，找到喜欢的作品/人投上自己的一票，所以投票页做太多的装饰无任何意义；市面上已有的投票服务网站已经足够强大，再开发一个类似的是得不偿失的，因此应将免费服务作为首选考虑因素，不要开发任何收费型功能；渐进式、增量式开发。

**定位**：基于微信客户端投票（不允许在非微信端投票）、开源、免费、国内。

**功能**：用户报名->发布者审核；


## 站点地图

### 主页

- 网站主页 http://vote.zizaixian.top

### 用户

- 登录页 http://vote.zizaixian.top/user/login

- 注册页 http://vote.zizaixian.top/user/register

- 信息页 http://vote.zizaixian.top/user/profile

- 用户主页 http://vote.zizaixian.top/user/home

### 投票

- 创建投票 http://vote.zizaixian.top/vote/create

- 投票管理 http://vote.zizaixian.top/vote/manage

- 审核报名 http://vote.zizaixian.top/vote/apply_manage?aid=activitieId

- 报名设置 http://vote.zizaixian.top/vote/item_manage?aid=activitieId

- 礼物设置 http://vote.zizaixian.top/vote/giftconf?aid=activitieId

- 活动链接 http://vote.zizaixian.top/vote/qrcode?aid=activitieId

- 结果汇总 http://vote.zizaixian.top/vote/gather?aid=activitieId

## 系统接口

- 处理创建投票 http://vote.zizaixian.top/api/vote/create

- 处理删除投票 http://vote.zizaixian.top/api/vote/delete

- 处理用户报名 http://vote.zizaixian.top/api/vote/apply

- 处理用户注册 http://vote.zizaixian.top/api/user/register

- 处理用户登录 http://vote.zizaixian.top/api/user/login

- 处理验证账户 http://vote.zizaixian.top/api/user/activation


## 微信网页授权 

### 测试账号
appID wx009793a980bbfa74
appsecret 96e410410d32e25d25a687dea7ec0afd

### OAuth认证

1. 获取认证码
https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx009793a980bbfa74&redirect_uri=http://vote.zizaixian.top&response_type=code&scope=snsapi_userinfo&state=lumingvoteremotevotesystem#wechat_redirect

2. 通过认证码获取访问令牌与OpenID
https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx009793a980bbfa74&secret=96e410410d32e25d25a687dea7ec0afd&code=${code}&grant_type=authorization_code

3. 通过访问令牌与OpenID获取用户信息
https://api.weixin.qq.com/sns/userinfo?access_token=${access_token}&openid=${openid}

### 通用access_token获取
https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx009793a980bbfa74&secret=96e410410d32e25d25a687dea7ec0afd

### 创建自定义菜单
**POST** https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${access_token}


### 系统架构

**Linux + Nginx + Mysql + Tomcat**

**Linux:** Debian v4.9.144-3.1

**Nginx:** Http Server 负载均衡、反向代理

**Mysql:** MariaDB v15.1 数据存储

**Tomcat** Servlet Container v8.5.37


**前端技术：** flat-ui(free v2.1.1) + bootstrap(v3.0.0) + font-awesome(v4.7.0)


## TODO

7. 报名管理

8. 编辑功能

10. 批量添加功能

11. 投票页面及前端后端处理

12. ...

<button class="layui-btn" onclick="addtr();"><i class="fa fa-plus"></i>新增一行</button>


      function getQueryString(name) {
        const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        const r = window.location.search.substr(1).match(reg);
        return r ? unescape(r[2]) : null;
      }

  




/**
 * 处理表单提交
 * 获取表单数据并校验
 */
function handleSubmit() {
  // 获取表单数据
  const aid = $('#aid').text();
  const title = $('#title').val();
  const imgName = $('#img-name').val();
  const description = tinyMCE.activeEditor.getContent();
  const name = $('#name').val();
  const sex = $(":radio:checked").val();
  const age = $('#age').val();
  const telephone = $('#telephone').val();
  const email = $('#email').val();
  const school = $('#school').val();
  const company = $('#company').val();
  const address = $('#address').val();

  // 校验表单是否为空
  if (title !== undefined && title === '') {
    openModal('error', '标题不能为空');
    return;
  }
  if (imgName !== undefined && imgName === '') {
    openModal('error', '请上传介绍图片');
    return;
  }
  if (description !== undefined && description === '') {
    openModal('error', '详细描述不能为空');
    return;
  }
  if (name !== undefined && name === '') {
    openModal('error', '真实姓名不能为空');
    return;
  }
  if (sex !== undefined && sex === '') {
    openModal('error', '真实性别不能为空');
    return;
  }
  if (age !== undefined && age === '') {
    openModal('error', '真实年龄不能为空');
    return;
  }
  if (telephone !== undefined && telephone === '') {
    openModal('error', '手机号码不能为空');
    return;
  }
  if (email !== undefined && email === '') {
    openModal('error', '电子邮件不能为空');
    return;
  }
  if (school !== undefined && school === '') {
    openModal('error', '学校名称不能为空');
    return;
  }
  if (company !== undefined && company === '') {
    openModal('error', '公司名称不能为空');
    return;
  }
  if (address !== undefined && address === '') {
    openModal('error', '收货地址不能为空');
    return;
  }


优化

```java
/**
   * 按条件映射查询
   * 
   * @param clazz 实例类
   * @param column 映射列名
   * @param propertyName 条件名
   * @param value 条件值
   * @return
   */
  protected Object projectionQuery(Class<?> clazz, String column, String propertyName, String value) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = session.beginTransaction();

    try {
      transaction.begin();

      // 创建条件容器并添加条件
      Criteria criteria = session.createCriteria(clazz);
      criteria.add(Restrictions.eq(propertyName, value));

      // 创建映射列表并添加映射列
      ProjectionList projectionList = Projections.projectionList();
      projectionList.add(Projections.property(column));
      criteria.setProjection(projectionList);

      List<?> activitys = (List<?>) criteria.list();
      
      transaction.commit();
      session.close();

      return activitys.size() == 0 ? ((Object []) activitys.get(0))[0] : null;
      } catch (HibernateException e) {
        e.printStackTrace();
        return null;
      }
  }
```
