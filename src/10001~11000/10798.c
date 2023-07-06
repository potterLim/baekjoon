#include <stdio.h>

int main(void)
{
    int i;
    int j;
    char boards[5][15];
    
    for (i = 0; i < 5; ++i) {
        for (j = 0; j < 15; ++j) {
            boards[i][j] = '\0';
        }
    }
    
    for (i = 0; i < 5; i++) {
        scanf("%s", boards[i]);
    }

    for (i = 0; i < 15; ++i) {
        for (j = 0; j < 5; ++j) {
            if (boards[j][i] != '\0') {
                printf("%c", boards[j][i]);
            }
        }
    }

    return 0;
}
