#include <stdio.h>

#define MAX_N (1000)

int cost[MAX_N + 1];
int vote[MAX_N + 1];
int judge_limit[MAX_N + 1];

int main(void)
{
    int num_games;
    int num_judges;
    int i;
    int j;
    int max_vote;
    int max_index;

    scanf("%d %d", &num_games, &num_judges);

    for (i = 1; i <= num_games; ++i) {
        scanf("%d", &cost[i]);
        vote[i] = 0;
    }

    for (j = 1; j <= num_judges; ++j) {
        scanf("%d", &judge_limit[j]);

        for (i = 1; i <= num_games; ++i) {
            if (cost[i] <= judge_limit[j]) {
                vote[i]++;
                break;
            }
        }
    }

    max_vote = -1;
    max_index = -1;

    for (i = 1; i <= num_games; ++i) {
        if (vote[i] > max_vote) {
            max_vote = vote[i];
            max_index = i;
        }
    }

    printf("%d\n", max_index);

    return 0;
}
