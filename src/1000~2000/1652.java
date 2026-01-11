import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int roomSize = scanner.nextInt();
        char[][] roomMap = new char[roomSize][roomSize];

        for (int i = 0; i < roomSize; ++i) {
            String line = scanner.next();
            for (int j = 0; j < roomSize; ++j) {
                roomMap[i][j] = line.charAt(j);
            }
        }

        int horizontalCount = 0;
        int verticalCount = 0;

        for (int i = 0; i < roomSize; ++i) {
            int continuousEmpty = 0;
            for (int j = 0; j < roomSize; ++j) {
                if (roomMap[i][j] == '.') {
                    ++continuousEmpty;
                } else {
                    if (continuousEmpty >= 2) {
                        ++horizontalCount;
                    }
                    continuousEmpty = 0;
                }
            }
            if (continuousEmpty >= 2) {
                ++horizontalCount;
            }
        }

        for (int j = 0; j < roomSize; ++j) {
            int continuousEmpty = 0;
            for (int i = 0; i < roomSize; ++i) {
                if (roomMap[i][j] == '.') {
                    ++continuousEmpty;
                } else {
                    if (continuousEmpty >= 2) {
                        ++verticalCount;
                    }
                    continuousEmpty = 0;
                }
            }
            if (continuousEmpty >= 2) {
                ++verticalCount;
            }
        }

        System.out.println(horizontalCount + " " + verticalCount);
    }
}