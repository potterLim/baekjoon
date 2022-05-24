#include <stdio.h>

int main(void)
{
    int fixed_cost;
    int variable_cost;
    int price;
    int break_even_point;

    scanf("%d %d %d", &fixed_cost, &variable_cost, &price);

    if (price <= variable_cost) {
        break_even_point = -1;
    } else {
        break_even_point = fixed_cost / (price - variable_cost) + 1;
    }

    printf("%d\n", break_even_point);
}
