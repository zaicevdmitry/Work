package com.mycompany.app;

/**
 * Created with IntelliJ IDEA.
 * User: Dimon
 * Date: 18.03.14
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciNumber {


    static BigInteger getByLoopWithBigInteger(int n) {

        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        for (int i = 0; i < n; i++) {
            BigInteger saveA = a;
            a = b;
            b = saveA.add(a);
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер n = ");
        int num = in.nextInt();
        System.out.println("Число Фибоначчи = "
                +FibonacciNumber.getByLoopWithBigInteger(num));
    }
}
