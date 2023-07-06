#include <stdio.h>
#include <string.h>

int main(void)
{
    char num_str[5];
    int i;
    int len_str;

    while (1) {
        scanf("%s", num_str);
        len_str = strlen(num_str);
        if(num_str[0] == '0') {
            break;
        }

        for (i = 0; i <= len_str / 2 - 1; ++i) {
            if (num_str[i] != num_str[len_str - i - 1]) {
                printf("no\n");
                break;
            }
        }
        if (i == len_str / 2) {
            printf("yes\n");
        }
    }
}
