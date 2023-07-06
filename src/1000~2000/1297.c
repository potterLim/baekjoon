#include <stdio.h>
#include <math.h>

int main(void)
{
    double D;
    double H;
    double W;
    double rate;

    scanf("%lf %lf %lf", &D, &H, &W);
    rate = D / sqrt(H * H + W * W);
    printf("%d %d\n", (int)(H * rate), (int)(W * rate));
    return 0;
}
