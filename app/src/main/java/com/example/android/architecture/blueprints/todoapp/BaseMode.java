package com.example.android.architecture.blueprints.todoapp;

/**
 * MVP中的Model，之前一直把Model与java EE中的Bean（POJO数据对象）搞混了！<br/>
 * Model不同于 Bean（POJO数据对象），Model中是包含业务逻辑的！！<br/>
 * 这里的Model通常指的是应用逻辑层（也叫领域层）的对象，如 Account、Order 等等。
 * 这些对象是你开发的应用程序中的一些核心对象，负责应用的逻辑计算，
 * 有许多与业务逻辑有关的方法或操作（如 Account.sendEmail()、Order.calculateTotal()、Order.removeItem() 等等），
 * 而不是仅仅像 Bean（POJO数据对象） 那样用来传递数据（getter、setter）。<br/>
 * Model 处理完逻辑获得数据后通常把数据包装成 Bean对象，然后传递给Presenter,Presenter在把Bean交给View。
 * <p/>
 * Created by Diagrams on 2016/4/20 11:16
 */
public interface BaseMode {
    //此接口是我自己添加的，没有任何用处，只是用来解释MVP中的Model用的，实际使用中不需要此接口。
}
