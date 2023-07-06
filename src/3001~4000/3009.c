#include <stdio.h>

typedef struct coordinate {
    int x;
    int y;
} coordinate_t;

int main(void)
{
    coordinate_t coordinate[3];
    int i;
    int x;
    int y;

    for (i = 0; i < 3; ++i) {
        scanf("%d %d", &coordinate[i].x, &coordinate[i].y);
    }

    if (coordinate[0].x == coordinate[1].x) {
        x = coordinate[2].x;
    } else if (coordinate[0].x == coordinate[2].x) {
        x = coordinate[1].x;
    } else {
        x = coordinate[0].x;
    }

    if (coordinate[0].y == coordinate[1].y) {
        y = coordinate[2].y;
    } else if (coordinate[0].y == coordinate[2].y) {
        y = coordinate[1].y;
    } else {
        y = coordinate[0].y;
    }

    printf("%d %d", x, y);
}
