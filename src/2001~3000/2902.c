#include <stdio.h>
#include <string.h>

int main(void)
{
    char input[101];
    size_t length;
    size_t i;

    scanf("%s", input);
    length = strlen(input);

    for (i = 0; i < length; ++i) {
        if (i == 0) {
            printf("%c", input[i]);
        }

        if (input[i] == '-') {
            printf("%c", input[i + 1]);
        }
    }

    return 0;
}
