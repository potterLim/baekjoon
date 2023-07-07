#include <stdio.h>

long long calculate_patty_count_recursive(int burger_level, long long x_position, long long total_ingredients[], long long total_patties[]) ;

int main(void) 
{
    int burger_level;
    long long x_position;
    long long total_ingredients[51];
    long long total_patties[51];
    int i;

    scanf("%d %lld", &burger_level, &x_position);
    total_ingredients[0] = 1;
    total_patties[0] = 1;
    for (i = 1; i <= burger_level; i++) {
        total_ingredients[i] = 1 + total_ingredients[i - 1] + 1 + total_ingredients[i - 1] + 1;
        total_patties[i] = total_patties[i - 1] + 1 + total_patties[i - 1];
    }

    printf("%lld\n", calculate_patty_count_recursive(burger_level, x_position, total_ingredients, total_patties));
    return 0;
}

long long calculate_patty_count_recursive(int burger_level, long long x_position, long long total_ingredients[], long long total_patties[]) 
{
    if (burger_level == 0) {
        if (x_position == 1) {
            return 1;
        } else if (x_position == 0) {
            return 0;
        }
    }
    if (x_position == 1) {
        return 0;
    } else if (x_position <= total_ingredients[burger_level - 1] + 1) {
        return calculate_patty_count_recursive(burger_level - 1, x_position - 1, total_ingredients, total_patties);
    } else if (x_position == 1 + total_ingredients[burger_level - 1] + 1) {
        return total_patties[burger_level - 1] + 1;
    } else if (x_position <= total_ingredients[burger_level - 1] + 1 + total_ingredients[burger_level - 1] + 1) {
        return total_patties[burger_level - 1] + 1 + calculate_patty_count_recursive(burger_level - 1, x_position - 1 - total_ingredients[burger_level - 1] - 1, total_ingredients, total_patties);
    } else {
        return total_patties[burger_level - 1] * 2 + 1;
    }
}
