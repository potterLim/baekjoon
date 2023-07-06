#include <stdio.h>
#define MAX_STR_LENGTH (101)
#define TRUE (1)

int main(void)
{
    char str[MAX_STR_LENGTH];
    int len = 0;

    scanf("%s", str);
    while (TRUE) {
        if(str[len] == '\0') {
            break;
        }
        len++;
    }

    printf("%d", len);
}
