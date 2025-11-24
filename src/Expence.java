import java.time.LocalDate;

public class Expence {
    public double amount;
    public String category;
    public LocalDate date;

    public Expence(double amount, String category, LocalDate date){
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}
