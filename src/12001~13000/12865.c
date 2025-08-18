#include <stdio.h>

#define MAX_N 100
#define MAX_K 100000

static int s_n;
static int s_k;
static int s_w[MAX_N + 1];
static int s_v[MAX_N + 1];
static int s_dp[MAX_K + 1];

static int max_int(int a, int b)
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
    int w;
    int v;
    int c;

    scanf("%d %d", &s_n, &s_k);
    for (i = 1; i <= s_n; ++i) {
        scanf("%d %d", &w, &v);
        s_w[i] = w;
        s_v[i] = v;
    }

    for (i = 1; i <= s_n; ++i) {
        for (c = s_k; c >= s_w[i]; --c) {
            s_dp[c] = max_int(s_dp[c], s_dp[c - s_w[i]] + s_v[i]);
        }
    }

    printf("%d\n", s_dp[s_k]);
    return 0;
}
