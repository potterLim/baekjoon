#include <stdio.h>

#define MAX_N (5)
#define MOD (1000)

static int matrix_size;

static void multiply(const int left_matrix[MAX_N][MAX_N], const int right_matrix[MAX_N][MAX_N], int dest_matrix[MAX_N][MAX_N])
{
    int i;
    int j;
    int k;
    int accum;

    for (i = 0; i < matrix_size; ++i) {
        for (j = 0; j < matrix_size; ++j) {
            accum = 0;
            for (k = 0; k < matrix_size; ++k) {
                accum += (left_matrix[i][k] * right_matrix[k][j]) % MOD;
                if (accum >= (1 << 30)) {
                    accum %= MOD;
                }
            }
            dest_matrix[i][j] = accum % MOD;
        }
    }
}

int main(void)
{
    long long power;
    int base_matrix[MAX_N][MAX_N];
    int result_matrix[MAX_N][MAX_N];
    int temp_matrix[MAX_N][MAX_N];
    int i;
    int j;

    scanf("%d %lld", &matrix_size, &power);

    for (i = 0; i < matrix_size; ++i) {
        for (j = 0; j < matrix_size; ++j) {
            scanf("%d", &base_matrix[i][j]);
            base_matrix[i][j] %= MOD;
        }
    }

    for (i = 0; i < matrix_size; ++i) {
        for (j = 0; j < matrix_size; ++j) {
            if (i == j) {
                result_matrix[i][j] = 1;
            } else {
                result_matrix[i][j] = 0;
            }
        }
    }

    while (power > 0) {
        if (power & 1LL) {
            multiply(result_matrix, base_matrix, temp_matrix);
            for (i = 0; i < matrix_size; ++i) {
                for (j = 0; j < matrix_size; ++j) {
                    result_matrix[i][j] = temp_matrix[i][j];
                }
            }
        }
        multiply(base_matrix, base_matrix, temp_matrix);
        for (i = 0; i < matrix_size; ++i) {
            for (j = 0; j < matrix_size; ++j) {
                base_matrix[i][j] = temp_matrix[i][j];
            }
        }
        power >>= 1;
    }

    for (i = 0; i < matrix_size; ++i) {
        for (j = 0; j < matrix_size; ++j) {
            printf("%d", result_matrix[i][j]);
            if (j + 1 == matrix_size) {
                printf("\n");
            } else {
                printf(" ");
            }
        }
    }

    return 0;
}
