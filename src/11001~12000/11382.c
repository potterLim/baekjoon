#include <stdio.h>

int main(void)
{
    long num1;
    long num2;
    long num3;
    long result;

    scanf("%ld %ld %ld", &num1, &num2, &num3);
    result = num1 + num2 + num3;

    printf("%ld\n", result);
    return 0;
}
