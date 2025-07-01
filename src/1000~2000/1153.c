#include <math.h>
#include <stdio.h>

int is_prime(int number)
{
    int i;

    if (number < 2) {
        return 0;
    }

    for (i = 2; i * i <= number; ++i) {
        if (number % i == 0) {
            return 0;
        }
    }

    return 1;
}

int main(void)
{
    int n;
    int i;
    int remaining;

    scanf("%d", &n);

    if (n < 8) {
        printf("-1\n");
        return 0;
    }

    if (n % 2 == 0) {
        printf("2 2 ");
        remaining = n - 4;
    } else {
        printf("2 3 ");
        remaining = n - 5;
    }

    for (i = 2; i <= remaining / 2; ++i) {
        if (is_prime(i) && is_prime(remaining - i)) {
            printf("%d %d\n", i, remaining - i);
            return 0;
        }
    }

    printf("-1\n");
    return 0;
}
