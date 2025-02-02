#include <stdio.h>

int main(void)
{
    int swap_count;
    int ball_position;
    size_t i;
    
    ball_position = 1;
    
    scanf("%d", &swap_count);

    for (i = 0; i < swap_count; ++i) {
        int cup_x;
        int cup_y;
        
        scanf("%d %d", &cup_x, &cup_y);

        if (ball_position == cup_x) {
            ball_position = cup_y;
        } else if (ball_position == cup_y) {
            ball_position = cup_x;
        }
    }

    printf("%d\n", ball_position);
    return 0;
}
