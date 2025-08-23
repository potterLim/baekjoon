#include <stdio.h>

#define MAX_N (64)

static char image[MAX_N][MAX_N];

void compress_quad(int x, int y, int size)
{
    int i;
    int j;
    char first;
    int all_same;

    first = image[x][y];
    all_same = 1;

    for (i = x; i < x + size; ++i) {
        for (j = y; j < y + size; ++j) {
            if (image[i][j] != first) {
                all_same = 0;
                break;
            }
        }
        if (all_same == 0) {
            break;
        }
    }

    if (all_same != 0) {
        putchar(first);
        return;
    }

    size /= 2;

    putchar('(');
    compress_quad(x, y, size);
    compress_quad(x, y + size, size);
    compress_quad(x + size, y, size);
    compress_quad(x + size, y + size, size);
    putchar(')');
}

int main(void)
{
    int n;
    int i;
    int j;
    char line[MAX_N + 1];

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        scanf("%s", line);
        for (j = 0; j < n; ++j) {
            image[i][j] = line[j];
        }
    }

    compress_quad(0, 0, n);
    putchar('\n');

    return 0;
}
