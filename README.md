# 鹿鸣远程投票系统
**Luming Remote Voting System**


>远程投票是比赛活动提高参与度，测试参赛人员社会认可度常用方法之一，现有远程投票系统存在水军重复投票、赛外利益无关人员参与度不高等问题。基于此，拟采用“充分宣传，一号一投，有奖投票，阐述理由”的管理策略。模块有投票模块、防止刷票模块、用户模块、奖励模块、汇总模块、微信模块、管理员模块等。

**指导思想**：简单；投票活动是借助社交网络传播的，因此应将投票范围限定为用户群体较大的社交平台；投票系统的主要职责是给用户一个链接，用户点击链接进入页面后，找到喜欢的作品/人投上自己的一票，所以投票页做太多的装饰无任何意义；市面上已有的投票服务网站已经足够强大，再开发一个类似的是得不偿失的，因此应将免费服务作为首选考虑因素，不要开发任何收费型功能。

**定位**：基于微信客户端投票（不允许在非微信端投票）、开源、免费、国内。

**功能**：用户报名->发布者审核；


## 站点地图

### 主页

- 网站主页 https://luming.com

- 管理中心 https://luming.com/action

### 用户

- 登录页 https://luming.com/user/login

- 注册页 https://luming.com/user/register

- 信息页 https://luming.com/user/profile

- 用户主页 https://luming.com/user/home


### 投票

- 创建投票 https://luming.com/vote/create

- 投票管理 https://luming.com/vote/manage

- 审核报名 https://luming.com/vote/review?aid=activitieId

- 报名设置 https://luming.com/vote/signupconf?aid=activitieId

- 礼物设置 https://luming.com/vote/giftconf?aid=activitieId

- 活动链接 https://luming.com/vote/link?aid=activitieId

- 结果分析 https://luming.com/vote/analysis?aid=activitieId



## 微信网页授权 

### 测试账号
appID wx009793a980bbfa74
appsecret 96e410410d32e25d25a687dea7ec0afd

### OAuth认证

1. 获取认证码
https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx009793a980bbfa74&redirect_uri=http://vote.zizaixian.top&response_type=code&scope=snsapi_userinfo&state=lumingremotevotesystem#wechat_redirect

2. 通过认证码获取访问令牌与OpenID
https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx009793a980bbfa74&secret=96e410410d32e25d25a687dea7ec0afd&code=${code}&grant_type=authorization_code

3. 通过访问令牌与OpenID获取用户信息
https://api.weixin.qq.com/sns/userinfo?access_token=${access_token}&openid=${openid}

### 通用access_token获取
https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx009793a980bbfa74&secret=96e410410d32e25d25a687dea7ec0afd

### 创建自定义菜单
**POST** https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${access_token}












### 临时


    User user = new User();
    user.setEmail("zsimline@163.com");
    user.setOrgannization("邢台学院");
    user.setPassword("76c6560484e449f590010d6fc9ce3055");

        Activity activity = new Activity();
    activity.setTitle("21世纪帅哥评选");
    activity.setPublisher(1000001);
    activity.setSuffix("人");
    activity.setDescription("描述");

    activity.setVoteTimeStart(new Date());
    activity.setVoteTimeEnd(new Date());
    activity.setSignUpTimeStart(new Date());
    activity.setSignUpTimeEnd(new Date());
    activity.setMaxium(5);
    activity.setImgAddr("https://img.baidu.com/1.jpg");
    activity.setOptions("0,1,2,34,5");

      Entry entry = new Entry();
    entry.setAid("4028aa496f93b54f016f93b918780002");
    entry.setTitle("张艳泽");
    entry.setDescription("真帅啊");
    entry.setImgAddr("https://img.baidu.com/1.jpg");