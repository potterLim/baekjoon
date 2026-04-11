import java.util.Scanner;

public class Main {
    private enum EChantState {
        IDLE,
        A_EXPECT_NO,
        A_EXPECT_FIRST_HAI_DASH,
        A_EXPECT_SECOND_HAI_DASH,
        A_EXPECT_FIRST_HAI,
        A_EXPECT_SECOND_HAI,
        A_EXPECT_THIRD_HAI,
        A_EXPECT_REPEAT_U,
        A_EXPECT_REPEAT_HAI,
        A_AFTER_REPEAT_HAI,
        B_EXPECT_KAWAII,
        C_EXPECT_YOU,
        C_EXPECT_READY,
        C_EXPECT_ANTENA,
        C_EXPECT_SENKU,
        C_EXPECT_HIGH,
        D_EXPECT_HAI
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int leadCount = 0;
        int finishedSetCount = 0;
        EChantState chantState = EChantState.IDLE;

        while (scanner.hasNext()) {
            String currentWord = scanner.next();
            boolean shouldReprocessCurrentWord = true;

            while (shouldReprocessCurrentWord) {
                shouldReprocessCurrentWord = false;

                switch (chantState) {
                    case IDLE:
                        if (currentWord.equals("se")) {
                            ++leadCount;
                            chantState = EChantState.A_EXPECT_NO;
                        } else if (currentWord.equals("mik-jjang")) {
                            ++leadCount;
                            chantState = EChantState.B_EXPECT_KAWAII;
                        } else if (currentWord.equals("Are")) {
                            ++leadCount;
                            chantState = EChantState.C_EXPECT_YOU;
                        } else if (currentWord.equals("u-")) {
                            chantState = EChantState.D_EXPECT_HAI;
                        } else if (currentWord.equals("hai")) {
                            ++finishedSetCount;
                        }
                        break;

                    case A_EXPECT_NO:
                        if (currentWord.equals("no")) {
                            chantState = EChantState.A_EXPECT_FIRST_HAI_DASH;
                        }
                        break;

                    case A_EXPECT_FIRST_HAI_DASH:
                        if (currentWord.equals("hai-")) {
                            chantState = EChantState.A_EXPECT_SECOND_HAI_DASH;
                        }
                        break;

                    case A_EXPECT_SECOND_HAI_DASH:
                        if (currentWord.equals("hai-")) {
                            chantState = EChantState.A_EXPECT_FIRST_HAI;
                        }
                        break;

                    case A_EXPECT_FIRST_HAI:
                        if (currentWord.equals("hai")) {
                            chantState = EChantState.A_EXPECT_SECOND_HAI;
                        }
                        break;

                    case A_EXPECT_SECOND_HAI:
                        if (currentWord.equals("hai")) {
                            chantState = EChantState.A_EXPECT_THIRD_HAI;
                        }
                        break;

                    case A_EXPECT_THIRD_HAI:
                        if (currentWord.equals("hai")) {
                            chantState = EChantState.A_EXPECT_REPEAT_U;
                        }
                        break;

                    case A_EXPECT_REPEAT_U:
                        if (currentWord.equals("u-")) {
                            chantState = EChantState.A_EXPECT_REPEAT_HAI;
                        }
                        break;

                    case A_EXPECT_REPEAT_HAI:
                        if (currentWord.equals("hai")) {
                            chantState = EChantState.A_AFTER_REPEAT_HAI;
                        }
                        break;

                    case A_AFTER_REPEAT_HAI:
                        if (currentWord.equals("u-")) {
                            chantState = EChantState.A_EXPECT_REPEAT_HAI;
                        } else if (currentWord.equals("se")
                                || currentWord.equals("mik-jjang")
                                || currentWord.equals("Are")
                                || currentWord.equals("hai")) {
                            ++finishedSetCount;
                            chantState = EChantState.IDLE;
                            shouldReprocessCurrentWord = true;
                        }
                        break;

                    case B_EXPECT_KAWAII:
                        if (currentWord.equals("kawaii!")) {
                            ++finishedSetCount;
                            chantState = EChantState.IDLE;
                        }
                        break;

                    case C_EXPECT_YOU:
                        if (currentWord.equals("you")) {
                            chantState = EChantState.C_EXPECT_READY;
                        }
                        break;

                    case C_EXPECT_READY:
                        if (currentWord.equals("ready")) {
                            chantState = EChantState.C_EXPECT_ANTENA;
                        }
                        break;

                    case C_EXPECT_ANTENA:
                        if (currentWord.equals("antena")) {
                            chantState = EChantState.C_EXPECT_SENKU;
                        }
                        break;

                    case C_EXPECT_SENKU:
                        if (currentWord.equals("senku")) {
                            chantState = EChantState.C_EXPECT_HIGH;
                        }
                        break;

                    case C_EXPECT_HIGH:
                        if (currentWord.equals("high!")) {
                            ++finishedSetCount;
                            chantState = EChantState.IDLE;
                        }
                        break;

                    case D_EXPECT_HAI:
                        if (currentWord.equals("hai")) {
                            ++finishedSetCount;
                            chantState = EChantState.IDLE;
                        }
                        break;

                    default:
                        assert (false) : "Unexpected chant state";
                }
            }
        }

        if (chantState == EChantState.A_AFTER_REPEAT_HAI) {
            ++finishedSetCount;
        }

        System.out.println(leadCount + " " + finishedSetCount);
    }
}