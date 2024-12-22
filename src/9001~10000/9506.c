#include <stdio.h>

void check_perfect_number(int n) 
{
    int sum;
    int i;
    int first;

    sum = 0;
    first = 1;

    for (i = 1; i <= n / 2; i++) {
        if (n % i == 0) {
            sum += i;
        }
    }

    if (sum == n) {
        printf("%d = ", n);
        for (i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                if (first) {
                    first = 0;
                } else {
                    printf(" + ");
                }
                printf("%d", i);
            }
        }
        printf("\n");
    } else {
        printf("%d is NOT perfect.\n", n);
    }
}

int main(void) 
{
    int n;

    while (1) {
        scanf("%d", &n);
        if (n == -1) {
            break;
        }
        check_perfect_number(n);
    }

    return 0;
}
