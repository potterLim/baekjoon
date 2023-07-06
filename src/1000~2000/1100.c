#include <stdio.h>

int main(void) 
{
    char board[9][9];
    int count = 0;
    int i;
    int j;
  
    for (i = 0; i < 8; i++) {
        scanf("%s", board[i]);
    }

    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            if ((i + j) % 2 == 0 && board[i][j] == 'F')
            count++;
        }
    }

    printf("%d", count);
    return 0;
}
