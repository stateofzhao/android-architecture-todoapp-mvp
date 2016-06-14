# android-architecture-todoapp-mvp
google官方推荐的Android框架MVP例子。自己添加了一些注释和心得。
基于 Model-View-Presenter的机构模式。

核心思想就再下面这张图里面：

![mvp](/mvp.png)

图中的REPOSITORY 就是Model。

源码来自：
https://github.com/googlesamples/android-architecture

# android-architecture-todoapp-mvp-clean
首先看一个Clean Architecture构想图：
![mvp](/CleanArchitecture.png)

约定，从最里面的圈向外依次为1、2、3、4。
- 第一个圈是具体的商业逻辑，就是要实现的功能。
- 第二个圈是 application specific business rules，来确保第一个圈中的商业逻辑能够高效正确的执行完毕，这一层的变化不会影响到任何实体、数据（数据库）和UI，它只是制定规则来运行第一个圈的任务。
- 第三个圈，就是Presenter、Controllers了，负责调用第二个圈来让第一个圈产生数据，然后处理产生的数据来适配 第四个圈（数据库、Web、用户UI等）。
- 第四个圈，展现产出的数据。

这个顺序反过来也可以~

****

基于 android-architecture-todoapp-mvp，使用Clean Architecture的概念来实现。参见链接：
https://github.com/stateofzhao/android-architecture-todoapp-mvp-clean

在这里唠叨下MVP和MVP-Clean的不同：
- MVP，Presenter直接持有Model（Repository），跨Model的业务逻辑（Model中会封装自己需要处理的逻辑，这部分的逻辑一般都是对Model关联的Java Been数据体直接操作的逻辑）实现在Presenter中实现。由于Presenter对业务逻辑至关重要，所以Presenter不光起到隔离Model与View的作用，还起到具体业务逻辑实现的作用，导致Presenter比较臃肿，不便于业务功能的扩展。
- MVP-Clean，这个是MVP结合Clean Architecture来使用的，M、V和基本MVP中的M、V一样，没有变化，关键的是Model这里变了，这里使用Clean Architecture模式把Model和Presenter隔离开了。在基本MVP例子中Presenter直接操作数据仓库Model（REPOSITORY）；而在MVP-Clean例子中不再直接操作数据仓库Model而是针对每个操作都写了一个Task Model。

# 与MVP很相近的MVC模式
参见知乎上的一个链接：
https://www.zhihu.com/question/22886622

回答中讲解的非常好，看了后发现我之前理解的Model和Controller完全是错误的！！
