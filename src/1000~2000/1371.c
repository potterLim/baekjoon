#include <stddef.h>
#include <stdio.h>
#include <string.h>

int main(void)
{
    char input[51];
    int count_alphabet[26] = {0, };
    int count_max = 0;
    size_t i;

    while (fgets(input, sizeof(input), stdin)) {
        input[strcspn(input, "\n")] = '\0';

        for (i = 0; input[i] != '\0'; ++i) {
            if (input[i] != ' ') {
                count_alphabet[input[i] - 'a']++;
            }
        }
    }

    for (i = 0; i < 26; ++i) {
        if (count_alphabet[i] > count_max) {
            count_max = count_alphabet[i];
        }
    }

    for (i = 0; i < 26; ++i) {
        if (count_alphabet[i] == count_max) {
            printf("%c", (char)(i + 'a'));
        }
    }

    return 0;
}
