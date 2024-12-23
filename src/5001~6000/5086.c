#include <stdio.h>

void check_relationship(int a, int b) 
{
    if (b % a == 0) {
        printf("factor\n");
    } else if (a % b == 0) {
        printf("multiple\n");
    } else {
        printf("neither\n");
    }
}

int main(void) 
{
    int a;
    int b;

    while (1) {
        scanf("%d %d", &a, &b);
        if (a == 0 && b == 0) {
            break;
        }
        check_relationship(a, b);
    }

    return 0;
}
