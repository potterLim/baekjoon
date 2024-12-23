#include <stdio.h>

int calculate_points(int n) 
{
    int side_length;
    int total_points;

    side_length = (1 << n) + 1;
    total_points = side_length * side_length;

    return total_points;
}

int main(void) 
{
    int n;
    int result;

    scanf("%d", &n);
    result = calculate_points(n);
    printf("%d\n", result);

    return 0;
}
