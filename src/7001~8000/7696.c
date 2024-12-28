#include <stdio.h>

#define MAX_UNIQUE_NUMBERS 1000000

int has_repeated_digits(int number) 
{
    int digit_seen[10] = {0};
    int digit_current;

    while (number > 0) {
        digit_current = number % 10;

        if (digit_seen[digit_current] == 1) {
            return 1;
        }

        digit_seen[digit_current] = 1;
        number /= 10;
    }
    return 0;
}

void precompute_unique_numbers(int* numbers_unique, size_t limit) 
{
    size_t index_current = 0;
    int number_candidate = 1;

    while (index_current < limit) {
        if (has_repeated_digits(number_candidate) == 0) {
            numbers_unique[index_current] = number_candidate;
            index_current++;
        }
        
        number_candidate++;
    }
}

int main(void) 
{
    static int numbers_unique[MAX_UNIQUE_NUMBERS];
    int count;

    precompute_unique_numbers(numbers_unique, MAX_UNIQUE_NUMBERS);

    while (1) {
        scanf("%d", &count);

        if (count == 0) {
            break;
        }

        printf("%d\n", numbers_unique[count - 1]);
    }

    return 0;
}
