#include <stdio.h>

int main(void)
{
    long n;
    long result;

    scanf("%ld", &n);

    result = n + 2 * n + n;

    printf("%ld", result);
    return 0;
}
