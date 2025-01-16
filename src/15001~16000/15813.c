#include <stdio.h>

int main(void)
{
    char str[101];
    size_t i;
    size_t length;
    int score = 0;

    scanf("%d", &length);
    scanf("%s", str);

    for (i = 0; i < length; ++i) {
        score += (str[i] - 'A' + 1);
    }

    printf("%d\n", score);
    return 0;
}
