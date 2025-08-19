#include <stdio.h>

#define MAX_N 100

static int s_t;
static int s_n;
static long long s_p[MAX_N + 1];

int main(void)
{
    int i;

    s_p[1] = 1;
    s_p[2] = 1;
    s_p[3] = 1;
    s_p[4] = 2;
    s_p[5] = 2;
    for (i = 6; i <= MAX_N; ++i) {
        s_p[i] = s_p[i - 1] + s_p[i - 5];
    }

    scanf("%d", &s_t);
    for (i = 0; i < s_t; ++i) {
        scanf("%d", &s_n);
        printf("%lld\n", s_p[s_n]);
    }

    return 0;
}
