#include <stdio.h>

int main(void)
{
    char lastname[256];
    int count_first_char_lastname[26];
    int N;
    int i;
    int count_can_pick = 0;

    for (i = 0; i < 26; ++i) {
        count_first_char_lastname[i] = 0;
    }

    scanf("%d", &N);
    for (i = 0; i < N; ++i) {
        scanf("%s", lastname);
        count_first_char_lastname[lastname[0] - 'a']++;
    }

    for (i = 0; i <26; ++i) {
        if (count_first_char_lastname[i] >= 5) {
            printf("%c", i + 'a');
            count_can_pick++;
        }
    }

    if (count_can_pick == 0) {
        printf("PREDAJA\n");
    }
    return 0;
}
