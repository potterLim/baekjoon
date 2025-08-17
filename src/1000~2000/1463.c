#include <stdio.h>

#define MAX_N 1000000

static int s_n;
static int s_dp[MAX_N + 1];

static int min_int(int a, int b)
{
    if (a < b) {
        return a;
    } else {
        return b;
    }
}

int main(void)
{
    int i;
    int best;

    scanf("%d", &s_n);

    s_dp[1] = 0;
    for (i = 2; i <= s_n; ++i) {
        best = s_dp[i - 1] + 1;
        if (i % 2 == 0) {
            best = min_int(best, s_dp[i / 2] + 1);
        }
        if (i % 3 == 0) {
            best = min_int(best, s_dp[i / 3] + 1);
        }
        s_dp[i] = best;
    }

    printf("%d\n", s_dp[s_n]);
    return 0;
}
