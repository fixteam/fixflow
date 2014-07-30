#简介

Fixflow是一款开源的基于BPMN2.0标准的工作流引擎,引擎底层直接支持BPMN2.0国际标准,
吸纳了 jBPM3 、 Activiti5、BonitaBPM 等国际开源流程引擎的精髓,
同时提供了强大的中国式流程流转处理,引擎采用微内核+插件形式设计,提供灵活的扩展模式, 建模采 用基于BPMN2.0标准的Eclipse设计器和基于Web的流程设计器,不仅仅为审批流程提供了解决方案, 同时还为复杂业务流程编排提供了强大的支持。



FixFLow本身并不具备完整的开发平台功能,它的定位是专门用于集成到现有系统的引擎。



#其他Git仓库
国内访问速度比较慢的朋友可以考虑从国内的Git仓库拉取代码:

**开源中国社区-中国**:(https://git.oschina.net/kenshinnet/fixflow)

**GitHub-美国**:(https://github.com/fixteam/fixflow)

**csdn_code -中国**:(https://code.csdn.net/fixflow/fixflow)  


#为什么选择FixFlow？
• 开源以及强大的社区支持

• 基于国际业务流程标准BPMN2.0

• 支持复杂式的中国流程流转处理

• 强大的基于BPMN2.0建模的Eclipse插件设计器

• 基于Web的流程设计器

• 强大灵活的扩展模式

• 基于图形化设计的外部系统调用连接器

• 基于Web的流程管控中心

• 专门用于集成的BPM产品

• 支持Groovy、BeanShell等多种动态


#资源打包下载
包含数据库脚本、jar包、用户向导、设计器插件、war包、内核源码，全部源码。
* [Fixflow Release 5.2(百度网盘)](http://pan.baidu.com/s/1o6M8XhK)

#流程设计器
(国内最强大的BPMN设计器)
设计器提供两种版本,完整Eclipse版本、单一插件版本。
* [完整版设计器下载 5.2(百度网盘)](http://pan.baidu.com/s/1o6x624E)
* [插件下载 5.2(百度网盘)](http://pan.baidu.com/s/1hqA9Spe)



#资源介绍

**开发者交流社区QQ群**: 152654373

**演示系统地址**: [http://218.107.242.90:9633/bpmcenter/ 用户名:admin 密码:1](http://218.107.242.90:9633/bpmcenter/) 

**用户向导**: [http://fixteam.github.io/fixflow/userguide/out/html/index.html](http://fixteam.github.io/fixflow/userguide/out/html/index.html)

**用户向导离线版本 5.2**: [http://pan.baidu.com/s/1pJk9QHX](http://pan.baidu.com/s/1kTqRdbh)

**学习怎样使用和集成FixFlow**: [https://github.com/fixteam/fixflow/wiki](https://github.com/fixteam/fixflow/wiki)

**团队Blog地址**: [http://fixteam.github.io/fixflow/blog/](http://fixteam.github.io/fixflow/blog/)

**Fixflow开放日活动视频**: [开放日视频](http://pan.baidu.com/s/1pbS4u)


#教学视频
[系列教学视频](http://www.youku.com/playlist_show/id_20321320.html)
![系统截图](https://github.com/fixteam/fixflow/wiki/images/Snip20131010_2.png)


#分支介绍
* develop 最新开发版
* master 最新稳定版
* v4.7 FixCS平台集成版本
* 5.0.0-Final-master  5.0正式版本
* *-cs CS开发平台专用版本
* release-*  发布分支为准备新的产品版本发布做支持
* hotfix-*   当产品版本的重大bug需要立即解决的时候，我们从对应版本的标签创建出一个热补丁分支。
* feature-*  特性分支是用来为下一发布版本开发新特性

#项目介绍

* fixflow-root  根项目，用来聚合各个模块

* fixflow-core、fixflow-expand  引擎内核模块

* fixflow-webapp-common 、fixflow-common-api、fixflow-common-servlet bpmcenter项目公用代码

* fixflow-webapp-taskcenter  任务中心模块

* fixflow-webapp-managecenter 、fixflow-manage-serlvet 管控中心模块

* fixflow-webapp-explorer 资源管理器模块

* fixflow-webapp-editor、fixflow-converter web设计器模块

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


#引擎构架图
Fixflow内核采用的Token驱动驱动机制,Api则层借鉴了Activiti的设计,并基于BPMN2.0的执行语义设计。
![引擎构架图](http://images.cnitblog.com/blog/20120/201401/231607436798.png)

#功能模块图
![功能模块图](http://images.cnitblog.com/blog/20120/201401/231629069445.png)

#Eclipse设计器界面
![Eclipse设计器界面](http://images.cnitblog.com/blog/20120/201401/231630266632.png)

#Web设计器界面
![Web设计器界面](http://images.cnitblog.com/blog/20120/201401/231631074447.png)


#如何提交bug或者问题
在Fixteam/FixFlow项目上点击Issues->New Issue提交bug,在标签栏选择bug、优先级、状态（未解决）三个标签，然后提交。我们会根据bug内容反馈相关信息给您。
![系统截图](https://github.com/fixteam/fixflow/wiki/images/Bug20130917093746.png)






#未来的版本
### 6.0.0（2014-12）
6.0版本采用全新的名称和架构

[FoxBPM 项目地址](https://github.com/FoxBPM/FoxBPM)





#历史的脚印

### 5.2.0（2014-3）

1.引入Maven方式构建,重新调整项目结构。

2.调整配置文件位置,流程系统文件位置可自由配置。

3.重构了异常体系,引入异常国际化支持,更加合理的异常管理。

4.重新设计了日志体系引入slf4j,流程的流转过程通过日志框架输出。

5.修复5.2里程碑中的Issues


### 5.1.0（2014-1）

#### Web流程设计器(预览版)

1. 新增流程资源管理器

2. 新增web流程编辑器,支持流程加载、保存、发布,暂不支持连接器


#### Eclipse设计器新增功能

1. 设计器插件新增支持支持kepler版本的eclipse.插件将支持主流的 Indigo (3.7) Juno (4.2)kepler(4.3).

2. 新的表达式编写方式,支持手写表达式不需要弹出编辑框.

3. 流程创建模板功能,选中一个节点创建模板,可以在任何流程中来使用创建的模板.

4. 全新连接器,支持更多控件模型,兼容老连接器可以继续使用.


#### 流程引擎新增功能

1. 引擎支持子流程终止退回主流程指定节点.

2. 新增全局操作表单、全局浏览表单.

3. 数据库语句外置,开发人员可自行修改.

4. 新增执行Rule配置。

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
