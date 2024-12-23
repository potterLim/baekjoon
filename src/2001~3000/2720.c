#include <stdio.h>

void calculate_coins(int cents, int *quarters, int *dimes, int *nickels, int *pennies) 
{
    *quarters = cents / 25;
    cents = cents % 25;

    *dimes = cents / 10;
    cents = cents % 10;

    *nickels = cents / 5;
    cents = cents % 5;

    *pennies = cents;
}

int main(void) 
{
    int t;
    int i;
    int cents;
    int quarters;
    int dimes;
    int nickels;
    int pennies;

    scanf("%d", &t);

    for (i = 0; i < t; i++) {
        scanf("%d", &cents);
        calculate_coins(cents, &quarters, &dimes, &nickels, &pennies);
        printf("%d %d %d %d\n", quarters, dimes, nickels, pennies);
    }

    return 0;
}
