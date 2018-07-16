package xyz.tuny.jx.server;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 */
public class MessageRecvExecutor {

    private static ThreadPoolExecutor threadPoolExecutor;

    public static void submit(Runnable task) {
        if (threadPoolExecutor == null) {
            synchronized (MessageRecvExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = (ThreadPoolExecutor) MessageThreadPool.getExecutor(16, -1);
                }
            }
        }
        threadPoolExecutor.submit(task);
    }

}
