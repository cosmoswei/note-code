package com.wei.clipboard;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.Deque;

@RestController
public class ClipCloudController {

    @Data
    static class ClipCloudDTO {
        private String data;
    }

    Deque<String> deque = new ArrayDeque<>(10);

    @RequestMapping("/putClipCloud")
    public String putClipCloud(@RequestBody ClipCloudDTO clipCloudDTO) {
        put(clipCloudDTO.getData());
        return "add ["+ clipCloudDTO.getData() + "] success";
    }

    @GetMapping("/getClipCloud")
    public String getClipCloud() {
        return get();
    }

    private void put(String key) {
        if (deque.size() > 10) {
            deque.removeFirst();
        }
        deque.offer(key);
    }

    private String get() {
        if (deque.isEmpty()) {
            return "";
        }
        return deque.getLast();
    }
}