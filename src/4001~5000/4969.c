#include <stdio.h>

#define MAX_N 300000
#define MAX_PRIMES 100000

int monday_to_saturday_primes[MAX_PRIMES];
int num_monday_to_saturday_primes = 0;

int is_monday_to_saturday(int n)
{
    return (n % 7 == 1 || n % 7 == 6);
}

int is_monday_to_saturday_prime(int n)
{
    int i;
    if (n <= 1) {
        return 0;
    }

    for (i = 0; i < num_monday_to_saturday_primes; ++i) {
        int p = monday_to_saturday_primes[i];
        if (p >= n) {
            break;
        }
        if (n % p == 0) {
            return 0;
        }
    }

    return 1;
}

void init_monday_to_saturday_primes()
{
    int i;
    for (i = 2; i < MAX_N; ++i) {
        if (is_monday_to_saturday(i)) {
            if (is_monday_to_saturday_prime(i)) {
                monday_to_saturday_primes[num_monday_to_saturday_primes++] = i;
            }
        }
    }
}

int main(void)
{
    int n;
    int i;

    init_monday_to_saturday_primes();

    while (1) {
        scanf("%d", &n);
        if (n == 1) {
            break;
        }

        printf("%d:", n);
        for (i = 0; i < num_monday_to_saturday_primes; ++i) {
            int prime = monday_to_saturday_primes[i];
            if (prime > n / 2) {
                break;
            }
            if (n % prime == 0) {
                printf(" %d", prime);
            }
        }

        if (is_monday_to_saturday(n) && is_monday_to_saturday_prime(n)) {
            printf(" %d", n);
        }

        printf("\n");
    }

    return 0;
}
