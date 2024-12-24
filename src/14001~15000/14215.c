#include <stdio.h>

int main(void)
{
    int len_stick1;
    int len_stick2;
    int len_stick3;

    int len_max_stick;
    int len_non_max_sticks;

    scanf("%d %d %d", &len_stick1, &len_stick2, &len_stick3);

    len_max_stick = len_stick1;
    len_non_max_sticks = len_stick2 + len_stick3;

    if (len_stick2 > len_max_stick) {
        len_max_stick = len_stick2;
        len_non_max_sticks = len_stick1 + len_stick3;
    }

    if (len_stick3 > len_max_stick) {
        len_max_stick = len_stick3;
        len_non_max_sticks = len_stick1 + len_stick2;
    }

    if (len_max_stick >= len_non_max_sticks) {
        printf("%d", len_non_max_sticks * 2 - 1);
    } else {
        printf("%d", len_max_stick + len_non_max_sticks);
    }

    return 0;
}
