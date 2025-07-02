#include <stdio.h>

#define MAX_PLAYER_COUNT (200)
#define GAME_COUNT (3)
#define MAX_CARD_NUMBER (101)

int main(void)
{
    int player_count;
    int cards[MAX_PLAYER_COUNT][GAME_COUNT];
    int total_scores[MAX_PLAYER_COUNT];
    int i;
    int j;
    int game_index;

    for (i = 0; i < MAX_PLAYER_COUNT; ++i) {
        total_scores[i] = 0;
    }

    scanf("%d", &player_count);

    for (i = 0; i < player_count; ++i) {
        for (j = 0; j < GAME_COUNT; ++j) {
            scanf("%d", &cards[i][j]);
        }
    }

    for (game_index = 0; game_index < GAME_COUNT; ++game_index) {
        int frequency[MAX_CARD_NUMBER];
        int card_number;

        for (i = 0; i < MAX_CARD_NUMBER; ++i) {
            frequency[i] = 0;
        }

        for (i = 0; i < player_count; ++i) {
            card_number = cards[i][game_index];
            ++frequency[card_number];
        }

        for (i = 0; i < player_count; ++i) {
            card_number = cards[i][game_index];
            if (frequency[card_number] == 1) {
                total_scores[i] += card_number;
            }
        }
    }

    for (i = 0; i < player_count; ++i) {
        printf("%d\n", total_scores[i]);
    }

    return 0;
}
