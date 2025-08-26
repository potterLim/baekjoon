#include <stdio.h>
#include <stdlib.h>

typedef struct edge {
    int to;
    int next;
} edge_t;

void add_edge(int from, int to, edge_t* const edges, int* const head, int* const out_edge_count)
{
    int index;
    index = *out_edge_count;

    edges[index].to = to;
    edges[index].next = head[from];
    head[from] = index;

    *out_edge_count = index + 1;
}

int main(void)
{
    int num_students;
    int num_compares;

    edge_t* pa_edges;
    int* pa_head;
    int* pa_indegree;
    int* pa_queue;
    int* pa_order;

    int edge_count;
    int i;
    int a;
    int b;

    int q_head;
    int q_tail;
    int out_count;

    scanf("%d %d", &num_students, &num_compares);

    pa_edges = (edge_t*)malloc((size_t)num_compares * sizeof(edge_t));
    pa_head = (int*)malloc((size_t)(num_students + 1) * sizeof(int));
    pa_indegree = (int*)malloc((size_t)(num_students + 1) * sizeof(int));
    pa_queue = (int*)malloc((size_t)(num_students + 1) * sizeof(int));
    pa_order = (int*)malloc((size_t)num_students * sizeof(int));

    for (i = 1; i <= num_students; ++i) {
        pa_head[i] = -1;
        pa_indegree[i] = 0;
    }
    edge_count = 0;

    for (i = 0; i < num_compares; ++i) {
        scanf("%d %d", &a, &b);
        add_edge(a, b, pa_edges, pa_head, &edge_count);
        pa_indegree[b] += 1;
    }

    q_head = 0;
    q_tail = 0;

    for (i = 1; i <= num_students; ++i) {
        if (pa_indegree[i] == 0) {
            pa_queue[q_tail++] = i;
        }
    }

    out_count = 0;
    while (q_head != q_tail) {
        int u;
        int e;

        u = pa_queue[q_head++];
        pa_order[out_count++] = u;

        e = pa_head[u];
        while (e != -1) {
            int v;
            v = pa_edges[e].to;
            pa_indegree[v] -= 1;
            if (pa_indegree[v] == 0) {
                pa_queue[q_tail++] = v;
            }
            e = pa_edges[e].next;
        }
    }

    for (i = 0; i < out_count; ++i) {
        if (i != 0) {
            printf(" ");
        }
        printf("%d", pa_order[i]);
    }
    printf("\n");

    free(pa_edges);
    free(pa_head);
    free(pa_indegree);
    free(pa_queue);
    free(pa_order);

    return 0;
}
