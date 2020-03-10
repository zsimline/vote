# 鹿鸣远程投票系统
**Luming Remote Voting System**

**定位**：基于微信客户端投票（不允许在非微信端投票）、开源、免费、国内。

## 站点地图

- 网站主页 http://vote.zizaixian.top

### 用户相关

- 注册页 http://vote.zizaixian.top/user/register

- 登录页 http://vote.zizaixian.top/user/login

- 主页  http://vote.zizaixian.top/user/profile

### 投票相关

- 发布投票 http://vote.zizaixian.top/vote/publish

- 管理中心 http://vote.zizaixian.top/vote/manage

- 编辑活动 http://vote.zizaixian.top/vote/edit

- 报名管理 http://vote.zizaixian.top/vote/apply_manage

- 条目管理 http://vote.zizaixian.top/vote/entry_manage

- 活动链接 http://vote.zizaixian.top/vote/qrcode

- 结果汇总 http://vote.zizaixian.top/vote/gather

- 微信投票 http://vote.zizaixian.top/vote/action

### 管理员相关

- 用户管理 http://vote.zizaixian.top/admin/user_manage

- 活动管理 http://vote.zizaixian.top/admin/activity_manage

## 系统接口

- 处理用户注册 http://vote.zizaixian.top/api/user/register

- 处理用户登录 http://vote.zizaixian.top/api/user/login

- 处理激活账户 http://vote.zizaixian.top/api/user/activation

- 处理更新用户信息 http://vote.zizaixian.top/api/user/info_update

- 处理忘记密码 http://vote.zizaixian.top/api/vote/pwdforget

- 处理创建活动 http://vote.zizaixian.top/api/vote/activity/publish

- 处理删除活动 http://vote.zizaixian.top/api/vote/activity/remove

- 处理更新活动信息  http://vote.zizaixian.top/api/vote/activity/update

- 处理外部用户报名 http://vote.zizaixian.top/api/vote/apply/external

- 处理发布则添加报名 http://vote.zizaixian.top/api/vote/apply/publisher

- 处理获取报名数据 http://vote.zizaixian.top/api/vote/apply/data

- 处理更新报名信息 http://vote.zizaixian.top/api/vote/apply/update

- 处理审核报名 http://vote.zizaixian.top/api/vote/apply/review

- 处理获取条目数据 http://vote.zizaixian.top/api/vote/entry/data

- 处理冻结条目 http://vote.zizaixian.top/api/vote/entry/freeze

- 处理搜索条目 http://vote.zizaixian.top/api/vote/entry/search

- 处理文件上传 http://vote.zizaixian.top/api/vote/file_upload

- 处理微信认证 http://vote.zizaixian.top/api/vote/auth

- 处理微信投票 http://vote.zizaixian.top/api/vote/action

- 处理生成排行 http://vote.zizaixian.top/api/gather/ranking

- 处理分析投票者性别 http://vote.zizaixian.top/api/gather/by_sex

- 处理分析投票者省份 http://vote.zizaixian.top/api/gather/by_province

- 处理分析投票热度 http://vote.zizaixian.top/api/gather/by_date


- 处理用户状态切换 http://vote.zizaixian.top/api/admin/user_status

- 处理用户权限切换 http://vote.zizaixian.top/api/admin/user_privilege

- 处理活动删除 http://vote.zizaixian.top/api/admin/activity_delete


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

**Linux + Nginx + MariaDB + Java + Tomcat**

- Linux: Debian v4.9.144-3.1

- Nginx: Http Server 负载均衡、反向代理

- MariaDB: MariaDB v15.1 数据存储

- Java:  JVM v1.8 应用程序运行平台

- Tomcat: Servlet Container v8.5.37


### 前端技术

- bootstrap(v3.0.0)

- flat-ui(free v2.1.1)

- font-awesome(v4.7.0)


## TODO

### 待开发

### 待弥补的不足

1. 并发访问问题

2. 搜索页面不能回退

3. 使账户只能在一个终端下登录（同一时间）

### 待修复的Bug

1. 搜索页面显示单个条目时错位

2. 某些页面存在重复提交的漏洞

3. 年龄未提交时将出现空指针错误

## 平台规则与系统限制

1. 投票被发布后，默认的外部人员可以报名，发布者也可以批量添加报名，当外部报名通道被关闭后，外部人员不可报名。

2. 如果外部人员报名后发现报名信息填写错误，可已通知发布者帮其修改，但是其本人是无法修改的。

3. 审核状态分为三种：待审核、已通过审核、未通过审核。其中，审核状态可以由待审核转为已通过审核或未通过审核，未通过审核可以转换为已通过审核但不可以转换为待审核，已通过审核不能转为待审核或未通过审核。

4. 报名通过审核后，必要的参赛信息如标题、参赛图片、详细介绍等会由报名信息表同步到条目信息表。

5. 当审核状态处于待审核与未通过审核时，其报名信息是可以修改的，一旦状态变为已通过审核，其报名信息虽然也可以修改，但是必要的参赛信息不会同步到条目信息表。

6. 外部人员报名后，其审核状态为待审核，而发布者批量添加报名后，其审核状态为已通过审核。

7. 外部人员需在报名时间段内完成报名，发布者需在投票截止时间之前完成批量添加报名。


## 总结

1. 面向对象的思考方式。

2. 如果引入了第三方库/框架，则要充分利用它们。

3. 开发阶段不要担心运行效率问题。
