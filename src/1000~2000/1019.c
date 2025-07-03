#include <stdio.h>

#define MAX_DIGIT (10)

int count[MAX_DIGIT];

void add_digit_counts(int n)
{
    int i;
    int pow10;
    int high;
    int low;
    int cur;

    pow10 = 1;

    while (n / pow10 != 0) {
        high = n / (pow10 * 10);
        cur = (n / pow10) % 10;
        low = n % pow10;

        for (i = 0; i < MAX_DIGIT; ++i) {
            if (i < cur) {
                count[i] += (high + 1) * pow10;
            } else if (i == cur) {
                count[i] += high * pow10 + (low + 1);
            } else {
                count[i] += high * pow10;
            }
        }

        count[0] -= pow10;

        pow10 *= 10;
    }
}

int main(void)
{
    int n;
    int i;

    scanf("%d", &n);

    for (i = 0; i < MAX_DIGIT; ++i) {
        count[i] = 0;
    }

    add_digit_counts(n);

    for (i = 0; i < MAX_DIGIT; ++i) {
        printf("%d", count[i]);
        if (i != MAX_DIGIT - 1) {
            printf(" ");
        }
    }

    printf("\n");

    return 0;
}
