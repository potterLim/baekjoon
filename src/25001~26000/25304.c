#include <stdio.h>

int main(void)
{
    int price;
    int count;
    int i;
    int sum = 0;
    int count_product;
    int price_product;
    scanf("%d", &price);
    scanf("%d", &count);
    
    for (i = 0; i < count; ++i) {
        scanf("%d %d", &price_product, &count_product);
        sum += price_product * count_product;
    }

    if (sum == price) {
        printf("Yes\n");
    } else {
        printf("No");
    }

    return 0;
}