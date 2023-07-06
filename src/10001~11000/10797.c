#include <stdio.h>

int main(void) 
{
    int day_firstdigit;
    int car_firstdigit;
    int count_violation = 0;
    int i;

    scanf("%d", &day_firstdigit);
    for (i = 0; i < 5; ++i) {
        scanf("%d", &car_firstdigit);

        if (day_firstdigit == car_firstdigit) {
            count_violation++;
        }
    }

    printf("%d\n", count_violation);
    return 0;
}
