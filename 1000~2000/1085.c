#include <stdio.h>

int main(void)
{
    int x;
    int y;
    int w;
    int h;
    int min_distance;

    scanf("%d %d %d %d", &x, &y, &w, &h);
    min_distance = x;

    if (min_distance > w - x) {
        min_distance = w - x;
    }

    if (min_distance > y) {
        min_distance = y;
    }

    if (min_distance > h - y) {
        min_distance = h - y;
    }

    printf("%d\n", min_distance);
    return 0;
}
