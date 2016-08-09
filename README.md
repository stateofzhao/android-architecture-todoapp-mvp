# android-architecture-todoapp-mvp
google官方推荐的Android框架MVP例子。自己添加了一些注释和心得。
基于 Model-View-Presenter的机构模式。

核心思想就再下面这张图里面：

![mvp](/mvp.png)

图中的REPOSITORY 就是Model。

源码来自：
https://github.com/googlesamples/android-architecture

# android-architecture-todoapp-mvp-clean
**下面的内容来自[简书](http://www.jianshu.com/p/c6a1a5c9a49b)**

首先看一个Clean Architecture构想图：
![mvp](/CleanArchitecture.jpg)

约定，从最里面的圈向外依次为1、2、3、4。
- 第一个圈是具体的商业逻辑，就是要实现的功能。
- 第二个圈是 application specific business rules，来确保第一个圈中的商业逻辑能够高效正确的执行完毕，这一层的变化不会影响到任何实体、数据（数据库）和UI，它只是制定规则来运行第一个圈的任务。
- 第三个圈，就是Presenter、Controllers了，负责调用第二个圈来让第一个圈产生数据，然后处理产生的数据来适配 第四个圈（数据库、Web、用户UI等）。
- 第四个圈，展现产出的数据。

上面只是一个设计规范，那么具体到代码实现应该怎么设计呢？
下图是一个大概的框架：
![CleanCode](/CleanCode.png)

#### Presentation Layer
这一层是处理UI、动画逻辑的地方，它使用了MVP（Model View Presenter）模式， 你也可以使用 MVC、MVVM（不再深究）。

在这里 Activity和Fragment都是Views，不处理除UI逻辑之外的任何逻辑，许多的渲染操作都在这里完成。

在这一层 Presenters 是由各种 interactors (use cases) 组成的，他们负责执行一个异步任务，并通过回调取回需要的数据给UI渲染。
![CleanCodeMvp](/CleanCodeMvp.png)

Model就相当于各种interactors(use cases)，可以把多个interactors(use cases)封装一下来实现一个页面的逻辑，这样封装好的就是Model了，当然如果不封装也可以。

如果你想看一看一些比较酷的MVP、MVVM例子，可以参考 [Effective Android UI](https://github.com/pedrovgs/EffectiveAndroidUI/) 。

#### Domain Layer
所有的业务逻辑都是在这层处理的。考虑到Android工程，你会看到所有的 interactors (use cases) 也是在这里实现的。

这层是一个纯Java的模块，不包含任何Android依赖，所有的外部交互都是通过接口来实现。
![CleanCodeDomain](/CleanCodeDomain.png)

通过上图可以发现，这一层即包含与 Presentation Layer交互的接口（interactors / use cases），也包含与 Data Layer交互的接口（Repository Interface）。

#### Data Layer
所有App需要的数据都是通过这层的 UserRepository （实现了DomainLayer的接口）提供的，它使用了 [Repository Pattern](http://martinfowler.com/eaaCatalog/repository.html) 的策略—— 通过一个工厂，根据不同的条件抓取不同的数据源。比如，当通过 id 来获取一个 user 时，如果这个 user 在缓存中不存在，那么它会选择磁盘缓存作为数据源，如果磁盘缓存也不存在它会通过云端接口获取数据，并保存在本地缓存中。

#### Error Handling
这是一个值得讨论的话题，非常欢迎任何分享。我的策略是实用回调机制。如果 DataRepo 发生变化，回调接口提供两个方法：onResponse() 和 onError(), 后者把错误封装在 ErrorBundle 类里面：这种处理方式带来一个问题，错误通过层层传递（就是异步编程中常见的 [CallbackHell](http://callbackhell.com/) 问题 ）导致代码可读性变差。

或者可以使用 EventBus 来处理，但是这种方式有点像 [GOTO](http://www.drdobbs.com/jvm/programming-with-reason-why-is-goto-bad/228200966) 会导致代码逻辑混乱。

github代码示例：
[Here is the github link](https://github.com/android10/Android-CleanArchitecture)

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
