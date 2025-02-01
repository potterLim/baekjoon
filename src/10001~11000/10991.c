#include <stdio.h>

int main(void)
{
    int n;
    size_t i;
    size_t j;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        for (j = 0; j < n - i - 1; ++j) {
            printf(" ");
        }
        for (j = 0; j <= i; ++j) {
            if (j > 0) {
                printf(" ");
            }
            printf("*");
        }
        printf("\n");
    }
}
