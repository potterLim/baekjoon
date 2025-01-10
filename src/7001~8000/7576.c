#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#define RIPE 1
#define UNRIPE 0
#define EMPTY -1

typedef struct potin {
    int x;
    int y;
} potin_t;

int bfs_ripen_tomatoes(int** pa_grid, size_t rows, size_t cols) 
{
    size_t i;
    size_t j;
    size_t queue_start;
    size_t queue_end;
    size_t total_tomatoes;
    size_t ripened_count;
    size_t level_size;
    int nx;
    int ny;
    int days;
    potin_t* pa_queue;
    potin_t current;
    potin_t directions[4];
    int result;

    queue_start = 0;
    queue_end = 0;
    total_tomatoes = 0;
    ripened_count = 0;
    days = 0;

    pa_queue = (potin_t*)malloc(rows * cols * sizeof(potin_t));
    assert(pa_queue != NULL);

    directions[0].x = 0;
    directions[0].y = 1;
    directions[1].x = 0;
    directions[1].y = -1;
    directions[2].x = 1;
    directions[2].y = 0;
    directions[3].x = -1;
    directions[3].y = 0;

    for (i = 0; i < rows; ++i) {
        for (j = 0; j < cols; ++j) {
            if (pa_grid[i][j] == RIPE) {
                pa_queue[queue_end].x = (int)i;
                pa_queue[queue_end].y = (int)j;
                queue_end++;
                ripened_count++;
            } else if (pa_grid[i][j] == UNRIPE) {
                total_tomatoes++;
            }
        }
    }

    total_tomatoes += ripened_count;

    while (queue_start < queue_end) {
        level_size = queue_end - queue_start;

        for (i = 0; i < level_size; ++i) {
            current = pa_queue[queue_start];
            ++queue_start;

            for (j = 0; j < 4; ++j) {
                nx = current.x + directions[j].x;
                ny = current.y + directions[j].y;

                if (nx >= 0 && nx < (int)rows && ny >= 0 && ny < (int)cols && pa_grid[nx][ny] == UNRIPE) {
                    pa_grid[nx][ny] = RIPE;
                    pa_queue[queue_end].x = nx;
                    pa_queue[queue_end].y = ny;
                    queue_end++;
                    ripened_count++;
                }
            }
        }

        if (queue_start < queue_end) {
            days++;
        }
    }

    if (ripened_count == total_tomatoes) {
        result = days;
    } else {
        result = -1;
    }

    free(pa_queue);
    pa_queue = NULL;

    return result;
}

int main(void) 
{
    size_t rows;
    size_t cols;
    size_t i;
    size_t j;
    int** pa_grid;
    int result;

    scanf("%zu %zu", &cols, &rows);

    pa_grid = (int**)malloc(rows * sizeof(int*));
    assert(pa_grid != NULL);

    for (i = 0; i < rows; ++i) {
        pa_grid[i] = (int*)malloc(cols * sizeof(int));
        assert(pa_grid[i] != NULL);
        for (j = 0; j < cols; ++j) {
            scanf("%d", &pa_grid[i][j]);
        }
    }

    result = bfs_ripen_tomatoes(pa_grid, rows, cols);

    printf("%d\n", result);

    for (i = 0; i < rows; ++i) {
        free(pa_grid[i]);
        pa_grid[i] = NULL;
    }

    free(pa_grid);
    pa_grid = NULL;

    return 0;
}
