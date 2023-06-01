#include <stdio.h>
#include <string.h>

int main(void)
{
    int N;
    int i;
    char str[21];

    scanf("%d", &N);

    for (i = 0; i < N; ++i) {
        scanf("%s", str);

        if (strlen(str) >=6 && strlen(str) <= 9) {
            printf("yes\n");
        } else {
            printf("no\n");
        }
    }
    
    return 0;
}
