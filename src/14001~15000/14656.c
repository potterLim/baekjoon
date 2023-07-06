#include <stdio.h>

int main(void)
{
    int i;
    int count_person;
    int num;
    int count_baseball = 0;;

    scanf("%d", &count_person);
    for (i = 1 ; i <= count_person; ++i) {
        scanf("%d", &num);
        if (i != num) {
            count_baseball++;
        }
    }

    printf("%d\n", count_baseball);
    return 0;
}
