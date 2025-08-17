#include <stdio.h>

#define MAX_N 300

static int s_n;
static int s_score[MAX_N + 1];
static int s_best[MAX_N + 1];

static int max_int(int a, int b)
{
    if (a > b) {
        return a;
    } else {
        return b;
    }
}

int main(void)
{
    int i;

    scanf("%d", &s_n);
    for (i = 1; i <= s_n; ++i) {
        scanf("%d", &s_score[i]);
    }

    if (s_n == 1) {
        printf("%d\n", s_score[1]);
        return 0;
    }

    s_best[1] = s_score[1];
    s_best[2] = s_score[1] + s_score[2];

    for (i = 3; i <= s_n; ++i) {
        s_best[i] = max_int(s_best[i - 2], s_best[i - 3] + s_score[i - 1]) + s_score[i];
    }

    printf("%d\n", s_best[s_n]);
    return 0;
}
