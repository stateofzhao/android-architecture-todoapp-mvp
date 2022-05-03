package com.example.android.architecture.blueprints.todoapp;

/**
 * 本例中 Model 指的是与 DataLayer（Repository）交互的传输对象-{@link com.example.android.architecture.blueprints.todoapp.data.Task}。<br/>
 * Repository 处理完业务逻辑产生的结果对象（ValueObject/DTO）作为MVP中的 Model传递给 Presenter,
 * Presenter 再根据 Model 来设置View的属性，在本示例中由于需求简单直接将Model传递给了View，
 * 本例中 Model（Task对象）是Immutable类型View无法更改它，所以也无妨。正常情况下Presenter需要将Model转换为View的属性再设置给View。<br/>
 * View读取结果对象来展示内容，View对结果对象的操作不能够影响到 Model，否则 Presenter 就需要转译下结果对象。<br/>
 * <p/>
 * Created by zfun on 2016/4/20 11:16
 */
public interface BaseMode {
    //此接口是我自己添加的，没有任何用处，只是用来解释MVP中的Model用的，实际使用中不需要此接口。
}
