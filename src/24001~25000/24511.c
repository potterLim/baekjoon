#include <stdio.h>

#define MAX_N 100000

static long deque_arr[MAX_N];
static int head_idx;
static int tail_idx;
static int dq_size;
static int dq_cap;

static void init_deque(int cap)
{
    head_idx = 0;
    tail_idx = 0;
    dq_size = 0;
    dq_cap = cap;
}

static void push_front(long v)
{
    head_idx = (head_idx - 1 + dq_cap) % dq_cap;
    deque_arr[head_idx] = v;
    if (dq_size < dq_cap) {
        ++dq_size;
    }
}

static long pop_back(void)
{
    long v;
    tail_idx = (tail_idx - 1 + dq_cap) % dq_cap;
    v = deque_arr[tail_idx];
    --dq_size;
    return v;
}

int main(void)
{
    int n;
    static int a[MAX_N];
    static long b[MAX_N];
    int i;
    int qcnt;
    int first_out;
    int m;

    scanf("%d", &n);
    for (i = 0; i < n; ++i) {
        scanf("%d", &a[i]);
    }
    for (i = 0; i < n; ++i) {
        scanf("%ld", &b[i]);
    }

    qcnt = 0;
    for (i = 0; i < n; ++i) {
        if (a[i] == 0) {
            ++qcnt;
        }
    }

    if (qcnt > 0) {
        init_deque(qcnt);
        for (i = 0; i < n; ++i) {
            if (a[i] == 0) {
                deque_arr[tail_idx++] = b[i];
                if (tail_idx == dq_cap) {
                    tail_idx = 0;
                }
                ++dq_size;
            }
        }
    }

    scanf("%d", &m);
    first_out = 1;
    for (i = 0; i < m; ++i) {
        long c;
        long outv;

        scanf("%ld", &c);

        if (qcnt == 0) {
            outv = c;
        } else {
            outv = pop_back();
            push_front(c);
        }

        if (!first_out) {
            printf(" ");
        }
        printf("%ld", outv);
        first_out = 0;
    }
    printf("\n");
    return 0;
}
