#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int tree_count;
    long long required_length;
    long long* heights;
    int i;
    long long max_height;
    long long low;
    long long high;
    long long best_height;

    scanf("%d %lld", &tree_count, &required_length);

    heights = (long long*)malloc((size_t)tree_count * sizeof(long long));

    max_height = 0;
    for (i = 0; i < tree_count; ++i) {
        scanf("%lld", &heights[i]);
        if (heights[i] > max_height) {
            max_height = heights[i];
        }
    }

    low = 0;
    high = max_height;
    best_height = 0;

    while (low <= high) {
        long long mid;
        long long collected;

        mid = low + (high - low) / 2;

        collected = 0;
        for (i = 0; i < tree_count; ++i) {
            if (heights[i] > mid) {
                collected += heights[i] - mid;
                if (collected >= required_length) {
                    break;
                }
            }
        }

        if (collected >= required_length) {
            best_height = mid;
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    printf("%lld\n", best_height);

    free(heights);
    return 0;
}
