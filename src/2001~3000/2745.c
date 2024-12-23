#include <stdio.h>
#include <string.h>

#define MAX_LEN 32

int convert_char_to_value(char c) 
{
    if ('0' <= c && c <= '9') {
        return c - '0';
    }
    return c - 'A' + 10;
}

int main(void) 
{
    char n[MAX_LEN + 1];
    int b;
    int len;
    int result;
    int i;
    int value;

    scanf("%s %d", n, &b);

    len = strlen(n);
    result = 0;

    for (i = 0; i < len; i++) {
        value = convert_char_to_value(n[i]);
        result = result * b + value;
    }

    printf("%d\n", result);

    return 0;
}
