#include <stdio.h>

#define MAX_N 2187

static char s_canvas[MAX_N][MAX_N + 1];

static void draw_stars_recursive(int row, int col, int size)
{
    int third;
    int r;
    int c;

    if (size == 1) {
        s_canvas[row][col] = '*';
        return;
    }

    third = size / 3;

    draw_stars_recursive(row, col, third);
    draw_stars_recursive(row, col + third, third);
    draw_stars_recursive(row, col + 2 * third, third);

    draw_stars_recursive(row + third, col, third);
    for (r = row + third; r < row + 2 * third; ++r) {
        for (c = col + third; c < col + 2 * third; ++c) {
            s_canvas[r][c] = ' ';
        }
    }
    draw_stars_recursive(row + third, col + 2 * third, third);

    draw_stars_recursive(row + 2 * third, col, third);
    draw_stars_recursive(row + 2 * third, col + third, third);
    draw_stars_recursive(row + 2 * third, col + 2 * third, third);
}

int main(void)
{
    int n;
    int i;
    int j;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
            s_canvas[i][j] = '*';
        }
        s_canvas[i][n] = '\0';
    }

    draw_stars_recursive(0, 0, n);

    for (i = 0; i < n; ++i) {
        puts(s_canvas[i]);
    }

    return 0;
}
