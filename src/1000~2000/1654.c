#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int owned_count;
    long long required_count;
    long long* cable_lengths;
    int i;
    long long highest_length;
    long long low;
    long long high;
    long long best_length;

    scanf("%d %lld", &owned_count, &required_count);

    cable_lengths = (long long*)malloc((size_t)owned_count * sizeof(long long));

    highest_length = 0;
    for (i = 0; i < owned_count; ++i) {
        scanf("%lld", &cable_lengths[i]);
        if (cable_lengths[i] > highest_length) {
            highest_length = cable_lengths[i];
        }
    }

    low = 1;
    high = highest_length;
    best_length = 0;

    while (low <= high) {
        long long candidate_length;
        long long pieces_produced;

        candidate_length = low + (high - low) / 2;

        pieces_produced = 0;
        for (i = 0; i < owned_count; ++i) {
            pieces_produced += cable_lengths[i] / candidate_length;
            if (pieces_produced >= required_count) {
                break;
            }
        }

        if (pieces_produced >= required_count) {
            best_length = candidate_length;
            low = candidate_length + 1;
        } else {
            high = candidate_length - 1;
        }
    }

    printf("%lld\n", best_length);

    free(cable_lengths);
    return 0;
}
