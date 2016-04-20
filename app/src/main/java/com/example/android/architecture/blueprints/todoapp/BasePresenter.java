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
 * 在MVP模式中，View是不允许与Model交互的，View只是负责展示数据，是彻底的哑View。当View需要更新数据时，
 * 需要通过Presenter来操作Model，Model获取到数据后再通过Presenter来更新View。<br/>
 * 所以数据流通是这样的:
 *      ->           ->           Remote data source
 * View    Presenter    Model ->
 *      <-            <-          Local data source
 *
 * 作为Presenter，不只是请求数据和更新UI，它还有另一层作用，响应UI来更新数据。<br/>
 * 在MVP中View不能够直接修改Mode（数据），View要想修改Mode（数据）必须通过Presenter来实现，这样就把View和
 * Mode（数据）彻底分割开来了，View彻底沦为哑View了。
 */
public interface BasePresenter {

    void start();

    //感觉这里应该再添加一个释放资源的方法
    //    void destroy();
}
