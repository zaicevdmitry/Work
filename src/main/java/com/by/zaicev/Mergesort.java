package com.by.zaicev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: Dimon
 * Date: 24.03.14
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class Mergesort extends Openfile {



    public int[] Sort(int[] chars) {
        int middle = chars.length / 2;
        int[] left = new int[middle];
        int[] right = new int[chars.length - middle];
        if (chars.length == 1)
            return chars;

        for (int i = 0; i < chars.length; i++) {
            if (i < middle) {
                left[i] = chars[i];
            } else {
                right[i - middle] = chars[i];
            }
        }

        return Merge(Sort(left), Sort(right));
    }

    public int[] Merge(int[] left, int[] right) {
        int[] result  = new int[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (k < result.length) {
            if (i == left.length) {
                result[k] = right[j];
                j++;
            } else if (j == right.length) {
                result[k] = left[i];
                i++;
            } else {
                if (left[i] < right[j]) {
                    result[k] = left[i];
                    i++;
                } else {
                    result[k] = right[j];
                    j++;
                }
            }

            k++;
        }

        return result;
    }



    public static void main(String[] args) throws IOException {
        Mergesort mergesort = new Mergesort();
        int[] chars = Openfile.read();
        int[] result =  mergesort.Sort(chars);
        String res = "";
        for(int i=0; i<result.length; i++){
            res += result[i];
        }
        System.out.println("Результат:" + res);
    }


}
