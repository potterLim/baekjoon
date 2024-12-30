#include <stdio.h>
#include <string.h>

#define MAX_STRING_LENGTH 1001

int gCallCount;

int recursion(const char *s, int l, int r) 
{
    gCallCount++;
    if (l >= r) return 1;
    else if (s[l] != s[r]) return 0;
    else return recursion(s, l + 1, r - 1);
}

int isPalindrome(const char *s) 
{
    gCallCount = 0;
    return recursion(s, 0, strlen(s) - 1);
}



int main(void) 
{
    int count;
    char str[MAX_STRING_LENGTH];
    int result;
    size_t i;

    scanf("%d", &count);

    for (i = 0; i < count; i++) {
        scanf("%s", str);
        result = isPalindrome(str);
        printf("%d %d\n", result, gCallCount);
    }

    return 0;
}
