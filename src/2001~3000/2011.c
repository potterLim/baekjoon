#include <stdio.h>
#include <string.h>

#define MOD 1000000

int main(void)
{
    char input[5001];
    int dp[5001];
    int length;
    size_t i;

    scanf("%s", input);
    length = strlen(input);

    if (input[0] == '0') {
        printf("0\n");
        return 0;
    }

    dp[0] = 1;
    dp[1] = 1;

    for (i = 2; i <= length; i++) {
        dp[i] = 0;

        if (input[i - 1] >= '1' && input[i - 1] <= '9') {
            dp[i] += dp[i - 1];
            dp[i] %= MOD;
        }

        if (input[i - 2] == '1' || (input[i - 2] == '2' && input[i - 1] <= '6')) {
            dp[i] += dp[i - 2];
            dp[i] %= MOD;
        }
    }

    printf("%d\n", dp[length]);
    return 0;
}
