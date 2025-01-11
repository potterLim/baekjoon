#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#define MAX_H 100
#define MAX_N 100
#define MAX_M 100
#define RIPE 1
#define UNRIPE 0
#define EMPTY -1

typedef struct position {
    int x;
    int y;
    int z;
} position_t;

int bfs_ripen_tomatoes(int*** pa_grid, size_t height, size_t rows, size_t cols) 
{
    size_t i;
    size_t j;
    size_t k;
    size_t queue_start;
    size_t queue_end;
    size_t total_tomatoes;
    size_t ripened_count;
    size_t level_size;
    int nx;
    int ny;
    int nz;
    int days;
    position_t* pa_queue;
    position_t current;
    position_t directions[6];
    int result;

    queue_start = 0;
    queue_end = 0;
    total_tomatoes = 0;
    ripened_count = 0;
    days = 0;

    pa_queue = (position_t*)malloc(height * rows * cols * sizeof(position_t));
    assert(pa_queue != NULL);

    directions[0].x = 0;
    directions[0].y = 0;
    directions[0].z = 1;
    directions[1].x = 0;
    directions[1].y = 0;
    directions[1].z = -1;
    directions[2].x = 0;
    directions[2].y = 1;
    directions[2].z = 0;
    directions[3].x = 0;
    directions[3].y = -1;
    directions[3].z = 0;
    directions[4].x = 1;
    directions[4].y = 0;
    directions[4].z = 0;
    directions[5].x = -1;
    directions[5].y = 0;
    directions[5].z = 0;

    for (k = 0; k < height; ++k) {
        for (i = 0; i < rows; ++i) {
            for (j = 0; j < cols; ++j) {
                if (pa_grid[k][i][j] == RIPE) {
                    pa_queue[queue_end].x = (int)j;
                    pa_queue[queue_end].y = (int)i;
                    pa_queue[queue_end].z = (int)k;
                    queue_end++;
                    ripened_count++;
                } else if (pa_grid[k][i][j] == UNRIPE) {
                    total_tomatoes++;
                }
            }
        }
    }

    total_tomatoes += ripened_count;

    while (queue_start < queue_end) {
        level_size = queue_end - queue_start;

        for (i = 0; i < level_size; ++i) {
            current = pa_queue[queue_start];
            ++queue_start;

            for (j = 0; j < 6; ++j) {
                nx = current.x + directions[j].x;
                ny = current.y + directions[j].y;
                nz = current.z + directions[j].z;

                if (nz >= 0 && nz < (int)height && ny >= 0 && ny < (int)rows && nx >= 0 && nx < (int)cols && pa_grid[nz][ny][nx] == UNRIPE) {
                    pa_grid[nz][ny][nx] = RIPE;
                    pa_queue[queue_end].x = nx;
                    pa_queue[queue_end].y = ny;
                    pa_queue[queue_end].z = nz;
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
    size_t height;
    size_t rows;
    size_t cols;
    size_t i;
    size_t j;
    size_t k;
    int*** pa_grid;
    int result;

    scanf("%zu %zu %zu", &cols, &rows, &height);

    pa_grid = (int***)malloc(height * sizeof(int**));

    for (k = 0; k < height; ++k) {
        pa_grid[k] = (int**)malloc(rows * sizeof(int*));

        for (i = 0; i < rows; ++i) {
            pa_grid[k][i] = (int*)malloc(cols * sizeof(int));
            for (j = 0; j < cols; ++j) {
                scanf("%d", &pa_grid[k][i][j]);
            }
        }
    }

    result = bfs_ripen_tomatoes(pa_grid, height, rows, cols);

    printf("%d\n", result);

    for (k = 0; k < height; ++k) {
        for (i = 0; i < rows; ++i) {
            free(pa_grid[k][i]);
            pa_grid[k][i] = NULL;
        }
        free(pa_grid[k]);
        pa_grid[k] = NULL;
    }

    free(pa_grid);
    pa_grid = NULL;

    return 0;
}
