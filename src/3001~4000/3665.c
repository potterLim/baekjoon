#include <stdio.h>
#include <stdlib.h>

typedef struct edge {
    int to;
    int next;
} edge_t;

int main(void)
{
    int num_cases;
    int i;

    scanf("%d", &num_cases);

    for (i = 0; i < num_cases; ++i) {
        int num_teams;
        int num_swaps;
        int j;

        int* pa_rank;
        unsigned char* pa_adj;
        int* pa_indegree;
        int* pa_queue;
        int* pa_result;

        int cap;
        int q_head;
        int q_tail;
        int out_count;
        int ambiguous;
        int impossible;

        scanf("%d", &num_teams);

        pa_rank = (int*)malloc((size_t)num_teams * sizeof(int));
        pa_adj = (unsigned char*)malloc((size_t)(num_teams + 1) * (size_t)(num_teams + 1) * sizeof(unsigned char));
        pa_indegree = (int*)malloc((size_t)(num_teams + 1) * sizeof(int));
        pa_queue = (int*)malloc((size_t)(num_teams + 1) * sizeof(int));
        pa_result = (int*)malloc((size_t)num_teams * sizeof(int));

        cap = num_teams + 1;

        for (j = 0; j < num_teams; ++j) {
            scanf("%d", &pa_rank[j]);
        }

        for (j = 0; j < cap * cap; ++j) {
            pa_adj[j] = 0u;
        }
        for (j = 0; j < cap; ++j) {
            pa_indegree[j] = 0;
        }

        for (j = 0; j < num_teams; ++j) {
            int higher;
            int k;

            higher = pa_rank[j];
            for (k = j + 1; k < num_teams; ++k) {
                int lower;
                lower = pa_rank[k];
                if (pa_adj[higher * cap + lower] == 0u) {
                    pa_adj[higher * cap + lower] = 1u;
                    pa_indegree[lower] += 1;
                }
            }
        }

        scanf("%d", &num_swaps);
        for (j = 0; j < num_swaps; ++j) {
            int a;
            int b;
            scanf("%d %d", &a, &b);

            if (pa_adj[a * cap + b]) {
                pa_adj[a * cap + b] = 0u;
                pa_adj[b * cap + a] = 1u;
                pa_indegree[b] -= 1;
                pa_indegree[a] += 1;
            } else {
                if (pa_adj[b * cap + a]) {
                    pa_adj[b * cap + a] = 0u;
                    pa_adj[a * cap + b] = 1u;
                    pa_indegree[a] -= 1;
                    pa_indegree[b] += 1;
                }
            }
        }

        q_head = 0;
        q_tail = 0;
        for (j = 1; j <= num_teams; ++j) {
            if (pa_indegree[j] == 0) {
                pa_queue[q_tail++] = j;
            }
        }

        out_count = 0;
        ambiguous = 0;
        impossible = 0;

        while (q_head != q_tail) {
            int q_size;
            int u;

            q_size = q_tail - q_head;
            if (q_size > 1) {
                ambiguous = 1;
            }

            u = pa_queue[q_head++];
            pa_result[out_count++] = u;

            for (j = 1; j <= num_teams; ++j) {
                if (pa_adj[u * cap + j]) {
                    pa_adj[u * cap + j] = 0u;
                    pa_indegree[j] -= 1;
                    if (pa_indegree[j] == 0) {
                        pa_queue[q_tail++] = j;
                    }
                }
            }
        }

        if (out_count != num_teams) {
            impossible = 1;
        }

        if (impossible) {
            printf("IMPOSSIBLE\n");
        } else if (ambiguous) {
            printf("?\n");
        } else {
            for (j = 0; j < num_teams; ++j) {
                if (j != 0) {
                    printf(" ");
                }
                printf("%d", pa_result[j]);
            }
            printf("\n");
        }

        free(pa_rank);
        free(pa_adj);
        free(pa_indegree);
        free(pa_queue);
        free(pa_result);
    }

    return 0;
}
