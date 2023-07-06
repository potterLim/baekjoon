#include <stdio.h>

int main(void)
{
    int N;
    int i;
    int opinion;
    int count_cute = 0;
    int count_notcute = 0;

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%d", &opinion);
        (opinion) ? count_cute++ : count_notcute++;
    }

    (count_cute > count_notcute) ? printf("Junhee is cute!\n") : printf("Junhee is not cute!\n");
    return 0;
}
