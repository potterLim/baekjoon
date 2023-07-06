#include <stdio.h>

int main(void)
{
    int N;
    scanf("%d", &N);

    while (1) {
        if (N < 4) {
            printf("int\n");
            break;
        }
        N -= 4;
        printf("long ");
    }

    return 0;
}
