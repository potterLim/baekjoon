import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static final class Participant {
        private final String mName;
        private final int mScore;
        private final boolean mIsHidden;
        private int mRank;

        public Participant(String name, int score, boolean isHidden) {
            mName = name;
            mScore = score;
            mIsHidden = isHidden;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int participantCount = Integer.parseInt(scanner.nextLine());

        Participant[] participants = new Participant[participantCount];

        for (int i = 0; i < participantCount; ++i) {
            String line = scanner.nextLine();
            participants[i] = parseParticipant(line);
        }

        Participant[] sortedParticipants = participants.clone();
        Arrays.sort(sortedParticipants, new Comparator<Participant>() {
            @Override
            public int compare(Participant left, Participant right) {
                return Integer.compare(right.mScore, left.mScore);
            }
        });

        int currentRank = 1;
        sortedParticipants[0].mRank = currentRank;

        for (int i = 1; i < participantCount; ++i) {
            if (sortedParticipants[i].mScore != sortedParticipants[i - 1].mScore) {
                currentRank = i + 1;
            }

            sortedParticipants[i].mRank = currentRank;
        }

        ArrayList<Participant> visibleParticipants = new ArrayList<>();

        for (int i = 0; i < participantCount; ++i) {
            if (!participants[i].mIsHidden) {
                visibleParticipants.add(participants[i]);
            }
        }

        visibleParticipants.sort(new Comparator<Participant>() {
            @Override
            public int compare(Participant left, Participant right) {
                if (left.mRank != right.mRank) {
                    return Integer.compare(left.mRank, right.mRank);
                }

                return left.mName.compareTo(right.mName);
            }
        });

        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < visibleParticipants.size(); ++i) {
            Participant participant = visibleParticipants.get(i);

            resultBuilder.append(participant.mRank)
                    .append(' ')
                    .append(participant.mName)
                    .append(' ')
                    .append(participant.mScore);

            if (i + 1 < visibleParticipants.size()) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }

    private static Participant parseParticipant(String line) {
        if (line.charAt(0) == '[') {
            line = line.substring(1);
        }

        if (line.charAt(line.length() - 1) == ',') {
            line = line.substring(0, line.length() - 1);
        }

        if (line.charAt(line.length() - 1) == ']') {
            line = line.substring(0, line.length() - 1);
        }

        int nameStartIndex = line.indexOf("\"name\":\"") + 8;
        int nameEndIndex = line.indexOf("\",\"score\":");
        String name = line.substring(nameStartIndex, nameEndIndex);

        int scoreStartIndex = nameEndIndex + 10;
        int scoreEndIndex = line.indexOf(",\"isHidden\":");
        int score = Integer.parseInt(line.substring(scoreStartIndex, scoreEndIndex));

        int isHiddenIndex = line.charAt(scoreEndIndex + 12) - '0';
        boolean isHidden = isHiddenIndex == 1;

        return new Participant(name, score, isHidden);
    }
}