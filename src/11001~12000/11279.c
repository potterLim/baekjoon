#include <stdio.h>
#include <stdlib.h>

#define SUCCESS (0)
#define FAILURE (1)

static void swap_int(int* const a, int* const b);
static void heap_push(int* const pa_heap, int* const out_size, const int value);
static int heap_pop_max(int* const pa_heap, int* const out_size);

int main(void)
{
    int n_ops;
    int* pa_heap;
    int heap_size;
    int i;

    scanf("%d", &n_ops);

    pa_heap = (int*)malloc((n_ops + 1) * sizeof(int));
    if (pa_heap == NULL) {
        return FAILURE;
    }

    heap_size = 0;

    for (i = 0; i < n_ops; ++i) {
        int x;
        scanf("%d", &x);

        if (x != 0) {
            heap_push(pa_heap, &heap_size, x);
        } else {
            int result = heap_pop_max(pa_heap, &heap_size);
            printf("%d\n", result);
        }
    }

    free(pa_heap);
    pa_heap = NULL;

    return SUCCESS;
}

static void swap_int(int* const a, int* const b)
{
    int tmp;

    tmp = *a;
    *a = *b;
    *b = tmp;
}

static void heap_push(int* const pa_heap, int* const out_size, const int value)
{
    int index;

    *out_size = *out_size + 1;
    index = *out_size;
    pa_heap[index] = value;

    while (index > 1) {
        int parent;

        parent = index / 2;
        if (pa_heap[parent] >= pa_heap[index]) {
            break;
        }

        swap_int(&pa_heap[parent], &pa_heap[index]);
        index = parent;
    }
}

static int heap_pop_max(int* const pa_heap, int* const out_size)
{
    int last_index;
    int index;
    int max_value;

    if (*out_size == 0) {
        return 0;
    }

    max_value = pa_heap[1];
    pa_heap[1] = pa_heap[*out_size];
    *out_size = *out_size - 1;

    index = 1;
    last_index = *out_size;

    while (1) {
        int left;
        int right;
        int larger_child;

        left = index * 2;
        right = left + 1;

        if (left > last_index) {
            break;
        }

        if (right <= last_index && pa_heap[right] > pa_heap[left]) {
            larger_child = right;
        } else {
            larger_child = left;
        }

        if (pa_heap[index] >= pa_heap[larger_child]) {
            break;
        }

        swap_int(&pa_heap[index], &pa_heap[larger_child]);
        index = larger_child;
    }

    return max_value;
}
