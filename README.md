#简介


FixFlow 是来自2000年 研发的Founder Fix 开发平台的工作流内核。
4.0 版本设计之初就参考了最为新颖的BPMN2.0标准草案，在新标准正式发布后，我们就积极参与到标准实现的工作中去。
我们吸纳了 jBPM3 、 Activiti5、BonitaBPM 等国际开源流程引擎的精髓，
在Founder Fix BPMCS开发平台发布后，经历了数个大型企业项目的历练，流程引擎的扩展体系逐渐完善。
到今天，我们认为是时候了，它已经可以做为独立的产品，它要从幕后走到台前了！

FixFLow本身并不具备完整的开发平台功能,它的定位是专门用于集成到现有系统的BPM引擎。



#为什么选择FixFlow？
* 1.开源以及强大的社区支持
* 2.基于国际标准 BPMN 2.0
* 3.支持Groovy、BeanShell等多种动态
* 4.专门用于集成的BPM产品
* 5.符合中国式的流程功能
* 6.强大灵活的扩展模式


#代码打包下载
内核版本不带web环境,用户需要自己开发web展现界面,内置Web版本自带任务、管控中心。
* [Fixflow内置任务、管控中心版本(百度网盘)](http://pan.baidu.com/s/1881zx)
* [Fixflow内核版本(百度网盘)](http://pan.baidu.com/s/1BWpgv)

#流程设计器
(国内最强大的BPMN设计器)
设计器提供两种版本,完整Eclipse版本、单一插件版本。
* [完整版设计器下载(百度网盘)](http://pan.baidu.com/s/13OKHM)
* [插件下载(百度网盘)](http://pan.baidu.com/s/1sZx6J)



#资源介绍

**开发者交流社区QQ群**: 152654373

**演示系统地址**: [http://218.107.242.90:9633/bpmcenter/ 用户名:admin 密码:1](http://218.107.242.90:9633/bpmcenter/) 

**用户向导**: [http://fixteam.github.io/fixflow/userguide/out/html/index.html](http://fixteam.github.io/fixflow/userguide/out/html/index.html)

**用户向导离线版本**: [http://pan.baidu.com/s/1tGkiM](http://pan.baidu.com/s/1tGkiM)

**学习怎样使用和集成FixFlow**: [https://github.com/fixteam/fixflow/wiki](https://github.com/fixteam/fixflow/wiki)

**团队Blog地址**: [http://fixteam.github.io/fixflow/blog/](http://fixteam.github.io/fixflow/blog/)

**更多产品请登录 FIXBPMCS 中间件平台官网**: [www.founderfix.com](http://www.founderfix.com)


#教学视频
[系列教学视频](http://www.youku.com/playlist_show/id_20321320.html)
![系统截图](https://github.com/fixteam/fixflow/wiki/images/Snip20131010_2.png)


#分支介绍
* develop 最新开发版
* master 最新稳定版
* v4.7 FixCS平台集成版本
* 5.0.0-Final-master  5.0正式版本
* *-cs CS开发平台专用版本
* 
* release-*  发布分支为准备新的产品版本发布做支持
* hotfix-*   当产品版本的重大bug需要立即解决的时候，我们从对应版本的标签创建出一个热补丁分支。
* feature-*  特性分支是用来为下一发布版本开发新特性

#项目介绍

* FixFlow引擎核心项目: **fixflow-core、fixflow-expand、fixflow-test**

* 任务、管控中心项目: **bpmcenter、fixflow-common-*、fixflow-manage-***

* 数据库脚本项目: **database**

* 文档项目: **docbook**

* 设计器相关项目: **eclipse**

* 最终成果物项目: **release**


#如何选择？
* Fixflow提供两种方式的集成:
* 1.完整集成版本,提供任务处理中心、流程管控中心、引擎内核、扩展项目、Junit测试库
* 2.核心集成版本,只提供引擎内核、扩展项目、Junit测试库

* 第一种适合不想在自己开发任务处理和管控中心的用户,用户可以将Fixflow的web和自己的web集成到一起。
* 第二种适合对界面需要大量订制的用户,通过流程提供的Api自己开发任务处理、管控界面。

* 注意：Fixflow自带的任务处理中心,在应用到实际项目中之前需要对其进行集成开发来使用用户各自系统的要求。

#如何操作流程引擎
* [代码调用流程引擎示例](http://fixteam.github.io/fixflow/userguide/out/html/index.html#api.connection)


#文档说明

【用户向导文档】提供了快速学习FixFlow引擎的途径,推荐从用户向导文档开始学习,【示例文档】提供了现实业务中常用的功能例子,【开发人员Blog文章列表】提供了高级功能详细讲解。



* [查看用户向导文档](http://fixteam.github.io/fixflow/userguide/out/html/index.html)
* [产品及开发社区介绍](https://github.com/fixteam/fixflow/wiki/产品及开发社区介绍)
* [FixFlow功能介绍](https://github.com/fixteam/fixflow/wiki/Fixflow功能介绍)
* [如何从GitHub中获取FixFlow代码](#)
* [如何构建运行FixFlow项目](#)
* [如何参与DocBook文档开发](https://github.com/fixteam/fixflow/wiki/如何参与DocBook文档)
* [如何参与FixFlow社区开发](https://github.com/fixteam/fixflow/wiki/如何参与FixFlow社区开发)
* [开发人员Blog文章列表](#)
* [团队及Blog](http://www.fixflow.org/blog/)
* [FAQ](#)



![系统截图](https://github.com/fixteam/fixflow/wiki/images/Snip20130916_1.png)


#如何提交bug或者问题
在Fixteam/FixFlow项目上点击Issues->New Issue提交bug,在标签栏选择bug、优先级、状态（未解决）三个标签，然后提交。我们会根据bug内容反馈相关信息给您。
![系统截图](https://github.com/fixteam/fixflow/wiki/images/Bug20130917093746.png)


#新版本预告
### 5.1.0（2013-11 - ？）

1. 设计器插件新增支持支持kepler版本的eclipse.至此设计器插件将支持主流的 Indigo (3.7) Juno (4.2)kepler(4.3)三大版本.

2. 新的表达式编写方式,支持手写表达式不需要弹出编辑框.

3. 流程创建模板功能,选中一个节点创建模板,也支持选中一组节点和线条创建模板,可以任何务和流程中来使用创建的模板.

4. 修复设计器双击弹出页面修改了数据下面的界面不显示的bug.

5. 设计器可以配置web地址,可以在发布、更新的时候清空web缓存.

6. 支持复制整个流程复制.

7. 引擎支持子流程终止退回主流程指定节点.

8. 规则引擎任务,能选择Groovy规则文件执行.

9. 新增全局操作表单、全局浏览表单.

10. 新增更多事件支持.

#历史的脚印

### 5.0.0（2013-10）
1. FixFlow引擎成为独立项目,从CS SOA中间件中剥离,贡献给开源社区。


### 4.7.0（2013-6）
1. CS SOA中间件 4.7版本发布.内置fixflow4.7版本流程引擎。


### 4.0.0（2012-3）
1. CS SOA中间件 4.0版本发布.内置fixflow4.0版本流程引擎
2. 开始支持BPMN标准,设计器改为基于Eclipse插件方式。

### 3.5.0（2010-5）
1. CS SOA中间件 3.5版本发布.内置fixflow3.5版本流程引擎。


### 3.0.0（2009-11）
1. CS SOA中间件 3.0版本发布.内置fixflow3.0版本流程引擎。



### 2.5.0（2007-06）
1. ES平 2.5版本发布.内置fixflow2.5版本流程引擎,分.net、java两个版本。


### 1.0.0（2000-05）
1. ES平台 1.0版本发布.内置fixflow1.0版本流程引擎
