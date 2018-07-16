package xyz.tuny.jx.server;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 业务处理线程池
 */
public class MessageThreadPool {
	// 独立出线程池主要是为了应对复杂耗I/O操作的业务，不阻塞netty的handler线程而引入
    // 当然如果业务足够简单，把处理逻辑写入netty的handler（ChannelInboundHandlerAdapter）也未尝不可
    public static Executor getExecutor(int threads, int queues) {
        String name = "NettyThreadPool";
        return new ThreadPoolExecutor(threads, threads, 0,  TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<Runnable>()
                        : (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                                : new LinkedBlockingQueue<Runnable>(queues)));
    }
}
