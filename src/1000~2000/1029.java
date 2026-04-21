import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int sArtistCount;
    private static int[][] sPrices;
    private static int[][][] sMaximumOwnerCountsByState;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        sArtistCount = scanner.nextInt();
        sPrices = new int[sArtistCount][sArtistCount];
        sMaximumOwnerCountsByState = new int[1 << sArtistCount][sArtistCount][10];

        for (int i = 0; i < (1 << sArtistCount); ++i) {
            for (int j = 0; j < sArtistCount; ++j) {
                Arrays.fill(sMaximumOwnerCountsByState[i][j], -1);
            }
        }

        for (int i = 0; i < sArtistCount; ++i) {
            String priceRow = scanner.next();

            for (int j = 0; j < sArtistCount; ++j) {
                sPrices[i][j] = priceRow.charAt(j) - '0';
            }
        }

        int maximumOwnerCount = calculateMaximumOwnerCountRecursive(0, 1, 0);
        System.out.println(maximumOwnerCount);

        scanner.close();
    }

    private static int calculateMaximumOwnerCountRecursive(int currentArtist, int ownedArtistBitMask, int lastPrice) {
        if (sMaximumOwnerCountsByState[ownedArtistBitMask][currentArtist][lastPrice] != -1) {
            return sMaximumOwnerCountsByState[ownedArtistBitMask][currentArtist][lastPrice];
        }

        int maximumOwnerCount = 1;

        for (int nextArtist = 0; nextArtist < sArtistCount; ++nextArtist) {
            if (hasOwnedArtist(ownedArtistBitMask, nextArtist)) {
                continue;
            }

            int nextPrice = sPrices[currentArtist][nextArtist];

            if (nextPrice < lastPrice) {
                continue;
            }

            int nextOwnedArtistBitMask = ownedArtistBitMask | (1 << nextArtist);
            int ownerCount = 1 + calculateMaximumOwnerCountRecursive(nextArtist, nextOwnedArtistBitMask, nextPrice);

            if (ownerCount > maximumOwnerCount) {
                maximumOwnerCount = ownerCount;
            }
        }

        sMaximumOwnerCountsByState[ownedArtistBitMask][currentArtist][lastPrice] = maximumOwnerCount;
        return maximumOwnerCount;
    }

    private static boolean hasOwnedArtist(int ownedArtistBitMask, int artistNumber) {
        return (ownedArtistBitMask & (1 << artistNumber)) != 0;
    }
}