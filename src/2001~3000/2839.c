#include <stdio.h>

int main(void)
{
    int weight;
    int count;
    scanf("%d", &weight);

    count = weight / 5;
    weight = weight - 5 * weight / 5 + weight % 5;
    while(1) {
        if (weight % 3 == 0) {
            count += weight / 3;
            break;
        }
        count--;
        weight += 5;
        if (count < 0) {
            count = -1;
            break;
        }
    }

    printf("%d", count);
    return 0;
}
