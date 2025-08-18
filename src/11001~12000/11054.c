#include <stdio.h>

#define MAX_N 1000

static int s_n;
static int s_a[MAX_N];
static int s_inc[MAX_N];
static int s_dec[MAX_N];

static int max_int(int x, int y)
{
    if (x > y) {
        return x;
    } else {
        return y;
    }
}

int main(void)
{
    int i;
    int j;
    int best_len;

    scanf("%d", &s_n);
    for (i = 0; i < s_n; ++i) {
        scanf("%d", &s_a[i]);
    }

    for (i = 0; i < s_n; ++i) {
        s_inc[i] = 1;
        for (j = 0; j < i; ++j) {
            if (s_a[j] < s_a[i]) {
                s_inc[i] = max_int(s_inc[i], s_inc[j] + 1);
            }
        }
    }

    for (i = s_n - 1; i >= 0; --i) {
        s_dec[i] = 1;
        for (j = s_n - 1; j > i; --j) {
            if (s_a[j] < s_a[i]) {
                s_dec[i] = max_int(s_dec[i], s_dec[j] + 1);
            }
        }
    }

    best_len = 0;
    for (i = 0; i < s_n; ++i) {
        best_len = max_int(best_len, s_inc[i] + s_dec[i] - 1);
    }

    printf("%d\n", best_len);
    return 0;
}
