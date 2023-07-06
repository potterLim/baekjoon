#include <stdio.h>
#include <stdlib.h>

typedef struct build {
    int weight;
    int height;
    int rank;
} build_t;

int main(void)
{
    int i;
    int j;
    int N;
    int ranking = 1;
    build_t* build;

    scanf("%d", &N);
    build = malloc(sizeof(build_t) * N);

    for (i = 0; i < N; ++i) {
        scanf("%d %d", &build[i].weight, &build[i].height);
    }
    for (i = 0; i < N; ++i) {
        for (j = 0; j < N; ++j) {
            if (j == i) {
                continue;
            }

            if (build[j].height > build[i].height && build[j].weight > build[i].weight) {
                ranking++;
            }
        }
        build[i].rank = ranking;
        ranking = 1;
    }

    for (i = 0; i < N; ++i) {
        printf("%d ", build[i].rank);
    }
    free(build);
	return 0;
}
