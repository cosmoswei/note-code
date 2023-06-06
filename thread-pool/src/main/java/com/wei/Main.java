package com.wei;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
public class Main {
    public static void main(String[] args) {
        GlobalExecutor.submitCommonExecutor(() -> {
            System.out.println("huangxweui");
        });
    }

    //自定义dubbo业务线程池
    @Bean(name = "dubboAsyncBizExecutor")
    public ThreadPoolTaskExecutor dubboAsyncBizExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("dubboAsyncBizExecutor-");
        executor.setRejectedExecutionHandler((r, executor1) -> log.error("dubbo async biz  task exceed limit"));
        return executor;
    }

//    public CompletableFuture<Result<GuessQuestionResponse>> asyncPredictQuestion(PredictQuestionExtRequest request) {
//        log.info("asyncPredictQuestion start");
//        CompletableFuture<Result<GuessQuestionResponse>> resultCompletableFuture =
//                CompletableFuture.supplyAsync(() -> predictQuestionNew(request), dubboAsyncBizExecutor);
//        log.info("asyncPredictQuestion end");
//        return resultCompletableFuture;
//    }
}
