#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int* arr;
    int n;
    int i;
    int j;
    int tmp;
    scanf("%d", &n);

    arr = malloc(sizeof(int) * n);
    for (i = 0; i <n; ++i) {
        scanf("%d", &arr[i]);
    }

    for (i = 0; i< n - 1; i++) {
        for (j = i + 1; j < n; j++) {
            if (arr[i] > arr[j]) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
    }

    for (i = 0; i <n; ++i) {
        printf("%d\n", arr[i]);
    }
    free(arr);
    return 0;
}
