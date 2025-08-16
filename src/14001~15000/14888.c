#include <stdio.h>

#define MAX_N 11

static int s_n;
static long s_nums[MAX_N];
static int s_op_count[4];
static long s_min_value;
static long s_max_value;

static long divide_toward_zero(long a, long b)
{
    long r;
    if (a < 0) {
        r = (-a) / b;
        return -r;
    }
    return a / b;
}

static void search_expressions_recursive(int idx, long current)
{
    int i;

    if (idx == s_n) {
        if (current < s_min_value) {
            s_min_value = current;
        }
        if (current > s_max_value) {
            s_max_value = current;
        }
        return;
    }

    for (i = 0; i < 4; ++i) {
        if (s_op_count[i] > 0) {
            long next_val;

            --s_op_count[i];

            if (i == 0) {
                next_val = current + s_nums[idx];
            } else if (i == 1) {
                next_val = current - s_nums[idx];
            } else if (i == 2) {
                next_val = current * s_nums[idx];
            } else {
                next_val = divide_toward_zero(current, s_nums[idx]);
            }

            search_expressions_recursive(idx + 1, next_val);
            ++s_op_count[i];
        }
    }
}

int main(void)
{
    int i;

    scanf("%d", &s_n);
    for (i = 0; i < s_n; ++i) {
        scanf("%ld", &s_nums[i]);
    }
    for (i = 0; i < 4; ++i) {
        scanf("%d", &s_op_count[i]);
    }

    s_min_value = 1000000000L;
    s_max_value = -1000000000L;

    search_expressions_recursive(1, s_nums[0]);

    printf("%ld\n%ld\n", s_max_value, s_min_value);
    return 0;
}
