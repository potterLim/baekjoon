#include <stdio.h>

int main(void)
{
    int N;
    int i;
    int time_call;
    int Y_pay = 0;
    int M_pay = 0;

    scanf("%d", &N);

    for (i = 0; i < N; ++i) {
        scanf("%d", &time_call);
        Y_pay += (time_call / 30 + 1) * 10;
        M_pay += (time_call / 60 + 1) * 15;
    }

    if (Y_pay > M_pay) {
        printf("M %d\n", M_pay);
    } else if (Y_pay < M_pay) {
        printf("Y %d\n", Y_pay);
    } else {
        printf("Y M %d\n", M_pay);
    }

    return 0;
}