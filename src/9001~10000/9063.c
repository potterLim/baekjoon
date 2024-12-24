#include <stdio.h>

int main(void) 
{
    int n;
    int x;
    int y;
    int min_x = 10000;
    int max_x = -10000;
    int min_y = 10000;
    int max_y = -10000;
    int area;
    size_t i;

    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        scanf("%d %d", &x, &y);

        if (x < min_x) {
            min_x = x;
        }
        if (x > max_x) {
            max_x = x;
        }
        if (y < min_y) {
            min_y = y;
        }
        if (y > max_y) {
            max_y = y;
        }
    }

    area = (max_x - min_x) * (max_y - min_y);
    printf("%d\n", area);

    return 0;
}
