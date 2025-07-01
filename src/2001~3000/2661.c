#include <stdio.h>

#define MAX_LEN 81

int target_length;
char sequence[MAX_LEN];
int is_found = 0;

int is_good_sequence(const char* seq, int len)
{
    int i;
    int j;
    for (i = 1; i <= len / 2; ++i) {
        for (j = 0; j < i; ++j) {
            if (seq[len - j - 1] != seq[len - j - 1 - i]) {
                break;
            }
        }
        if (j == i) {
            return 0;
        }
    }
    return 1;
}

void backtrack(int depth)
{
    int i;
    if (is_found) {
        return;
    }

    if (depth == target_length) {
        sequence[depth] = '\0';
        printf("%s\n", sequence);
        is_found = 1;
        return;
    }

    for (i = 1; i <= 3; ++i) {
        sequence[depth] = i + '0';
        if (is_good_sequence(sequence, depth + 1)) {
            backtrack(depth + 1);
        }
        if (is_found) {
            return;
        }
    }
}

int main(void)
{
    scanf("%d", &target_length);

    backtrack(0);

    return 0;
}
