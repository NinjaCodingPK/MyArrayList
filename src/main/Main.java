package main;

import myUtil.MyArrayList;

import java.util.ListIterator;

/**
 * Created by wookie on 6/2/16.
 */
public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> awesomeList = new MyArrayList();
        awesomeList.add(new Integer(4));
        awesomeList.add(new Integer(10));
        awesomeList.add(new Integer(3));
        awesomeList.add(new Integer(576));

        for (Integer i : awesomeList) {
            System.out.println(i);
        }
        System.out.println("======================");
        ListIterator<Integer> iter = awesomeList.listIterator();
        Integer tmp = iter.next();
        iter.remove();
        iter.add(3);
        for (Integer i: awesomeList) {
            System.out.println(i);
        }
    }
}
