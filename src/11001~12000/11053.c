#include <stdio.h>

#define MAX_N 1000

static int s_n;
static int s_a[MAX_N];
static int s_dp[MAX_N];

static int max_int(int x, int y)
{
    if (x > y) {
        return x;
    } else {
        return y;
    }
}

int main(void)
{
    int i;
    int j;
    int best_len;

    scanf("%d", &s_n);
    for (i = 0; i < s_n; ++i) {
        scanf("%d", &s_a[i]);
    }

    best_len = 0;
    for (i = 0; i < s_n; ++i) {
        s_dp[i] = 1;
        for (j = 0; j < i; ++j) {
            if (s_a[j] < s_a[i]) {
                s_dp[i] = max_int(s_dp[i], s_dp[j] + 1);
            }
        }
        best_len = max_int(best_len, s_dp[i]);
    }

    printf("%d\n", best_len);
    return 0;
}
