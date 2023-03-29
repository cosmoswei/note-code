package com.wei.guvva;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncTest {


    public static void main(String[] args) {


        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("callable execute...");
                TimeUnit.SECONDS.sleep(1);
                return 1;
            }
        });
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                //成功执行...
                System.out.println("Get listenable future's result with callback " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                //异常情况处理...
                t.printStackTrace();
            }
        }, executorService);
    }

}
