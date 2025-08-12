#include <stdio.h>

#define MAX_N 123456
#define LIMIT (2 * MAX_N)

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

static int count_primes_between(int n, const char* is_comp)
{
    int i;
    int to;
    int cnt;

    to = n * 2;
    cnt = 0;
    for (i = n + 1; i <= to; ++i) {
        if (!is_comp[i]) {
            ++cnt;
        }
    }
    return cnt;
}

int main(void)
{
    static char is_comp[LIMIT + 1];
    int n;

    build_sieve(LIMIT, is_comp);

    while (1) {
        scanf("%d", &n);
        if (n == 0) {
            break;
        }
        printf("%d\n", count_primes_between(n, is_comp));
    }
    return 0;
}
