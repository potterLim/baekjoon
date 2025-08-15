#include <stdio.h>

#define MAX_N 8

static int s_n;
static int s_m;
static int s_used[MAX_N + 1];
static int s_choice[MAX_N];

static void generate_sequences_recursive(int depth)
{
    int i;

    if (depth == s_m) {
        for (i = 0; i < s_m; ++i) {
            if (i > 0) {
                putchar(' ');
            }
            printf("%d", s_choice[i]);
        }
        putchar('\n');
        return;
    }

    for (i = 1; i <= s_n; ++i) {
        if (!s_used[i]) {
            s_used[i] = 1;
            s_choice[depth] = i;
            generate_sequences_recursive(depth + 1);
            s_used[i] = 0;
        }
    }
}

int main(void)
{
    scanf("%d %d", &s_n, &s_m);
    generate_sequences_recursive(0);
    return 0;
}
