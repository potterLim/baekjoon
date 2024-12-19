#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_LEN 51

int main(void) 
{
    char expression[MAX_LEN];
    int result = 0;
    int tmp = 0;
    int is_negative = 0;
    size_t i;

    scanf("%s", expression);

    for (i = 0; i <= strlen(expression); i++) {
        if (expression[i] == '+' || expression[i] == '-' || expression[i] == '\0') {
            if (is_negative) {
                result -= tmp; // 음수라면 뺌
            } else {
                result += tmp; // 양수라면 더함
            }
            tmp = 0;

            if (expression[i] == '-') {
                is_negative = 1;
            }
        } else {
            tmp = tmp * 10 + (expression[i] - '0');
        }
    }

    printf("%d\n", result);
    return 0;
}
