#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int total_days;
    int window_size;
    int* temperatures;
    int i;
    long current_sum;
    long max_sum;

    scanf("%d %d", &total_days, &window_size);

    temperatures = (int*)malloc(sizeof(int) * (size_t)total_days);

    for (i = 0; i < total_days; ++i) {
        scanf("%d", &temperatures[i]);
    }

    current_sum = 0;
    for (i = 0; i < window_size; ++i) {
        current_sum += temperatures[i];
    }
    max_sum = current_sum;

    for (i = window_size; i < total_days; ++i) {
        current_sum += temperatures[i];
        current_sum -= temperatures[i - window_size];
        if (current_sum > max_sum) {
            max_sum = current_sum;
        }
    }

    printf("%ld\n", max_sum);

    free(temperatures);
    return 0;
}
