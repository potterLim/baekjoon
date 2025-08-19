#include <stdio.h>

#define MAX_N 40

static int s_n;
static int s_dp[MAX_N + 1];

static int get_fibonacci(int n)
{
    if (n == 1 || n == 2) {
        return 1;
    }
    if (s_dp[n] != 0) {
        return s_dp[n];
    }
    s_dp[n] = get_fibonacci(n - 1) + get_fibonacci(n - 2);
    return s_dp[n];
}

int main(void)
{
    int code1_count;
    int code2_count;

    scanf("%d", &s_n);

    code1_count = get_fibonacci(s_n);
    code2_count = s_n - 2;

    printf("%d %d\n", code1_count, code2_count);

    return 0;
}
