#include <stdio.h>
#include <stdlib.h>

#define MAX_N 20

size_t factorial(size_t n) 
{
    size_t result;
    size_t i;

    result = 1;
    for (i = 1; i <= n; i++) {
        result *= i;
    }

    return result;
}

void find_kth_permutation(size_t n, size_t k, int *result) 
{
    size_t i;
    size_t fact;
    size_t index;
    int available[MAX_N];

    for (i = 0; i < n; i++) {
        available[i] = i + 1;
    }

    k--;
    for (i = 0; i < n; i++) {
        fact = factorial(n - 1 - i);
        index = k / fact;
        result[i] = available[index];

        for (; index < n - 1 - i; index++) {
            available[index] = available[index + 1];
        }

        k %= fact;
    }
}

size_t find_permutation_index(size_t n, const int *perm) 
{
    size_t i;
    size_t j;
    size_t fact;
    size_t index;
    size_t rank;
    int available[MAX_N];

    rank = 0;
    for (i = 0; i < n; i++) {
        available[i] = i + 1;
    }

    for (i = 0; i < n; i++) {
        fact = factorial(n - 1 - i);
        for (j = 0; j < n - i; j++) {
            if (available[j] == perm[i]) {
                break;
            }
        }

        rank += j * fact;
        for (; j < n - 1 - i; j++) {
            available[j] = available[j + 1];
        }
    }

    return rank + 1;
}

int main(void) 
{
    size_t n;
    size_t sub_problem;
    size_t k;
    size_t i;
    int permutation[MAX_N];

    scanf("%zu", &n);
    scanf("%zu", &sub_problem);

    if (sub_problem == 1) {
        scanf("%zu", &k);
        find_kth_permutation(n, k, permutation);
        for (i = 0; i < n; i++) {
            printf("%d%c", permutation[i], i == n - 1 ? '\n' : ' ');
        }
    } else if (sub_problem == 2) {
        for (i = 0; i < n; i++) {
            scanf("%d", &permutation[i]);
        }
        printf("%zu\n", find_permutation_index(n, permutation));
    }

    return 0;
}
