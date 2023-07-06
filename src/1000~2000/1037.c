#include <stdio.h>

int main(void)
{
    int min = 1000000;
    int max = 1;
    int count;
    int result;
    int tmp;
    int i;

    scanf("%d", &count);

    for(i = 0; i < count; i++) {
        scanf("%d", &tmp);
        if (tmp > max) {
            max = tmp;
        }

        if (tmp < min) {
            min = tmp;
        }
    }

    result = max * min;
    printf("%d\n", result);
}
