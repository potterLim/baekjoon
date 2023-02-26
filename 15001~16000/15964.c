#include <stdio.h>

int main() {
    long long num1;
    long long num2;

    scanf("%lld %lld", &num1, &num2);
    printf("%lld", (num1 + num2) * (num1 - num2));

    return 0;
}
