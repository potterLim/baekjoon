#include <stdio.h>
#include <stdlib.h>

typedef struct edge {
    int to;
    int weight;
    int next;
} edge_t;

void add_edge(int from, int to, int weight, edge_t* const pa_edges, int* const pa_head, int* const p_edge_count)
{
    int index;
    index = *p_edge_count;
    pa_edges[index].to = to;
    pa_edges[index].weight = weight;
    pa_edges[index].next = pa_head[from];
    pa_head[from] = index;
    *p_edge_count = index + 1;
}

void heap_push(int* const pa_heap_node, int* const pa_heap_dist, int* const p_heap_size, int node_value, int dist_value)
{
    int i;
    int parent;
    int tmp_node;
    int tmp_dist;

    (*p_heap_size) += 1;
    i = *p_heap_size;
    pa_heap_node[i] = node_value;
    pa_heap_dist[i] = dist_value;

    while (i > 1) {
        parent = i / 2;
        if (pa_heap_dist[parent] <= pa_heap_dist[i]) {
            break;
        }
        tmp_node = pa_heap_node[parent];
        tmp_dist = pa_heap_dist[parent];
        pa_heap_node[parent] = pa_heap_node[i];
        pa_heap_dist[parent] = pa_heap_dist[i];
        pa_heap_node[i] = tmp_node;
        pa_heap_dist[i] = tmp_dist;
        i = parent;
    }
}

int heap_pop(int* const pa_heap_node, int* const pa_heap_dist, int* const p_heap_size, int* const p_out_dist)
{
    int ret_node;
    int ret_dist;
    int i;
    int left;
    int right;
    int smallest;
    int tmp_node;
    int tmp_dist;

    ret_node = pa_heap_node[1];
    ret_dist = pa_heap_dist[1];
    pa_heap_node[1] = pa_heap_node[*p_heap_size];
    pa_heap_dist[1] = pa_heap_dist[*p_heap_size];
    (*p_heap_size) -= 1;

    i = 1;
    while (1) {
        left = i * 2;
        right = left + 1;
        smallest = i;

        if (left <= *p_heap_size && pa_heap_dist[left] < pa_heap_dist[smallest]) {
            smallest = left;
        }
        if (right <= *p_heap_size && pa_heap_dist[right] < pa_heap_dist[smallest]) {
            smallest = right;
        }
        if (smallest == i) {
            break;
        }

        tmp_node = pa_heap_node[i];
        tmp_dist = pa_heap_dist[i];
        pa_heap_node[i] = pa_heap_node[smallest];
        pa_heap_dist[i] = pa_heap_dist[smallest];
        pa_heap_node[smallest] = tmp_node;
        pa_heap_dist[smallest] = tmp_dist;

        i = smallest;
    }

    *p_out_dist = ret_dist;
    return ret_node;
}

int main(void)
{
    int num_vertices;
    int num_edges;
    int start_vertex;

    edge_t* pa_edges;
    int* pa_head;
    int* pa_dist;
    unsigned char* pa_visited;
    int* pa_heap_node;
    int* pa_heap_dist;

    int edge_count;
    int heap_size;

    int i;
    int u;
    int v;
    int w;

    int popped_node;
    int popped_dist;

    scanf("%d %d", &num_vertices, &num_edges);
    scanf("%d", &start_vertex);

    pa_edges = (edge_t*)malloc((size_t)num_edges * sizeof(edge_t));
    pa_head = (int*)malloc((size_t)(num_vertices + 1) * sizeof(int));
    pa_dist = (int*)malloc((size_t)(num_vertices + 1) * sizeof(int));
    pa_visited = (unsigned char*)malloc((size_t)(num_vertices + 1) * sizeof(unsigned char));
    pa_heap_node = (int*)malloc((size_t)(num_edges + 5) * sizeof(int));
    pa_heap_dist = (int*)malloc((size_t)(num_edges + 5) * sizeof(int));

    for (i = 1; i <= num_vertices; ++i) {
        pa_head[i] = -1;
        pa_dist[i] = 0x3f3f3f3f;
        pa_visited[i] = 0u;
    }
    edge_count = 0;

    for (i = 0; i < num_edges; ++i) {
        scanf("%d %d %d", &u, &v, &w);
        add_edge(u, v, w, pa_edges, pa_head, &edge_count);
    }

    heap_size = 0;
    pa_dist[start_vertex] = 0;
    heap_push(pa_heap_node, pa_heap_dist, &heap_size, start_vertex, 0);

    while (heap_size > 0) {
        int edge_index;
        popped_node = heap_pop(pa_heap_node, pa_heap_dist, &heap_size, &popped_dist);
        if (pa_visited[popped_node]) {
            continue;
        }
        pa_visited[popped_node] = 1u;
        if (popped_dist != pa_dist[popped_node]) {
            continue;
        }

        edge_index = pa_head[popped_node];
        while (edge_index != -1) {
            int next_node;
            int next_weight;
            int new_dist;

            next_node = pa_edges[edge_index].to;
            next_weight = pa_edges[edge_index].weight;
            new_dist = pa_dist[popped_node] + next_weight;

            if (new_dist < pa_dist[next_node]) {
                pa_dist[next_node] = new_dist;
                heap_push(pa_heap_node, pa_heap_dist, &heap_size, next_node, new_dist);
            }
            edge_index = pa_edges[edge_index].next;
        }
    }

    for (i = 1; i <= num_vertices; ++i) {
        if (pa_dist[i] == 0x3f3f3f3f) {
            printf("INF\n");
        } else {
            printf("%d\n", pa_dist[i]);
        }
    }

    free(pa_edges);
    free(pa_head);
    free(pa_dist);
    free(pa_visited);
    free(pa_heap_node);
    free(pa_heap_dist);

    return 0;
}
