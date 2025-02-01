#include <stdio.h>

int main(void)
{
    int min_of_odd;
    int sum_of_odd;
    int num;
    size_t i;

    min_of_odd = 100;
    sum_of_odd = 0;

    for (i = 0; i < 7; ++i) {
        scanf("%d", &num);
        if (num % 2 != 0) {
            if (num < min_of_odd) {
                min_of_odd = num;
            }
            sum_of_odd += num;
        }
    }

    if (sum_of_odd == 0) {
        printf("-1\n");
    } else {
        printf("%d\n", sum_of_odd);
        printf("%d\n", min_of_odd);
    }
}
