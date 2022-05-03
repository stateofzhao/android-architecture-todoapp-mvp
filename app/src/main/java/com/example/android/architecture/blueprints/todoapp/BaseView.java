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
 * MVP中的View，显示数据，响应操作。<br/>
 * View中也要尽量去除所有逻辑操作，比如Activity的各个生命周期方法，以及Activity的
 * onActivityResult(int requestCode, int resultCode, Intent data)方法都需要放到Presenter中托管和处理。<br/>
 * <P/>
 * 不能够直接操作Model即不能持有Model。<br/>
 * 对于简单的需求，可以持有Model，并根据Model来显示数据，前提是Model必须为Immutable类型。<br/>
 */
public interface BaseView<T> {
    /** 让View持有Presenter，这样可以让View在合适的时机（View初始化完成）来调用Presenter中的方法加载数据 */
    void setPresenter(T presenter);
}
