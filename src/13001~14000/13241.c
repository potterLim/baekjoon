#include <stdio.h>
#include <stdlib.h>

static long calculate_gcd_long(long x, long y)
{
    if (x < 0) {
        x = -x;
    }
    if (y < 0) {
        y = -y;
    }

    while (y != 0) {
        long t;
        t = x % y;
        x = y;
        y = t;
    }
    return x;
}

static void convert_to_decimal_digits(long value, int* out_digits, int* out_len)
{
    int len;

    if (value == 0) {
        out_digits[0] = 0;
        *out_len = 1;
        return;
    }

    if (value < 0) {
        value = -value;
    }

    len = 0;
    while (value > 0) {
        out_digits[len] = (int)(value % 10);
        value /= 10;
        ++len;
    }
    *out_len = len;
}

static void multiply_decimal_by_int(int* digits, int* inout_len, long multiplier)
{
    long carry;
    int i;

    if (multiplier < 0) {
        multiplier = -multiplier;
    }

    carry = 0;
    for (i = 0; i < *inout_len; ++i) {
        long tmp;
        tmp = (long)digits[i] * multiplier + carry;
        digits[i] = (int)(tmp % 10);
        carry = tmp / 10;
    }

    while (carry > 0) {
        digits[*inout_len] = (int)(carry % 10);
        carry /= 10;
        ++(*inout_len);
    }
}

static void print_decimal_digits(const int* digits, int len)
{
    int i;

    for (i = len - 1; i >= 0; --i) {
        printf("%d", digits[i]);
    }
    printf("\n");
}

int main(void)
{
    long a_value;
    long b_value;

    scanf("%ld %ld", &a_value, &b_value);

    if (a_value == 0 || b_value == 0) {
        printf("0\n");
        return 0;
    }

    {
        long gcd_value;
        long q_value;
        int digits[32];
        int len;

        gcd_value = calculate_gcd_long(a_value, b_value);
        q_value = a_value / gcd_value;

        convert_to_decimal_digits(b_value, digits, &len);
        multiply_decimal_by_int(digits, &len, q_value);
        print_decimal_digits(digits, len);
    }

    return 0;
}
