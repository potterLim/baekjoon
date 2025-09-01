#include <stdio.h>
#include <stdlib.h>

#define SUCCESS (0)
#define FAILURE (1)

typedef struct heap_node
{
    int value;
    int row;
    int col;
} heap_node_t;

static void swap_node(heap_node_t* const a, heap_node_t* const b);
static void heap_push(heap_node_t* const pa_heap, int* const out_size, const heap_node_t node);
static heap_node_t heap_pop_max(heap_node_t* const pa_heap, int* const out_size);

int main(void)
{
    int n;
    int* pa_mat;
    heap_node_t* pa_heap;
    int heap_size;
    int i;
    int j;
    heap_node_t node;
    int nth_value;

    scanf("%d", &n);

    pa_mat = (int*)malloc(n * n * sizeof(int));
    if (pa_mat == NULL) {
        return FAILURE;
    }

    for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
            scanf("%d", &pa_mat[i * n + j]);
        }
    }

    pa_heap = (heap_node_t*)malloc((n + 1) * sizeof(heap_node_t));
    if (pa_heap == NULL) {
        free(pa_mat);
        pa_mat = NULL;
        return FAILURE;
    }

    heap_size = 0;
    for (j = 0; j < n; ++j) {
        node.value = pa_mat[(n - 1) * n + j];
        node.row = n - 1;
        node.col = j;
        heap_push(pa_heap, &heap_size, node);
    }

    nth_value = 0;
    for (i = 0; i < n; ++i) {
        heap_node_t top;
        top = heap_pop_max(pa_heap, &heap_size);
        nth_value = top.value;
        if (top.row - 1 >= 0) {
            node.value = pa_mat[(top.row - 1) * n + top.col];
            node.row = top.row - 1;
            node.col = top.col;
            heap_push(pa_heap, &heap_size, node);
        }
    }

    printf("%d\n", nth_value);

    free(pa_heap);
    pa_heap = NULL;
    free(pa_mat);
    pa_mat = NULL;

    return SUCCESS;
}

static void swap_node(heap_node_t* const a, heap_node_t* const b)
{
    heap_node_t tmp;

    tmp = *a;
    *a = *b;
    *b = tmp;
}

static void heap_push(heap_node_t* const pa_heap, int* const out_size, const heap_node_t node)
{
    int index;
    int parent;

    *out_size = *out_size + 1;
    index = *out_size;
    pa_heap[index] = node;

    while (index > 1) {
        parent = index / 2;
        if (pa_heap[parent].value >= pa_heap[index].value) {
            break;
        }
        swap_node(&pa_heap[parent], &pa_heap[index]);
        index = parent;
    }
}

static heap_node_t heap_pop_max(heap_node_t* const pa_heap, int* const out_size)
{
    int last_index;
    int index;
    int left;
    int right;
    int larger_child;
    heap_node_t max_node;

    if (*out_size == 0) {
        heap_node_t zero_node;
        zero_node.value = 0;
        zero_node.row = -1;
        zero_node.col = -1;
        return zero_node;
    }

    max_node = pa_heap[1];
    pa_heap[1] = pa_heap[*out_size];
    *out_size = *out_size - 1;

    index = 1;
    last_index = *out_size;

    while (1) {
        left = index * 2;
        right = left + 1;

        if (left > last_index) {
            break;
        }

        if (right <= last_index && pa_heap[right].value > pa_heap[left].value) {
            larger_child = right;
        } else {
            larger_child = left;
        }

        if (pa_heap[index].value >= pa_heap[larger_child].value) {
            break;
        }

        swap_node(&pa_heap[index], &pa_heap[larger_child]);
        index = larger_child;
    }

    return max_node;
}
