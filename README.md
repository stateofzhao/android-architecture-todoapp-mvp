# android-architecture-todoapp-mvp
google官方推荐的Android框架MVP例子。自己添加了一些注释和心得。
基于 Model-View-Presenter的机构模式。

核心思想就再下面这张图里面：

![mvp](/mvp.png)

图中的REPOSITORY 也就是数据层，相当于Model。

源码来自：
https://github.com/googlesamples/android-architecture

# android-architecture-todoapp-mvp-clean
首先看一个Clean Architecture构想图：
![mvp](/CleanArchitecture.jpg)

约定，从最里面的圈向外依次为1、2、3、4。
- 第一个圈，是代表App的业务对象（business objects of the application），我的理解也就是相当于数据层。
- 第二个圈，是编排数据从 Entity 入或出的流，也叫交互器(Interactors)，基本上所有业务逻辑都在这里。
- 第三个圈，把 Use Cases 或者 Entity 使用的数据结构转换成需要的形式。Presenters 和 Controllers 都属于这里。
- 第四个圈，框架和驱动：处理细节的地方：UI、工具（tools）、框架（framework）等。


****

基于 android-architecture-todoapp-mvp，使用Clean Architecture的概念来实现。参见链接：
https://github.com/stateofzhao/android-architecture-todoapp-mvp-clean

在这里唠叨下MVP和MVP-Clean的不同：
- MVP，Presenter直接持并操作数据层UserRepository，数据层就被看做是MVP中的M了。由于Presenter对业务逻辑至关重要，所以Presenter不光起到隔离Model与View的作用，还起到具体业务逻辑实现的作用，导致Presenter比较臃肿，不便于业务功能的扩展。
- MVP-Clean，这个是MVP结合Clean Architecture来使用的，不让Presenter直接处理数据层，而是使用Domain Layer把数据层与上层（Presenter和View）彻底隔离起来，所有的业务逻辑都是在这层处理的，考虑到Android工程，你会看到所有的 interactors (use cases) 也是在这里实现的，这一层是纯java模块，不包含任何Android依赖；Presenter是由各种interactors (use cases) 组成，Presenter其实就是起到一个数据转换作用，把数据层的数据封装成View需要显示的数据；数据层UserRepository（实现了DomainLayer的接口）与上面的基本MVP数据层一样，就是提供APP需要的各种数据，注意数据层需要实现DomainLayer层提供的接口，但是在官方例子中，数据层接口直接放到数据层里面。

通过上面的理解，可以看出俩MVP中的M是一个相当泛类的指代，它并不是指具体的一个类，比如在基本MVP中它指数据层；而在MVP-Clean中指UseCase层的各种useCase。

# 与MVP很相近的MVC模式
参见知乎上的一个链接：
https://www.zhihu.com/question/22886622

回答中讲解的非常好，看了后发现我之前理解的Model和Controller完全是错误的！！
