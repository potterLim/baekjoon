#include <stdio.h>

int main(void)
{
    int weight[3];
    int i;
    int j;
    int tmp;

    for (i = 0; i < 3; ++i) {
        scanf("%d", &weight[i]);
    }

    for (i = 0; i < 3; ++i) {
        for (j = 0; j < 2; ++j) {
            if (weight[j] > weight[j + 1]) {
                tmp = weight[j + 1];
                weight[j + 1] = weight[j];
                weight[j] = tmp;
            }
        }
    }

    printf("%d", weight[1]);
    return 0;
}
