#include <stdio.h>

int main(void)
{
    int time;
    int is_drink;

    scanf("%d %d", &time, &is_drink);

    if (is_drink == 1 || (time < 12 || time > 16)) {
        printf("280");
    } else {
        printf("320");
    }

    return 0;
}
