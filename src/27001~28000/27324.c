#include <stdio.h>

int main(void)
{
    int num;

    scanf("%d", &num);

    if(num / 10 == num % 10) {
        printf("1");
    } else {
        printf("0");
    }

    return 0;
}
