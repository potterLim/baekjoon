#include <stdio.h>
#include <string.h>

#define MAX_OP_LEN (100000)
#define MAX_BUF_LEN (400005)
#define MAX_N (100000)

static int numbers[MAX_N];
static char operations[MAX_OP_LEN + 5];
static char buffer[MAX_BUF_LEN];

static int parse_array_into(int* out, int expected_count, const char* text)
{
    int i;
    int len;
    int count;
    int value;
    int in_number;

    len = (int)strlen(text);
    count = 0;
    value = 0;
    in_number = 0;

    for (i = 0; i < len; ++i) {
        char ch = text[i];

        if (ch >= '0' && ch <= '9') {
            value = value * 10 + (ch - '0');
            in_number = 1;
        } else {
            if (in_number) {
                if (count < expected_count) {
                    out[count] = value;
                }
                ++count;
                value = 0;
                in_number = 0;
            }
        }
    }

    if (in_number) {
        if (count < expected_count) {
            out[count] = value;
        }
        ++count;
    }

    if (count != expected_count) {
        return 0;
    }
    return 1;
}

static void print_slice(const int* arr, int left, int right, int is_reversed)
{
    int i;

    printf("[");
    if (left <= right) {
        if (is_reversed == 0) {
            for (i = left; i <= right; ++i) {
                printf("%d", arr[i]);
                if (i != right) {
                    printf(",");
                }
            }
        } else {
            for (i = right; i >= left; --i) {
                printf("%d", arr[i]);
                if (i != left) {
                    printf(",");
                }
            }
        }
    }
    printf("]\n");
}

int main(void)
{
    int test_count;
    int t;

    scanf("%d", &test_count);

    for (t = 0; t < test_count; ++t) {
        int element_count;
        int i;
        int left_index;
        int right_index;
        int is_reversed;
        int has_error;

        scanf("%s", operations);
        scanf("%d", &element_count);
        scanf("%s", buffer);

        if (element_count > 0) {
            if (!parse_array_into(numbers, element_count, buffer)) {
                printf("error\n");
                continue;
            }
        }

        left_index = 0;
        right_index = element_count - 1;
        is_reversed = 0;
        has_error = 0;

        for (i = 0; operations[i] != '\0'; ++i) {
            char op = operations[i];

            if (op == 'R') {
                is_reversed = 1 - is_reversed;
            } else if (op == 'D') {
                if (left_index > right_index) {
                    has_error = 1;
                    break;
                }
                if (is_reversed == 0) {
                    ++left_index;
                } else {
                    --right_index;
                }
            }
        }

        if (has_error != 0) {
            printf("error\n");
        } else {
            print_slice(numbers, left_index, right_index, is_reversed);
        }
    }

    return 0;
}
