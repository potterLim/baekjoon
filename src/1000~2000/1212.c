#include <stdio.h>
#include <string.h>

int main(void)
{
    char base8[333335];
    int len;
    int num;
    int i;
    scanf("%s", base8);

    len = strlen(base8);
    
    if (base8[0] - 48 == 0 && base8[1] == '\0') {
        printf("0");
        return 0;
    }

    for(i = 0; i < len; ++i) {
        switch (base8[i] - 48) {
        case 0:
            if (i == 0) {
                break;    
            }
            printf("000");
            break;
        case 1:
            if (i == 0) {
                printf("1");
                break;    
            }
            printf("001");
            break;
        case 2:
            if (i == 0) {
                printf("10");
                break;    
            }
            printf("010");
            break;
        case 3:
            if (i == 0) {
                printf("11");
                break;    
            }
            printf("011");
            break;
        case 4:
            printf("100");
            break;
        case 5:
            printf("101");
            break;
        case 6:
            printf("110");
            break;
        case 7:
            printf("111");
            break;
        default:
            printf("Something is wrong!!\n");
            break;
        }
    }

    return 0;
}
