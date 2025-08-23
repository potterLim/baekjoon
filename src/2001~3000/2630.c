#include <stdio.h>

#define MAX_N (128)

int board[MAX_N][MAX_N];
int white_count;
int blue_count;

void count_papers_recursive(int x, int y, int size)
{
    int i;
    int j;
    int first_color;
    int all_same;

    first_color = board[x][y];
    all_same = 1;

    for (i = x; i < x + size; ++i) {
        for (j = y; j < y + size; ++j) {
            if (board[i][j] != first_color) {
                all_same = 0;
                break;
            }
        }
        if (all_same == 0) {
            break;
        }
    }

    if (all_same != 0) {
        if (first_color == 0) {
            ++white_count;
        } else {
            ++blue_count;
        }
        return;
    }

    size /= 2;
    count_papers_recursive(x, y, size);
    count_papers_recursive(x, y + size, size);
    count_papers_recursive(x + size, y, size);
    count_papers_recursive(x + size, y + size, size);
}

int main(void)
{
    int n;
    int i;
    int j;

    scanf("%d", &n);
    for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
            scanf("%d", &board[i][j]);
        }
    }

    white_count = 0;
    blue_count = 0;

    count_papers_recursive(0, 0, n);

    printf("%d\n", white_count);
    printf("%d\n", blue_count);

    return 0;
}
