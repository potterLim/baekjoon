#include <stdio.h>

int main(void)
{
    int count_coin;
    int price;

    scanf("%d %d", &count_coin, &price);
    if (count_coin * 100 >= price) {
        printf("Yes\n");
    } else {
        printf("No\n");
    }

    return 0;
}
