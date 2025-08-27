#include <stdio.h>
#include <stdlib.h>

typedef struct edge {
    int to;
    int weight;
    int next;
} edge_t;

void add_edge(int from, int to, int weight, edge_t* const edges, int* const head, int* const p_edge_count)
{
    int idx;
    idx = *p_edge_count;
    edges[idx].to = to;
    edges[idx].weight = weight;
    edges[idx].next = head[from];
    head[from] = idx;
    *p_edge_count = idx + 1;
}

void heap_push(int* const heap_nodes, int* const heap_dists, int* const p_size, int node_value, int dist_value)
{
    int i;
    int parent;
    int tmp_node;
    int tmp_dist;

    (*p_size) += 1;
    i = *p_size;
    heap_nodes[i] = node_value;
    heap_dists[i] = dist_value;

    while (i > 1) {
        parent = i / 2;
        if (heap_dists[parent] <= heap_dists[i]) {
            break;
        }
        tmp_node = heap_nodes[parent];
        tmp_dist = heap_dists[parent];
        heap_nodes[parent] = heap_nodes[i];
        heap_dists[parent] = heap_dists[i];
        heap_nodes[i] = tmp_node;
        heap_dists[i] = tmp_dist;
        i = parent;
    }
}

int heap_pop(int* const heap_nodes, int* const heap_dists, int* const p_size, int* const p_out_dist)
{
    int popped_node;
    int popped_dist;
    int i;
    int left_child;
    int right_child;
    int smallest_index;
    int tmp_node;
    int tmp_dist;

    popped_node = heap_nodes[1];
    popped_dist = heap_dists[1];
    heap_nodes[1] = heap_nodes[*p_size];
    heap_dists[1] = heap_dists[*p_size];
    (*p_size) -= 1;

    i = 1;
    while (1) {
        left_child = i * 2;
        right_child = left_child + 1;
        smallest_index = i;

        if (left_child <= *p_size && heap_dists[left_child] < heap_dists[smallest_index]) {
            smallest_index = left_child;
        }
        if (right_child <= *p_size && heap_dists[right_child] < heap_dists[smallest_index]) {
            smallest_index = right_child;
        }
        if (smallest_index == i) {
            break;
        }

        tmp_node = heap_nodes[i];
        tmp_dist = heap_dists[i];
        heap_nodes[i] = heap_nodes[smallest_index];
        heap_dists[i] = heap_dists[smallest_index];
        heap_nodes[smallest_index] = tmp_node;
        heap_dists[smallest_index] = tmp_dist;

        i = smallest_index;
    }

    *p_out_dist = popped_dist;
    return popped_node;
}

void dijkstra(int start_vertex, int vertex_count, const int* const head, const edge_t* const edges, int* const dist, unsigned char* const visited, int* const heap_nodes, int* const heap_dists)
{
    int i;
    int heap_size;
    int popped_node;
    int popped_dist;

    for (i = 1; i <= vertex_count; ++i) {
        dist[i] = 0x3f3f3f3f;
        visited[i] = 0u;
    }

    heap_size = 0;
    dist[start_vertex] = 0;
    heap_push(heap_nodes, heap_dists, &heap_size, start_vertex, 0);

    while (heap_size > 0) {
        int edge_idx;

        popped_node = heap_pop(heap_nodes, heap_dists, &heap_size, &popped_dist);
        if (visited[popped_node]) {
            continue;
        }
        visited[popped_node] = 1u;
        if (popped_dist != dist[popped_node]) {
            continue;
        }

        edge_idx = head[popped_node];
        while (edge_idx != -1) {
            int next_node;
            int weight;
            int new_dist;

            next_node = edges[edge_idx].to;
            weight = edges[edge_idx].weight;
            new_dist = dist[popped_node] + weight;

            if (new_dist < dist[next_node]) {
                dist[next_node] = new_dist;
                heap_push(heap_nodes, heap_dists, &heap_size, next_node, new_dist);
            }
            edge_idx = edges[edge_idx].next;
        }
    }
}

int compare_int_asc(const void* lhs, const void* rhs)
{
    int a;
    int b;

    a = *(const int*)lhs;
    b = *(const int*)rhs;

    if (a < b) {
        return -1;
    } else if (a > b) {
        return 1;
    }
    return 0;
}

