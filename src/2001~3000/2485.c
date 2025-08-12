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
    int n;
    long first_pos;
    long prev_pos;
    long curr_pos;
    long gap_gcd;
    long i;
    long total_add;

    scanf("%d", &n);
    scanf("%ld", &first_pos);
    prev_pos = first_pos;
    gap_gcd = 0;

    for (i = 1; i < n; ++i) {
        long diff;
        scanf("%ld", &curr_pos);
        diff = curr_pos - prev_pos;
        if (gap_gcd == 0) {
            gap_gcd = diff;
        } else {
            gap_gcd = calculate_gcd_long(gap_gcd, diff);
        }
        prev_pos = curr_pos;
    }

    total_add = ((prev_pos - first_pos) / gap_gcd + 1) - n;
    printf("%ld\n", total_add);
    return 0;
}
