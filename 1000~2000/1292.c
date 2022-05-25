#include <stdio.h>

int main(void)
{
    int i;
    int j;
    int min;
    int max;
    int sum = 0;
    int count = 0;
    
    scanf("%d %d", &min, &max);

    for (i = 0; i <= 1000; i++) {
        for (j = 0; j < i; j++) {
            count++;
            if (count >= min) {
                sum += i;
            }

            if (count == max) {
                goto exit_loop;
            }
        }
    }
exit_loop:
    printf("%d\n", sum);
    return 0;
}