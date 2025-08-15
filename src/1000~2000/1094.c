#include <stdio.h>

static int count_set_bits_unsigned(unsigned int value)
{
    int bit_count;
    bit_count = 0;
    while (value != 0U) {
        bit_count += (value & 1U) ? 1 : 0;
        value >>= 1;
    }
    return bit_count;
}

int main(void)
{
    unsigned int x;
    scanf("%u", &x);
    printf("%d\n", count_set_bits_unsigned(x));
    return 0;
}
