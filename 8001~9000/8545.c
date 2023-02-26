#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[1024];
    int i;

    scanf("%s", str);

    for (i = strlen(str) - 1; i >= 0; --i) {
        printf("%c", str[i]);
    }

    return 0;
}
