import java.util.Scanner;

public class Main {
    private static final int MAX_SKILL = 100000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hiCount = scanner.nextInt();
        int arcCount = scanner.nextInt();

        int[] hiSkillCounts = new int[MAX_SKILL + 1];
        int[] arcSkillCounts = new int[MAX_SKILL + 1];

        for (int participantIndex = 0; participantIndex < hiCount; ++participantIndex) {
            int skill = scanner.nextInt();
            ++hiSkillCounts[skill];
        }

        for (int participantIndex = 0; participantIndex < arcCount; ++participantIndex) {
            int skill = scanner.nextInt();
            ++arcSkillCounts[skill];
        }

        long[] arcPrefixCounts = new long[MAX_SKILL + 1];

        for (int skill = 1; skill <= MAX_SKILL; ++skill) {
            arcPrefixCounts[skill] = arcPrefixCounts[skill - 1] + arcSkillCounts[skill];
        }

        long hiWinCount = 0L;
        long arcWinCount = 0L;
        long drawCount = 0L;

        for (int skill = 1; skill <= MAX_SKILL; ++skill) {
            int sameSkillHiCount = hiSkillCounts[skill];

            if (sameSkillHiCount == 0) {
                continue;
            }

            long weakerArcCount = arcPrefixCounts[skill - 1];
            long sameSkillArcCount = arcSkillCounts[skill];
            long strongerArcCount = arcCount - arcPrefixCounts[skill];

            hiWinCount += (long) sameSkillHiCount * weakerArcCount;
            drawCount += (long) sameSkillHiCount * sameSkillArcCount;
            arcWinCount += (long) sameSkillHiCount * strongerArcCount;
        }

        System.out.println(hiWinCount + " " + arcWinCount + " " + drawCount);
    }
}