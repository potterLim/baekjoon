#include <stdio.h>

int main(void)
{
    int N;
    int A;
    int B;
    int i;

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%d,%d", &A, &B);
        printf("%d\n", A + B);
    }

    return 0;
}
