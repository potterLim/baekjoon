#include <stdio.h>
#include <string.h>

int main(void)
{
    char base2[1000001];
    int len;
    int num;
    int i;
    scanf("%s", base2);

    len = strlen(base2);
    switch (len % 3) {
    case 0:
        num = ((int)base2[0] - 48) * 4 + ((int)base2[1] - 48) * 2 + ((int)base2[2] - 48) * 1;
        i = 3;
        break;
    case 1:
        num = ((int)base2[0] - 48) * 1;
        i = 1;
        break;
    case 2:
        num = ((int)base2[0] - 48) * 2 + ((int)base2[1] - 48) * 1;
        i = 2;
        break;
    default:
        printf("Something is wrong!!\n");
        break;
    }
    printf("%d", num);

    for (i = i; i < len; i+=3) {
        num = ((int)base2[i] - 48) * 4 + ((int)base2[i + 1] - 48) * 2 + ((int)base2[i + 2] - 48) * 1;
        printf("%d", num);
    }

    return 0;
}
