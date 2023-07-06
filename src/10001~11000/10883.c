#include <stdio.h>

int main(void)
{
    int count_school;
    int count_remain_apple = 0;
    int count_studnet;
    int count_apple;
    int i;

    scanf("%d", &count_school);

    for (i = 0; i < count_school; ++i) {
        scanf("%d %d", &count_studnet, &count_apple);
        count_remain_apple += count_apple % count_studnet;
    }

    printf("%d\n", count_remain_apple);
    return 0;
}
