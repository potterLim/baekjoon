#include <stdio.h>
#define MAX_STR_LENGTH (257)
#define TRUE (1)
#define FALSE (0)

int main(void)
{
    char str[MAX_STR_LENGTH];
    int i;
    int count;

    while (TRUE) {
        /* fgets() function stdin + \n + \0 */
        fgets(str, MAX_STR_LENGTH, stdin);
        str[MAX_STR_LENGTH - 1] = '\0';

        if(str[0] == '#') {
            break;
        }

        count = 0;
        for (i = 0; i < MAX_STR_LENGTH; ++i) {
            if (str[i] == '\0') {
                break;
            }

            if ((str[i] | 32) == 'a' || (str[i] | 32) == 'e' || (str[i] | 32) == 'i' || (str[i] | 32) == 'o' || (str[i] | 32) == 'u') {
                count++;
            }
        }

        printf("%d\n", count);
    }

    return 0;
}
