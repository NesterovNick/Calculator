import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        double total = 0;
        String removecategory = null;
        HashMap<String, Double> catgorysum = new HashMap<>();
        boolean count = true;
        while (count == true) {
            try {
            System.out.println("Выберите значение");
            System.out.println("1. Добавить расход");
            System.out.println("2. Сумма расходов");
            System.out.println("3. Выход");
            System.out.println("4. Сводка по категориям: ");
            System.out.println("5. Удаление последней категории: ");
            num = sc.nextInt();
                if (num == 1) {
                    System.out.println("Сколько ты потратил?");
                    double sum = sc.nextDouble();
                    System.out.println("Что за категория?");
                    sc.nextLine();
                    String category = sc.nextLine();
                    removecategory = category;
                    if (sum < 0) {
                        System.out.println("Не может быть трата меньше 0");
                    } else if (catgorysum.containsKey(category)) {
                        sum += catgorysum.get(category);
                        catgorysum.replace(category, sum);
                    } else {
                        catgorysum.put(category, sum);
                        System.out.println("Запомнил! Ты потратил " + sum + " рублей на категорию " + category);

                    }
                } else if (num == 2) {
                    total = 0;
                    for (Double value : catgorysum.values())
                        total += value;
                    System.out.println("Вся сумма расходов: " + total);
                } else if (num == 3) {
                    count = false;
                } else if (num == 4) {
                    System.out.println("Статистика по категориям: ");
                    System.out.println(catgorysum);
                } else if (num == 5) {
                    catgorysum.remove(removecategory);
                    System.out.println("Категория " + removecategory + " удалена!");
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

    /*public class Expense {
        double amount;
        String category;
    }*/


