## 一、 项目说明

本项目想法的萌生是我自大学以来有用markdown整理自己笔记的习惯，希望正好借数据库这个项目来给自己创造一个可以专注于写作、可以自由自在地分享自己的想法和知识的小空间。希望根据自己的思维习惯，创建属于自己的知识系统，保持个人思想与言论独立，以及对如今逐趋完整的局域网保持谨慎。

这是一个基于springboot+mysql的轻量级博客系统，分为前端展示网页和后台管理网页，使用thymyleaf模板轻松的实现前后端分离，前端采用了semantic UI框架，网站界面以自己喜好来设计的粉色和黑色为主色调，具有强烈的个人风格。



已部署到服务器

http://8.130.53.239:8080/

 

## 二、 需求描述

 

![img](https://caiyiimg.oss-cn-shanghai.aliyuncs.com/typora/20211017213026.jpg)

## 三、设计说明

#### （1）数据库概念设计

![image-20211017213551689](https://caiyiimg.oss-cn-shanghai.aliyuncs.com/typora/20211017213553.png)

**Assumptions:**

一个博客只能有一个分类，一个分类下可以有多个博客

一个博客可以有多个标签，一个标签下可以有多个博客

一个博客下可以有多条评论，有一级评论和二级评论

一个用户可以发布多条博客

 

#### （2）数据库逻辑设计

| **BLOG**             |               |
| -------------------- | ------------- |
| Blog_id              | 博客编号      |
| Blog_title           | 标题          |
| Blog_content         | 内容          |
| Blog_description     | 描述          |
| Blog_cover           | 封面          |
| Blog_thumbup         | 点赞量        |
| Blog_views           | 浏览量        |
| Blog_comment         | 评论数量      |
| Blog_create_time     | 创建时间      |
| Blog_update_time     | 修改时间      |
| Blog_statue          | 已发布/未发布 |
| Type_id(foreign key) | 类别编号      |
| Tag_id(foreign key)  | 标签编号      |

 

| **USER**      |             |
| ------------- | ----------- |
| User_id       | 用户编号    |
| User_name     | 用户名      |
| User_password | 密码        |
| User_avatar   | 头像        |
| User_Email    | 邮箱        |
| User_nickname | 昵称        |
| User_type     | 管理员/访客 |

 

| **COMMENT**              |                        |
| ------------------------ | ---------------------- |
| Cm_id                    | 评论编号               |
| Cm_content               | 评论内容               |
| Cm_creat_time            | 创建时间               |
| Blog_id(foreign key)     | 关联的博客             |
| User_id(foreign key)     | 关联的用户             |
| Parent_comment_id(index) | 二级评论的父评论的编号 |

 

| **TYPE**                |                  |
| ----------------------- | ---------------- |
| Category_id             | 博客类别编号     |
| Category_name           | 分类名称         |
| Category_amount_of_blog | 分类下博客的数量 |

 

| **TAG**            |                  |
| ------------------ | ---------------- |
| Tag_id             | 博客标签编号     |
| Tag_name           | 标签名称         |
| Tag_amount_of_blog | 标签下博客的数量 |

 

| **TAG_BLOG**        |          |
| ------------------- | -------- |
| Tag_id(foreign key) | 标签编号 |
| Blog_id(foreign key | 博客编号 |

 

1.分类和标签其实可以作为两个属性放在blog里，但是把它们单独拿出来就是方便管理，新增查询删除维护，也可以把分类里的博客/标签里的博客提出来

2.个人博客系统没有其他用户登录，但是我想的是，有用户的话可以进行权限限制，进入后台发布的入口，不是任何人都能后台管理，没有登录进来的用户不能随便发布内容

3.评论类自关联



### 四、技术栈

#### （1）系统架构

后端:Spring Boot + JPA + thymeleaf框架
 数据库:MySQL + navicat（可视化管理工具）

使用 Spring Security + MD5加密做登录验证和权限校验

搜索服务： ElasticSearch

前端使用 [Semantic UI ](https://semantic-ui.com/)

#### （2）集成插件：

Markdown编辑器 Editor.md  pandao.github.io

内容排版 typo.css https://github.com/sofish/typo.css

动画 animate.css https://daneden.github.io/animate.css

目录自动生成 https://tscanlin.github.io/tocbot

代码高亮 https://github.com/PrismJS/prism

 

## 五、项目导入本地说明

1.选择解压好的源代码，选择maven项目类型进行导入

![img](file:///C:/Users/lenovo/AppData/Local/Temp/msohtmlclip1/01/clip_image026.gif)

 

2.导入完成之后在 application-dev.yml文件里面配置你本地数据库的账户和密码

![img](file:///C:/Users/lenovo/AppData/Local/Temp/msohtmlclip1/01/clip_image028.gif)

 

3.在你本地的MYSQL数据库中新建一个blog的数据库，注意默认字符串utf8

4.在idea里面启动工程，这时就会发现navicat里自动生成了数据库表啦

5.初始用户信息，在用户表中加入一个用户数据用于后台管理员登录，头像用户名密码邮箱都可以自己填，但是用户类型一定要输入“1”，管理员的意思

6.登录网页http://127.0.0.1:8080

登录后台http://127.0.0.1:8080/admin