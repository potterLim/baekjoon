import java.util.Scanner;

public class Main {
    private static final int ALPHABET_COUNT = 26;
    private static final int NONE = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String[] pieces = new String[n];
        String[] compressedPieces = new String[n];

        int[] nextPieceByStart = new int[ALPHABET_COUNT];
        int[] prevPieceByEnd = new int[ALPHABET_COUNT];
        int[] pureLetterCounts = new int[ALPHABET_COUNT];
        int[] endLetterOfPiece = new int[n];
        boolean[] isMiddleLetterUsed = new boolean[ALPHABET_COUNT];

        for (int i = 0; i < ALPHABET_COUNT; ++i) {
            nextPieceByStart[i] = NONE;
            prevPieceByEnd[i] = NONE;
        }

        boolean isValid = true;

        for (int i = 0; i < n; ++i) {
            pieces[i] = scanner.next();
            compressedPieces[i] = compress(pieces[i]);

            if (!isCompressedGroupWord(compressedPieces[i])) {
                isValid = false;
            }
        }

        if (!isValid) {
            System.out.println("gg");
            scanner.close();
            return;
        }

        for (int i = 0; i < n; ++i) {
            String compressed = compressedPieces[i];

            if (compressed.length() == 1) {
                int letterIndex = compressed.charAt(0) - 'a';
                pureLetterCounts[letterIndex] += pieces[i].length();
                continue;
            }

            int startLetter = compressed.charAt(0) - 'a';
            int endLetter = compressed.charAt(compressed.length() - 1) - 'a';

            if (nextPieceByStart[startLetter] != NONE) {
                isValid = false;
                break;
            }

            if (prevPieceByEnd[endLetter] != NONE) {
                isValid = false;
                break;
            }

            nextPieceByStart[startLetter] = i;
            prevPieceByEnd[endLetter] = i;
            endLetterOfPiece[i] = endLetter;

            for (int j = 1; j < compressed.length() - 1; ++j) {
                int middleLetter = compressed.charAt(j) - 'a';

                if (isMiddleLetterUsed[middleLetter]) {
                    isValid = false;
                    break;
                }

                isMiddleLetterUsed[middleLetter] = true;
            }

            if (!isValid) {
                break;
            }
        }

        if (isValid) {
            for (int i = 0; i < ALPHABET_COUNT; ++i) {
                if (!isMiddleLetterUsed[i]) {
                    continue;
                }

                if (pureLetterCounts[i] > 0) {
                    isValid = false;
                    break;
                }

                if (nextPieceByStart[i] != NONE) {
                    isValid = false;
                    break;
                }

                if (prevPieceByEnd[i] != NONE) {
                    isValid = false;
                    break;
                }
            }
        }

        if (!isValid) {
            System.out.println("gg");
            scanner.close();
            return;
        }

        boolean[] visitedLetters = new boolean[ALPHABET_COUNT];
        int componentCount = 0;
        String answer = "";

        for (int i = 0; i < ALPHABET_COUNT; ++i) {
            boolean hasPurePart = pureLetterCounts[i] > 0;
            boolean hasOutgoingPart = nextPieceByStart[i] != NONE;
            boolean hasIncomingPart = prevPieceByEnd[i] != NONE;

            if (!hasIncomingPart && (hasPurePart || hasOutgoingPart)) {
                ++componentCount;

                String builtWord = buildWord(i, pieces, nextPieceByStart, pureLetterCounts, endLetterOfPiece, visitedLetters
                );

                if (builtWord == null) {
                    isValid = false;
                    break;
                }

                if (componentCount == 1) {
                    answer = builtWord;
                }
            }
        }

        if (isValid) {
            for (int i = 0; i < ALPHABET_COUNT; ++i) {
                boolean exists = pureLetterCounts[i] > 0 || nextPieceByStart[i] != NONE || prevPieceByEnd[i] != NONE || isMiddleLetterUsed[i];

                if (exists && !visitedLetters[i] && !isMiddleLetterUsed[i]) {
                    isValid = false;
                    break;
                }
            }
        }

        if (!isValid) {
            System.out.println("gg");
        } else if (componentCount == 1) {
            System.out.println(answer);
        } else {
            System.out.println("-_-");
        }

        scanner.close();
    }

    private static String compress(String piece) {
        StringBuilder builder = new StringBuilder();
        builder.append(piece.charAt(0));

        for (int i = 1; i < piece.length(); ++i) {
            if (piece.charAt(i) != piece.charAt(i - 1)) {
                builder.append(piece.charAt(i));
            }
        }

        return builder.toString();
    }

    private static boolean isCompressedGroupWord(String compressed) {
        boolean[] used = new boolean[ALPHABET_COUNT];

        for (int i = 0; i < compressed.length(); ++i) {
            int letterIndex = compressed.charAt(i) - 'a';

            if (used[letterIndex]) {
                return false;
            }

            used[letterIndex] = true;
        }

        return true;
    }

    private static String buildWord(int startLetter, String[] pieces, int[] nextPieceByStart, int[] pureLetterCounts, int[] endLetterOfPiece, boolean[] visitedLetters) {
        StringBuilder builder = new StringBuilder();
        int currentLetter = startLetter;

        while (true) {
            if (visitedLetters[currentLetter]) {
                return null;
            }

            visitedLetters[currentLetter] = true;

            for (int i = 0; i < pureLetterCounts[currentLetter]; ++i) {
                builder.append((char) ('a' + currentLetter));
            }

            int pieceIndex = nextPieceByStart[currentLetter];
            if (pieceIndex == NONE) {
                break;
            }

            builder.append(pieces[pieceIndex]);
            currentLetter = endLetterOfPiece[pieceIndex];
        }

        return builder.toString();
    }
}