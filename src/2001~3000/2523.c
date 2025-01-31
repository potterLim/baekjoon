#include <stdio.h>

int main(void)
{
    size_t i;
    size_t j;
    int n;

    scanf("%d", &n);

    for (i = 0; i < 2 * n - 1; ++i) {
        size_t star_count = (i < n) ? (i + 1) : (2 * n - 1 - i);

        for (j = 0; j < star_count; ++j) {
            printf("*");
        }

        printf("\n");
    }

    return 0;
}
