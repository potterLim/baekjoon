#include <stdio.h>

static unsigned long calculate_isqrt_ul(unsigned long n)
{
    unsigned long lo;
    unsigned long hi;
    unsigned long ans;

    lo = 0UL;
    hi = n;
    ans = 0UL;

    while (lo <= hi) {
        unsigned long mid;
        mid = lo + (hi - lo) / 2UL;

        if (mid != 0UL && mid > n / mid) {
            hi = mid - 1UL;
        } else {
            ans = mid;
            lo = mid + 1UL;
        }
    }
    return ans;
}

int main(void)
{
    unsigned long n;

    scanf("%lu", &n);
    printf("%lu\n", calculate_isqrt_ul(n));
    return 0;
}
