#include <stdio.h>
#include <stdlib.h>

typedef struct edge {
    int from_city;
    int to_city;
    int time_cost;
} edge_t;

int main(void)
{
    int num_cities;
    int num_routes;

    edge_t* pa_edges;
    long* pa_min_time;

    const long INF = 0x3f3f3f3fL;
    int i;
    int j;

    scanf("%d %d", &num_cities, &num_routes);

    pa_edges = (edge_t*)malloc((size_t)num_routes * sizeof(edge_t));
    pa_min_time = (long*)malloc((size_t)(num_cities + 1) * sizeof(long));

    for (i = 0; i < num_routes; ++i) {
        int a;
        int b;
        int c;
        scanf("%d %d %d", &a, &b, &c);
        pa_edges[i].from_city = a;
        pa_edges[i].to_city = b;
        pa_edges[i].time_cost = c;
    }

    for (i = 1; i <= num_cities; ++i) {
        pa_min_time[i] = INF;
    }
    pa_min_time[1] = 0;

    for (i = 1; i <= num_cities - 1; ++i) {
        int updated;
        updated = 0;
        for (j = 0; j < num_routes; ++j) {
            int u;
            int v;
            int w;
            long cand;

            u = pa_edges[j].from_city;
            v = pa_edges[j].to_city;
            w = pa_edges[j].time_cost;

            if (pa_min_time[u] == INF) {
                continue;
            }
            cand = pa_min_time[u] + (long)w;
            if (cand < pa_min_time[v]) {
                pa_min_time[v] = cand;
                updated = 1;
            }
        }
        if (!updated) {
            break;
        }
    }

    for (j = 0; j < num_routes; ++j) {
        int u;
        int v;
        int w;
        u = pa_edges[j].from_city;
        v = pa_edges[j].to_city;
        w = pa_edges[j].time_cost;
        if (pa_min_time[u] == INF) {
            continue;
        }
        if (pa_min_time[u] + (long)w < pa_min_time[v]) {
            printf("-1\n");
            free(pa_edges);
            free(pa_min_time);
            return 0;
        }
    }

    for (i = 2; i <= num_cities; ++i) {
        if (pa_min_time[i] == INF) {
            printf("-1\n");
        } else {
            printf("%ld\n", pa_min_time[i]);
        }
    }

    free(pa_edges);
    free(pa_min_time);
    return 0;
}
