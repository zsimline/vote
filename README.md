# 鹿鸣远程投票系统
**Luming Remote Voting System**


>远程投票是比赛活动提高参与度，测试参赛人员社会认可度常用方法之一，现有远程投票系统存在水军重复投票、赛外利益无关人员参与度不高等问题。基于此，拟采用“充分宣传，一号一投，有奖投票，阐述理由”的管理策略。模块有...

**指导思想**：简单；投票活动是借助社交网络传播的，因此应将投票范围限定为用户群体较大的社交平台；投票系统的主要职责是给用户一个链接，用户点击链接进入页面后，找到喜欢的作品/人投上自己的一票，所以投票页做太多的装饰无任何意义；市面上已有的投票服务网站已经足够强大，再开发一个类似的是得不偿失的，因此应将免费服务作为首选考虑因素，不要开发任何收费型功能；先完成再完善、渐进式、增量式开发。

**定位**：基于微信客户端投票（不允许在非微信端投票）、开源、免费、国内。


## 站点地图

- 网站主页 http://vote.zizaixian.top

### 用户相关

- 主页  http://vote.zizaixian.top/user/home

- 注册页 http://vote.zizaixian.top/user/register

- 登录页 http://vote.zizaixian.top/user/login

### 投票相关

- 发布投票 http://vote.zizaixian.top/vote/publish

- 管理中心 http://vote.zizaixian.top/vote/manage

- 报名管理 http://vote.zizaixian.top/vote/apply_manage

- 条目管理 http://vote.zizaixian.top/vote/entry_manage

- 活动链接 http://vote.zizaixian.top/vote/qrcode

- 结果汇总 http://vote.zizaixian.top/vote/gather

## 系统接口

- 处理创建投票 http://vote.zizaixian.top/api/vote/publish

- 处理删除投票 http://vote.zizaixian.top/api/vote/remove

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
https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx009793a980bbfa74&redirect_uri=http://vote.zizaixian.top/api/vote/oauth&response_type=code&scope=snsapi_userinfo&state=0d11eed65e8e4c90ac99d91f2b8b6627#wechat_redirect

2. 通过认证码获取访问令牌与openid
https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx009793a980bbfa74&secret=96e410410d32e25d25a687dea7ec0afd&code=${code}&grant_type=authorization_code

3. 通过访问令牌与openid获取用户信息
https://api.weixin.qq.com/sns/userinfo?access_token=${access_token}&openid=${openid}

### 通用access_token获取
https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx009793a980bbfa74&secret=96e410410d32e25d25a687dea7ec0afd

### 创建自定义菜单
**POST** https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${access_token}



## 系统架构

### 后端服务

**Linux + Nginx + Mysql + Java + Tomcat**

- Linux: Debian v4.9.144-3.1

- Nginx: Http Server 负载均衡、反向代理

- Mysql: MariaDB v15.1 数据存储

- Java:  JVM v1.8 应用程序运行平台

- Tomcat: Servlet Container v8.5.37


### 前端技术

- bootstrap(v3.0.0)

- flat-ui(free v2.1.1)

- font-awesome(v4.7.0)


## TODO

### 待开发

14. 结果与日志功能

15. 编辑功能

16. 用户功能完善
 - 注册/登录页框线颜色
 - 主页登录状态切换
 - 用户注销功能
 - 身份过期检验

17. 禁止外部报名功能

18. 阐述理由功能

19. 礼物功能

20. 管理员功能
 - 冻结用户
 - 删除活动

21. 链接与二维码页增加报名页面二维码

### 待弥补的不足

1. 并发访问问题

2. 搜索页面不能回退

### 待修复的Bug

1. 报名信息存在被非法修改的可能

2. 搜索页面显示单个条目时错位


## 平台规则与系统限制

1. 投票被发布后，默认的外部人员可以报名，发布者也可以批量添加报名，当外部报名通道被关闭后，外部人员不可报名。

2. 如果外部人员报名后发现报名信息填写错误，可已通知发布者帮其修改，但是其本人是无法修改的。

3. 审核状态分为三种：待审核、已通过审核、未通过审核。其中，审核状态可以由待审核转为已通过审核或未通过审核，未通过审核可以转换为已通过审核但不可以转换为待审核，已通过审核不能转为待审核或未通过审核。

4. 报名通过审核后，必要的参赛信息如标题、参赛图片、详细介绍等会由报名信息表同步到条目信息表。

5. 当审核状态处于待审核与未通过审核时，其报名信息是可以修改的，一旦状态变为已通过审核，其报名信息虽然也可以修改，但是必要的参赛信息不会同步到条目信息表。

6. 外部人员报名后，其审核状态为待审核，而发布者批量添加报名后，其审核状态为已通过审核。

7. 



## 总结

1. 面向对象的思考方式。

2. 如果引入了第三方库/框架，则要充分利用它们。

3. 开发阶段不要担心运行效率问题。

