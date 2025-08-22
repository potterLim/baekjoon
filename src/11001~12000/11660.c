#include <stdio.h>
#include <stdlib.h>

#define PREFIX_SUM(i, j) prefix[(i) * (size_t) (n + 1) + (j)]

int main(void)
{
    int n;
    int m;
    int i;
    int j;
    int x1;
    int y1;
    int x2;
    int y2;
    int* grid;
    int* prefix;
    int result;

    if (scanf("%d %d", &n, &m) != 2) {
        return 1;
    }

    grid = (int *) malloc((size_t) n * (size_t) n * sizeof(int));
    prefix = (int *) calloc((size_t) (n + 1) * (size_t) (n + 1), sizeof(int));

    if (grid == NULL || prefix == NULL) {
        free(grid);
        free(prefix);
        return 1;
    }

    for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
            scanf("%d", &grid[i * n + j]);
        }
    }

    for (i = 1; i <= n; ++i) {
        for (j = 1; j <= n; ++j) {
            PREFIX_SUM(i, j) = PREFIX_SUM(i - 1, j) + PREFIX_SUM(i, j - 1) - PREFIX_SUM(i - 1, j - 1) + grid[(i - 1) * n + (j - 1)];
        }
    }

    for (i = 0; i < m; ++i) {
        scanf("%d %d %d %d", &x1, &y1, &x2, &y2);

        result = PREFIX_SUM(x2, y2) - PREFIX_SUM(x1 - 1, y2) - PREFIX_SUM(x2, y1 - 1) + PREFIX_SUM(x1 - 1, y1 - 1);

        printf("%d\n", result);
    }

    free(grid);
    free(prefix);

    return 0;
}
