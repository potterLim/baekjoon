#include <stdio.h>
#include <string.h>

int main(void)
{
    int len;
    char str[1000000];
    int count1 = 0;
    int count2 = 0;
    int result_count;
    int i;

    scanf("%s", str);
    len = strlen(str);

    if (str[0] == '0') {
        count1 = 1;
    } else {
        count2 = 1;
    }

    for (i = 1; i < len; ++i) {
        if (str[i] != str[i-1]) {
            if (str[i] == '0') {
                count1++;
            } else {
                count2++;
            }
        }
    }

    result_count = (count1 < count2) ? count1 : count2;
    printf("%d", result_count);
    return 0;
}