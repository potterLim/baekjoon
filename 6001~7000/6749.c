#include <stdio.h>

int main(void)
{
    int age[3];
    int i;

    for (i = 0; i < 2; ++i) {
        scanf("%d", &age[i]);
    }
    age[2] = age[1] + (age[1] - age[0]);

    printf("%d\n", age[2]);
    return 0;
}
