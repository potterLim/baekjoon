#include <stdio.h>

int main(void)
{
    double defense_average;
    double ignore_defense_average;
    double sensible_defense_average;

    scanf("%lf %lf", &defense_average, &ignore_defense_average);

    sensible_defense_average = defense_average - (defense_average * (ignore_defense_average / 100.0));

    if (sensible_defense_average >= 100) {
        printf("0\n");
    } else {
        printf("1\n");
    }

    return 0;
}
