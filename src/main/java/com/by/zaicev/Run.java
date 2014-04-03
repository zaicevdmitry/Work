package com.by.zaicev;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created with IntelliJ IDEA.
 * User: dmitry.zaicev
 * Date: 03.04.14
 * Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class Run {
    public static void main(String[] args) throws IOException{
            Openfile mainTask = new Openfile();
            ForkJoinPool pool = new ForkJoinPool();
            mainTask.readfile();
            pool.invoke(mainTask);
    }
}
