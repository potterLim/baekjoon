#include <stdio.h>
#include <stdlib.h>

#define MAX_N (100)

typedef struct wire {
    int a_pos;
    int b_pos;
} wire_t;

static int s_n;
static wire_t s_wires[MAX_N];
static int s_lis_dp[MAX_N];

static int compare_wire_by_a_pos(const void* lhs, const void* rhs)
{
    const wire_t* left_wire;
    const wire_t* right_wire;

    left_wire = (const wire_t*)lhs;
    right_wire = (const wire_t*)rhs;

    if (left_wire->a_pos < right_wire->a_pos) {
        return -1;
    } else if (left_wire->a_pos > right_wire->a_pos) {
        return 1;
    } else {
        return 0;
    }
}

static int max_int(int x, int y)
{
    if (x > y) {
        return x;
    } else {
        return y;
    }
}

int main(void)
{
    int i;
    int j;
    int lis_len;

    scanf("%d", &s_n);

    for (i = 0; i < s_n; ++i) {
        scanf("%d %d", &s_wires[i].a_pos, &s_wires[i].b_pos);
    }

    qsort(s_wires, (size_t)s_n, sizeof(wire_t), compare_wire_by_a_pos);

    lis_len = 0;
    for (i = 0; i < s_n; ++i) {
        s_lis_dp[i] = 1;
        for (j = 0; j < i; ++j) {
            if (s_wires[j].b_pos < s_wires[i].b_pos) {
                s_lis_dp[i] = max_int(s_lis_dp[i], s_lis_dp[j] + 1);
            }
        }
        lis_len = max_int(lis_len, s_lis_dp[i]);
    }

    printf("%d\n", s_n - lis_len);
    return 0;
}
