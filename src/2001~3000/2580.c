#include <stdio.h>

#define SIZE 9
#define FULL_MASK 0x1FF

static int s_grid[SIZE][SIZE];
static int s_row_mask[SIZE];
static int s_col_mask[SIZE];
static int s_box_mask[SIZE];

static int get_box_index(int r, int c)
{
    return (r / 3) * 3 + (c / 3);
}

static int digit_from_bit(int bit)
{
    int d;
    d = 1;
    while (bit > 1) {
        bit >>= 1;
        ++d;
    }
    return d;
}

static int count_bits_int(int x)
{
    int cnt;
    cnt = 0;
    while (x) {
        x &= (x - 1);
        ++cnt;
    }
    return cnt;
}

static int solve_sudoku_recursive(void)
{
    int i;
    int j;
    int best_r;
    int best_c;
    int best_mask;
    int min_options;
    int box_idx;
    int used_mask;
    int avail;

    best_r = -1;
    best_c = -1;
    best_mask = 0;
    min_options = 10;

    for (i = 0; i < SIZE; ++i) {
        for (j = 0; j < SIZE; ++j) {
            if (s_grid[i][j] == 0) {
                box_idx = get_box_index(i, j);
                used_mask = s_row_mask[i] | s_col_mask[j] | s_box_mask[box_idx];
                avail = (~used_mask) & FULL_MASK;
                if (avail == 0) {
                    return 0;
                }
                if (count_bits_int(avail) < min_options) {
                    min_options = count_bits_int(avail);
                    best_r = i;
                    best_c = j;
                    best_mask = avail;
                    if (min_options == 1) {
                        i = SIZE;
                        break;
                    }
                }
            }
        }
    }

    if (best_r == -1) {
        return 1;
    }

    while (best_mask) {
        int bit;
        int d;
        int bidx;

        bit = best_mask & -best_mask;
        best_mask -= bit;
        d = digit_from_bit(bit);
        bidx = get_box_index(best_r, best_c);

        s_grid[best_r][best_c] = d;
        s_row_mask[best_r] |= (1 << (d - 1));
        s_col_mask[best_c] |= (1 << (d - 1));
        s_box_mask[bidx] |= (1 << (d - 1));

        if (solve_sudoku_recursive()) {
            return 1;
        }

        s_row_mask[best_r] &= ~(1 << (d - 1));
        s_col_mask[best_c] &= ~(1 << (d - 1));
        s_box_mask[bidx] &= ~(1 << (d - 1));
        s_grid[best_r][best_c] = 0;
    }

    return 0;
}

int main(void)
{
    int i;
    int j;

    for (i = 0; i < SIZE; ++i) {
        for (j = 0; j < SIZE; ++j) {
            int v;
            scanf("%d", &v);
            s_grid[i][j] = v;
            if (v != 0) {
                int bit;
                int bidx;
                bit = 1 << (v - 1);
                bidx = get_box_index(i, j);
                s_row_mask[i] |= bit;
                s_col_mask[j] |= bit;
                s_box_mask[bidx] |= bit;
            }
        }
    }

    solve_sudoku_recursive();

    for (i = 0; i < SIZE; ++i) {
        for (j = 0; j < SIZE; ++j) {
            if (j) {
                putchar(' ');
            }
            printf("%d", s_grid[i][j]);
        }
        putchar('\n');
    }

    return 0;
}
