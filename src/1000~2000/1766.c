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

void heap_push(int* const heap, int* const p_size, int value)
{
    int i;
    int parent;
    int tmp;

    (*p_size) += 1;
    i = *p_size;
    heap[i] = value;

    while (i > 1) {
        parent = i / 2;
        if (heap[parent] <= heap[i]) {
            break;
        }
        tmp = heap[parent];
        heap[parent] = heap[i];
        heap[i] = tmp;

        i = parent;
    }
}

int heap_pop(int* const heap, int* const p_size)
{
    int ret;
    int i;
    int left;
    int right;
    int smallest;
    int tmp;

    ret = heap[1];
    heap[1] = heap[*p_size];
    (*p_size) -= 1;

    i = 1;
    while (1) {
        left = i * 2;
        right = left + 1;
        smallest = i;

        if (left <= *p_size && heap[left] < heap[smallest]) {
            smallest = left;
        }
        if (right <= *p_size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if (smallest == i) {
            break;
        }

        tmp = heap[i];
        heap[i] = heap[smallest];
        heap[smallest] = tmp;

        i = smallest;
    }

    return ret;
}

int main(void)
{
    int num_problems;
    int num_rules;
    int i;

    edge_t* pa_edges;
    int* pa_head;
    int* pa_indegree;
    int* pa_heap;
    int* pa_order;

    int edge_count;
    int heap_size;
    int printed;

    int prerequisite;
    int dependent;
    int current_problem;
    int edge_index;
    int next_problem;
    int out_count;

    scanf("%d %d", &num_problems, &num_rules);

    pa_edges = (edge_t*)malloc((size_t)num_rules * sizeof(edge_t));
    pa_head = (int*)malloc((size_t)(num_problems + 1) * sizeof(int));
    pa_indegree = (int*)malloc((size_t)(num_problems + 1) * sizeof(int));
    pa_heap = (int*)malloc((size_t)(num_problems + 1) * sizeof(int));
    pa_order = (int*)malloc((size_t)num_problems * sizeof(int));

    for (i = 1; i <= num_problems; ++i) {
        pa_head[i] = -1;
        pa_indegree[i] = 0;
    }
    edge_count = 0;

    for (i = 0; i < num_rules; ++i) {
        scanf("%d %d", &prerequisite, &dependent);
        add_edge(prerequisite, dependent, pa_edges, pa_head, &edge_count);
        pa_indegree[dependent] += 1;
    }

    heap_size = 0;
    for (i = 1; i <= num_problems; ++i) {
        if (pa_indegree[i] == 0) {
            heap_push(pa_heap, &heap_size, i);
        }
    }

    out_count = 0;
    while (heap_size > 0) {
        current_problem = heap_pop(pa_heap, &heap_size);
        pa_order[out_count++] = current_problem;

        edge_index = pa_head[current_problem];
        while (edge_index != -1) {
            next_problem = pa_edges[edge_index].to;
            pa_indegree[next_problem] -= 1;
            if (pa_indegree[next_problem] == 0) {
                heap_push(pa_heap, &heap_size, next_problem);
            }
            edge_index = pa_edges[edge_index].next;
        }
    }

    printed = 0;
    for (i = 0; i < num_problems; ++i) {
        if (printed) {
            printf(" ");
        }
        printf("%d", pa_order[i]);
        printed = 1;
    }
    printf("\n");

    free(pa_edges);
    free(pa_head);
    free(pa_indegree);
    free(pa_heap);
    free(pa_order);

    return 0;
}
