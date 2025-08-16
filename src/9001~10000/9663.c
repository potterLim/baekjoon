#include <stdio.h>

static int s_n;
static unsigned int s_full_mask;
static long s_solution_count;

static void count_arrangements_recursive(unsigned int columns_mask, unsigned int diag_left_mask, unsigned int diag_right_mask)
{
    unsigned int available;
    unsigned int pick;

    if (columns_mask == s_full_mask) {
        ++s_solution_count;
        return;
    }

    available = s_full_mask & ~(columns_mask | diag_left_mask | diag_right_mask);
    while (available != 0U) {
        pick = available & (~available + 1U);
        available -= pick;
        count_arrangements_recursive(columns_mask | pick, (diag_left_mask | pick) << 1, (diag_right_mask | pick) >> 1);
    }
}

int main(void)
{
    scanf("%d", &s_n);
    s_full_mask = (1U << s_n) - 1U;
    s_solution_count = 0;
    count_arrangements_recursive(0U, 0U, 0U);
    printf("%ld\n", s_solution_count);
    return 0;
}
