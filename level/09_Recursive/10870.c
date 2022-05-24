#include <stdio.h>

int main(void)
{
    int n;
    int result;

    scanf("%d", &n);
    result = fibonacci_recursive(n);

    printf("%d\n", result);
    return 0;

}

int fibonacci_recursive(int n)
{
    if (n == 0) {
        return 0;
    }

    if (n == 1) {
        return 1;
    }

    return fibonacci_recursive(n-1) + fibonacci_recursive(n-2);
}
