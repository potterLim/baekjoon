#include <stdio.h>

int main(void)
{
    int i;
    int count_take;
    int count_getoff;
    int count_person = 0;
    int max_count_person = 0;

    for (i = 0; i < 4; ++i) {
        scanf("%d %d", &count_getoff, &count_take);
        count_person -= count_getoff;
        count_person += count_take;
        if (max_count_person < count_person) {
            max_count_person = count_person;
        }
    }
    printf("%d\n", max_count_person);

    return 0;
}
