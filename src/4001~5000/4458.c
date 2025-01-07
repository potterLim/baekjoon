#include <stddef.h>
#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[32];
    size_t count_str;
    size_t i;

    scanf("%d", &count_str);
    getchar();

    for (i = 0; i < count_str; ++i) {
        fgets(str, sizeof(str), stdin);

        str[strlen(str) - 1] = '\0';
        str[0] = str[0] & ~(1 << 5);
        
        printf("%s\n", str);
    }

    return 0;
}
