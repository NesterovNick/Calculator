import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        String line = "Категория: eda сумма: 50.0 дата: [2025-11-24]";
        LocalDate date = LocalDate.now();
        if (line.startsWith("Категория: ") && line.contains("сумма: ") && line.contains("дата: ")){
            String[] parts = line.split(": ");
            String category = parts[1].replace(" сумма", "");
            double amount = Double.parseDouble(parts[2].replace(" дата", ""));
            date = LocalDate.parse(parts[3].replace("[", "").replace("]", ""));
            System.out.println("Категория: " + category + " Сумма: " + amount + " Дата: " + date);
        }
    }
}
