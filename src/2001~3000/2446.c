#include <stddef.h>
#include <stdio.h>

int main(void)
{
    int n;
    size_t i;
    size_t j;

    scanf("%d", &n);

    for (i = 0; i < 2 * n - 1; ++i)
    {
        for (j = 0; j < 2 * n - 1; ++j) {
            if (i < (2 * n - 1) / 2) {
                if (j < i) {
                    printf(" ");
                } else if (j < 2 * n - 1 - i) {
                    printf("*");
                }
            } else {
                if (j < 2 * n - 2 - i) {
                    printf(" ");
                } else if (j <= i){
                    printf("*");
                }
            }
        }
        printf("\n");
    }

    return 0;
}
