#include <stdio.h>
#include <string.h>

int main(void)
{
    int N;
    int i;
    char num_str[61];

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%s", num_str);
        if ((num_str[strlen(num_str) - 1] - '0') % 2 == 0) {
            printf("even\n");
        } else {
            printf("odd\n");
        }
    }

    return 0;
}
