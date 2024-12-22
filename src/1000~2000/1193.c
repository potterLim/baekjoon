#include <stdio.h>

void find_fraction(int x, int *numerator, int *denominator) 
{
    int diagonal;
    int sum;
    int pos;

    diagonal = 1;
    sum = 0;

    while (sum + diagonal < x) {
        sum += diagonal;
        diagonal++;
    }

    pos = x - sum;

    if (diagonal % 2 == 0) {
        *numerator = pos;
        *denominator = diagonal - pos + 1;
    } else {
        *numerator = diagonal - pos + 1;
        *denominator = pos;
    }
}

int main(void) 
{
    int x;
    int numerator;
    int denominator;

    scanf("%d", &x);
    find_fraction(x, &numerator, &denominator);
    printf("%d/%d\n", numerator, denominator);

    return 0;
}
