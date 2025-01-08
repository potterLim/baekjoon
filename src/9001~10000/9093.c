#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* reverse_words_malloc(const char* input_str)
{
    char* pa_reversed_words;
    size_t str_length;
    size_t word_start_idx;
    size_t i;
    size_t j;

    str_length = strlen(input_str);
    pa_reversed_words = (char*)malloc(str_length + 1);
    word_start_idx = 0;

    for (i = 0; i <= str_length; ++i) {
        if (input_str[i] == ' ' || input_str[i] == '\0') {
            for (j = word_start_idx; j < i; ++j) {
                pa_reversed_words[j] = input_str[i - 1 - (j - word_start_idx)];
            }

            if (input_str[i] == ' ') {
                pa_reversed_words[i] = ' ';
            }

            word_start_idx = i + 1;
        }
    }

    pa_reversed_words[str_length] = '\0';

    return pa_reversed_words;
}

int main(void)
{
    char input_buffer[1002];
    size_t num_test_cases;
    size_t i;
    char* pa_result;

    scanf("%d", &num_test_cases);
    getchar();

    for (i = 0; i < num_test_cases; ++i) {
        fgets(input_buffer, sizeof(input_buffer), stdin);
        input_buffer[strlen(input_buffer) - 1] = '\0';
        pa_result = reverse_words_malloc(input_buffer);
        printf("%s\n", pa_result);
        free(pa_result);
        pa_result = NULL;
    }

    return 0;
}
