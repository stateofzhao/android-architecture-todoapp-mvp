/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp;

/**
 * Presenter 是Model和View的中间衔接层，一个标准的Presenter中应该至少包含一个Model和一个View。<br/>
 * 在MVP模式中，View不允许与Model交互，View仅负责展示数据。
 * View通过Presenter来获取Model（注意不是操作Model，例如在本例中是通过操作Repository来获取的Model），
 * Presenter 获取到Model后来更新View。<br/>
 * 数据流通是这样的:
 *      ->           ----Model作为参数-->                 Remote data source
 * View    Presenter                      Repository ->
 *      <-            <--Model作为结果---                 Local data source
 * <br/>
 * 需要注意：Presenter 一般不含有业务逻辑，仅仅负责将Model返回的数据转换为View显示的数据<br/>
 *
 */
public interface BasePresenter {
    void start();
}
