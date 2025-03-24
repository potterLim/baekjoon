import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static StringBuilder sBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int diskCount = Integer.parseInt(br.readLine());

        int moves = (1 << diskCount) - 1;
        sBuilder.append(moves).append('\n');
        moveDisksRecursive(diskCount, 1, 3, 2);

        System.out.print(sBuilder);
    }

    private static void moveDisksRecursive(int diskCount, int fromRod, int toRod, int auxRod) {
        if (diskCount == 1) {
            sBuilder.append(fromRod).append(' ').append(toRod).append('\n');
            return;
        }

        moveDisksRecursive(diskCount - 1, fromRod, auxRod, toRod);
        sBuilder.append(fromRod).append(' ').append(toRod).append('\n');
        moveDisksRecursive(diskCount - 1, auxRod, toRod, fromRod);
    }
}
