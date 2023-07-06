#include <stdio.h>
#include <string.h>

int main(void)
{
    char bowl[51];
    int height = 10;
    int len;
    int i;
    
    scanf("%s", bowl);
    len = strlen(bowl);

    for (i = 1; i < len; ++i) {
        if (bowl[i] == bowl[i - 1]) {
            height += 5;
        } else {
            height += 10;
        }
    }

    printf("%d\n", height);
}
