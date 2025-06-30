#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_N (50)
#define MAX_LEN (51)

char words[MAX_N][MAX_LEN];

int compare_words(const void* a, const void* b)
{
    return strcmp((const char*)a, (const char*)b);
}

int is_prefix(const char* prefix, const char* word)
{
    while (*prefix && *word) {
        if (*prefix != *word) {
            return 0;
        }
        ++prefix;
        ++word;
    }
    return *prefix == '\0';
}

int main(void)
{
    int n;
    int i;
    int count;

    scanf("%d", &n);

    for (i = 0; i < n; ++i) {
        scanf("%s", words[i]);
    }

    qsort(words, n, sizeof(words[0]), compare_words);

    count = 0;

    for (i = 0; i < n; ++i) {
        if (i + 1 < n && is_prefix(words[i], words[i + 1])) {
            continue;
        }

        count++;
    }

    printf("%d\n", count);

    return 0;
}
