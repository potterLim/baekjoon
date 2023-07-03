#include <stdio.h>

#define N_MAX 100
#define M_MAX 100
#define K_MAX 100

int main() {
  int num_rows_A;
  int num_cols_A;
  int num_cols_B;
  int sum = 0;
  int matrix_A[N_MAX][M_MAX];
  int matrix_B[M_MAX][K_MAX];
  int i, j, k;

  scanf("%d %d", &num_rows_A, &num_cols_A);

  for(i = 0; i < num_rows_A; i++) {
    for(j = 0; j < num_cols_A; j++) {
      scanf("%d", &matrix_A[i][j]);
    }
  }

  scanf("%d %d", &num_cols_A, &num_cols_B);

  for(i = 0; i < num_cols_A; i++) {
    for(j = 0; j < num_cols_B; j++) {
      scanf("%d", &matrix_B[i][j]);
    }
  }

  for(i = 0; i < num_rows_A; i++) {
    for(j = 0; j < num_cols_B; j++) {
      for(k = 0; k < num_cols_A; k++) {
        sum += matrix_A[i][k] * matrix_B[k][j];
      }
      printf("%d ", sum);
      sum = 0;
    }

    printf("\n");
  }

  return 0;
}
