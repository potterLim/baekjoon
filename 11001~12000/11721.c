#include <stdio.h>

int main(void) {
    char str[101];
    int count = 0;

    scanf("%s", str);

    while(str[count] != '\0') {
        printf("%c", str[count++]);
        if (count % 10 == 0) {
            printf("\n");
        }
    }

    return 0;
}
