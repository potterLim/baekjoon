#include <stdio.h>

int main(void) 
{
    int n;
    int m;
    int coins[100][100];
    int flip_count;
    int i;
    int j;
    int a;
    int b;

    scanf("%d %d", &n, &m);

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%1d", &coins[i][j]);
        }
    }

    flip_count = 0;

    for (i = n - 1; i >= 0; i--) {
        for (j = m - 1; j >= 0; j--) {
            if (coins[i][j] == 1) {
                flip_count++;
                for (a = 0; a <= i; a++) {
                    for (b = 0; b <= j; b++) {
                        coins[a][b] = 1 - coins[a][b];
                    }
                }
            }
        }
    }

    printf("%d\n", flip_count);

    return 0;
}
