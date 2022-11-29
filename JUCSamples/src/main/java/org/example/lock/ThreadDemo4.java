package org.example.lock;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadDemo4 {
    public static void main(String[] args) {
        //demoHash();
        demoHashMap();
    }
    public static void demoHashMap(){
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 300; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, "BB").start();
        }
    }


    public static void demoHash(){
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "BB").start();
        }
    }

    public static void demoList(){
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new CopyOnWriteArrayList<>();

//ArrayList is not Thread safe
//        private void add(E e, Object[] elementData, int s) {
//            if (s == elementData.length)
//                elementData = grow();
//            elementData[s] = e;
//            size = s + 1;
//        }
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//
//                System.out.println(list);
//            }, "AA").start();
//        }
        // When we print the list , the list has been modified
        //Exception in thread "AA" java.util.ConcurrentModificationException
    }
}
