#include <stdio.h>
#include <stdlib.h>

static int can_serve_in_order(const int* order, int n)
{
    int* stack_arr;
    int top;
    int expected;
    int i;

    stack_arr = (int*)malloc(sizeof(int) * n);
    if (stack_arr == NULL) {
        return 0;
    }

    top = -1;
    expected = 1;

    for (i = 0; i < n; ++i) {
        int x;
        x = order[i];

        while (top >= 0 && stack_arr[top] == expected) {
            --top;
            ++expected;
        }

        if (x == expected) {
            ++expected;
        } else {
            stack_arr[++top] = x;
        }
    }

    while (top >= 0 && stack_arr[top] == expected) {
        --top;
        ++expected;
    }

    free(stack_arr);
    return expected == n + 1;
}

int main(void)
{
    int n;
    int* order;
    int i;

    scanf("%d", &n);
    order = (int*)malloc(sizeof(int) * n);
    if (order == NULL) {
        return 0;
    }

    for (i = 0; i < n; ++i) {
        scanf("%d", &order[i]);
    }

    if (can_serve_in_order(order, n)) {
        printf("Nice\n");
    } else {
        printf("Sad\n");
    }

    free(order);
    return 0;
}
