#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <string.h>

int main(void)
{
    int i;
    char num1_str[10002] = { 0, };
    char num2_str[10002] = { 0, };
    char num3_str[10003] = { 0, };
    int diff;
    int num1_len;
    int num2_len;
    int is_start = 0;

    scanf("%s %s", num1_str, num2_str);

    num1_len = strlen(num1_str);
    num2_len = strlen(num2_str);

    diff = num1_len - num2_len;
    if (diff == 0) {
        i = num1_len;
    } else if (diff > 0) {
        for (i = num2_len; i >= 0; --i) {
            num2_str[i + diff] = num2_str[i];
            num2_str[i] = 0;
        }

        i = num1_len;
    } else {
        for (i = num1_len; i >= 0; --i) {
            num1_str[i - diff] = num1_str[i];
            num1_str[i] = 0;
        }

        i = num2_len;
    }

    for (i = i - 1; i >= 0; --i) {
        if (num1_str[i] == '\0') {
            if (num3_str[i + 1] == '\0') {
                num3_str[i + 1] = num2_str[i];
            } else {
                num3_str[i + 1] = num3_str[i + 1] - 48 + num2_str[i] - 48 + 48;
            }
        } else if (num2_str[i] == '\0') {
            if (num3_str[i + 1] == '\0') {
                num3_str[i + 1] = num1_str[i];
            } else {
                num3_str[i + 1] = num3_str[i + 1] - 48 + num1_str[i] - 48 + 48;
            }
        } else {
            if (num3_str[i + 1] == '\0') {
                num3_str[i + 1] = num1_str[i] - 48 + num2_str[i] - 48 + 48; 
            } else {
                num3_str[i + 1] = num3_str[i + 1] - 48 + num1_str[i] - 48 + num2_str[i] - 48 + 48; 
            }
        }
        if (num3_str[i + 1] - 48 >= 10) {
            num3_str[i + 1] -= 10;

            if(num3_str[i] == '\0') {
                num3_str[i] = 1 + 48;
            } else {
                num3_str[i] = num3_str[i] - 48 + 1 + 48;
            }
        }
    }

    if (num3_str[0] == '\0') {
        num3_str[0] = 48;
    }

    i = 0;
    while (1) {

        if (num3_str[i] == '\0') {
            break;
        }
        
        if (is_start == 0 && num3_str[i] == 48) {
            i++;
            continue;
        }

        if (is_start == 0 && num3_str[i] != 48) {
            is_start = 1;
        }

        printf("%c", num3_str[i++]);
    }

    return 0;
}
