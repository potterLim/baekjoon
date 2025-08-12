#include <stdio.h>

static long calculate_gcd_long(long x, long y)
{
    if (x < 0) {
        x = -x;
    }
    if (y < 0) {
        y = -y;
    }

    while (y != 0) {
        long r;
        r = x % y;
        x = y;
        y = r;
    }
    return x;
}

int main(void)
{
    long num1;
    long den1;
    long num2;
    long den2;
    long sum_num;
    long sum_den;
    long gcd;

    scanf("%ld %ld", &num1, &den1);
    scanf("%ld %ld", &num2, &den2);

    sum_num = num1 * den2 + num2 * den1;
    sum_den = den1 * den2;

    gcd = calculate_gcd_long(sum_num, sum_den);

    sum_num /= gcd;
    sum_den /= gcd;

    printf("%ld %ld\n", sum_num, sum_den);

    return 0;
}
