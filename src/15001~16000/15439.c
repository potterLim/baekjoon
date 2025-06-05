#include <stdio.h>

int main(void)
{
    int n;
    int result;

    scanf("%d", &n);
    
    result = n * (n - 1);
    printf("%d\n", result);

    return 0;
}
