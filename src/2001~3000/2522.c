#include <stdio.h>

int main(void)
{
    size_t i;
    size_t j;
    int n;

    scanf("%d", &n);

    for (i = 0; i < 2 * n - 1; ++i) {
        size_t star_count = (i < n) ? (i + 1) : (2 * n - 1 - i);
        size_t space_count = n - star_count;

        for (j = 0; j < space_count; ++j) {
            printf(" ");
        }

        for (j = 0; j < star_count; ++j) {
            printf("*");
        }

        printf("\n");
    }

    return 0;
}
