package com.pullein.common.listener;

/**
 * FileName: TwoParameterListener.java
 * PackageName: com.pullein.common.listener
 * Author: XG
 * CreateDate: 2019/12/29 0:00
 * Description:有两个参数回调
 */
public interface TwoParameterListener<T, E> {
    void onResult(T t, E e);
}
