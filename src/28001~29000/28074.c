#include <stdio.h>
#include <string.h>

int main(void)
{
    int is_mobis[5] = {0, };
    char str[101];
    size_t i;

    scanf("%s", str);

    for (i = 0; i < strlen(str); ++i) {
        if (str[i] == 'M') {
            is_mobis[0] = 1;
        } else if (str[i] == 'O') {
            is_mobis[1] = 1;
        } else if (str[i] == 'B') {
            is_mobis[2] = 1;
        } else if (str[i] == 'I') {
            is_mobis[3] = 1;
        } else if (str[i] == 'S') {
            is_mobis[4] = 1;
        }
    }

    if (is_mobis[0] == 1 && is_mobis[1] == 1 && is_mobis[2] == 1 && is_mobis[3] == 1 && is_mobis[4] == 1) {
        printf("YES\n");
    } else {
        printf("NO\n");
    }

    return 0;
}
