#include <stdio.h>
#include <stdlib.h>

#define LIMIT 100000
#define INF 0x3f3f3f3f

int pop_front(int* const pa_deque, int* const p_head, int cap)
{
    int value;
    value = pa_deque[*p_head];
    *p_head = (*p_head + 1) % cap;
    return value;
}

void push_front(int* const pa_deque, int* const p_head, int* const p_tail, int cap, int value)
{
    *p_head = (*p_head - 1 + cap) % cap;
    pa_deque[*p_head] = value;
    if (*p_head == *p_tail) {
        exit(0);
    }
}

void push_back(int* const pa_deque, int* const p_head, int* const p_tail, int cap, int value)
{
    pa_deque[*p_tail] = value;
    *p_tail = (*p_tail + 1) % cap;
    if (*p_tail == *p_head) {
        exit(0);
    }
}

int main(void)
{
    int start_pos;
    int target_pos;

    int* pa_dist;
    unsigned char* pa_in_queue;
    int* pa_deque;

    int cap;
    int head_index;
    int tail_index;

    int current;

    int next_pos;
    int new_dist;

    int i;

    scanf("%d %d", &start_pos, &target_pos);

    cap = (LIMIT + 1) * 2 + 5;

    pa_dist = (int*)malloc((size_t)(LIMIT + 1) * sizeof(int));
    pa_in_queue = (unsigned char*)malloc((size_t)(LIMIT + 1) * sizeof(unsigned char));
    pa_deque = (int*)malloc((size_t)cap * sizeof(int));

    for (i = 0; i <= LIMIT; ++i) {
        pa_dist[i] = INF;
        pa_in_queue[i] = 0u;
    }

    head_index = 0;
    tail_index = 0;

    pa_dist[start_pos] = 0;
    push_front(pa_deque, &head_index, &tail_index, cap, start_pos);
    pa_in_queue[start_pos] = 1u;

    while (head_index != tail_index) {
        current = pop_front(pa_deque, &head_index, cap);
        pa_in_queue[current] = 0u;
        if (current == target_pos) {
            printf("%d\n", pa_dist[current]);
            free(pa_dist);
            free(pa_in_queue);
            free(pa_deque);
            return 0;
        }

        next_pos = current * 2;
        if (next_pos <= LIMIT) {
            new_dist = pa_dist[current];
            if (new_dist < pa_dist[next_pos]) {
                pa_dist[next_pos] = new_dist;
                if (!pa_in_queue[next_pos]) {
                    push_front(pa_deque, &head_index, &tail_index, cap, next_pos);
                    pa_in_queue[next_pos] = 1u;
                }
            }
        }

        next_pos = current - 1;
        if (next_pos >= 0) {
            new_dist = pa_dist[current] + 1;
            if (new_dist < pa_dist[next_pos]) {
                pa_dist[next_pos] = new_dist;
                if (!pa_in_queue[next_pos]) {
                    push_back(pa_deque, &head_index, &tail_index, cap, next_pos);
                    pa_in_queue[next_pos] = 1u;
                }
            }
        }

        next_pos = current + 1;
        if (next_pos <= LIMIT) {
            new_dist = pa_dist[current] + 1;
            if (new_dist < pa_dist[next_pos]) {
                pa_dist[next_pos] = new_dist;
                if (!pa_in_queue[next_pos]) {
                    push_back(pa_deque, &head_index, &tail_index, cap, next_pos);
                    pa_in_queue[next_pos] = 1u;
                }
            }
        }
    }

    printf("%d\n", pa_dist[target_pos]);

    free(pa_dist);
    free(pa_in_queue);
    free(pa_deque);
    return 0;
}
