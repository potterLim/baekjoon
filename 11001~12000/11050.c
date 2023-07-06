#include <stdio.h>

int factorial(int num);

int factorial(int num)
{
    int i;
    int result = 1;

    for (i = 1; i <= num; ++i) {
        result *= i;
    }

    return result;
}

int main(void)
{
    int N;
    int R;

    scanf("%d %d", &N, &R);

    printf("%d\n", factorial(N) / factorial(R) / factorial(N - R));
    return 0;
}
