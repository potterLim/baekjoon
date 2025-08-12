#include <stdio.h>

static int is_prime_ul(unsigned long n)
{
    unsigned long i;

    if (n < 2) {
        return 0;
    }
    if (n == 2 || n == 3) {
        return 1;
    }
    if ((n % 2) == 0 || (n % 3) == 0) {
        return 0;
    }

    for (i = 5; i <= n / i; i += 6) {
        if ((n % i) == 0 || (n % (i + 2)) == 0) {
            return 0;
        }
    }
    return 1;
}

static unsigned long next_prime_ul(unsigned long n)
{
    if (n <= 2) {
        return 2UL;
    }
    if ((n % 2UL) == 0UL) {
        n += 1UL;
    }

    while (!is_prime_ul(n)) {
        n += 2UL;
    }
    return n;
}

int main(void)
{
    int t;
    int i;
    unsigned long n;

    scanf("%d", &t);
    for (i = 0; i < t; ++i) {
        scanf("%lu", &n);
        printf("%lu\n", next_prime_ul(n));
    }
    return 0;
}
