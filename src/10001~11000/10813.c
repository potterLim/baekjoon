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

    scanf("%d %d", &n, &m);

    for (i = 0; i < n; i++) {
        baskets[i] = i + 1;
    }

    for (i = 0; i < m; i++) {
        scanf("%d %d", &a, &b);
        temp = baskets[a - 1];
        baskets[a - 1] = baskets[b - 1];
        baskets[b - 1] = temp;
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
