#include <stdio.h>
#include <string.h>

#define MAX_LEN (52)

void make_palindrome(char* dst, const char* src)
{
    int len;
    int i;

    len = (int)strlen(src);
    for (i = 0; i < len / 2; ++i) {
        dst[i] = src[i];
        dst[len - 1 - i] = src[i];
    }

    if (len % 2 == 1) {
        dst[len / 2] = src[len / 2];
    }

    dst[len] = '\0';
}

int is_greater(const char* a, const char* b)
{
    int len;
    int i;

    len = (int)strlen(a);
    for (i = 0; i < len; ++i) {
        if (a[i] > b[i]) {
            return 1;
        } else if (a[i] < b[i]) {
            return 0;
        }
    }

    return 0;
}

void increment_middle(char* str)
{
    int len;
    int mid;
    int left;
    int right;
    int carry;
    int i;

    len = (int)strlen(str);
    mid = len / 2;
    carry = 1;

    if (len % 2 == 1) {
        if (str[mid] < '9') {
            str[mid] += 1;
            carry = 0;
        } else {
            str[mid] = '0';
        }
        left = mid - 1;
        right = mid + 1;
    } else {
        left = mid - 1;
        right = mid;
    }

    while (left >= 0 && carry == 1) {
        if (str[left] < '9') {
            str[left] += 1;
            str[right] = str[left];
            carry = 0;
        } else {
            str[left] = '0';
            str[right] = '0';
        }
        --left;
        ++right;
    }

    if (carry == 1) {
        for (i = len; i > 0; --i) {
            str[i] = str[i - 1];
        }
        str[0] = '1';
        str[len + 1] = '\0';
        str[len] = '1';
    } else {
        while (left >= 0) {
            str[right] = str[left];
            --left;
            ++right;
        }
    }
}

int main(void)
{
    char input[MAX_LEN];
    char candidate[MAX_LEN];

    scanf("%s", input);

    make_palindrome(candidate, input);

    if (is_greater(candidate, input)) {
        printf("%s\n", candidate);
    } else {
        strcpy(candidate, input);
        increment_middle(candidate);
        printf("%s\n", candidate);
    }

    return 0;
}
