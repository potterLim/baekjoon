#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static unsigned long long get_gcd_ull(unsigned long long a, unsigned long long b)
{
    while (b != 0ULL) {
        unsigned long long r = a % b;
        a = b;
        b = r;
    }

    return a;
}

static int get_number_mod_k(const char* number_str, const int k)
{
    int mod_value;
    int i;
    int length;

    mod_value = 0;
    length = (int)strlen(number_str);

    for (i = 0; i < length; ++i) {
        int digit;
        digit = number_str[i] - '0';
        mod_value = (mod_value * 10 + digit) % k;
    }

    return mod_value;
}

static int get_pow10_mod_k(const int length, const int k)
{
    int value;
    int i;

    value = 1;
    for (i = 0; i < length; ++i) {
        value = (value * 10) % k;
    }

    return value;
}

int main(void)
{
    int n;
    int k;

    char numbers[15][51];
    int lengths[15];
    int number_mods[15];
    int pow10_mods[15];

    unsigned long long* dp;
    int full_mask;

    unsigned long long total_permutations;
    unsigned long long valid_count;

    int i;
    int mask;
    int remainder;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        scanf("%50s", numbers[i]);
        lengths[i] = (int)strlen(numbers[i]);
    }

    scanf("%d", &k);

    for (i = 0; i < n; ++i) {
        number_mods[i] = get_number_mod_k(numbers[i], k);
        pow10_mods[i] = get_pow10_mod_k(lengths[i], k);
    }

    full_mask = (1 << n) - 1;

    dp = (unsigned long long*)calloc((size_t)(1 << n) * (size_t)k, sizeof(unsigned long long));
    dp[0 * k + 0] = 1ULL;

    for (mask = 0; mask <= full_mask; ++mask) {
        for (remainder = 0; remainder < k; ++remainder) {
            unsigned long long ways;

            ways = dp[mask * k + remainder];
            if (ways == 0ULL) {
                continue;
            }

            for (i = 0; i < n; ++i) {
                int next_mask;
                int next_remainder;

                if ((mask & (1 << i)) != 0) {
                    continue;
                }

                next_mask = mask | (1 << i);
                next_remainder = (remainder * pow10_mods[i] + number_mods[i]) % k;

                dp[next_mask * k + next_remainder] += ways;
            }
        }
    }

    valid_count = dp[full_mask * k + 0];

    total_permutations = 1ULL;
    for (i = 2; i <= n; ++i) {
        total_permutations *= (unsigned long long)i;
    }

    if (valid_count == 0ULL) {
        printf("0/1\n");
    } else if (valid_count == total_permutations) {
        printf("1/1\n");
    } else {
        unsigned long long g;
        unsigned long long numerator;
        unsigned long long denominator;

        g = get_gcd_ull(valid_count, total_permutations);
        numerator = valid_count / g;
        denominator = total_permutations / g;

        printf("%llu/%llu\n", numerator, denominator);
    }

    free(dp);
    dp = NULL;

    return 0;
}
