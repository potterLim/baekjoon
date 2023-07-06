#include <stdio.h>
#include <string.h>

int main(void)
{
    int i;
    int j;
    int n;
    int count = 0;
    char bracket[64];
    scanf("%d", &n);
    for (i = 0; i < n; ++i) {
        count = 0;
        scanf("%s", bracket);
        for (j = 0; j < strlen(bracket); ++j) {
            if (bracket[j] == '(') {
                count ++;
            } else {
                count --;
            }

            if (count < 0) {
                printf("NO\n");
                break;
            }
        }

        if (j == strlen(bracket)) {
            if (count == 0) {
                printf("YES\n");
            } else {
                printf("NO\n");
            }
        }
    }
}
