#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int numbers_count;
    int target_sum;
    int* pa_values;
    int i;
    int left_index;
    int best_length;
    long current_sum;

    scanf("%d %d", &numbers_count, &target_sum);

    pa_values = (int*)malloc((size_t)numbers_count * sizeof(int));
    for (i = 0; i < numbers_count; ++i) {
        scanf("%d", &pa_values[i]);
    }

    left_index = 0;
    current_sum = 0;
    best_length = numbers_count + 1;

    for (i = 0; i < numbers_count; ++i) {
        current_sum += pa_values[i];
        while (current_sum >= (long)target_sum && left_index <= i) {
            int window_len;
            window_len = i - left_index + 1;
            if (window_len < best_length) {
                best_length = window_len;
            }
            current_sum -= pa_values[left_index];
            left_index += 1;
        }
    }

    if (best_length == numbers_count + 1) {
        printf("0\n");
    } else {
        printf("%d\n", best_length);
    }

    free(pa_values);
    return 0;
}
