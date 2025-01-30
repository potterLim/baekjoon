#include <stdio.h>

void swap(int* num1, int* num2)
{
    int tmp;

    tmp = *num1;
    *num1 = *num2;
    *num2 = tmp;
}

int main(void)
{
    int num1;
    int num2;
    int num3;

    scanf("%d %d %d", &num1, &num2, &num3);

    if (num1 > num2) {
        swap(&num1, &num2);
    }
    if (num1 > num3) {
        swap(&num1, &num3);
    }
    if (num2 > num3) {
        swap(&num2, &num3);
    }

    printf("%d %d %d\n", num1, num2, num3);

    return 0;
}
