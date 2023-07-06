#include <stdio.h>
#include <string.h>

int main(void)
{
    int len_emoji;
    char emoji[33];
    int count_colon = 0;
    int count_underbar = 0;
    int difficulty;
    int i;

    scanf("%s", emoji);
    len_emoji = strlen(emoji);
    for (i = 0; i < len_emoji; ++i) {
        if (emoji[i] == ':') {
            count_colon++;
        } else if (emoji[i] == '_') {
            count_underbar++;
        }
    }

    difficulty = len_emoji + count_colon + count_underbar * 5;

    printf("%d", difficulty);
    return 0;
}
