package com.wei.jmx;

import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JFRExample {
    public static void main(String[] args) throws Exception {
        // 开始录制
        try (Recording recording = new Recording()) {
            recording.start();

            // 模拟一些工作负载
            Thread.sleep(5000);

            // 停止录制
            recording.stop();
            Path path = Paths.get("myrecording.jfr");
            recording.dump(path);

            // 读取录制的事件
            List<RecordedEvent> events = RecordingFile.readAllEvents(path);
            for (RecordedEvent event : events) {
                System.out.println(event);
            }
        }
    }
}
