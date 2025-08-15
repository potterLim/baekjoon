#include <stdio.h>

#define MAX_N 8

static int s_n;
static int s_m;
static int s_sequence[MAX_N];

static void generate_combinations_recursive(int depth, int start)
{
    int i;

    if (depth == s_m) {
        for (i = 0; i < s_m; ++i) {
            if (i > 0) {
                putchar(' ');
            }
            printf("%d", s_sequence[i]);
        }
        putchar('\n');
        return;
    }

    for (i = start; i <= s_n; ++i) {
        s_sequence[depth] = i;
        generate_combinations_recursive(depth + 1, i + 1);
    }
}

int main(void)
{
    scanf("%d %d", &s_n, &s_m);
    generate_combinations_recursive(0, 1);
    return 0;
}
