package com.wei;

import java.util.ArrayList;
import java.util.List;

public class JFRTest {

    public static void main(String[] args) {
        try {
            Thread.sleep(21000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        List<Object> items = new ArrayList<>(1);
        try {
            while (true){
                items.add(new byte[1024]);
            }
        } catch (OutOfMemoryError e){
            System.out.println(e.getMessage());
        }
        assert items.size() > 0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
