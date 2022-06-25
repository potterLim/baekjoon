#include <stdio.h>

int main(void)
{
    int datas[5] = {0, };
    int i;
    int j;
    int sum = 0;
    int avg = 0;
    int tmp;

    for (i = 0; i < 5; ++i) {
        scanf("%d", &datas[i]);
        sum += datas[i];
    }
    avg = sum / 5;

    for (i = 1; i < 5; i++) {
        tmp = datas[i];
        for (j = i - 1; j >= 0 && datas[j] < tmp; j--) {
            datas[j + 1] = datas[j];
        }
        datas[j + 1] = tmp;
    }

    printf("%d\n", avg);
    printf("%d\n", datas[2]);
    return 0;
}
