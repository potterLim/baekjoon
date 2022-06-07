#include <stdio.h>

#define SIZE (100001)

int main(void)
{
    int arr[SIZE] = {0, };
    int N;
    int num;
    int i;
    int j;

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%d", &num);
        arr[num]++;
    }

    for (i = 0; i < SIZE; ++i) {
        if(arr[i] != 0) {
            for (j = 0; j < arr[i]; ++j) {
                printf("%d\n", i);
            }
        }
    }
    
    return 0;
}
