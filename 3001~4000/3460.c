#include <stdio.h>
 
int main(void) 
{
    int T;
    int n;
    int digit;
    int i;

    scanf("%d", &T);
    for (i = 0; i < T; ++i) {
        scanf("%d", &n);
        digit = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                printf("%d ", digit);
            }
            n /= 2;
            digit++;
        }
        printf("\n");
    }

    return 0;
}
