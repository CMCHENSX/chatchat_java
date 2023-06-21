package com.hncboy.chatgpt.base.async.manager;

import cn.hutool.extra.spring.SpringUtil;
import com.hncboy.chatgpt.base.async.threads.Threads;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 异步任务管理器
 * 
 * @author ruoyi
 */
public class AsyncManager
{
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ThreadPoolTaskExecutor executor = SpringUtil.getBean("threadPoolTaskExecutor");

    /**
     * 单例模式
     */
    private AsyncManager(){}

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me()
    {
        return me;
    }

    /**
     * 执行任务
     * 
     * @param task 任务
     */
    public void execute(TimerTask task)
    {
        executor.execute(task);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown()
    {
//        Threads.shutdownAndAwaitTermination(executor);
        executor.shutdown();
    }
}
