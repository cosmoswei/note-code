package com.wei.leetcode.f3000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution_2981 {

    public static void main(String[] args) {
        String str = "aaaaaa";
        int res = new Solution_2981().maximumLength(str);
        System.out.println("res = " + res);
    }

    /**
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/find-longest-special-substring-that-occurs-thrice-i/solutions/2585837/fei-bao-li-zuo-fa-fen-lei-tao-lun-python-p1g2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maximumLength(String s) {
        char[] charArray = s.toCharArray();
        List<Integer>[] groups = new ArrayList[26];
        Arrays.setAll(groups, i -> new ArrayList<>());
        int count = 0;
        for (int i = 0; i < charArray.length; i++) {
            count++;
            if (i + 1 == charArray.length || charArray[i] != charArray[i + 1]) {
                groups[charArray[i] - 'a'].add(count);
                count = 0;
            }
        }
        int res = 0;
        for (List<Integer> group : groups) {
            if (group.isEmpty()) {
                continue;
            }
            group.sort(Collections.reverseOrder());
            group.add(0);
            group.add(0);
            res = Math.max(res, Math.max(group.get(0) - 2,
                    Math.max(Math.min(group.get(0) - 1, group.get(1)), group.get(2))));
        }
        return res > 0 ? res : -1;
    }

}