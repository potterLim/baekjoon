#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[501];
    size_t length_str;
    size_t i;
    
    fgets(str, sizeof(str), stdin);
    str[strcspn(str, "\n")] = '\0';
    length_str = strlen(str);

    for (i = 0 ; i < length_str; ++i) {
        if (str[i] != 'C' && str[i] != 'A' && str[i] != 'M' && str[i] != 'B' && str[i] != 'R' && str[i] != 'I' && str[i] != 'D' && str[i] != 'G' && str[i] != 'E') {
            printf("%c", str[i]);
        }
    }

    printf("\n");
    return 0;
}
