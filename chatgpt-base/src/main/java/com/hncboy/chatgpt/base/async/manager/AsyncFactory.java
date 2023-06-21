package com.hncboy.chatgpt.base.async.manager;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

@Slf4j
public class AsyncFactory {

    public static TimerTask executeMethod(Object o, String method, Object... args) {

        return new TimerTask() {
            @Override
            public void run() {
                ReflectUtil.invoke(o, method, args);
            }
        };
    }

}
