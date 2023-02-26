#include <stdio.h>

int main(void)
{
    int len;
    int min;

    scanf("%d", &len);

    min = (len % 5 == 0) ? len / 5 : len / 5 + 1;

    printf("%d", min);
    return 0;
}
