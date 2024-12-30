#include <stdio.h>
#include <string.h>

#define MAX_STR_LEN 100001

void reverse_str(char* str, size_t idx_start, size_t idx_end)
{
    size_t i;
    char tmp;

    for (i = 0; i < (idx_end - idx_start + 1) / 2; ++i) {
        tmp = str[idx_start + i];
        str[idx_start + i] = str[idx_end - i];
        str[idx_end - i] = tmp;
    }
}

int main(void)
{
    char str[MAX_STR_LEN];
    size_t str_len;
    size_t i;
    int is_bracket = 0;
    size_t idx_start;
    size_t idx_end;

    fgets(str, sizeof(str), stdin);
    str_len = strlen(str);

    for (i = 0; i < str_len; ++i) {
        if (str[i] == '\n') {
            str[i] = '\0';
            str_len = i;
            break;
        }
    }

    idx_start = 0;
    idx_end = str_len - 1;

    for (i = 0; i < str_len; ++i) {
        if (str[i] == ' ') {
            if (i != 0) {
                idx_end = i - 1;
            }
            if (is_bracket == 0) {
                reverse_str(str, idx_start, idx_end);
            }
            idx_start = i + 1;
            

        } else if (str[i] == '<') {
            if (i != 0) {
                idx_end = i - 1;
                reverse_str(str, idx_start, idx_end);
            }
            is_bracket = 1;
            idx_start = i + 1;

        } else if (str[i] == '>') {
            idx_start = i + 1;
            is_bracket = 0;

        } else {
            if (i == str_len - 1) {
                idx_end = i;
                reverse_str(str, idx_start, idx_end);
            }
        }

    }

    printf("%s", str);
    return 0;
}
