package com.wei.jmx;

import jdk.jfr.Recording;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main2 {
    public static void main(String[] args) throws Exception {
        // Start a recording
        try (Recording recording = new Recording()) {
            recording.start();

            // Your application code here

            // Stop the recording
            recording.stop();
            Path path = Paths.get("myrecording.jfr");
            recording.dump(path);

            // Analyze the recording
            List<RecordedEvent> events = RecordingFile.readAllEvents(path);
            for (RecordedEvent event : events) {
                System.out.println(event);
            }
        }
    }
}
