import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();

        System.out.printf("%d-%02d-%02d", year, monthValue, dayOfMonth);
    }
}