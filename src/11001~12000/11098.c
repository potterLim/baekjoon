#include <stdio.h>
#include <string.h>

int main(void)
{
    int cases;
    int option;
    int i;
    int j;
    int max_price;
    int price;
    char max_price_name[21];
    char name[21];

    scanf("%d", &cases);

    for (i = 0; i < cases; ++i) {
        max_price = -1;
        price = -1;
        scanf("%d", &option);
        for (j = 0; j < option; ++j) {
            scanf("%d %s", &price, name);
            if (price > max_price) {
                max_price = price;
                strcpy(max_price_name, name);
            }
        }

        printf("%s\n", max_price_name);
    }
}
