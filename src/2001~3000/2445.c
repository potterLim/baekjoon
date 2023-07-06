#include <stdio.h>

int main(void)
{
    int N;
    int i;
    int j;

    scanf("%d", &N);

    for (i = 1; i <= N; ++i) {
        for (j = 1; j <= 2 * N; ++j) {
            if(i >= j || 2 * N - i < j) {
                printf("*");
            } else {
                printf(" ");
            }
        }
        printf("\n");
    }

    for (i = N -1; i >= 1; --i) {
        for (j = 1; j <= 2 * N; ++j) {
            if(i >= j || 2 * N - i < j) {
                printf("*");
            } else {
                printf(" ");
            }
        }
        printf("\n");
    } 
    
    return 0;
}
