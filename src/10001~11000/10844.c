#include <stdio.h>

#define MAX_N 100
#define DIGITS 10
#define MOD 1000000000

static int s_n;
static int s_dp[MAX_N + 1][DIGITS];

int main(void)
{
    int i;
    int d;
    int answer;

    scanf("%d", &s_n);

    for (d = 1; d <= 9; ++d) {
        s_dp[1][d] = 1;
    }
    s_dp[1][0] = 0;

    for (i = 2; i <= s_n; ++i) {
        s_dp[i][0] = s_dp[i - 1][1] % MOD;
        for (d = 1; d <= 8; ++d) {
            s_dp[i][d] = s_dp[i - 1][d - 1] + s_dp[i - 1][d + 1];
            if (s_dp[i][d] >= MOD) {
                s_dp[i][d] -= MOD;
            }
        }
        s_dp[i][9] = s_dp[i - 1][8] % MOD;
    }

    answer = 0;
    for (d = 0; d <= 9; ++d) {
        answer += s_dp[s_n][d];
        if (answer >= MOD) {
            answer -= MOD;
        }
    }

    printf("%d\n", answer);
    return 0;
}
