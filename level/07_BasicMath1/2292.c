#include <stdio.h>

int main(void)
{
    int n;
    int k = 1;
    int count = 1;
    scanf("%d", &n);

    if (n == 1) {
        printf("%d\n", count);
    } else {
        while(1) {
            if (k + (count-1) * 6 >= n) {
                printf("%d\n", count);
                break;
            } else {
                k = k + (count-1) * 6;
                count++;
            }
        }
    }

    return 0;
}
