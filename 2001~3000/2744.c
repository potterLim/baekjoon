#include <stdio.h>
#include <string.h>

#define MAX_STR_LENGTH 101

int main(void) {
    char str[MAX_STR_LENGTH];
    int i;
    int str_length;

    scanf("%s", str);
    str_length = strlen(str);

    for (i = 0; i < str_length; ++i) {
        str[i] = str[i] ^ 32;
    }

    printf("%s\n", str);
}
