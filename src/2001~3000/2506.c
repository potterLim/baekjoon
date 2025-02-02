#include <stdio.h>

int main(void)
{
    int question_count;
    int total_score;
    int previous_correct_count;
    size_t i;
    int is_answer_correct;

    total_score = 0;
    previous_correct_count = 0;

    scanf("%d", &question_count);

    for (i = 0; i < question_count; ++i) {
        scanf("%d", &is_answer_correct);

        if (is_answer_correct == 1) {
            total_score += (is_answer_correct + previous_correct_count);
            previous_correct_count++;
        } else {
            previous_correct_count = 0;
        }
    }

    printf("%d\n", total_score);
    return 0;
}
