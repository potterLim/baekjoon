#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int vertex_count;
    int edge_count;
    int* pa_dist;
    int i;
    int j;
    int k;
    int from_vertex;
    int to_vertex;
    int edge_length;
    int cap;
    int answer;
    const int INF = 0x3f3f3f3f;

    scanf("%d %d", &vertex_count, &edge_count);

    cap = vertex_count + 1;
    pa_dist = (int*)malloc((size_t)cap * (size_t)cap * sizeof(int));

    for (i = 1; i <= vertex_count; ++i) {
        for (j = 1; j <= vertex_count; ++j) {
            if (i == j) {
                pa_dist[i * cap + j] = 0;
            } else {
                pa_dist[i * cap + j] = INF;
            }
        }
    }

    for (i = 0; i < edge_count; ++i) {
        scanf("%d %d %d", &from_vertex, &to_vertex, &edge_length);
        if (edge_length < pa_dist[from_vertex * cap + to_vertex]) {
            pa_dist[from_vertex * cap + to_vertex] = edge_length;
        }
    }

    for (k = 1; k <= vertex_count; ++k) {
        for (i = 1; i <= vertex_count; ++i) {
            int ik = pa_dist[i * cap + k];
            if (ik == INF) {
                continue;
            }
            for (j = 1; j <= vertex_count; ++j) {
                int kj = pa_dist[k * cap + j];
                int ij = pa_dist[i * cap + j];
                if (kj == INF) {
                    continue;
                }
                if (ik + kj < ij) {
                    pa_dist[i * cap + j] = ik + kj;
                }
            }
        }
    }

    answer = INF;
    for (i = 1; i <= vertex_count; ++i) {
        for (j = 1; j <= vertex_count; ++j) {
            if (i == j) {
                continue;
            }
            if (pa_dist[i * cap + j] != INF && pa_dist[j * cap + i] != INF) {
                int cycle_len = pa_dist[i * cap + j] + pa_dist[j * cap + i];
                if (cycle_len < answer) {
                    answer = cycle_len;
                }
            }
        }
    }

    if (answer == INF) {
        printf("-1\n");
    } else {
        printf("%d\n", answer);
    }

    free(pa_dist);
    return 0;
}
