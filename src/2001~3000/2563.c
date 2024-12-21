#include <stdio.h>

int main(void) 
{
    int n;
    int paper[100][100] = {0};
    int x;
    int y;
    int i;
    int j;
    int k;
    int area;

    scanf("%d", &n);

    for (k = 0; k < n; k++) {
        scanf("%d %d", &x, &y);

        for (i = x; i < x + 10; i++) {
            for (j = y; j < y + 10; j++) {
                paper[i][j] = 1;
            }
        }
    }

    area = 0;

    for (i = 0; i < 100; i++) {
        for (j = 0; j < 100; j++) {
            if (paper[i][j] == 1) {
                area++;
            }
        }
    }

    printf("%d\n", area);

    return 0;
}
