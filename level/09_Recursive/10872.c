#include <stdio.h>

int main(void)
{
    int n;
    int result;

    scanf("%d", &n);
    result = factorial_recursive(n);

    printf("%d\n", result);
    return 0;

}

int factorial_recursive(int n)
{
    if (n == 1) {
        return 1;
    }
    
    if (n == 0) {
        return 1;
    }

    return n * factorial_recursive(n-1);
}
