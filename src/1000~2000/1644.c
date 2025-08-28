#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int target_value;
    unsigned char* pa_is_composite;
    int* pa_primes;
    int prime_count;
    int i;
    int j;
    long current_sum;
    int left_index;
    int right_index;
    int ways;

    scanf("%d", &target_value);

    if (target_value < 2) {
        printf("0\n");
        return 0;
    }

    pa_is_composite = (unsigned char*)malloc((size_t)(target_value + 1) * sizeof(unsigned char));
    pa_primes = (int*)malloc((size_t)(target_value + 1) * sizeof(int));

    for (i = 0; i <= target_value; ++i) {
        pa_is_composite[i] = 0u;
    }

    pa_is_composite[0] = 1u;
    pa_is_composite[1] = 1u;

    for (i = 2; (long)i * (long)i <= (long)target_value; ++i) {
        if (!pa_is_composite[i]) {
            for (j = i * i; j <= target_value; j += i) {
                pa_is_composite[j] = 1u;
            }
        }
    }

    prime_count = 0;
    for (i = 2; i <= target_value; ++i) {
        if (!pa_is_composite[i]) {
            pa_primes[prime_count++] = i;
        }
    }

    ways = 0;
    left_index = 0;
    right_index = 0;
    current_sum = 0;

    while (1) {
        if (current_sum >= (long)target_value) {
            if (current_sum == (long)target_value) {
                ways += 1;
            }
            current_sum -= pa_primes[left_index];
            left_index += 1;
        } else {
            if (right_index == prime_count) {
                break;
            }
            current_sum += pa_primes[right_index];
            right_index += 1;
        }
    }

    printf("%d\n", ways);

    free(pa_is_composite);
    free(pa_primes);
    return 0;
}
