#include <stdio.h>
#include <stdlib.h>

int max_int(int a, int b)
{
    if (a > b) {
        return a;
    }
    return b;
}

void dfs_best_sum(int row, int col, int depth, int current_sum, int rows, int cols, const int* const pa_board, unsigned char* const pa_visited, int* const p_best, int max_cell_value)
{
    int remaining;
    int idx;
    int nr;
    int nc;

    remaining = 4 - depth;
    if (current_sum + remaining * max_cell_value <= *p_best) {
        return;
    }

    if (depth == 4) {
        if (current_sum > *p_best) {
            *p_best = current_sum;
        }
        return;
    }

    idx = row * cols + col;
    pa_visited[idx] = 1u;

    nr = row - 1;
    nc = col;
    if (nr >= 0) {
        idx = nr * cols + nc;
        if (!pa_visited[idx]) {
            dfs_best_sum(nr, nc, depth + 1, current_sum + pa_board[idx], rows, cols, pa_board, pa_visited, p_best, max_cell_value);
        }
    }

    nr = row + 1;
    nc = col;
    if (nr < rows) {
        idx = nr * cols + nc;
        if (!pa_visited[idx]) {
            dfs_best_sum(nr, nc, depth + 1, current_sum + pa_board[idx], rows, cols, pa_board, pa_visited, p_best, max_cell_value);
        }
    }

    nr = row;
    nc = col - 1;
    if (nc >= 0) {
        idx = nr * cols + nc;
        if (!pa_visited[idx]) {
            dfs_best_sum(nr, nc, depth + 1, current_sum + pa_board[idx], rows, cols, pa_board, pa_visited, p_best, max_cell_value);
        }
    }

    nr = row;
    nc = col + 1;
    if (nc < cols) {
        idx = nr * cols + nc;
        if (!pa_visited[idx]) {
            dfs_best_sum(nr, nc, depth + 1, current_sum + pa_board[idx], rows, cols, pa_board, pa_visited, p_best, max_cell_value);
        }
    }

    idx = row * cols + col;
    pa_visited[idx] = 0u;
}

int best_t_shape(int row, int col, int rows, int cols, const int* const pa_board)
{
    int center_idx;
    int sum;
    int count;
    int min_neighbor;
    int total_best;
    int val;

    total_best = 0;

    center_idx = row * cols + col;

    sum = pa_board[center_idx];
    count = 0;
    min_neighbor = 2147483647;

    if (row - 1 >= 0) {
        val = pa_board[(row - 1) * cols + col];
        sum += val;
        if (val < min_neighbor) {
            min_neighbor = val;
        }
        count += 1;
    }
    if (row + 1 < rows) {
        val = pa_board[(row + 1) * cols + col];
        sum += val;
        if (val < min_neighbor) {
            min_neighbor = val;
        }
        count += 1;
    }
    if (col - 1 >= 0) {
        val = pa_board[row * cols + (col - 1)];
        sum += val;
        if (val < min_neighbor) {
            min_neighbor = val;
        }
        count += 1;
    }
    if (col + 1 < cols) {
        val = pa_board[row * cols + (col + 1)];
        sum += val;
        if (val < min_neighbor) {
            min_neighbor = val;
        }
        count += 1;
    }

    if (count >= 3) {
        if (count == 4) {
            total_best = sum - min_neighbor;
        } else {
            total_best = sum;
        }
    }

    return total_best;
}

int main(void)
{
    int rows;
    int cols;
    int* pa_board;
    unsigned char* pa_visited;
    int i;
    int j;
    int idx;
    int best_sum;
    int max_cell_value;
    int cell_sum;
    int start_idx;

    scanf("%d %d", &rows, &cols);

    pa_board = (int*)malloc((size_t)rows * (size_t)cols * sizeof(int));
    pa_visited = (unsigned char*)malloc((size_t)rows * (size_t)cols * sizeof(unsigned char));

    max_cell_value = 0;
    for (i = 0; i < rows; ++i) {
        for (j = 0; j < cols; ++j) {
            idx = i * cols + j;
            scanf("%d", &pa_board[idx]);
            if (pa_board[idx] > max_cell_value) {
                max_cell_value = pa_board[idx];
            }
            pa_visited[idx] = 0u;
        }
    }

    best_sum = 0;

    for (i = 0; i < rows; ++i) {
        for (j = 0; j < cols; ++j) {
            start_idx = i * cols + j;
            dfs_best_sum(i, j, 1, pa_board[start_idx], rows, cols, pa_board, pa_visited, &best_sum, max_cell_value);
            cell_sum = best_t_shape(i, j, rows, cols, pa_board);
            if (cell_sum > best_sum) {
                best_sum = cell_sum;
            }
        }
    }

    printf("%d\n", best_sum);

    free(pa_board);
    free(pa_visited);
    return 0;
}
