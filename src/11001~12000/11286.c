#include <stdio.h>
#include <stdlib.h>

#define SUCCESS (0)
#define FAILURE (1)

static int abs_int(const int v);
static int compare_abs(const int a, const int b);
static void swap_int(int* const a, int* const b);
static void heap_push(int* const pa_heap, int* const out_size, const int value);
static int heap_pop_min_abs(int* const pa_heap, int* const out_size);

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
            int result = heap_pop_min_abs(pa_heap, &heap_size);
            printf("%d\n", result);
        }
    }

    free(pa_heap);
    pa_heap = NULL;

    return SUCCESS;
}

static int abs_int(const int v)
{
    if (v < 0) {
        return -v;
    }
    return v;
}

static int compare_abs(const int a, const int b)
{
    int aa;
    int bb;

    aa = abs_int(a);
    bb = abs_int(b);

    if (aa < bb) {
        return -1;
    }
    if (aa > bb) {
        return 1;
    }
    if (a < b) {
        return -1;
    }
    if (a > b) {
        return 1;
    }
    return 0;
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
    int parent;

    *out_size = *out_size + 1;
    index = *out_size;
    pa_heap[index] = value;

    while (index > 1) {
        parent = index / 2;
        if (compare_abs(pa_heap[parent], pa_heap[index]) <= 0) {
            break;
        }
        swap_int(&pa_heap[parent], &pa_heap[index]);
        index = parent;
    }
}

static int heap_pop_min_abs(int* const pa_heap, int* const out_size)
{
    int last_index;
    int index;
    int left;
    int right;
    int better_child;
    int min_value;

    if (*out_size == 0) {
        return 0;
    }

    min_value = pa_heap[1];
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

        if (right <= last_index && compare_abs(pa_heap[right], pa_heap[left]) < 0) {
            better_child = right;
        } else {
            better_child = left;
        }

        if (compare_abs(pa_heap[index], pa_heap[better_child]) <= 0) {
            break;
        }

        swap_int(&pa_heap[index], &pa_heap[better_child]);
        index = better_child;
    }

    return min_value;
}