int main(void)
{
    int case_count;
    int i;

    scanf("%d", &case_count);

    for (i = 0; i < case_count; ++i) {
        int vertex_count;
        int edge_count_input;
        int candidate_count;

        int start_vertex;
        int special_g;
        int special_h;

        edge_t* pa_edges;
        int* pa_head;

        int* dist_from_start;
        int* dist_from_g;
        int* dist_from_h;

        unsigned char* pa_visited;
        int* heap_nodes;
        int* heap_dists;

        int* pa_candidates;

        int edge_count;
        int j;
        int a;
        int b;
        int d;
        int gh_weight;

        int printed;

        scanf("%d %d %d", &vertex_count, &edge_count_input, &candidate_count);
        scanf("%d %d %d", &start_vertex, &special_g, &special_h);

        pa_head = (int*)malloc((size_t)(vertex_count + 1) * sizeof(int));
        pa_edges = (edge_t*)malloc((size_t)(edge_count_input * 2) * sizeof(edge_t));
        dist_from_start = (int*)malloc((size_t)(vertex_count + 1) * sizeof(int));
        dist_from_g = (int*)malloc((size_t)(vertex_count + 1) * sizeof(int));
        dist_from_h = (int*)malloc((size_t)(vertex_count + 1) * sizeof(int));
        pa_visited = (unsigned char*)malloc((size_t)(vertex_count + 1) * sizeof(unsigned char));
        heap_nodes = (int*)malloc((size_t)(edge_count_input + vertex_count + 5) * sizeof(int));
        heap_dists = (int*)malloc((size_t)(edge_count_input + vertex_count + 5) * sizeof(int));
        pa_candidates = (int*)malloc((size_t)candidate_count * sizeof(int));

        for (j = 1; j <= vertex_count; ++j) {
            pa_head[j] = -1;
        }
        edge_count = 0;
        gh_weight = -1;

        for (j = 0; j < edge_count_input; ++j) {
            scanf("%d %d %d", &a, &b, &d);
            add_edge(a, b, d, pa_edges, pa_head, &edge_count);
            add_edge(b, a, d, pa_edges, pa_head, &edge_count);
            if ((a == special_g && b == special_h) || (a == special_h && b == special_g)) {
                gh_weight = d;
            }
        }

        for (j = 0; j < candidate_count; ++j) {
            scanf("%d", &pa_candidates[j]);
        }
        qsort(pa_candidates, (size_t)candidate_count, sizeof(int), compare_int_asc);

        dijkstra(start_vertex, vertex_count, pa_head, pa_edges, dist_from_start, pa_visited, heap_nodes, heap_dists);
        dijkstra(special_g, vertex_count, pa_head, pa_edges, dist_from_g, pa_visited, heap_nodes, heap_dists);
        dijkstra(special_h, vertex_count, pa_head, pa_edges, dist_from_h, pa_visited, heap_nodes, heap_dists);

        printed = 0;
        for (j = 0; j < candidate_count; ++j) {
            int dest;
            int passes_gh;
            int len_via_g_then_h;
            int len_via_h_then_g;

            dest = pa_candidates[j];
            passes_gh = 0;

            if (dist_from_start[special_g] != 0x3f3f3f3f && dist_from_h[dest] != 0x3f3f3f3f) {
                len_via_g_then_h = dist_from_start[special_g] + gh_weight + dist_from_h[dest];
                if (len_via_g_then_h == dist_from_start[dest]) {
                    passes_gh = 1;
                }
            }
            if (!passes_gh) {
                if (dist_from_start[special_h] != 0x3f3f3f3f && dist_from_g[dest] != 0x3f3f3f3f) {
                    len_via_h_then_g = dist_from_start[special_h] + gh_weight + dist_from_g[dest];
                    if (len_via_h_then_g == dist_from_start[dest]) {
                        passes_gh = 1;
                    }
                }
            }

            if (passes_gh) {
                if (printed) {
                    printf(" ");
                }
                printf("%d", dest);
                printed = 1;
            }
        }
        printf("\n");

        free(pa_head);
        free(pa_edges);
        free(dist_from_start);
        free(dist_from_g);
        free(dist_from_h);
        free(pa_visited);
        free(heap_nodes);
        free(heap_dists);
        free(pa_candidates);
    }

    return 0;
}
