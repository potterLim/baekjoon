#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[101];
    int i;
    int str_len;
    
    scanf("%s", str);
    str_len = strlen(str);

    for (i = 0; i < str_len  / 2; i++) {
        if (str[i] != str[str_len - (i + 1)]) {
            break;
        }
    }

    if (i == str_len / 2) {
        printf("1\n");
    } else {
        printf("0\n");
    }
}
