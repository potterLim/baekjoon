#include <stdio.h>

int main(void)
{
    int i;
    int score;
    int sum = 0;

    for (i = 0; i < 5; i++) {
        scanf("%d", &score);
        sum += score;
    }

    printf("%d\n", sum);
}
