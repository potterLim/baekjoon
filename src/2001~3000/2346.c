#include <stdio.h>

#define MAX_N 1000

static int next_idx[MAX_N + 1];
static int prev_idx[MAX_N + 1];
static int val[MAX_N + 1];

int main(void)
{
    int n;
    int i;
    int curr;
    int remain;

    scanf("%d", &n);
    for (i = 1; i <= n; ++i) {
        scanf("%d", &val[i]);
    }

    for (i = 1; i <= n; ++i) {
        next_idx[i] = (i == n) ? 1 : (i + 1);
        prev_idx[i] = (i == 1) ? n : (i - 1);
    }

    curr = 1;
    remain = n;

    while (remain > 0) {
        int k;
        int left;
        int p;
        printf("%d", curr);
        --remain;
        if (remain > 0) {
            printf(" ");
        } else {
            printf("\n");
            break;
        }

        k = val[curr];

        next_idx[prev_idx[curr]] = next_idx[curr];
        prev_idx[next_idx[curr]] = prev_idx[curr];

        if (k > 0) {
            left = k - 1;
            p = curr;
            while (left > 0) {
                p = next_idx[p];
                --left;
            }
            curr = next_idx[curr];
            while (left < 0) {
                curr = prev_idx[curr];
                ++left;
            }
            while (k > 1) {
                curr = next_idx[curr];
                --k;
            }
        } else {
            k = -k;
            curr = prev_idx[curr];
            while (k > 1) {
                curr = prev_idx[curr];
                --k;
            }
        }
    }

    return 0;
}
