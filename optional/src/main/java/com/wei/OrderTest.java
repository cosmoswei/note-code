package com.wei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("1");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("a");
        list2.add("1");
        list2.add("2");
        list2.add("d");
        list2.add("c");
        list2.add("a");
        System.out.println(list2);
        setListOrder(list1, list2);
        System.out.println(list2);

    }

    private void setListOrderV2(List<String> orderRegulation, List<User> targetList) {
        //按照字段名来排序
        Collections.sort(targetList, ((o1, o2) -> {
            int io1 = orderRegulation.indexOf(o1.getName());
            int io2 = orderRegulation.indexOf(o2.getName());

            if (io1 != -1) {
                io1 = targetList.size() - io1;
            }
            if (io2 != -1) {
                io2 = targetList.size() - io2;
            }

            return io2 - io1;
        }));
    }

    private static void setListOrder(List<String> orderRegulation, List<String> targetList) {
        //按照字段名来排序
        targetList.sort(((o1, o2) -> {
            int io1 = orderRegulation.indexOf(o1);
            int io2 = orderRegulation.indexOf(o2);

            if (io1 != -1) {
                io1 = targetList.size() - io1;
            }
            if (io2 != -1) {
                io2 = targetList.size() - io2;
            }

            return io2 - io1;
        }));
    }


}
