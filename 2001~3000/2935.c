#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
    char num1_str[101];
    char num2_str[101];
    char op[2];
    char* result_str = NULL;
    int len_num1;
    int len_num2;
    int len_result;
    int i;

    scanf("%s", num1_str);
    scanf("%s", &op);
    scanf("%s", num2_str);

    len_num1 = strlen(num1_str);
    len_num2 = strlen(num2_str);

    switch (op[0]) {
    case '+':
        if (len_num1 > len_num2) {
            result_str = malloc(sizeof(char) * (len_num1 + 1));
            result_str[0] = 1 + '0';
            for (i = 1; i < len_num1; ++i) {
                result_str[i] = 0 + '0';

                if (i == len_num1 - len_num2) {
                    result_str[i] = 1 + '0';
                }
            }

            result_str[len_num1] = '\0';
        }
        else  if (len_num1 < len_num2) {
            result_str = malloc(sizeof(int) * (len_num2 + 1));
            result_str[0] = 1 + '0';
            for (i = 1; i < len_num2; ++i) {
                result_str[i] = 0 + '0';

                if (i == len_num2 - len_num1) {
                    result_str[i] = 1 + '0';
                }
            }

            result_str[len_num2] = '\0';
        }
        else {
            result_str = malloc(sizeof(char) * (len_num1));
            result_str[0] = 2 + '0';
            for (i = 1; i < len_num1; ++i) {
                result_str[i] = 0 + '0';
            }

            result_str[len_num1] = '\0';
        }

        len_result = strlen(result_str);
        break;
    case '*':
        result_str = malloc(sizeof(char) * (len_num1 - 1 + len_num2 - 1 + 1));
        len_result = len_num1 - 1 + len_num2 - 1 + 1;
        result_str[0] = 1 + '0';
        for (i = 1; i < len_result; ++i) {
            result_str[i] = 0 + '0';
        }
        break;
    default:
        printf("Something is wrong!!\n");
        return 0;
    }

    for (i = 0; i < len_result; ++i) {
        printf("%c", result_str[i]);
    }
    free(result_str);
    return 0;
}
