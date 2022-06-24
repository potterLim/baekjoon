#include <stdio.h>

int main(void)
{
    int count_park_car[101] = {0, };
    int price_A;
    int price_B;
    int price_C;
    int time_in;
    int time_out;
    int total_price_parking = 0;
    int i;
    int j;


    scanf("%d %d %d", &price_A, &price_B, &price_C);

    for (i = 0; i < 3; ++i) {
        scanf("%d %d", &time_in, &time_out);
        for (j = time_in; j < time_out; j++) {
            count_park_car[j]++;
        } 
    }

    for (i = 1; i <= 100; ++i) {
        if (count_park_car[i] == 1) {
            total_price_parking += count_park_car[i] * price_A;
        } else if (count_park_car[i] == 2) {
            total_price_parking += count_park_car[i] * price_B;
        } else if (count_park_car[i] == 3) {
            total_price_parking += count_park_car[i] * price_C;
        }
    }

    printf("%d\n", total_price_parking);
    return 0;
}
