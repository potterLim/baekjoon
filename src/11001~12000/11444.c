#include <stdio.h>

#define MOD (1000000007LL)

static void get_fibonacci_pair(unsigned long long n, long long* fib_n, long long* fib_n_plus1)
{
    long long fib_half;
    long long fib_half_plus1;
    long long fib_n_value;
    long long fib_n_plus1_value;
    long long two_b_minus_a;

    if (n == 0ULL) {
        *fib_n = 0;
        *fib_n_plus1 = 1;
        return;
    }

    get_fibonacci_pair(n >> 1, &fib_half, &fib_half_plus1);

    two_b_minus_a = ((2 * fib_half_plus1) % MOD - fib_half + MOD) % MOD;
    fib_n_value = (fib_half * two_b_minus_a) % MOD;
    fib_n_plus1_value = ((fib_half * fib_half) % MOD + (fib_half_plus1 * fib_half_plus1) % MOD) % MOD;

    if ((n & 1ULL) == 0ULL) {
        *fib_n = fib_n_value;
        *fib_n_plus1 = fib_n_plus1_value;
    } else {
        *fib_n = fib_n_plus1_value;
        *fib_n_plus1 = (fib_n_value + fib_n_plus1_value) % MOD;
    }
}

int main(void)
{
    unsigned long long n;
    long long fib_n;
    long long fib_n_plus1;

    scanf("%llu", &n);

    get_fibonacci_pair(n, &fib_n, &fib_n_plus1);

    printf("%lld\n", fib_n % MOD);

    return 0;
}
