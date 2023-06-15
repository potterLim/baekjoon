#include <stdio.h>

int main(void)
{
    int N;
    int prize = 0;
    int i;
    int dices[3];

    scanf("%d", &N);

    for (i = 0; i < N; ++i) {
        scanf("%d %d %d", &dices[0], &dices[1], &dices[2]);

        if (dices[0] == dices[1] && dices[0] == dices[2]) {
            if (10000 + dices[0] * 1000 > prize) {
                prize = 10000 + dices[0] * 1000;
            }
        } else if (dices[0] == dices[1]) {
            if (1000 + dices[0] * 100 > prize) {
                prize = 1000 + dices[0] * 100;
            }
        } else if (dices[0] == dices[2]) {
            if (1000 + dices[0] * 100 > prize) {
                prize = 1000 + dices[0] * 100;
            }
        } else if (dices[1] == dices[2]) {
            if (1000 + dices[1] * 100 > prize) {
                prize = 1000 + dices[1] * 100;
            }
        } else {
            if (dices[0] > dices[1] && dices[0] > dices[2]) {
                if (dices[0] * 100 > prize) {
                    prize = dices[0] * 100;
                }
            } else if (dices[1] > dices[0] && dices[1] > dices[2]) {
                if (dices[1] * 100 > prize) {
                    prize = dices[1] * 100;
                }
            } else {
                if (dices[2] * 100 > prize) {
                    prize = dices[2] * 100;
                }
            }
        }
    }

    printf("%d\n", prize);
    return 0;
}