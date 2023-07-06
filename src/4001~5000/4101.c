#include <stdio.h>

int main(void)
{
    int num1;
    int num2;

    while (1) {
        scanf("%d %d", &num1, &num2);
        if (num1 == 0 && num2 == 0) {
            break;
        }

        (num1 > num2) ? printf("Yes\n") : printf("No\n");
    }

    return 0;
}
