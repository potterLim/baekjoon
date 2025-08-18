#include <stdio.h>

#define MAX_N 10000

static int s_n;
static long s_wine[MAX_N + 1];
static long s_dp[MAX_N + 1];

static long max_long(long a, long b)
{
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

int main(void)
{
    int i;

    scanf("%d", &s_n);
    for (i = 1; i <= s_n; ++i) {
        scanf("%ld", &s_wine[i]);
    }

    if (s_n == 1) {
        printf("%ld\n", s_wine[1]);
        return 0;
    }

    s_dp[1] = s_wine[1];
    s_dp[2] = s_wine[1] + s_wine[2];

    for (i = 3; i <= s_n; ++i) {
        long case1 = s_dp[i - 1];
        long case2 = s_dp[i - 2] + s_wine[i];
        long case3 = s_dp[i - 3] + s_wine[i - 1] + s_wine[i];
        s_dp[i] = max_long(case1, max_long(case2, case3));
    }

    printf("%ld\n", s_dp[s_n]);
    return 0;
}
