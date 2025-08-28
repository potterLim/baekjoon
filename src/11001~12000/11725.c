#include <stdio.h>
#include <stdlib.h>

typedef struct edge {
    int to;
    int next;
} edge_t;

void add_edge(int from, int to, edge_t* const edges, int* const head, int* const p_edge_count)
{
    int idx;
    idx = *p_edge_count;
    edges[idx].to = to;
    edges[idx].next = head[from];
    head[from] = idx;
    *p_edge_count = idx + 1;
}

int main(void)
{
    int num_nodes;
    int i;

    edge_t* pa_edges;
    int* pa_head;
    int* pa_parent;
    unsigned char* pa_visited;
    int* pa_queue;

    int edge_count;
    int edge_capacity;

    int u;
    int v;

    int q_head;
    int q_tail;

    scanf("%d", &num_nodes);

    edge_capacity = (num_nodes - 1) * 2;

    pa_edges = (edge_t*)malloc((size_t)edge_capacity * sizeof(edge_t));
    pa_head = (int*)malloc((size_t)(num_nodes + 1) * sizeof(int));
    pa_parent = (int*)malloc((size_t)(num_nodes + 1) * sizeof(int));
    pa_visited = (unsigned char*)malloc((size_t)(num_nodes + 1) * sizeof(unsigned char));
    pa_queue = (int*)malloc((size_t)(num_nodes + 5) * sizeof(int));

    for (i = 1; i <= num_nodes; ++i) {
        pa_head[i] = -1;
        pa_parent[i] = 0;
        pa_visited[i] = 0u;
    }
    edge_count = 0;

    for (i = 0; i < num_nodes - 1; ++i) {
        scanf("%d %d", &u, &v);
        add_edge(u, v, pa_edges, pa_head, &edge_count);
        add_edge(v, u, pa_edges, pa_head, &edge_count);
    }

    q_head = 0;
    q_tail = 0;

    pa_visited[1] = 1u;
    pa_parent[1] = 0;
    pa_queue[q_tail++] = 1;

    while (q_head != q_tail) {
        int node;
        int e;

        node = pa_queue[q_head++];
        e = pa_head[node];
        while (e != -1) {
            int nxt;
            nxt = pa_edges[e].to;
            if (!pa_visited[nxt]) {
                pa_visited[nxt] = 1u;
                pa_parent[nxt] = node;
                pa_queue[q_tail++] = nxt;
            }
            e = pa_edges[e].next;
        }
    }

    for (i = 2; i <= num_nodes; ++i) {
        printf("%d\n", pa_parent[i]);
    }

    free(pa_edges);
    free(pa_head);
    free(pa_parent);
    free(pa_visited);
    free(pa_queue);
    return 0;
}
