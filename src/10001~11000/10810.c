#include <stdio.h>

int main(void) 
{
    int n;
    int m;
    int x;
    int y;
    int value;
    int baskets[100] = {0};
    int i;
    int j;

    scanf("%d %d", &n, &m);

    for (i = 0; i < m; ++i) {
        scanf("%d %d %d", &x, &y, &value);
        for (j = x - 1; j < y; ++j) {
            baskets[j] = value;
        }
    }

    for (i = 0; i < n; ++i) {
        printf("%d ", baskets[i]);
    }

    return 0;
}
