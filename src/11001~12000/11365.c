#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[501];
    size_t length_str;
    int i;
    
    while (1) {
        fgets(str, sizeof(str), stdin);
        str[strcspn(str, "\n")] = '\0';

        if (strcmp(str, "END") == 0) {
            break;
        }
        length_str = strlen(str);

        for (i = length_str - 1; i >= 0; --i) {
            printf("%c", str[i]);
        }

        printf("\n");
    }
    
    return 0;
}
