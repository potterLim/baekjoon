#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int city_count;
    int route_count;
    int* pa_costs;
    int i;
    int j;
    int k;
    int from_city;
    int to_city;
    int route_cost;
    int cap;
    const int INF = 0x3f3f3f3f;

    scanf("%d", &city_count);
    scanf("%d", &route_count);

    cap = city_count + 1;
    pa_costs = (int*)malloc((size_t)cap * (size_t)cap * sizeof(int));

    for (i = 1; i <= city_count; ++i) {
        for (j = 1; j <= city_count; ++j) {
            pa_costs[i * cap + j] = (i == j) ? 0 : INF;
        }
    }

    for (i = 0; i < route_count; ++i) {
        scanf("%d %d %d", &from_city, &to_city, &route_cost);
        if (route_cost < pa_costs[from_city * cap + to_city]) {
            pa_costs[from_city * cap + to_city] = route_cost;
        }
    }

    for (k = 1; k <= city_count; ++k) {
        for (i = 1; i <= city_count; ++i) {
            int ik = pa_costs[i * cap + k];
            if (ik == INF) {
                continue;
            }
            for (j = 1; j <= city_count; ++j) {
                int kj = pa_costs[k * cap + j];
                int ij = pa_costs[i * cap + j];
                int cand = ik + kj;
                if (kj != INF && cand < ij) {
                    pa_costs[i * cap + j] = cand;
                }
            }
        }
    }

    for (i = 1; i <= city_count; ++i) {
        for (j = 1; j <= city_count; ++j) {
            if (j != 1) {
                printf(" ");
            }
            if (pa_costs[i * cap + j] == INF) {
                printf("0");
            } else {
                printf("%d", pa_costs[i * cap + j]);
            }
        }
        printf("\n");
    }

    free(pa_costs);
    return 0;
}
