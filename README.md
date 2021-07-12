# 前提
这里一定要说明一下，对于MVP,MVC,MVVM,Clean Architecture 等都只是设计思想，并不是代码框架，具体的框架需要来使用上述的设计思想来实现。就比如下面的MVP实现中，View是Activity,Fragment，Presenter是单独的类来处理逻辑，REPOSITORY可以看做是Model。

# android-architecture-todoapp-mvp
google官方推荐的Android框架MVP例子。自己添加了一些注释和心得。
基于 Model-View-Presenter的机构模式。

核心思想就再下面这张图里面：

![mvp](/mvp.png)

图中的REPOSITORY 也就是数据层，相当于Model。

源码来自：
https://github.com/googlesamples/android-architecture

# android-architecture-todoapp-mvp-clean
**下面的内容来自[简书](http://www.jianshu.com/p/c6a1a5c9a49b)**

首先看一个Clean Architecture构想图：
![mvp](/CleanArchitecture.jpg)

约定，从最里面的圈向外依次为1、2、3、4。
- 第一个圈，是代表App的业务对象（business objects of the application），我的理解也就是相当于数据层。
- 第二个圈，是编排数据从 Entity 入或出的流，也叫交互器(Interactors)，基本上所有业务逻辑都在这里。
- 第三个圈，把 Use Cases 或者 Entity 使用的数据结构转换成需要的形式。Presenters 和 Controllers 都属于这里。
- 第四个圈，框架和驱动：处理细节的地方：UI、工具（tools）、框架（framework）等。

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
- MVP，Presenter直接持并操作数据层UserRepository，数据层就被看做是MVP中的M了。由于Presenter对业务逻辑至关重要，所以Presenter不光起到隔离Model与View的作用，还起到具体业务逻辑实现的作用，导致Presenter比较臃肿，不便于业务功能的扩展。
- MVP-Clean，这个是MVP结合Clean Architecture来使用的，不让Presenter直接处理数据层，而是使用Domain Layer把数据层与上层（Presenter和View）彻底隔离起来，所有的业务逻辑都是在这层处理的，考虑到Android工程，你会看到所有的 interactors (use cases) 也是在这里实现的，这一层是纯java模块，不包含任何Android依赖；Presenter是由各种interactors (use cases) 组成，Presenter其实就是起到一个数据转换作用，把数据层的数据封装成View需要显示的数据；数据层UserRepository（实现了DomainLayer的接口）与上面的基本MVP数据层一样，就是提供APP需要的各种数据，注意数据层需要实现DomainLayer层提供的接口，但是在官方例子中，数据层接口直接放到数据层里面。

通过上面的理解，可以看出俩MVP中的M是一个相当泛类的指代，它并不是指具体的一个类，比如在基本MVP中它指数据层；而在MVP-Clean中指UseCase层的各种useCase。

# MVP是MVC的变种
参见知乎上的一个链接：
https://www.zhihu.com/question/22886622

回答中讲解的非常好，看了后发现我之前理解的Model和Controller有点不一样。

- MVP，Presenter直接持并操作数据层UserRepository，数据层就被看做是MVP中的M了。由于Presenter对业务逻辑至关重要，所以Presenter不光起到隔离Model与View的作用，还起到具体业务逻辑实现的作用，导致Presenter比较臃肿，不便于业务功能的扩展。
