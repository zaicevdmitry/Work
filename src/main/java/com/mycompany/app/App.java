package com.mycompany.app;


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class App 
{
	 private static Scanner keyb;
	    private static int mass[];


	    public static void Urovn(){

	        float a,b,c,d;
	        double x1 = 0;
	        double x2 = 0;

	        keyb = new Scanner(System.in);
	        System.out.print("Vvedite a  = ");
	        a = keyb.nextFloat();
	        System.out.print("Vvedite b  = ");
	        b = keyb.nextFloat();
	        System.out.print("Vvedite c = ");
	        c = keyb.nextFloat();

	        d = b * b - 4 * a *c;
	        if(d<0){
	            System.out.println("D < 0");
	        }else{
	            if(d == 0){
	                x1 = -b/(2*a);
	                System.out.print("X1 = " + x1);
	            }else{
	                x1 = -b + Math.sqrt(d)/2*a;
	                x2 = -b - Math.sqrt(d)/2*a;
	                System.out.print("X1 = " + x1);
	                System.out.println(" X2 = " + x2);
	            }
	        }
            System.out.println("******************************");
	    }

	    public static void Mass(){
	        int mass[] = {23, 65, 105, 1, 23, 65, 78, 98, 32, 45, 66};
	        int max = mass[0];
	        int min = mass[0];

	        //for(int i = 0; i !=  mass.length; i++) {
	        //  mass[i] =  (float) Math.floor(Math.random());
	        //}

	        for(int i = 0; i != mass.length; i++){
	            if(mass[i] > max){
	                max = mass[i];
	            }
	            if(mass[i] < min){
	                min = mass[i];
	            }
	        }
	        System.out.println("MAX = " + max);
            System.out.println("MIN = " + min);

            System.out.println("******************************");
	    }

	    public static void SortMass(){
	        Integer[] mass = new Integer[] {23, 65, 105, 1, 23, 65, 78, 98, 32, 45, 66};
	        //Collections.reverseOrder()
//	        Arrays.sort(mass);
            Arrays.sort(mass,Collections.reverseOrder());
            System.out.println("Отсортированный массив:");
	        for(int r : mass)
	            System.out.println(r);
	    }

	    public static void main(String args[]){
	        Urovn();
	        Mass();
	        SortMass();
	    }
}
