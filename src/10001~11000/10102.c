#include <stdio.h>

int main(void)
{
    int count_judge;
    char votes[16];
    int count_A = 0;
    int count_B = 0;
    int i;

    scanf("%d", &count_judge);
    scanf("%s", votes);

    for (i = 0; i < count_judge; ++i) {
        if (votes[i] == 'A') {
            count_A++;
        } else if (votes[i] == 'B') {
            count_B++;
        }
    }

    if (count_A > count_B) {
        printf("A\n");
    } else if (count_B > count_A) {
        printf("B\n");
    } else {
        printf("Tie\n");
    }

    return 0;
}
