#include <stdio.h>
#include <string.h>

int main(void)
{
    char input[102];
    int count_small_letters;
    int count_capital_letters;
    int count_numbers;
    int count_spaces;

    size_t i;

    while (fgets(input, sizeof(input), stdin)) {
        input[strcspn(input, "\n")] = '\0';

        count_small_letters = 0;
        count_capital_letters = 0;
        count_numbers = 0;
        count_spaces = 0;

        for (i = 0; input[i] != '\0'; ++i) {
            if (input[i] >= 97 && input[i] <= 122) {
                count_small_letters++;
            } else if (input[i] >= 65 && input[i] <= 90) {
                count_capital_letters++;
            } else if (input[i] >= 47 && input[i] <= 58) {
                count_numbers++;
            } else if (input[i] == 32) {
                count_spaces++;
            }
        }

        printf("%d %d %d %d\n", count_small_letters, count_capital_letters, count_numbers, count_spaces);
    }

    return 0;
}
