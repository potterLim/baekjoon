#include <stdio.h>

#define MAX_N 20

static int s_n;
static int s_score[MAX_N][MAX_N];
static int s_selected[MAX_N];
static int s_best_diff;

static int abs_int(int x)
{
    if (x < 0) {
        return -x;
    } else {
        return x;
    }
}

static void choose_teams_recursive(int idx, int chosen)
{
    int i;
    int j;

    if (chosen == s_n / 2) {
        int start_sum = 0;
        int link_sum = 0;
        for (i = 0; i < s_n; ++i) {
            for (j = i + 1; j < s_n; ++j) {
                if (s_selected[i] && s_selected[j]) {
                    start_sum += s_score[i][j] + s_score[j][i];
                } else if (!s_selected[i] && !s_selected[j]) {
                    link_sum += s_score[i][j] + s_score[j][i];
                }
            }
        }
        if (abs_int(start_sum - link_sum) < s_best_diff) {
            s_best_diff = abs_int(start_sum - link_sum);
        }
        return;
    }

    if (idx == s_n) {
        return;
    }

    s_selected[idx] = 1;
    choose_teams_recursive(idx + 1, chosen + 1);
    s_selected[idx] = 0;

    if (s_n - idx - 1 + chosen >= s_n / 2) {
        choose_teams_recursive(idx + 1, chosen);
    }
}

int main(void)
{
    int i;
    int j;

    scanf("%d", &s_n);
    for (i = 0; i < s_n; ++i) {
        for (j = 0; j < s_n; ++j) {
            scanf("%d", &s_score[i][j]);
        }
    }

    s_best_diff = 2147483647;

    s_selected[0] = 1;
    choose_teams_recursive(1, 1);

    printf("%d\n", s_best_diff);
    return 0;
}
