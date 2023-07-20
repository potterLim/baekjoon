#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void eratosthenes_sieve(int max, int *primes);
int is_prime_number(int num, int *primes);

int main(void)
{
    int min;
    int max;
    int i;
    int j;
    int count_divisor;
    int count_under_prime;
    int* primes;

    scanf("%d %d", &min, &max);

    primes = (int*)malloc(sizeof(int) * (max + 1));
    eratosthenes_sieve(max, primes);

    count_under_prime = 0;
    for (i = min; i <= max; ++i) {
        if(is_prime_number(i, primes)) {
            count_divisor = 1;
        } else {
            count_divisor = 0;
            int cur_num = i;
            j = 2;

            while (j <= cur_num) {
                if (cur_num % j == 0 && primes[j] == 1) {
                    cur_num /= j;
                    count_divisor++;
                } else {
                    j++;
                }
            }
        }

        if (primes[count_divisor] == 1) {
            count_under_prime++;
        }
    }

    printf("%d\n", count_under_prime);
    free(primes);
    return 0;
}

void eratosthenes_sieve(int max, int *primes)
{
    int i;
    int j;
    for(i=0; i<=max; i++) {
        primes[i] = 1;
    }
    primes[0] = primes[1] = 0;

    int sqrt_max = sqrt(max);
    for (i = 2; i <= sqrt_max; ++i) {
        if (primes[i] == 1) {
            for (j = i*i; j <= max; j += i) {
                primes[j] = 0;
            }
        }
    }
}

int is_prime_number(int num, int *primes)
{
    return primes[num];
}
