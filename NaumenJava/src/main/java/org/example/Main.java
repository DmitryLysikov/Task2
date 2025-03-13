package org.example;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.print("Введите номер задания (1, 2, 3, 4, 5, 6-выход): ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    Task1 task1 = new Task1();
                    task1.AvrgMass();
                    break;
                case 2:
                    Task2 task2 = new Task2();
                    StartTask2(task2, scanner);
                    break;
                case 3:
                    StartEmployee();
                    break;
                case 4:
                    Task4 task4 = new Task4();
                    task4.UrlGet();
                    break;
                case 5:
                    if(args.length < 2){
                        System.out.println("Нет пути");
                        break;
                    }
                    String sourceDir = args[0];
                    String targetDir = args[1];
                    FolderSyncTask task = new FolderSyncTask(sourceDir, targetDir);
                    task.start();
                    Thread.sleep(2000);
                    task.stop();
                    break;
                case 6:
                    System.out.println("Выход из программы.");
                    check = false;
                    break;
            }
        }
        scanner.close();
    }

    public static void StartEmployee() {
        List<Employee> employees = List.of(
                new Employee("Lysikov Dima Aleksandrovich", 20, "It", 100000.0),
                new Employee("Shkiper Nikita Aleksandrovich", 21, "It", 100009.0),
                new Employee("Hit Vova Aleksandrovich", 30, "It", 302000.0),
                new Employee("Dog Maks Aleksandrovich", 25, "It", 100000.9012),
                new Employee("Ritter Dima Aleksandrovich", 19, "It", 103400.32),
                new Employee("Fifa Dima Aleksandrovich", 26, "It", 500000.0)
        );
        List<Employee> stream = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary))
                .toList();
        stream.forEach(System.out::println);
    }

    public static void StartTask2(Task2 task2, Scanner sc) {
        System.out.print("Введите количесвто элементов: ");
        int n = sc.nextInt();
        List<Double> array = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            array.add(i, random.nextDouble());
        }
        System.out.println(array);
        System.out.println(task2.bubbleSort(array));
    }
}