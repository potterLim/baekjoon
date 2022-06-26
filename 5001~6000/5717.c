#include <stdio.h>

int main(void)
{
    int count_boyfriend = -1;
    int count_girlfriend = -1;
    int count_totalfriend = -1;

    while(1) {
        scanf("%d %d", &count_boyfriend, &count_girlfriend);
        if (count_boyfriend == 0 && count_girlfriend == 0) {
            break;
        }

        count_totalfriend = count_boyfriend + count_girlfriend;
        printf("%d\n", count_totalfriend);
    }

    return 0;
}
