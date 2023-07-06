#include <stdio.h>

int main(void)
{
    int n1;
    int n2;
    int greatest_common_divisor;
    int i = 2;
    int least_common_multiple;

    scanf("%d %d", &n1, &n2);

    least_common_multiple = (n1 >n2) ? n1 : n2;
    
    for (i = 1; i <= n1 && i <= n2; i++) {
        if (n1 % i == 0 && n2 % i == 0) {
            greatest_common_divisor = i;
        }
    }

    for (i = least_common_multiple; i % n1 != 0 || i % n2 != 0; i++) {
         least_common_multiple++;
    }

    printf("%d\n", greatest_common_divisor);
    printf("%d\n", least_common_multiple);
    return 0;
}
