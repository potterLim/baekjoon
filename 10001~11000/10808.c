#include <stdio.h>

int main(void)
{
    int count_alphabets[26] = {0, };
    char str[101];
    int i = 0;

    scanf("%s", str);

    while(str[i] != '\0') {
        count_alphabets[str[i++] - 'a']++;
    }

    for (i = 0; i < 26; ++i) {
        printf("%d ", count_alphabets[i]);
    }
}
