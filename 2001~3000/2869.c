#include <stdio.h>

int main(void)
{
    int A;
    int B;
    int V;
    int day;

    scanf("%d %d %d", &A, &B, &V);
    day = (V - B) / (A - B);

    if ((V - B) % (A - B) != 0) {
        day++;
    }

    printf("%d\n", day);
    return 0;
}
