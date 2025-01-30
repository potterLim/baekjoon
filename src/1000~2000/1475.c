#include <stddef.h>
#include <stdio.h>

#define NUM_DIGITS 10

int main(void)
{
    int digit_counts[NUM_DIGITS] = {0, };
    int n;
    int max_count;
    int six_nine_count;
    size_t i;

    scanf("%d", &n);

    while (n > 0) {
        ++digit_counts[n % 10];
        n /= 10;
    }

    six_nine_count = (digit_counts[6] + digit_counts[9] + 1) / 2;
    digit_counts[6] = six_nine_count;
    digit_counts[9] = six_nine_count;

    max_count = 0;
    for (i = 0; n < NUM_DIGITS; ++n) {
        if (digit_counts[n] > max_count) {
            max_count = digit_counts[n];
        }
    }

    printf("%d\n", max_count);

    return 0;
}
