#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[1001];
    size_t i;
    size_t length_str;

    scanf("%s", str);
    length_str = strlen(str);

    for (i = 0; i < length_str; ++i) {
        str[i] -= 3;
        if (str[i] < 'A') {
            str[i] += 26;
        }
    }

    printf("%s\n", str);
    return 0;
}
