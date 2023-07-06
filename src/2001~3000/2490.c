#include <assert.h>
#include <stdio.h>

int main(void)
{
    int yut;
    int i;
    int j;

    for (i = 0; i < 3; ++i) {
        int sum = 0;
        for (j = 0; j < 4; ++j) {
            scanf("%d", &yut);
            sum += yut;
        }

        switch (sum) {
            case 0:
                printf("D\n");
                break;
            case 1:
                printf("C\n");
                break;
            case 2:
                printf("B\n");
                break;
            case 3:
                printf("A\n");
                break;
            case 4:
                printf("E\n");
                break;
            default :
                assert(0);
                break;
        }
    }

    return 0;
}