import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static final int[] DX = { -1, 0, 1, 1, 0, -1 };
    private static final int[] DY = { 0, 1, 1, 0, -1, -1 };

    private static int sOffset;
    private static int[] sXCoords;
    private static int[] sYCoords;
    private static int[][] sRoomNumbers;
    private static int sMaxRoomNumber;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        int maxTarget = Math.max(start, end);

        buildHoneycomb(maxTarget);

        int[] previousRooms = new int[sMaxRoomNumber + 1];

        findShortestPath(start, end, previousRooms);
        printPath(start, end, previousRooms);
    }

    private static void buildHoneycomb(int maxTarget) {
        int maxRing = calculateMaxRing(maxTarget);

        sMaxRoomNumber = 1 + 3 * maxRing * (maxRing + 1);
        sOffset = maxRing + 2;

        int gridSize = sOffset * 2 + 1;

        sXCoords = new int[sMaxRoomNumber + 1];
        sYCoords = new int[sMaxRoomNumber + 1];
        sRoomNumbers = new int[gridSize][gridSize];

        setRoom(1, 0, 0);

        int roomNumber = 2;

        for (int ring = 1; ring <= maxRing; ++ring) {
            for (int i = 0; i < ring; ++i) {
                setRoom(roomNumber, -ring, -ring + 1 + i);
                ++roomNumber;
            }

            for (int i = 0; i < ring; ++i) {
                setRoom(roomNumber, -ring + 1 + i, 1 + i);
                ++roomNumber;
            }

            for (int i = 0; i < ring; ++i) {
                setRoom(roomNumber, 1 + i, ring);
                ++roomNumber;
            }

            for (int i = 0; i < ring; ++i) {
                setRoom(roomNumber, ring, ring - 1 - i);
                ++roomNumber;
            }

            for (int i = 0; i < ring; ++i) {
                setRoom(roomNumber, ring - 1 - i, -1 - i);
                ++roomNumber;
            }

            for (int i = 0; i < ring; ++i) {
                setRoom(roomNumber, -1 - i, -ring);
                ++roomNumber;
            }
        }
    }

    private static int calculateMaxRing(int maxTarget) {
        int ring = 0;

        while (1 + 3 * ring * (ring + 1) < maxTarget) {
            ++ring;
        }

        return ring;
    }

    private static void setRoom(int roomNumber, int xCoord, int yCoord) {
        sXCoords[roomNumber] = xCoord;
        sYCoords[roomNumber] = yCoord;
        sRoomNumbers[xCoord + sOffset][yCoord + sOffset] = roomNumber;
    }

    private static void findShortestPath(int start, int end, int[] previousRooms) {
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

        previousRooms[start] = -1;
        queue.add(start);

        while (!queue.isEmpty()) {
            int currentRoom = queue.poll();

            if (currentRoom == end) {
                return;
            }

            int currentXCoord = sXCoords[currentRoom];
            int currentYCoord = sYCoords[currentRoom];

            for (int i = 0; i < 6; ++i) {
                int nextXCoord = currentXCoord + DX[i];
                int nextYCoord = currentYCoord + DY[i];
                int nextRoom = sRoomNumbers[nextXCoord + sOffset][nextYCoord + sOffset];

                if (nextRoom == 0) {
                    continue;
                }

                if (previousRooms[nextRoom] != 0) {
                    continue;
                }

                previousRooms[nextRoom] = currentRoom;
                queue.add(nextRoom);
            }
        }
    }

    private static void printPath(int start, int end, int[] previousRooms) {
        ArrayList<Integer> path = new ArrayList<Integer>();

        int currentRoom = end;

        while (currentRoom != -1) {
            path.add(currentRoom);

            if (currentRoom == start) {
                break;
            }

            currentRoom = previousRooms[currentRoom];
        }

        Collections.reverse(path);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < path.size(); ++i) {
            if (i > 0) {
                stringBuilder.append(' ');
            }

            stringBuilder.append(path.get(i));
        }

        System.out.println(stringBuilder);
    }
}