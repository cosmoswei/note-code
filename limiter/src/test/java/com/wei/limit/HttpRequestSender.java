package com.wei.limit;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpRequestSender {

    static int successCnt = 0;

    static int failCnt = 0;
    private static final String TARGET_URL = "http://localhost:8080/test31"; // 替换为你要发送请求的目标URL

    public static void main(String[] args) {
        // 创建一个定时任务执行器，设置为单线程池
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        long start = System.currentTimeMillis();
        long end = start + 20 * 1000;

        // 设定任务，每隔1秒执行一次
        executorService.scheduleAtFixedRate(HttpRequestSender::sendHttpRequest, 0, 2, TimeUnit.MILLISECONDS);

        while (true) {
            if (end < System.currentTimeMillis()) {
                executorService.shutdownNow();
                break;
            }
        }
        log.info("start: {}", start);
        log.info("System.currentTimeMillis(): {}", System.currentTimeMillis());
    }

    private static void sendHttpRequest() {
        try {
            // 创建URL对象
            URL url = new URL(TARGET_URL);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 获取响应码
            int responseCode = connection.getResponseCode();

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 打印响应信息
            log.info("response: {}", response);
//            successCnt++;
//            System.out.println("successCnt : " + successCnt);
//            System.out.println("success time: " + System.currentTimeMillis());
            // 关闭连接和读取器
            reader.close();
            connection.disconnect();
        } catch (IOException e) {
//            failCnt++;
//            System.out.println("failCnt : " + failCnt);
//            System.out.println("fail time: " + System.currentTimeMillis());
        }
    }
}
