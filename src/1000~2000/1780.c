#include <stdio.h>

#define MAX_N (2187)

static int s_board[MAX_N][MAX_N];
static int s_count_neg1;
static int s_count_zero;
static int s_count_pos1;

static void count_squares(int x, int y, int size)
{
    int i;
    int j;
    int first;
    int all_same;

    first = s_board[x][y];
    all_same = 1;

    for (i = x; i < x + size; ++i) {
        for (j = y; j < y + size; ++j) {
            if (s_board[i][j] != first) {
                all_same = 0;
                break;
            }
        }
        if (all_same == 0) {
            break;
        }
    }

    if (all_same != 0) {
        if (first == -1) {
            ++s_count_neg1;
        } else if (first == 0) {
            ++s_count_zero;
        } else {
            ++s_count_pos1;
        }
        return;
    }

    size /= 3;

    count_squares(x, y, size);
    count_squares(x, y + size, size);
    count_squares(x, y + 2 * size, size);

    count_squares(x + size, y, size);
    count_squares(x + size, y + size, size);
    count_squares(x + size, y + 2 * size, size);

    count_squares(x + 2 * size, y, size);
    count_squares(x + 2 * size, y + size, size);
    count_squares(x + 2 * size, y + 2 * size, size);
}

int main(void)
{
    int n;
    int i;
    int j;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
            scanf("%d", &s_board[i][j]);
        }
    }

    s_count_neg1 = 0;
    s_count_zero = 0;
    s_count_pos1 = 0;

    count_squares(0, 0, n);

    printf("%d\n", s_count_neg1);
    printf("%d\n", s_count_zero);
    printf("%d\n", s_count_pos1);

    return 0;
}
