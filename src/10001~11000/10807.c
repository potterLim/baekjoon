#include <stdio.h>
#define SIZE (201)

int main(void)
{
    int arr[SIZE] = {0, };
    int num;
    int N;
    int i;

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%d", &num);
        arr[num+100]++;
    }

    scanf("%d", &num);
    printf("%d\n", arr[num+100]);

    return 0;
}
