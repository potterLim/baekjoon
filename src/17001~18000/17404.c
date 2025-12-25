#include <stdio.h>

#define COLOR_COUNT (3)
#define INF_COST (1000000000)

static int min_int(const int a, const int b)
{
    if (a < b) {
        return a;
    }

    return b;
}

int main(void)
{
    int house_count;
    int cost[1000][COLOR_COUNT];

    int answer_cost;

    scanf("%d", &house_count);

    for (int i = 0; i < house_count; ++i) {
        scanf("%d %d %d", &cost[i][0], &cost[i][1], &cost[i][2]);
    }

    answer_cost = INF_COST;

    for (int first_color = 0; first_color < COLOR_COUNT; ++first_color) {
        int dp[1000][COLOR_COUNT];

        dp[0][0] = INF_COST;
        dp[0][1] = INF_COST;
        dp[0][2] = INF_COST;
        dp[0][first_color] = cost[0][first_color];

        for (int i = 1; i < house_count; ++i) {
            dp[i][0] = cost[i][0] + min_int(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = cost[i][1] + min_int(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = cost[i][2] + min_int(dp[i - 1][0], dp[i - 1][1]);
        }

        for (int last_color = 0; last_color < COLOR_COUNT; ++last_color) {
            if (last_color == first_color) {
                continue;
            }

            answer_cost = min_int(answer_cost, dp[house_count - 1][last_color]);
        }
    }

    printf("%d\n", answer_cost);

    return 0;
}
