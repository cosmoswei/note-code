package com.wei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailPrefixTree {
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    static class Email {
        private String sender;
        private String title;
        private String sendTime;

        public Email(String sender, String title, String sendTime) {
            this.sender = sender;
            this.title = title;
            this.sendTime = sendTime;
        }

        public String getSender() {
            return sender;
        }

        public String getTitle() {
            return title;
        }

        public String getSendTime() {
            return sendTime;
        }
    }

    static TrieNode root;

    public static void main(String[] args) {
        List<Email> emails = loadEmails(); // 加载邮件数据
        buildPrefixTree(emails); // 构建前缀树

        // 在前缀树中查找匹配的邮件
        List<Email> matchedEmails = searchEmails("send", emails);

        // 显示匹配的邮件
        for (Email email : matchedEmails) {
            System.out.println(email.getSender() + " - " + email.getTitle() + " - " + email.getSendTime());
        }
    }

    // 加载邮件数据的示例方法
    private static List<Email> loadEmails() {
        List<Email> emails = new ArrayList<>();

        // 假设从数据库或其他数据源中加载邮件数据
        // 这里仅作示例，手动创建几封邮件
        emails.add(new Email("sender1@example.com", "Title 1", "2022-01-01 08:00:00"));
        emails.add(new Email("sender2@example.com", "Title 2", "2022-01-02 09:00:00"));
        emails.add(new Email("sender3@example.com", "Title 3", "2022-01-03 10:00:00"));
        emails.add(new Email("sender4@example.com", "Title 3", "2022-01-03 10:00:00"));
        emails.add(new Email("sender5@example.com", "Title 3", "2022-01-03 10:00:00"));
        // ...

        return emails;
    }

    // 构建前缀树的方法
    private static void buildPrefixTree(List<Email> emails) {
        root = new TrieNode();

        for (Email email : emails) {
            String sender = email.getSender().toLowerCase();
            String title = email.getTitle().toLowerCase();
            String sendTime = email.getSendTime().toLowerCase();

            insertString(sender);
            insertString(title);
            insertString(sendTime);
        }
    }

    // 在前缀树中插入一个字符串
    private static void insertString(String str) {
        TrieNode node = root;

        for (char ch : str.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }

        node.isEndOfWord = true;
    }

    // 在前缀树中搜索匹配的邮件
    private static List<Email> searchEmails(String prefix, List<Email> emails) {
        List<Email> matchedEmails = new ArrayList<>();
        TrieNode node = root;

        for (char ch : prefix.toLowerCase().toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return matchedEmails;
            }
            node = node.children.get(ch);
        }

        searchHelper(prefix.toLowerCase(), node, matchedEmails, emails);

        return matchedEmails;
    }

    // 辅助方法：在前缀树中搜索匹配的邮件
    private static void searchHelper(String prefix, TrieNode node, List<Email> matchedEmails, List<Email> emails) {
        if (node.isEndOfWord) {
            for (Email email : emails) {
                if (email.getSender().toLowerCase().startsWith(prefix)
                        || email.getTitle().toLowerCase().startsWith(prefix)
                        || email.getSendTime().toLowerCase().startsWith(prefix)) {
                    matchedEmails.add(email);
                }
            }
        }

        for (char ch : node.children.keySet()) {
            searchHelper(prefix + ch, node.children.get(ch), matchedEmails, emails);
        }
    }
}
