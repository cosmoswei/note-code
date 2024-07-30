package com.wei.clipboard;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClipboardSync {

   static String lastContend = "";

    public static void main(String[] args) {
        Thread clipboardPutTask = new Thread(new ClipboardPutTask());
        clipboardPutTask.start();
        Thread clipboardGetTask = new Thread(new ClipboardGetTask());
        clipboardGetTask.start();
    }


    static class ClipboardPutTask implements Runnable {
        @Override
        public void run() {
            try {
                String lastContent = "";
                while (true) {
                    String content = getClipboardContent();
                    if (!content.equals(lastContent)) {
                        sendClipboardToServer(content);
                        lastContent = content;
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getClipboardContent() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable transferable = clipboard.getContents(null);
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String) transferable.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void sendClipboardToServer(String content) {
        String url = "http://127.0.0.1:8081/putClipCloud";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity("{\"data\":\"" + content + "\"}",
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("剪切板内容已成功发送到服务器，响应：" + responseString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClipboardGetTask implements Runnable {

        public void getClipCloud() throws Exception {
            // 创建URL对象
            URL url = new URL("http://localhost:8081/getClipCloud");
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            // 将剪切板内容复制到本地剪切板
            String clipboardData = response.toString();
            if (lastContend.equals(clipboardData)) {
                connection.disconnect();
                return;
            }
            lastContend = clipboardData;
            System.out.println("获取到云剪切板的内容：" + clipboardData);
            StringSelection selection = new StringSelection(clipboardData);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
            // 关闭连接
            connection.disconnect();
        }


        @Override
        public void run() {
            while (true) {
                try {
                    this.getClipCloud();
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}