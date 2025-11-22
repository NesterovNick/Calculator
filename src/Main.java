import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        double total = 0;
        ArrayList<Expence> expences = new ArrayList<>();
        boolean count = true;
        while (count == true) {
            try {
            System.out.println("Выберите значение");
            System.out.println("1. Добавить расход");
            System.out.println("2. Сумма расходов");
            System.out.println("3. Выход");
            System.out.println("4. Сводка по категориям: ");
            System.out.println("5. Сохранить в файл: ");
            num = sc.nextInt();
                if (num == 1) {
                    System.out.println("Сколько ты потратил?");
                    double sum = sc.nextDouble();
                    System.out.println("Что за категория?");
                    sc.nextLine();
                    String category = sc.nextLine();
                    Expence newExpence = new Expence(sum, category);
                    expences.add(newExpence);
                    System.out.println("Запомнил! Ты потратил " + sum + " рублей на категорию " + category);
                } else if (num == 2) {
                    total = 0;
                    for (Expence exp : expences)
                        total += exp.amount;
                    System.out.println("Вся сумма расходов: " + total);
                } else if (num == 3) {
                    System.out.println("Хотите сохранить файл? Да/Нет ");
                    sc.nextLine();
                    String answer = sc.nextLine();
                    if (answer.equals("да") || answer.equals("Да") || answer.equals("ДА")){
                        System.out.println("Происходит сохранение в файл.");
                        try (FileWriter writer = new FileWriter("lastreport.txt")) {
                            HashMap<String, Double> stats = new HashMap<>();
                            for (Expence exp :expences) {
                                String catgory = exp.category;
                                double sum = exp.amount;
                                if (stats.containsKey(catgory)) {
                                    stats.put(catgory, stats.get(catgory) + sum);
                                } else {
                                    stats.put(catgory, sum);
                                }
                            }
                            writer.write("ОТЧЕТ ПО КАТЕГОРИЯМ\n");
                            writer.write("===================\n");
                            for (String category : stats.keySet()){
                                writer.write("- " + category + ": " + stats.get(category) + " руб.\n");
                            }
                            writer.write("\nОбщая сумма: " + total + " руб.\n");
                            System.out.println("Данные успешно записаны в файл: lastreport.txt");
                        }   catch (IOException e){
                            System.err.println("Произошла ошибка при записи в файл: lastreport.txt");
                        }
                        count = false;

                    } else {
                        count = false;
                    }
                } else if (num == 4) {
                    HashMap<String, Double> stats = new HashMap<>();
                    for (Expence exp :expences) {
                        String catgory = exp.category;
                        double sum = exp.amount;
                        if (stats.containsKey(catgory)){
                            stats.put(catgory, stats.get(catgory) + sum);
                        } else {
                            stats.put(catgory, sum);
                        }
                    }
                    System.out.println("Статистика по категориям:");
                    for (String category : stats.keySet()){
                        System.out.println("- " + category + ": " + stats.get(category) + " руб.");
                    }
                } else if (num == 5) {
                    System.out.println("Происходит сохранение в файл.");
                    try (FileWriter writer = new FileWriter("report.txt")) {
                        HashMap<String, Double> stats = new HashMap<>();
                        for (Expence exp :expences) {
                            String catgory = exp.category;
                            double sum = exp.amount;
                            if (stats.containsKey(catgory)) {
                                stats.put(catgory, stats.get(catgory) + sum);
                            } else {
                                stats.put(catgory, sum);
                            }
                        }
                        writer.write("ОТЧЕТ ПО КАТЕГОРИЯМ\n");
                        writer.write("===================\n");
                        for (String category : stats.keySet()){
                            writer.write("- " + category + ": " + stats.get(category) + " руб.\n");
                        }
                        writer.write("\nОбщая сумма: " + total + " руб.\n");
                        System.out.println("Данные успешно записаны в файл: report.txt");
                    }   catch (IOException e){
                        System.err.println("Произошла ошибка при записи в файл: report.txt");
                    }
                } else {
                    System.out.println("Такой цифры нет в меню.");
                }
            } catch (Exception e) {
                System.out.println("Это не цифра/число!");
                sc.nextLine();
            }
        }
    }
}
