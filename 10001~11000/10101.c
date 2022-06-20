#include <stdio.h>

int main(void)
{
    int angle[3];
    int i;

    for (i = 0; i < 3; ++i) {
        scanf("%d", &angle[i]);
    }

    if (angle[0] + angle[1] + angle[2] != 180) {
        printf("Error\n");
    } else if (angle[0] == angle[1] && angle[1] == angle[2]) {
        printf("Equilateral\n");
    } else if (angle[0] == angle[1] || angle[0] == angle[2] || angle[1] == angle[2]) {
        printf("Isosceles\n");
    } else {
        printf("Scalene\n");
    }

    return 0;
}
