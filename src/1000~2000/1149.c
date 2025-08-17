#include <stdio.h>

#define MAX_N 1000

static int s_n;
static int s_cost[MAX_N][3];
static int s_dp[MAX_N][3];

static int min_int(int a, int b)
{
    if (a < b) {
        return a;
    } else {
        return b;
    }
}

static int min3_int(int a, int b, int c)
{
    int m;
    m = min_int(a, b);
    m = min_int(m, c);
    return m;
}

int main(void)
{
    int i;

    scanf("%d", &s_n);
    for (i = 0; i < s_n; ++i) {
        scanf("%d %d %d", &s_cost[i][0], &s_cost[i][1], &s_cost[i][2]);
    }

    s_dp[0][0] = s_cost[0][0];
    s_dp[0][1] = s_cost[0][1];
    s_dp[0][2] = s_cost[0][2];

    for (i = 1; i < s_n; ++i) {
        s_dp[i][0] = s_cost[i][0] + min_int(s_dp[i - 1][1], s_dp[i - 1][2]);
        s_dp[i][1] = s_cost[i][1] + min_int(s_dp[i - 1][0], s_dp[i - 1][2]);
        s_dp[i][2] = s_cost[i][2] + min_int(s_dp[i - 1][0], s_dp[i - 1][1]);
    }

    printf("%d\n", min3_int(s_dp[s_n - 1][0], s_dp[s_n - 1][1], s_dp[s_n - 1][2]));
    return 0;
}
