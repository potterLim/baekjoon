#include <stdio.h>

int main(void)
{
    int n;
    int result = 1;
    int i;

    scanf("%d", &n);

    for (i = 0; i < n; ++i)
    {
        result *= 2;
    }

    printf("%d\n", result);

    return 0;
}
