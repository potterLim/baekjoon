#include <stdio.h>

int main(void) 
{
    int n;
    int m;
    int baskets[100];
    int temp;
    int a;
    int b;
    int i;
    int start;
    int end;

    scanf("%d %d", &n, &m);

    for (i = 0; i < n; i++) {
        baskets[i] = i + 1;
    }

    for (i = 0; i < m; i++) {
        scanf("%d %d", &a, &b);

        start = a - 1;
        end = b - 1;

        while (start < end) {
            temp = baskets[start];
            baskets[start] = baskets[end];
            baskets[end] = temp;
            start++;
            end--;
        }
    }

    for (i = 0; i < n; i++) {
        printf("%d", baskets[i]);
        if (i < n - 1) {
            printf(" ");
        }
    }
    printf("\n");

    return 0;
}
