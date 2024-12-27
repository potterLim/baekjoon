#include <stdio.h>
#include <stdlib.h>

typedef struct piece {
    int length;
    int download_time;
} piece_t;

int main(void) 
{
    int num_pieces;
    piece_t* pieces;
    int total_wait_time;
    int remaining_length;
    size_t i;

    scanf("%d", &num_pieces);

    pieces = (piece_t*)malloc(num_pieces * sizeof(piece_t));
    if (pieces == NULL) {
        return 1;
    }

    for (i = 0; i < num_pieces; ++i) {
        scanf("%d %d", &pieces[i].length, &pieces[i].download_time);
    }

    total_wait_time = 0;
    remaining_length = 0;

    for (i = 0; i < num_pieces; ++i) {
        remaining_length -= pieces[i].download_time;
        if (remaining_length < 0) {
            total_wait_time -= remaining_length;
            remaining_length = 0;
        }
        remaining_length += pieces[i].length;
    }

    printf("%d\n", total_wait_time);

    free(pieces);
    pieces = NULL;

    return 0;
}
