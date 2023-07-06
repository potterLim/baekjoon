#include <stdio.h>

int main(void)
{
    int T;
    int arr[10];
    int i;
    int j;
    int max = -1;
    int second_max = -1;
    int third_max = -1;

    scanf("%d", &T);

    for (i = 0; i < T; ++i) {
        max = -1;
        second_max = -1;
        third_max = -1;
        for (j = 0; j < 10; ++j) {
            scanf("%d", &arr[j]);
        }

        for (j = 0; j < 10; ++j) {
            if (arr[j] > max) {
                max = arr[j];
            }
        }

        for (j = 0; j < 10; ++j) {
            if (arr[j] > second_max && arr[j] < max) {
                second_max = arr[j];
            }
        }

        for (j = 0; j < 10; ++j) {
            if (arr[j] > third_max && arr[j] < second_max) {
                third_max = arr[j];
            }
        }

        printf("%d\n", third_max);
    }
    return 0;
}