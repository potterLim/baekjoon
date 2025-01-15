#include <stdio.h>
#include <string.h>

int main(void)
{
    int count_row;
    int count_column;
    char bread_row[11];
    size_t i;
    size_t j;

    scanf("%d %d", &count_row, &count_column);
    for (i = 0; i < count_row; ++i) {
        scanf("%s", bread_row);
        for (j = count_column; j-- > 0;) {
            printf("%c", bread_row[j]);
        }
        printf("\n");
    }

    return 0;
}
