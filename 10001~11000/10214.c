#include <stdio.h>

int main(void)
{
    int total_score_yonsei = 0;
    int total_score_korea = 0;
    int score_yonsei;
    int score_korea;
    int T;
    int i;
    int j;

    scanf("%d", &T);
    for (i = 0; i < T; ++i) {
        for (j = 0; j < 9; ++j) {
            scanf("%d %d", &score_yonsei, &score_korea);
            total_score_yonsei += score_yonsei;
            total_score_korea += score_korea;
        }

        if (total_score_yonsei > total_score_korea) {
            printf("Yonsei\n");
        } else if (total_score_korea > total_score_yonsei) {
            printf("Korea\n");
        } else {
            printf("Draw\n");
        }

        total_score_yonsei = 0;
        total_score_yonsei = 0;
    }

    return 0;
}
