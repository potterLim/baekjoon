#include <stdio.h>

int main() 
{
    int n;
    long num1;
    long num2;
 
    scanf("%d", &n);
 
    for (int i = 0; i < n; i++) {
        scanf("%ld %ld", &num1, &num2);
        printf("%ld\n", num1 + num2);
    }

    return 0;
}