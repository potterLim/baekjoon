#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int city_count;
    long long* road_len;
    long long* price;
    int i;
    long long min_price;
    long long total_cost;

    scanf("%d", &city_count);

    road_len = (long long*)malloc((size_t)(city_count - 1) * sizeof(long long));
    price = (long long*)malloc((size_t)city_count * sizeof(long long));

    for (i = 0; i < city_count - 1; ++i) {
        scanf("%lld", &road_len[i]);
    }
    for (i = 0; i < city_count; ++i) {
        scanf("%lld", &price[i]);
    }

    min_price = price[0];
    total_cost = 0;

    for (i = 0; i < city_count - 1; ++i) {
        if (price[i] < min_price) {
            min_price = price[i];
        }
        total_cost += min_price * road_len[i];
    }

    printf("%lld\n", total_cost);

    free(road_len);
    free(price);
    return 0;
}
