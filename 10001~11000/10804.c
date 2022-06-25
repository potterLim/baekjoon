#include <stdio.h>

int main(void)
{
    int card[21] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    int start;
    int end;
    int i;
    int j;
    int k;
    int tmp;

    for (i = 0; i < 10; ++i) {
        k = 0;
        scanf("%d %d", &start, &end);
        for (j = start; j <= (start + end) / 2; ++j) {
            tmp = card[start + k];
            card[start + k] = card[end - k];
            card[end - k] = tmp;
            k++;
        }
    }
    for (i = 1; i < 21; ++i) {
        printf("%d ", card[i]);
    }

    return 0;
}
