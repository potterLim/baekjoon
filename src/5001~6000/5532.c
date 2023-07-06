#include <stdio.h>

int main(void)
{
    int L;
    int A;
    int B;
    int C;
    int D;
    int korean_day;
    int math_day;

    scanf("%d %d %d %d %d", &L, &A, &B, &C, &D);


    korean_day = (A /C);
    if (A % C != 0) {
        korean_day += 1;
    }

    math_day = (B / D);
    if (B % D != 0) {
        math_day += 1;
    }
 
    if (korean_day > math_day) {
        printf("%d", L - korean_day);
    } else {
        printf("%d", L - math_day);
    }

    return 0;
}
