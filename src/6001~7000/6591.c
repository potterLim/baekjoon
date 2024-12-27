#include <stdio.h>

unsigned long calculate_combination(int n, int k) 
{
    unsigned long result;
    int i;

    result = 1;

    if (k > n - k) {
        k = n - k;
    }

    for (i = 1; i <= k; ++i) {
        result = result * (n - i + 1) / i;
    }

    return result;
}

int main(void) 
{
    int n;
    int k;

    while (1) {
        scanf("%d %d", &n, &k);

        if (n == 0 && k == 0) {
            break;
        }

        printf("%lu\n", calculate_combination(n, k));
    }

    return 0;
}
