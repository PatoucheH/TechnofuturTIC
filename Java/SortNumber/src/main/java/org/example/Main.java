package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        while (true) {

        String result = "";
        System.out.print("Please enter a number:");
        int num1 = sc.nextInt();
        System.out.print("Please enter a number:");
        int num2 = sc.nextInt();
        System.out.print("Please enter a number:");
        int num3 = sc.nextInt();

        if(num1 == num2 && num1 == num3) result = num1 + " = " + num2 + " = " + num3;
        else if(num1 == num2){
            if(num1 < num3) result += num1 + " = " + num2 + " < " + num3;
            else result += num3 + " < " + num1 + " = " + num2;
        }else if(num1 == num3){
            if(num1 < num2) result += num1 + " = " + num3 + " < " + num2;
            else result += num2 + " < " + num1 + " = " + num3;
        }else if(num2 == num3){
            if(num2 < num1) result += num3 + " = " + num2 + " < " + num1;
            else result += num1 + " < " + num2 + " = " + num3;
        }else if(num1 < num2 && num1 < num3){
            if(num2 < num3) result += num1 + " < " + num2 + " < " + num3;
            else result += num1 + " < " + num3 + " = " + num2;
        }else if (num2 < num1 && num2 < num3){
            if(num1 < num3) result += num2 + " < " + num1 + " < " + num3;
            else result += num2 + " < " + num3 + " < " + num1;
        }else{
            if(num1 < num2) result += num3 + " < " + num1 + " < " + num2;
            else result += num3 + " < " + num2 + " < " + num1;
        }

        System.out.println(result);

        }
    }
}
