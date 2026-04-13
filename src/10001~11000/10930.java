import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; ++i) {
            resultBuilder.append(String.format("%02x", hash[i]));
        }

        System.out.println(resultBuilder);
    }
}