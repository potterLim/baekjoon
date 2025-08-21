#include <stdio.h>

#define MAX_M 1000

int main(void)
{
    int element_count;
    int modulo_base;
    long long modulo_counts[MAX_M] = {0};
    long long current_sum;
    long long answer;
    int i;

    scanf("%d %d", &element_count, &modulo_base);

    modulo_counts[0] = 1;
    current_sum = 0;

    for (i = 0; i < element_count; ++i) {
        int value;
        int r;

        scanf("%d", &value);
        current_sum += (long long)value;
        r = (int)(current_sum % (long long)modulo_base);
        modulo_counts[r] += 1;
    }

    answer = 0;
    for (i = 0; i < modulo_base; ++i) {
        long long c = modulo_counts[i];
        if (c >= 2) {
            answer += c * (c - 1) / 2;
        }
    }

    printf("%lld\n", answer);
    return 0;
}
