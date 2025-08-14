#include <stdio.h>

#define MAX_N 12
#define MAX_LEN 531441

static int s_pow3[MAX_N + 1];
static char s_line[MAX_LEN + 1];

static void build_pow3(void)
{
    int i;
    s_pow3[0] = 1;
    for (i = 1; i <= MAX_N; ++i) {
        s_pow3[i] = s_pow3[i - 1] * 3;
    }
}

static void fill_line(int length)
{
    int i;
    for (i = 0; i < length; ++i) {
        s_line[i] = '-';
    }
    s_line[length] = '\0';
}

static void carve_cantor_recursive(char* buffer, int start_index, int length)
{
    int third;
    int i;
    if (length == 1) {
        return;
    }
    third = length / 3;
    for (i = start_index + third; i < start_index + 2 * third; ++i) {
        buffer[i] = ' ';
    }
    carve_cantor_recursive(buffer, start_index, third);
    carve_cantor_recursive(buffer, start_index + 2 * third, third);
}

int main(void)
{
    int n;
    build_pow3();
    while (scanf("%d", &n) == 1) {
        int length;
        length = s_pow3[n];
        fill_line(length);
        carve_cantor_recursive(s_line, 0, length);
        printf("%s\n", s_line);
    }
    return 0;
}
