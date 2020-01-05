package com.pullein.common.listener;

/**
 * FileName: AppProcessStateListener.java
 * PackageName: com.pullein.common.listener
 * Author: XG
 * CreateDate: 2019/12/28 23:59
 * Description:app进程状态改变监听
 */
public interface AppProcessStateListener {
    /**
     * 进程进入前台
     */
    void onProcessForeground();

    /**
     * 进程进入后台
     */
    void onProcessBackground();
}
