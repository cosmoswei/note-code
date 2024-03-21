package com.wei.leetcode.f3000;

class FrequencyTracker_2671 {
    private static final int N = 100001;
    private int[] freq;
    private int[] freqCnt;

    public FrequencyTracker_2671() {
        freq = new int[N];
        freqCnt = new int[N];
    }

    public void add(int number) {
        --freqCnt[freq[number]];
        ++freq[number];
        ++freqCnt[freq[number]];
    }

    public void deleteOne(int number) {
        if (freq[number] == 0) {
            return;
        }
        --freqCnt[freq[number]];
        --freq[number];
        ++freqCnt[freq[number]];
    }

    public boolean hasFrequency(int frequency) {
        return freqCnt[frequency] > 0;
    }
}
