#include <stdio.h>

int main(void) 
{
    unsigned long x;
    const char* uos = "UOS";

    scanf("%lu", &x);

    if ((x - 1) % 3 == 0) {
        printf("U\n");
    } else if ((x - 1) % 3 == 1) {
        printf("O\n");
    } else {
        printf("S\n");
    }

    return 0;
}
