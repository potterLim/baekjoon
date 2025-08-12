#include <stdio.h>

#define MAX_N 1000000

static void build_sieve(int limit, char* is_comp)
{
    int i;
    int j;

    is_comp[0] = 1;
    is_comp[1] = 1;

    for (i = 2; i <= limit / i; ++i) {
        if (!is_comp[i]) {
            for (j = i * i; j <= limit; j += i) {
                is_comp[j] = 1;
            }
        }
    }
}

static int count_partitions(int n, const char* is_comp)
{
    int i;
    int half;
    int cnt;

    half = n / 2;
    cnt = 0;
    for (i = 2; i <= half; ++i) {
        if (!is_comp[i] && !is_comp[n - i]) {
            ++cnt;
        }
    }
    return cnt;
}

int main(void)
{
    static char is_comp[MAX_N + 1];
    int t;
    int i;
    int n;

    build_sieve(MAX_N, is_comp);

    scanf("%d", &t);
    for (i = 0; i < t; ++i) {
        scanf("%d", &n);
        printf("%d\n", count_partitions(n, is_comp));
    }
    return 0;
}
