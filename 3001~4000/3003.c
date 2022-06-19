#include <stdio.h>

int main(void)
{
    int count_king;
    int count_queen;
    int count_rook;
    int count_bishop;
    int count_knight;
    int count_pawn;

    int short_king;
    int short_queen;
    int short_rook;
    int short_bishop;
    int short_knight;
    int short_pawn;

    scanf("%d %d %d %d %d %d", &count_king, &count_queen, &count_rook, &count_bishop, &count_knight, &count_pawn);
    short_king = 1 - count_king;
    short_queen = 1 - count_queen;
    short_rook = 2 - count_rook;
    short_bishop = 2 - count_bishop;
    short_knight = 2 - count_knight;
    short_pawn = 8 - count_pawn;

    printf("%d %d %d %d %d %d\n", short_king, short_queen, short_rook, short_bishop, short_knight, short_pawn);
    return 0;
}
