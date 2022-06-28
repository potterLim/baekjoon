#include <stdio.h>

int main(void)
{
    int scores[6];
    int i;
    int min_idx;
    int min_score = 101;
    int sum_score = 0;

    for (i = 0; i < 6; ++i) {
        scanf("%d", &scores[i]);
    }

    for (i = 0; i < 4; ++i) {
        if (scores[i] < min_score) {
            min_idx = i;
            min_score = scores[i];
        }
    }

    for (i = 0; i < 4; ++i) {
        if (i == min_idx) {
            continue;
        }

        sum_score += scores[i];
    }

    printf("min_idx: %d\n", min_idx);
    printf("sum_score: %d\n", sum_score);

    sum_score += (scores[4] > scores[5]) ? scores[4] : scores[5];

    printf("%d\n", sum_score);
    return 0;
}
