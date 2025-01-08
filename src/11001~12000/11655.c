#include <stdio.h>
#include <string.h>

int main(void)
{
    char input[101];
    char input_convert_ROT13[101];
    size_t i;

    fgets(input, sizeof(input), stdin);
    input[strcspn(input, "\n")] = '\0';

    for (i = 0; input[i] != '\0'; ++i) {
        if (input[i] >= 'a' && input[i] <= 'z') {
            if (input[i] + 13 <= 'z') {
                input_convert_ROT13[i] = input[i] + 13;
            } else {
                input_convert_ROT13[i] = input[i] + 13 - 26;
            }
        } else if (input[i] >= 'A' && input[i] <= 'Z') {
            if (input[i] + 13 <= 'Z') {
                input_convert_ROT13[i] = input[i] + 13;
            } else {
                input_convert_ROT13[i] = input[i] + 13 - 26;
            }
        } else {
            input_convert_ROT13[i] = input[i];
        }
    }

    input_convert_ROT13[i] = '\0';

    printf("%s\n", input_convert_ROT13);
    return 0;
}
