#include <stdio.h>

#define MAX_ROWS 51
#define MAX_COLS 51

void clearVerticalLine(int row, int col);
void clearHorizontalLine(int row, int col);

char grid[MAX_ROWS][MAX_COLS];
int num_rows;
int num_cols;

int main(void) 
{
    int i;
    int j;
    int num_cleared_lines = 0;

    scanf("%d %d", &num_rows, &num_cols);


    for (i = 0; i < num_rows; i++) {
        scanf("%s", grid[i]);
    }

    for (i = 0; i < num_rows; i++) {
        for (j = 0; j < num_cols; j++) {
            if (grid[i][j] == '|') {
                clearVerticalLine(i, j);
                num_cleared_lines++;
            }
            if (grid[i][j] == '-') {
                clearHorizontalLine(i, j);
                num_cleared_lines++;
            }
        }
    }

    printf("%d\n", num_cleared_lines);
    return 0;
}

void clearVerticalLine(int row, int col) 
{
    int i;
    for (i = row; i < num_rows; i++) {
        if (grid[i][col] != '|') {
            break;
        }

        grid[i][col] = '.';
    }
}

void clearHorizontalLine(int row, int col) 
{
    int j;
    for (j = col; j < num_cols; j++) {
        if (grid[row][j] != '-') {
            break;
        }

        grid[row][j] = '.';
    }
}
