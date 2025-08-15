#include <stdio.h>
#include <string.h>

#define MAX_N 8
#define MAX_WORD_LEN 20
#define MAX_TOTAL_LEN (MAX_N * MAX_WORD_LEN)

static int s_n;
static char s_words[MAX_N][MAX_WORD_LEN + 1];
static int s_word_len[MAX_N];
static int s_k;

static char s_concat[MAX_TOTAL_LEN + 1];
static int s_concat_len;

static int s_used[MAX_N];
static long s_answer;

static void build_prefix_function(const char* s, int n, int* pi)
{
    int i;
    int j;

    pi[0] = 0;
    j = 0;
    for (i = 1; i < n; ++i) {
        while (j > 0 && s[i] != s[j]) {
            j = pi[j - 1];
        }
        if (s[i] == s[j]) {
            ++j;
        }
        pi[i] = j;
    }
}

static int get_min_period_len(const char* s, int n)
{
    static int pi[MAX_TOTAL_LEN];
    int p;

    build_prefix_function(s, n, pi);
    p = n - pi[n - 1];
    if (n % p == 0) {
        return p;
    }
    return n;
}

static void generate_permutations_recursive(int depth)
{
    int i;

    if (depth == s_n) {
        int min_period;
        int rotations;

        min_period = get_min_period_len(s_concat, s_concat_len);
        rotations = s_concat_len / min_period;
        if (rotations == s_k) {
            ++s_answer;
        }
        return;
    }

    for (i = 0; i < s_n; ++i) {
        if (!s_used[i]) {
            int len;
            len = s_word_len[i];

            memcpy(s_concat + s_concat_len, s_words[i], (size_t)len);
            s_concat_len += len;
            s_concat[s_concat_len] = '\0';

            s_used[i] = 1;
            generate_permutations_recursive(depth + 1);
            s_used[i] = 0;

            s_concat_len -= len;
            s_concat[s_concat_len] = '\0';
        }
    }
}

int main(void)
{
    int i;

    scanf("%d", &s_n);
    for (i = 0; i < s_n; ++i) {
        scanf("%20s", s_words[i]);
        s_word_len[i] = (int)strlen(s_words[i]);
    }
    scanf("%d", &s_k);

    s_concat_len = 0;
    s_concat[0] = '\0';
    s_answer = 0;

    generate_permutations_recursive(0);

    printf("%ld\n", s_answer);
    return 0;
}
