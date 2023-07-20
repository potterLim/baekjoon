#include<stdio.h>

int get_count_digits(int num);

int main(void)
{
    int small_num;
    int large_num;
    int lim;
    int count8 = 0;
    int i;

    scanf("%d %d", &small_num, &large_num);
    
    lim = get_count_digits(small_num);
    
    for (i = 0; i < lim; i++) {
        if (small_num % 10 == 8 ) {
            if (large_num - small_num == 0)  { 
                count8++;
            } 
        }
        small_num /= 10;
        large_num /= 10;
    }
    
    printf("%d\n", count8);
    return 0;
}

int get_count_digits(int num)
{
    int count_digits = 0;

    while (num) {
        num /= 10;
        count_digits++;
    }
    return count_digits;
}
