#include <stdio.h>
#include <string.h>

#define MAX_CMD 2000000

static int queue[MAX_CMD];
static int head_idx;
static int tail_idx;

static void push_queue(int x)
{
    queue[tail_idx++] = x;
}

static int pop_queue(void)
{
    if (head_idx == tail_idx) {
        return -1;
    }
    return queue[head_idx++];
}

static int size_queue(void)
{
    return tail_idx - head_idx;
}

static int empty_queue(void)
{
    return head_idx == tail_idx ? 1 : 0;
}

static int front_queue(void)
{
    if (head_idx == tail_idx) {
        return -1;
    }
    return queue[head_idx];
}

static int back_queue(void)
{
    if (head_idx == tail_idx) {
        return -1;
    }
    return queue[tail_idx - 1];
}

int main(void)
{
    int n;
    int i;
    char cmd[6];

    scanf("%d", &n);
    head_idx = 0;
    tail_idx = 0;

    for (i = 0; i < n; ++i) {
        scanf("%s", cmd);
        if (strcmp(cmd, "push") == 0) {
            int x;
            scanf("%d", &x);
            push_queue(x);
        } else if (strcmp(cmd, "pop") == 0) {
            printf("%d\n", pop_queue());
        } else if (strcmp(cmd, "size") == 0) {
            printf("%d\n", size_queue());
        } else if (strcmp(cmd, "empty") == 0) {
            printf("%d\n", empty_queue());
        } else if (strcmp(cmd, "front") == 0) {
            printf("%d\n", front_queue());
        } else if (strcmp(cmd, "back") == 0) {
            printf("%d\n", back_queue());
        }
    }
    return 0;
}
