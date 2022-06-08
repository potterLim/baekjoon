#include <stdio.h>

int main(void) {
    int mat1[100][100];
    int mat2[100][100];
    int mat_result[100][100];
    int row;
    int col;
    int i;
    int j;

    scanf("%d %d", &row, &col);

    for (i = 0; i < row; ++i) {
        for (j = 0; j < col; ++j) {
            scanf("%d", &mat1[i][j]);
        }
    }

    for (i = 0; i < row; ++i) {
        for (j = 0; j < col; ++j) {
            scanf("%d", &mat2[i][j]);
        }
    }

    for (i = 0; i < row; ++i) {
        for (j = 0; j < col; ++j) {
            mat_result[i][j] = mat1[i][j] + mat2[i][j];
        }
    }

    for (i = 0; i < row; ++i) {
        for (j = 0; j < col; ++j) {
            printf("%d ", mat_result[i][j]);
        }

        printf("\n");
    }

    return 0;
}
