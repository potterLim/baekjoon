#include <stdio.h>

int main(void)
{
    long long N;
    long long M;
    long long dif;

    scanf("%lld %lld", &N, &M);
    dif = (N - M) > 0 ? N - M : M - N;

    printf("%lld", dif);
    return 0;
}
