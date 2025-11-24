import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        double total = 0;
        LocalDate date = LocalDate.now();
        ArrayList<Expence> expences = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("lastreport.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Категория: ") && line.contains("сумма: ") && line.contains("дата: ")){
                String[] parts = line.split(": ");
                String category = parts[1].replace(" сумма", "");
                double amount = Double.parseDouble(parts[2].replace(" дата", ""));
                date = LocalDate.parse(parts[3].replace("[", "").replace("]", ""));
                expences.add(new Expence(amount, category, date));
            }
        }
        System.out.println("Данные загружены из lastreport.txt");
        }
        catch (IOException e) {
                System.out.println("Предыдущих данных не найдено! Введите расходы заново");
        }
        boolean count = true;
        while (count == true) {
            try {
                System.out.println("Выберите значение");
            System.out.println("1. Добавить расход");
            System.out.println("2. Сумма расходов");
            System.out.println("3. Выход");
            System.out.println("4. Сводка по категориям: ");
            System.out.println("5. Сохранить в файл: ");
            System.out.println ("6. Введите категорию для поиска: ");
            num = sc.nextInt();
                if (num == 1) {
                    System.out.println("Сколько ты потратил?");
                    double sum = sc.nextDouble();
                    System.out.println("Что за категория?");
                    sc.nextLine();
                    String category = sc.nextLine();
                    System.out.println("Введите дату формата: ГГГГ-ММ-ДД ");
                    date = LocalDate.parse(sc.nextLine());
                    System.out.println("Дата успешно записана");
                    Expence newExpence = new Expence(sum, category, date);
                    expences.add(newExpence);
                    System.out.println("Запомнил! Ты потратил " + sum + " рублей на категорию " + category + " " + date);
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
                            writer.write("Полная история расходов:\n");
                            writer.write("========================\n");
                            for (Expence exp : expences){
                                writer.write("Категория: " + exp.category + " сумма: " + exp.amount + " дата: [" + exp.date +"]\n");
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
                    for (Expence exp : expences) {
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
                        writer.write("Полная история расходов:\n");
                        writer.write("========================\n");
                        for (Expence exp : expences){
                            writer.write("Категория: " + exp.category + " сумма: " + exp.amount + " дата: [" + exp.date +"]\n");
                        }
                        writer.write("\nОбщая сумма: " + total + " руб.\n");
                        System.out.println("Данные успешно записаны в файл: report.txt");
                    }   catch (IOException e){
                        System.err.println("Произошла ошибка при записи в файл: report.txt");
                    }
                } else if (num == 6) {
                    System.out.println("Введите категорию: ");
                    sc.nextLine();
                    String searchingcategory = sc.nextLine();
                    System.out.println("Ваша категория: " + searchingcategory);
                    boolean found = false;
                    for (Expence exp : expences){
                        if (searchingcategory.equalsIgnoreCase(exp.category)){
                            System.out.println(exp.category + " " + exp.amount + " " + exp.date);
                            found = true;
                        }
                    }
                    if (!found){
                        System.out.println("Категория не найдена!");
                    }
                }
                else {
                    System.out.println("Такой цифры нет в меню.");
                }
            } catch (Exception e) {
                System.out.println("Это не цифра/число!");
                sc.nextLine();
            }
        }
    }
}
