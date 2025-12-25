#include <stdio.h>
#include <stdlib.h>

#define INF_COST 2147483647

static int min_int(const int a, const int b)
{
    if (a < b) {
        return a;
    }

    return b;
}

int main(void)
{
    int city_count;
    int full_visited_mask;
    int minimum_cost;

    int* cost_matrix;
    int* dp;

    int i;
    int j;
    int visited_mask;
    int from_city;
    int to_city;

    scanf("%d", &city_count);

    cost_matrix = (int*)malloc(sizeof(int) * city_count * city_count);

    for (i = 0; i < city_count; ++i) {
        for (j = 0; j < city_count; ++j) {
            scanf("%d", &cost_matrix[i * city_count + j]);
        }
    }

    full_visited_mask = (1 << city_count) - 1;

    dp = (int*)malloc(sizeof(int) * (1 << city_count) * city_count);

    for (i = 0; i < (1 << city_count) * city_count; ++i) {
        dp[i] = INF_COST;
    }

    dp[(1 << 0) * city_count + 0] = 0;

    for (visited_mask = 0; visited_mask <= full_visited_mask; ++visited_mask) {
        for (from_city = 0; from_city < city_count; ++from_city) {
            int current_cost;

            if ((visited_mask & (1 << from_city)) == 0) {
                continue;
            }

            current_cost = dp[visited_mask * city_count + from_city];
            if (current_cost == INF_COST) {
                continue;
            }

            for (to_city = 0; to_city < city_count; ++to_city) {
                int edge_cost;
                int next_mask;
                int next_cost;

                if ((visited_mask & (1 << to_city)) != 0) {
                    continue;
                }

                edge_cost = cost_matrix[from_city * city_count + to_city];
                if (edge_cost == 0) {
                    continue;
                }

                next_mask = visited_mask | (1 << to_city);
                next_cost = current_cost + edge_cost;

                if (next_cost < dp[next_mask * city_count + to_city]) {
                    dp[next_mask * city_count + to_city] = next_cost;
                }
            }
        }
    }

    minimum_cost = INF_COST;
    for (i = 1; i < city_count; ++i) {
        int path_cost;
        int return_cost;

        path_cost = dp[full_visited_mask * city_count + i];
        if (path_cost == INF_COST) {
            continue;
        }

        return_cost = cost_matrix[i * city_count + 0];
        if (return_cost == 0) {
            continue;
        }

        minimum_cost = min_int(minimum_cost, path_cost + return_cost);
    }

    printf("%d\n", minimum_cost);

    free(dp);
    dp = NULL;

    free(cost_matrix);
    cost_matrix = NULL;

    return 0;
}
