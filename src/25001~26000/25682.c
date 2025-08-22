#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int rows;
    int cols;
    int k;
    int stride;
    int* mismatch_prefix;
    char* line;
    int i;
    int j;
    int answer;

    scanf("%d %d %d", &rows, &cols, &k);

    stride = cols + 1;
    mismatch_prefix = (int*)calloc((size_t)(rows + 1) * (size_t)(cols + 1), sizeof(int));
    line = (char*)malloc((size_t)cols + 1);

    for (i = 1; i <= rows; ++i) {
        scanf("%s", line);
        for (j = 1; j <= cols; ++j) {
            int expect_w;
            int mism;
            expect_w = ((i + j) % 2 == 0) ? 'W' : 'B';
            mism = (line[j - 1] != expect_w) ? 1 : 0;

            mismatch_prefix[i * stride + j]
                = mismatch_prefix[(i - 1) * stride + j]
                + mismatch_prefix[i * stride + (j - 1)]
                - mismatch_prefix[(i - 1) * stride + (j - 1)]
                + mism;
        }
    }

    answer = rows * cols;
    for (i = k; i <= rows; ++i) {
        for (j = k; j <= cols; ++j) {
            int x1;
            int y1;
            int x2;
            int y2;
            int mism_w;
            int repaint_w;
            int repaint_b;
            int local_min;

            x1 = i - k + 1;
            y1 = j - k + 1;
            x2 = i;
            y2 = j;

            mism_w = mismatch_prefix[x2 * stride + y2]
                   - mismatch_prefix[(x1 - 1) * stride + y2]
                   - mismatch_prefix[x2 * stride + (y1 - 1)]
                   + mismatch_prefix[(x1 - 1) * stride + (y1 - 1)];

            repaint_w = mism_w;
            repaint_b = k * k - mism_w;
            local_min = (repaint_w < repaint_b) ? repaint_w : repaint_b;

            if (local_min < answer) {
                answer = local_min;
            }
        }
    }

    printf("%d\n", answer);

    free(line);
    free(mismatch_prefix);
    return 0;
}
