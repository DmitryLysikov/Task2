package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void AvrgMass() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количесвто элементов: ");
        int n = sc.nextInt();
        int[] mass = new int[n];
        int sum = 0;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            mass[i] = random.nextInt();
            sum += mass[i];
        }
        double avrValue = (double) sum / n;
        long roundAvr = Math.round(avrValue);
        System.out.println(Arrays.toString(mass));
        System.out.println("Avr: " + roundAvr);
    }
}
