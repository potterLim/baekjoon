#include <stdio.h>

int main(void) 
{
    int len_p1;
    int len_p2;
    int len_p3;

    while (1) {
        scanf("%d %d %d", &len_p1, &len_p2, &len_p3);

        if (len_p1 == 0 && len_p2 == 0 && len_p3 == 0) {
            break;
        }

        if (len_p1 + len_p2 <= len_p3 || len_p1 + len_p3 <= len_p2 || len_p2 + len_p3 <= len_p1) {
            printf("Invalid\n");
        } else if (len_p1 == len_p2 && len_p2 == len_p3) {
            printf("Equilateral\n");
        } else if (len_p1 == len_p2 || len_p2 == len_p3 || len_p1 == len_p3) {
            printf("Isosceles\n");
        } else {
            printf("Scalene\n");
        }
    }

    return 0;
}
