import java.util.Scanner;

public class Main {
    private static final int LIMIT = 50000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            int memorySize = scanner.nextInt();
            int codeSize = scanner.nextInt();
            int inputSize = scanner.nextInt();

            char[] code = scanner.next().toCharArray();
            char[] input = scanner.next().toCharArray();

            int[] pairIndexByBracket = new int[codeSize];
            int[] stack = new int[codeSize];
            int stackSize = 0;

            for (int j = 0; j < codeSize; ++j) {
                if (code[j] == '[') {
                    stack[stackSize] = j;
                    ++stackSize;
                } else if (code[j] == ']') {
                    --stackSize;
                    int openIndex = stack[stackSize];
                    pairIndexByBracket[openIndex] = j;
                    pairIndexByBracket[j] = openIndex;
                }
            }

            int[] memory = new int[memorySize];
            int pointer = 0;
            int codeIndex = 0;
            int inputIndex = 0;
            int executedCount = 0;
            int loopRightIndex = 0;

            while (executedCount < LIMIT && codeIndex < codeSize) {
                char command = code[codeIndex];

                if (command == '-') {
                    memory[pointer] = (memory[pointer] + 255) & 255;
                    ++codeIndex;
                } else if (command == '+') {
                    memory[pointer] = (memory[pointer] + 1) & 255;
                    ++codeIndex;
                } else if (command == '<') {
                    --pointer;
                    if (pointer < 0) {
                        pointer = memorySize - 1;
                    }
                    ++codeIndex;
                } else if (command == '>') {
                    ++pointer;
                    if (pointer == memorySize) {
                        pointer = 0;
                    }
                    ++codeIndex;
                } else if (command == '[') {
                    if (memory[pointer] == 0) {
                        codeIndex = pairIndexByBracket[codeIndex] + 1;
                    } else {
                        ++codeIndex;
                    }
                } else if (command == ']') {
                    if (memory[pointer] != 0) {
                        codeIndex = pairIndexByBracket[codeIndex] + 1;
                    } else {
                        ++codeIndex;
                    }
                } else if (command == ',') {
                    if (inputIndex < inputSize) {
                        memory[pointer] = input[inputIndex];
                        ++inputIndex;
                    } else {
                        memory[pointer] = 255;
                    }
                    ++codeIndex;
                } else {
                    ++codeIndex;
                }

                ++executedCount;
            }

            if (codeIndex >= codeSize) {
                resultBuilder.append("Terminates");
            } else {
                while (executedCount < LIMIT * 2) {
                    if (codeIndex > loopRightIndex) {
                        loopRightIndex = codeIndex;
                    }

                    char command = code[codeIndex];

                    if (command == '-') {
                        memory[pointer] = (memory[pointer] + 255) & 255;
                        ++codeIndex;
                    } else if (command == '+') {
                        memory[pointer] = (memory[pointer] + 1) & 255;
                        ++codeIndex;
                    } else if (command == '<') {
                        --pointer;
                        if (pointer < 0) {
                            pointer = memorySize - 1;
                        }
                        ++codeIndex;
                    } else if (command == '>') {
                        ++pointer;
                        if (pointer == memorySize) {
                            pointer = 0;
                        }
                        ++codeIndex;
                    } else if (command == '[') {
                        if (memory[pointer] == 0) {
                            codeIndex = pairIndexByBracket[codeIndex] + 1;
                        } else {
                            ++codeIndex;
                        }
                    } else if (command == ']') {
                        if (memory[pointer] != 0) {
                            codeIndex = pairIndexByBracket[codeIndex] + 1;
                        } else {
                            ++codeIndex;
                        }
                    } else if (command == ',') {
                        if (inputIndex < inputSize) {
                            memory[pointer] = input[inputIndex];
                            ++inputIndex;
                        } else {
                            memory[pointer] = 255;
                        }
                        ++codeIndex;
                    } else {
                        ++codeIndex;
                    }

                    ++executedCount;
                }

                int loopLeftIndex = pairIndexByBracket[loopRightIndex];
                if (loopLeftIndex > loopRightIndex) {
                    int temp = loopLeftIndex;
                    loopLeftIndex = loopRightIndex;
                    loopRightIndex = temp;
                }

                resultBuilder.append("Loops ")
                        .append(loopLeftIndex)
                        .append(' ')
                        .append(loopRightIndex);
            }

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }
}